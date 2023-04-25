package ru.nsu.ccfit.avtsinova.factory.people;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.obj.Body;
import ru.nsu.ccfit.avtsinova.threadpool.Task;

public class BodySupplier implements Task {
    private String name;
    private int counter;
    private int timeQuant;

    public BodySupplier(String name, int count, int quant) {
        this.name = name;
        counter = count;
        timeQuant = quant;
    }
    public void performWork(Factory factory, Controller controller) throws InterruptedException {
        Body e = new Body();
        factory.addBody(e);
        Thread.sleep(timeQuant);
    }
    public String getName()
    {
        return name;
    }
}
