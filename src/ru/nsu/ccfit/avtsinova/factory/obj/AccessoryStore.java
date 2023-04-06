package ru.nsu.ccfit.avtsinova.factory.obj;

import java.util.ArrayList;

public class AccessoryStore implements Store {
    private int Size;
    private ArrayList<Detail> elements = new ArrayList<>();
    public void addDetail(Detail elem){
        if (elem.getClass() != Accessory.class)
            System.out.println("Error");
        else
            elements.add(elem);
    }
    public void getSize(int size){
        Size = size;
    }
    public Detail getDetail(){
        if (!elements.isEmpty()){
            return elements.remove(0);
        }
        else {
            System.out.println("Error");
            return new Body();
        }
    }
    public boolean isFilled(){
        return elements.size() == Size;
    }
}
