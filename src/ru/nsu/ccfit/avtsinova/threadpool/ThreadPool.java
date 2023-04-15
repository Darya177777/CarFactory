package ru.nsu.ccfit.avtsinova.threadpool;
import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;

import java.util.*;


public class ThreadPool implements TaskListener {
   private static final int THREAD_COUNT = 3;
   private List taskQueue = new LinkedList();
   private Set availableThreads = new HashSet();

   public void taskStarted(Task t)
   {
      System.out.println("Started:" + t.getName());
   }
   public void taskFinished(Task t)
   {
      System.out.println("Finished:" + t.getName());
   }
   public void taskInterrupted(Task t)
   {
      System.out.println("Interrupted:" + t.getName());
   }

   public void addTask(Task t)
   {
      addTask(t, this);
   }

   public void addTask(Task t, TaskListener l) {
      synchronized (taskQueue) {
         taskQueue.add( new ThreadPoolTask(t, l) );
         taskQueue.notify();
      }
   }

   public ThreadPool(Factory factory, Controller controller) {
      availableThreads.add(new SupplierThread("Supplier ",taskQueue, factory, controller));
      availableThreads.add(new WorkerThread("Worker ",taskQueue, factory, controller));
      availableThreads.add(new DealerThread("Dealer ",taskQueue, factory, controller));
      for (Iterator iter = availableThreads.iterator(); iter.hasNext(); ) {
         ((Thread)iter.next()).start();
      }
   }
}