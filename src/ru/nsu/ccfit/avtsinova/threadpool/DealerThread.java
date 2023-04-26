package ru.nsu.ccfit.avtsinova.threadpool;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.MainProcess;
import ru.nsu.ccfit.avtsinova.factory.Window;
import ru.nsu.ccfit.avtsinova.factory.people.Dealer;


public class DealerThread extends Thread{
    private Window myWindow;
    private Factory factory;
    private Controller controller;

    public DealerThread(String name, Window myWindow, Factory factory, Controller controller) {
        super(name);
        this.myWindow = myWindow;
        this.factory = factory;
        this.controller = controller;
    }

    public void run() {
        while (true) {
            synchronized (controller) {
                while (controller.CStore.getSize() <= 0) {
                    try {
                        controller.wait();
                    } catch (InterruptedException ex) {
                        System.err.println("Thread was interrupted:" + getName());
                    }
                }

                System.out.println(getName() + " got the job");
                Dealer a = new Dealer("Dealer", MainProcess.M / myWindow.SpeedDealer);
                try {
                    a.performWork(factory, controller);
                } catch (InterruptedException ex) {
                    System.out.println("Error1");
                }
                controller.notifyAll();
            }
        }
    }
}
