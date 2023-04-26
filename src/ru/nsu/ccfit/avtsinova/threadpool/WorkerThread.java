package ru.nsu.ccfit.avtsinova.threadpool;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.Window;


public class WorkerThread extends Thread{
    private Factory factory;
    private Controller controller;

    public WorkerThread(String name, Factory factory, Controller controller) {
        super(name);
        this.factory = factory;
        this.controller = controller;
    }

    public void run() {
        Task toExecute;
        while (true) {
            synchronized (ThreadPool.taskQueueW) {
                while (ThreadPool.taskQueueW.isEmpty()) {
                    try {
                        ThreadPool.taskQueueW.wait();
                    } catch (InterruptedException ex) {
                        System.err.println("Thread was interrupted:" + getName());
                    }
                }
                toExecute = (Task) ThreadPool.taskQueueW.remove(0);

                Window.Need.setText("Need to Proceeded " + ThreadPool.taskQueueW.size());
                System.out.println(getName() + " got the job: " + toExecute.getName());
                try {
                    toExecute.performWork(factory, controller);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ThreadPool.taskQueueW.isEmpty())
                    ThreadPool.taskQueueW.notifyAll();
            }
        }
    }
}
