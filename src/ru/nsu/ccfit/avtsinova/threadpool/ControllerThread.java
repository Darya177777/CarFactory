package ru.nsu.ccfit.avtsinova.threadpool;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.Window;
import ru.nsu.ccfit.avtsinova.factory.people.Worker;

import java.util.List;

public class ControllerThread extends Thread{
    private Factory factory;
    private Controller controller;

    public ControllerThread(String name, Factory factory, Controller controller) {
        super(name);
        this.factory = factory;
        this.controller = controller;
    }

    public void run() {
        while (true) {
            synchronized (factory) {
                if (factory.AStore.getSize() == 0 || factory.BStore.getSize() == 0 || factory.EStore.getSize() == 0
                        || controller.CStore.isFilled()) {
                    try {
                        factory.wait(1000);
                        if (factory.AStore.getSize() == 0 || factory.BStore.getSize() == 0 || factory.EStore.getSize() == 0
                                || controller.CStore.isFilled())
                            break;
                    }
                    catch (InterruptedException ex) {
                        System.err.println("Thread was interrupted:"+getName());
                    }
                    continue;
                }
                factory.notify();
            }
            System.out.println(getName() + " got the job");
            synchronized (ThreadPool.taskQueueW) {
                if (ThreadPool.taskQueueW.size() >= 10) {
                    try {
                        ThreadPool.taskQueueW.wait();
                        if (ThreadPool.taskQueueW.size() >= 10) {
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                ThreadPool.taskQueueW.add(new Worker("Worker", 10, 1000));
                Window.Need.setText("Need to Proceeded " + ThreadPool.taskQueueW.size());

                ThreadPool.taskQueueW.notify();
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
