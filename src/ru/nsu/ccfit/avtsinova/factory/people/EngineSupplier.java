package ru.nsu.ccfit.avtsinova.factory.people;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.obj.Engine;
import ru.nsu.ccfit.avtsinova.threadpool.Task;

public class EngineSupplier implements Task {
    private String name;
    private int counter;
    private int timeQuant;

    public EngineSupplier(String name, int count, int quant) {
        this.name = name;
        counter = count;
        timeQuant = quant;
    }
    public void performWork(Factory factory, Controller controller) throws InterruptedException {
        Engine e = new Engine();
        factory.addEngine(e);
        Thread.sleep(timeQuant);
    }
    public String getName()
    {
        return name;
    }
}
