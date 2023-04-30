package ru.nsu.ccfit.avtsinova.threadpool;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.MainProcess;
import ru.nsu.ccfit.avtsinova.factory.Window;
import ru.nsu.ccfit.avtsinova.factory.people.BodySupplier;

public class SupplierBThread extends Thread {
    private Window myWindow;
    private Factory factory;
    private Controller controller;

    public SupplierBThread(String name, Window myWindow, Factory factory, Controller controller) {
        super(name);
        this.myWindow = myWindow;
        this.factory = factory;
        this.controller = controller;
    }

    public void run() {
        while (true) {
            synchronized (factory) {
                while (factory.BStore.isFilled()) {
                    try {
                        factory.wait();
                    } catch (InterruptedException ex) {
                        System.err.println("Thread was interrupted:" + getName());
                    }
                }
                BodySupplier a = new BodySupplier("BodyS", MainProcess.N / myWindow.SpeedSupB);
                try {
                    a.performWork(factory, controller, myWindow);
                } catch (InterruptedException ex) {
                    System.out.println("Error1");
                }
                factory.notifyAll();
            }
        }
    }
}
