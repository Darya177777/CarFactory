package ru.nsu.ccfit.avtsinova.factory.obj;

import java.util.ArrayList;

public class CarStore implements Store {
    private int Size;
    private ArrayList<Car> elements = new ArrayList<>();
    public void addDetail(Detail elem){
        System.out.println("wrong method");
    }
    public void addCar(Car car){
        elements.add(car);
    }
    public void getSize(int size){
        Size = size;
    }
    public Detail getDetail(){
        System.out.println("wrong method");
        return new Body();
    }
    public Car getCar(){
        if (!elements.isEmpty()){
            return elements.remove(0);
        }
        else {
            System.out.println("Error");
            return new Car();
        }
    }
    public boolean isFilled(){
        return elements.size() == Size;
    }
}
