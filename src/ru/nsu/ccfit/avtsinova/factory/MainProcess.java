package ru.nsu.ccfit.avtsinova.factory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainProcess {
    private static ArrayList<Integer> Data = new ArrayList();
    private static void readConf(){
        try {
            InputStream inp = MainProcess.class.getResourceAsStream("\\data\\configs.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inp));
            String l;
            while((l = reader.readLine()) != null) {
                String[] words = l.split("=");
                Data.add(Integer.parseInt(words[1]));
            }
            inp.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        readConf();
        Window myWindow = new Window();
        myWindow.SliderTest();
        //while(true)
            //System.out.println(myWindow.checkval);
    }
}
