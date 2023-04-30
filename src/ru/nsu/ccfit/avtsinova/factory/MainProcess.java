package ru.nsu.ccfit.avtsinova.factory;

import ru.nsu.ccfit.avtsinova.threadpool.ThreadPool;

import java.io.*;
import java.util.HashMap;

public class MainProcess {
    public static Integer PROD = 0;
    public static Integer ID = 0;
    public static Integer M = 4000;
    public static Integer N = 1000;
    public static HashMap<String, Integer> conf = new HashMap<>();
    private static FileWriter writer;

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
    public static void writeData(String data){
        try {
            writer = new FileWriter("src/ru/nsu/ccfit/avtsinova/factory/data/log.txt", true);
            writer.write(data);
            writer.append('\n');
            writer.flush();
            writer.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) throws IOException {
        readConf();
        Window myWindow = new Window();
        myWindow.launch();
        Factory myFactory = new Factory();
        Controller myController = new Controller();
        myFactory.init(conf.get("StorageAccessorySize"), conf.get("StorageBodySize"), conf.get("StorageEngineSize"));
        myController.init(conf.get("StorageCarSize"));
        ThreadPool threadPool = new ThreadPool(myFactory, myController, myWindow);
    }
}
