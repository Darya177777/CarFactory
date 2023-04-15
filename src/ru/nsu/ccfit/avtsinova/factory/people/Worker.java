package ru.nsu.ccfit.avtsinova.factory.people;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.obj.Accessory;
import ru.nsu.ccfit.avtsinova.factory.obj.Body;
import ru.nsu.ccfit.avtsinova.factory.obj.Car;
import ru.nsu.ccfit.avtsinova.factory.obj.Engine;
import ru.nsu.ccfit.avtsinova.threadpool.Task;

public class Worker implements Task {
    private String name;
    private int counter;
    private int timeQuant;

    public Worker(String name, int count, int quant) {
        this.name = name;
        counter = count;
        timeQuant = quant;
    }
    public void performWork(Factory factory, Controller controller) throws InterruptedException {
        Accessory a = factory.getAccessory();
        Body b = factory.getBody();
        Engine e = factory.getEngine();
        Car myCar = new Car();
        myCar.addDetail(a);
        myCar.addDetail(b);
        myCar.addDetail(e);
        controller.addCar(myCar);
    }
    public String getName()
    {
        return name;
    }
}
