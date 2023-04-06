package ru.nsu.ccfit.avtsinova.threadpool;


public interface Task {
   String getName();
   public void performWork() throws InterruptedException;
}