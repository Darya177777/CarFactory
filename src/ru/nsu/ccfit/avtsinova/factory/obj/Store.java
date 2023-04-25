package ru.nsu.ccfit.avtsinova.factory.obj;


import java.util.ArrayList;

public interface Store {
    public void addDetail(Detail elem);
    public void setSize(int size);
    public Detail getDetail() throws Exception;
    public boolean isFilled();
}
