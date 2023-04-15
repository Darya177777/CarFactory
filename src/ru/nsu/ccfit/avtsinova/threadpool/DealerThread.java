package ru.nsu.ccfit.avtsinova.threadpool;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;

import java.util.List;

public class DealerThread extends PooledThread{
    public DealerThread(String name, List taskQueue, Factory factory, Controller controller) {
        super(name, taskQueue, factory, controller);
    }
}
