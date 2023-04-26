package ru.nsu.ccfit.avtsinova.factory.people;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.Window;
import ru.nsu.ccfit.avtsinova.threadpool.Task;

public class Dealer implements Task {
    private String name;
    private int timeQuant;

    public Dealer(String name, int quant) {
        this.name = name;
        timeQuant = quant;
    }
    public void performWork(Factory factory, Controller controller) throws InterruptedException {
        try {
            controller.getCar();
            Window.Cars.setText("Cars On Store " + controller.CStore.getSize());
            Thread.sleep(timeQuant);
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
