package ru.nsu.ccfit.avtsinova.threadpool;

import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;

import java.util.*;

public class PooledThread extends Thread {
   private List taskQueue;
   private Factory factory;
   private Controller controller;

   public PooledThread(String name, List taskQueue, Factory factory, Controller controller) {
      super(name);
      this.taskQueue = taskQueue;
      this.factory = factory;
      this.controller = controller;
   }

   private void performTask(ThreadPoolTask t, Factory factory, Controller controller) {
      t.prepare();
      try {
         t.go(factory, controller);
      }
      catch (InterruptedException ex) {
         t.interrupted();
      }
      t.finish();
   }

   public void run() {
      ThreadPoolTask toExecute;
      while (true) {
         synchronized (taskQueue) {
            if (taskQueue.isEmpty()) {
               try {
                  taskQueue.wait(1000);
                  if (taskQueue.isEmpty())
                     break;
               }
               catch (InterruptedException ex) {
                  System.err.println("Thread was interrupted:"+getName());
               }
               continue;
            }
            else
               toExecute = (ThreadPoolTask)taskQueue.remove(0);
         }
         System.out.println(getName() + " got the job: " + toExecute.getName());
         performTask(toExecute, factory, controller);
      }
   }
}