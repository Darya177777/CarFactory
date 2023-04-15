package ru.nsu.ccfit.avtsinova.factory;

import ru.nsu.ccfit.avtsinova.factory.obj.*;

public class Factory {
    AccessoryStore AStore;
    BodyStore BStore;
    EngineStore EStore;
    public void init(int SizeA, int SizeB, int SizeE){
        AStore = new AccessoryStore();
        BStore = new BodyStore();
        EStore = new EngineStore();
        AStore.setSize(SizeA);
        BStore.setSize(SizeB);
        EStore.setSize(SizeE);
    }
    public void addAccessory(Accessory el){
        AStore.addDetail(el);
    }
    public void addBody(Body el){
        BStore.addDetail(el);
    }
    public void addEngine(Engine el){
        EStore.addDetail(el);
    }
    public Accessory getAccessory(){
        return (Accessory) AStore.getDetail();
    }
    public Body getBody(){
        return (Body) BStore.getDetail();
    }
    public Engine getEngine(){
        return (Engine) EStore.getDetail();
    }
}
