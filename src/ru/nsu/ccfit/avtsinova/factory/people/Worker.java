package ru.nsu.ccfit.avtsinova.factory.people;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.MainProcess;
import ru.nsu.ccfit.avtsinova.factory.Window;
import ru.nsu.ccfit.avtsinova.factory.obj.Accessory;
import ru.nsu.ccfit.avtsinova.factory.obj.Body;
import ru.nsu.ccfit.avtsinova.factory.obj.Car;
import ru.nsu.ccfit.avtsinova.factory.obj.Engine;
import ru.nsu.ccfit.avtsinova.threadpool.Task;

public class Worker implements Task {
    private String name;
    private int timeQuant;

    public Worker(String name, int quant) {
        this.name = name;
        timeQuant = quant;
    }
    public void performWork(Factory factory, Controller controller) throws InterruptedException {
        try {
            synchronized (factory) {
                Accessory a = factory.getAccessory();
                Body b = factory.getBody();
                Engine e = factory.getEngine();
                Car myCar = new Car();
                myCar.setID(MainProcess.ID);
                MainProcess.ID++;
                myCar.addDetail(a);
                myCar.addDetail(b);
                myCar.addDetail(e);
                synchronized (controller) {
                    controller.addCar(myCar);
                    Window.Cars.setText("Cars On Store " + controller.CStore.getSize());
                    Thread.sleep(1000);
                    MainProcess.PROD++;
                    Window.Produced.setText("Produced Cars " + MainProcess.PROD);
                    if (controller.CStore.isFilled())
                        controller.notifyAll();
                }
                factory.notify();
                Thread.sleep(timeQuant);
            }
        }
        catch (Exception ex) {
            Thread.sleep(timeQuant);
        }
    }
    public String getName()
    {
        return name;
    }
}
