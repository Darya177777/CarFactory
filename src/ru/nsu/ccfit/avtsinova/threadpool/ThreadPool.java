package ru.nsu.ccfit.avtsinova.threadpool;
import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;
import ru.nsu.ccfit.avtsinova.factory.MainProcess;
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
      for (int i = 0; i < MainProcess.conf.get("Suppliers"); i++)
         availableThreads.add(new SupplierAThread("SupplierA Thr", myWindow, factory, controller));
      for (int i = 0; i < MainProcess.conf.get("Suppliers"); i++)
         availableThreads.add(new SupplierBThread("SupplierB Thr", myWindow, factory, controller));
      for (int i = 0; i < MainProcess.conf.get("Suppliers"); i++)
         availableThreads.add(new SupplierEThread("SupplierE Thr", myWindow, factory, controller));
      availableThreads.add(new ControllerThread("Controller Thr", factory, controller));
      for (int i = 0; i < MainProcess.conf.get("Workers"); i++)
         availableThreads.add(new WorkerThread("Worker Thr", factory, controller));
      for (int i = 0; i < MainProcess.conf.get("Dealers"); i++)
         availableThreads.add(new DealerThread("Dealer Thr", myWindow, factory, controller));
      for (Iterator iter = availableThreads.iterator(); iter.hasNext(); ) {
         ((Thread)iter.next()).start();
      }
   }
}