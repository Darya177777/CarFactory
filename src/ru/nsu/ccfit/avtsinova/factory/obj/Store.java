package ru.nsu.ccfit.avtsinova.factory.obj;


import java.util.ArrayList;

public interface Store {
    public void addDetail(Detail elem);
    public void getSize(int size);
    public Detail getDetail();
    public boolean isFilled();
}
