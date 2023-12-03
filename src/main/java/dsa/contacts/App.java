package dsa.contacts;

import dsa.contacts.util.Util;
import dsa.contacts.model.User;
import dsa.contacts.model.exceptions.ValidationException;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class App extends Application {
    public static String PATH = "src/main/resources/dsa/contacts/";
    public static String IMAGEPATH = PATH+"assets/images/";
    public static String ICONPATH = PATH+"assets/icon/";
    public static ArrayList<User> users;
    private static Scene scene;
    public static Stack<String> recorrido = new Stack();

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        loadSer();
        String fxml = "login";
        recorrido.push(fxml);
        scene = new Scene(loadFXML(fxml), 720, 1280);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        System.out.println(recorrido);
        recorrido.push(fxml);
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
    
    public static void showAlert(AlertType type, ValidationException e){
        Alert alert = new Alert(type);
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
    private void loadSer() throws IOException, ClassNotFoundException{
        String ruta = PATH+"serializables/users.ser";
        File serFile = new File(ruta);
        if (serFile.exists()){
            users = (ArrayList<User>) Util.loadSer(ruta);
        }
        else{
            users = new ArrayList<User>();
            Util.createSer(ruta, users);
        }
    }
    
    public static void save() throws IOException{
        String ruta = PATH+"serializables/users.ser";
        Util.createSer(ruta, users);
    }
    
    public static void retroceder() throws IOException{
        
        recorrido.pop();
        App.setRoot(recorrido.pop());
    }

}