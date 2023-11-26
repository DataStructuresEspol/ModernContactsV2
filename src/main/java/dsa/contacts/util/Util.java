
package dsa.contacts.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.Node;


public class Util {
    public static void createSer(String ruta, Object o) throws IOException{
        System.out.println(ruta);
        FileOutputStream fileOutputStream = new FileOutputStream(ruta);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(o);
    }
    
    public static Object loadSer(String ruta) throws IOException, ClassNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(ruta);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return objectInputStream.readObject();
    }
    
    public static void alertStyle(Node node){
        node.setStyle("-fx-control-inner-background: red;-fx-text-fill: white");
    }
    
    public static void removeAlerstStyle(Node node){
        node.setStyle("-fx-control-inner-background: white;-fx-text-fill: black");
    }
}