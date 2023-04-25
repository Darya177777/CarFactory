package ru.nsu.ccfit.avtsinova.factory.obj;

import ru.nsu.ccfit.avtsinova.factory.obj.Store;

import java.util.ArrayList;

public class EngineStore implements Store{
    private int Size;
    private ArrayList<Detail> elements = new ArrayList<>();
    public void addDetail(Detail elem){
        if (elem.getClass() != Engine.class)
            System.out.println("Error");
        else
            elements.add(elem);
    }
    public void setSize(int size){
        Size = size;
    }
    public Detail getDetail() throws Exception {
        if (!elements.isEmpty()){
            return elements.remove(0);
        }
        else {
            throw new Exception("error");
        }
    }
    public boolean isFilled(){
        return elements.size() == Size;
    }
    public int getSize(){
        return elements.size();
    }
}
