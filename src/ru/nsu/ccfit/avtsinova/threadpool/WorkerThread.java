package ru.nsu.ccfit.avtsinova.threadpool;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;

import java.util.List;

public class WorkerThread extends PooledThread{
    public WorkerThread(String name, List taskQueue, Factory factory, Controller controller) {
        super(name, taskQueue, factory, controller);
    }
}
