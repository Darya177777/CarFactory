package ru.nsu.ccfit.avtsinova.threadpool;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.MainProcess;
import ru.nsu.ccfit.avtsinova.factory.Window;
import ru.nsu.ccfit.avtsinova.factory.people.Worker;

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
                while (factory.AStore.getSize() == 0 || factory.BStore.getSize() == 0 || factory.EStore.getSize() == 0
                        || controller.CStore.isFilled()) {
                    try {
                        factory.wait();
                    }
                    catch (InterruptedException ex) {
                        System.err.println("Thread was interrupted:"+getName());
                    }
                }
                factory.notifyAll();
            }
            System.out.println(getName() + " got the job");
            synchronized (ThreadPool.taskQueueW) {
                while (ThreadPool.taskQueueW.size() >= MainProcess.conf.get("StorageCarSize")) {
                    try {
                        ThreadPool.taskQueueW.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ThreadPool.taskQueueW.add(new Worker("Worker", 1000));
                Window.Need.setText("Need to Proceeded " + ThreadPool.taskQueueW.size());
                if (ThreadPool.taskQueueW.size() >= MainProcess.conf.get("StorageCarSize"))
                    ThreadPool.taskQueueW.notifyAll();
            }
        }
    }
}
