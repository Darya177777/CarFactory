package ru.nsu.ccfit.avtsinova.factory.people;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.MainProcess;
import ru.nsu.ccfit.avtsinova.factory.Window;
import ru.nsu.ccfit.avtsinova.factory.obj.Car;
import ru.nsu.ccfit.avtsinova.threadpool.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dealer implements Task {
    private String name;
    private int timeQuant;

    public Dealer(String name, int quant) {
        this.name = name;
        timeQuant = quant;
    }
    public void performWork(Factory factory, Controller controller, Window myWindow) throws InterruptedException {
        try {
            Car t = controller.getCar();
            Window.Cars.setText("Cars On Store " + controller.CStore.getSize());
            if (myWindow.LogSale) {
                String dateTime = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a")
                        .format(LocalDateTime.now());
                MainProcess.writeData(dateTime + ":Dealer " + name + ":Auto " + t.getID() + "(Body: " +
                        t.getBodyID() + ", Motor: " + t.getEngineID() + ", Accessory: " + t.getAccessID() + ")");
            }
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
