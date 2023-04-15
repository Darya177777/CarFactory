package ru.nsu.ccfit.avtsinova.threadpool;


import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;

public interface Task {
   String getName();
   public void performWork(Factory factory, Controller controller) throws InterruptedException;
}