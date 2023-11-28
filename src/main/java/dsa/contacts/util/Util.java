
package dsa.contacts.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.Node;
import javafx.scene.image.Image;


public class Util {
    
    public static Image loadImage(String ruta) throws FileNotFoundException{
        FileInputStream fileInputStream = new FileInputStream(ruta);
        return new Image(fileInputStream);
    }
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
    
    public static void copiarImagen(String rutaOrigen, String rutaDestino) throws IOException {
        Path origenPath = Paths.get(rutaOrigen);
        Path destinoPath = Paths.get(rutaDestino);

        // Copiar el archivo desde la ruta de origen a la ruta de destino
        Files.copy(origenPath, destinoPath);
    }
}