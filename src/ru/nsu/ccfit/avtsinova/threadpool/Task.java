package ru.nsu.ccfit.avtsinova.threadpool;


import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.Window;

public interface Task {
   String getName();
   public void performWork(Factory factory, Controller controller, Window myWindow) throws InterruptedException;
}