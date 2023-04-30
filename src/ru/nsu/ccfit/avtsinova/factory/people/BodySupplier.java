package ru.nsu.ccfit.avtsinova.factory.people;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.MainProcess;
import ru.nsu.ccfit.avtsinova.factory.Window;
import ru.nsu.ccfit.avtsinova.factory.obj.Body;
import ru.nsu.ccfit.avtsinova.threadpool.Task;

public class BodySupplier implements Task {
    private String name;
    private int timeQuant;

    public BodySupplier(String name, int quant) {
        this.name = name;
        timeQuant = quant;
    }
    public void performWork(Factory factory, Controller controller, Window w) throws InterruptedException {
        Body e = new Body();
        e.setID(MainProcess.ID);
        MainProcess.ID++;
        factory.addBody(e);
        Thread.sleep(timeQuant);
    }
    public String getName()
    {
        return name;
    }
}
