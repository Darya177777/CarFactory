package ru.nsu.ccfit.avtsinova.threadpool;


import ru.nsu.ccfit.avtsinova.factory.Controller;
import ru.nsu.ccfit.avtsinova.factory.Factory;

public class Countdown implements Task {
   private String name;
   private int counter;
   private int timeQuant;

   public Countdown(String name, int count, int quant) {
      this.name = name;
      counter = count;
      timeQuant = quant;
   }
   public void performWork(Factory factory, Controller controller) throws InterruptedException
   {
      System.out.println(name+" " +counter);
      for (int i = counter-1; i >= 0; i--)
      {
         Thread.sleep(timeQuant);
         System.out.println(name + " " +i);
      }
   }
   public String getName()
   {
      return name;
   }
}