package ru.nsu.ccfit.avtsinova.threadpool;


import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;

class ThreadPoolTask {
   private TaskListener listener;
   private Task task;

   public ThreadPoolTask(Task t, TaskListener l) {
      listener = l;
      task = t;
   }
   void prepare()
   {
      listener.taskStarted(task);
   }
   void finish()
   {
      listener.taskFinished(task);
   }
   void interrupted()
   {
      listener.taskInterrupted(task);
   }
   void go(Factory factory, Controller controller) throws InterruptedException
   {
      task.performWork(factory, controller);
   }
   String getName()
   {
      return task.getName();
   }
}