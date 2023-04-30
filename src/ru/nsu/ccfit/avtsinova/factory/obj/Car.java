package ru.nsu.ccfit.avtsinova.factory.obj;


public class Car {
    private boolean isReady = false;
    private int ID = 0;
    Engine engine = new Engine();
    Body body = new Body();
    Accessory access = new Accessory();

    public void setID(int ID){
        this.ID = ID;
    }
    public int getID(){
        return ID;
    }
    public int getAccessID(){return access.getID();}
    public int getBodyID(){return body.getID();}
    public int getEngineID(){return engine.getID();}
    public void addDetail(Detail elem){
        if (elem.getClass() == Body.class && body.getID() == 0)
            body = (Body) elem;
        else if (elem.getClass() == Engine.class && engine.getID() == 0)
            engine = (Engine) elem;
        else if (elem.getClass() == Accessory.class && access.getID() == 0)
            access = (Accessory) elem;
        else
            System.out.println("Error with adding detail\n");
    }
}
