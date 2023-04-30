package ru.nsu.ccfit.avtsinova.factory.people;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.MainProcess;
import ru.nsu.ccfit.avtsinova.factory.Window;
import ru.nsu.ccfit.avtsinova.factory.obj.Accessory;
import ru.nsu.ccfit.avtsinova.threadpool.Task;

public class AccessorySupplier implements Task {
    private String name;
    private int timeQuant;

    public AccessorySupplier(String name, int quant) {
        this.name = name;
        timeQuant = quant;
    }
    public void performWork(Factory factory, Controller controller, Window w) throws InterruptedException {
        Accessory e = new Accessory();
        e.setID(MainProcess.ID);
        MainProcess.ID++;
        factory.addAccessory(e);
        Thread.sleep(timeQuant);
    }
    public String getName()
    {
        return name;
    }
}
