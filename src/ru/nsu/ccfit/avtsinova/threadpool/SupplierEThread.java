package ru.nsu.ccfit.avtsinova.threadpool;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.Window;
import ru.nsu.ccfit.avtsinova.factory.people.EngineSupplier;

public class SupplierEThread extends Thread {
    private Window myWindow;
    private Factory factory;
    private Controller controller;

    public SupplierEThread(String name, Window myWindow, Factory factory, Controller controller) {
        super(name);
        this.myWindow = myWindow;
        this.factory = factory;
        this.controller = controller;
    }

    public void run() {
        while (true) {
            synchronized (factory) {
                if (factory.EStore.isFilled()) {
                    try {
                        factory.wait();
                        if (factory.EStore.isFilled())
                            break;
                    } catch (InterruptedException ex) {
                        System.err.println("Thread was interrupted:" + getName());
                    }
                    continue;
                }

                System.out.println(getName() + " got the job");
                EngineSupplier a = new EngineSupplier("BodyS", 10, 100 * myWindow.SpeedSupE);
                try {
                    a.performWork(factory, controller);
                } catch (InterruptedException ex) {
                    System.out.println("Error1");
                }
                factory.notifyAll();
            }
        }
    }
}
