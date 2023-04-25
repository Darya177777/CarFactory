package ru.nsu.ccfit.avtsinova.threadpool;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.Window;
import ru.nsu.ccfit.avtsinova.factory.people.AccessorySupplier;


public class SupplierAThread extends Thread{
    private Window myWindow;
    private Factory factory;
    private Controller controller;

    public SupplierAThread(String name, Window myWindow, Factory factory, Controller controller) {
        super(name);
        this.myWindow = myWindow;
        this.factory = factory;
        this.controller = controller;
    }

    public void run() {
        while (true) {
            synchronized (factory) {
                if (factory.AStore.isFilled()) {
                    try {
                        factory.wait();
                        if (factory.AStore.isFilled())
                            break;
                    } catch (InterruptedException ex) {
                        System.err.println("Thread was interrupted:" + getName());
                    }
                    continue;
                }

                System.out.println(getName() + " got the job");
                AccessorySupplier a = new AccessorySupplier("AccessoryS", 10, 100 * myWindow.SpeedSupA);
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
