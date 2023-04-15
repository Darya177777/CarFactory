package ru.nsu.ccfit.avtsinova.factory;

import ru.nsu.ccfit.avtsinova.factory.people.Worker;
import ru.nsu.ccfit.avtsinova.threadpool.Countdown;
import ru.nsu.ccfit.avtsinova.threadpool.ThreadPool;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MainProcess {
    private static HashMap<String, Integer> conf = new HashMap<>();
    private static void readConf(){
        try {
            InputStream inp = MainProcess.class.getResourceAsStream("\\data\\configs.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inp));
            String l;
            while((l = reader.readLine()) != null) {
                String[] words = l.split("=");
                conf.put(words[0], Integer.parseInt(words[1]));
            }
            inp.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        readConf();
        Window myWindow = new Window();
        myWindow.launch();
        Factory myFactory = new Factory();
        Controller myController = new Controller();
        myFactory.init(conf.get("StorageAccessorySize"), conf.get("StorageBodySize"), conf.get("StorageEngineSize"));
        myController.init(conf.get("StorageCarSize"));
        ThreadPool threadPool = new ThreadPool(myFactory, myController);
        for (int i = 0; i < 10 ; i++) {
            threadPool.addTask(new Worker( "C"+i, 10, 1000));
        }
        //while(true)
            //System.out.println(myWindow.checkval);
    }
}
