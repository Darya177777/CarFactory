package ru.nsu.ccfit.avtsinova.factory.people;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.obj.Accessory;
import ru.nsu.ccfit.avtsinova.threadpool.Task;

public class AccessorySupplier implements Task {
    private String name;
    private int counter;
    private int timeQuant;

    public AccessorySupplier(String name, int count, int quant) {
        this.name = name;
        counter = count;
        timeQuant = quant;
    }
    public void performWork(Factory factory, Controller controller) throws InterruptedException {
        Accessory e = new Accessory();
        factory.addAccessory(e);
        Thread.sleep(timeQuant);
    }
    public String getName()
    {
        return name;
    }
}
