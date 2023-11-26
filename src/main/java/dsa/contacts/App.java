package dsa.contacts;

import dsa.contacts.util.Util;
import dsa.contacts.model.User;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class App extends Application {
    public static String PATH = "src/main/resources/dsa/contacts/";
    public static ArrayList<User> users;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        loadSer();
        scene = new Scene(loadFXML("inicioSesion"), 400, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
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

}