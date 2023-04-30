package ru.nsu.ccfit.avtsinova.threadpool;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.MainProcess;
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
                while (factory.EStore.isFilled()) {
                    try {
                        factory.wait();
                    } catch (InterruptedException ex) {
                        System.err.println("Thread was interrupted:" + getName());
                    }
                }
                EngineSupplier a = new EngineSupplier("BodyS", MainProcess.N / myWindow.SpeedSupE);
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
