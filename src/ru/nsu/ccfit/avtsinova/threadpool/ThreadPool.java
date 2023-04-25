package ru.nsu.ccfit.avtsinova.threadpool;
import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.Window;


import java.util.*;


public class ThreadPool implements TaskListener {
   public static List taskQueueW = new LinkedList();
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


   public ThreadPool(Factory factory, Controller controller, Window myWindow) {
      SupplierAThread th11 = new SupplierAThread("SupplierA Thr", myWindow, factory, controller);
      SupplierBThread th12 = new SupplierBThread("SupplierB Thr", myWindow, factory, controller);
      SupplierEThread th13 = new SupplierEThread("SupplierE Thr", myWindow, factory, controller);
      ControllerThread th21 = new ControllerThread("Controller", factory, controller);
      WorkerThread th2 = new WorkerThread("Worker Thr1", factory, controller);
      WorkerThread th22 = new WorkerThread("Worker Thr2", factory, controller);
      DealerThread th3 = new DealerThread("Dealer Thr", myWindow, factory, controller);
      availableThreads.add(th11);
      availableThreads.add(th12);
      availableThreads.add(th13);
      availableThreads.add(th21);
      availableThreads.add(th2);
      availableThreads.add(th22);
      availableThreads.add(new WorkerThread("Worker Thr3", factory, controller));
      availableThreads.add(new WorkerThread("Worker Thr4", factory, controller));
      availableThreads.add(th3);
      for (Iterator iter = availableThreads.iterator(); iter.hasNext(); ) {
         ((Thread)iter.next()).start();
      }
   }
}