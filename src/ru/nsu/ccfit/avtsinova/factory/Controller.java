package ru.nsu.ccfit.avtsinova.factory;

import ru.nsu.ccfit.avtsinova.factory.obj.Car;
import ru.nsu.ccfit.avtsinova.factory.obj.CarStore;

public class Controller {
    CarStore CStore;
    public void init(int SizeC){
        CStore = new CarStore();
        CStore.setSize(SizeC);
    }
    public void addCar(Car el){
        CStore.addCar(el);
    }
    public Car getCar(){
        return CStore.getCar();
    }
}
