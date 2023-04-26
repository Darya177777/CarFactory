package ru.nsu.ccfit.avtsinova.factory.people;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.MainProcess;
import ru.nsu.ccfit.avtsinova.factory.obj.Engine;
import ru.nsu.ccfit.avtsinova.threadpool.Task;

public class EngineSupplier implements Task {
    private String name;
    private int timeQuant;

    public EngineSupplier(String name, int quant) {
        this.name = name;
        timeQuant = quant;
    }
    public void performWork(Factory factory, Controller controller) throws InterruptedException {
        Engine e = new Engine();
        e.setID(MainProcess.ID);
        MainProcess.ID++;
        factory.addEngine(e);
        Thread.sleep(timeQuant);
    }
    public String getName()
    {
        return name;
    }
}
