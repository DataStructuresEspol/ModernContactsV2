
package dsa.contacts.controllers;

import dsa.contacts.App;
import dsa.contacts.model.User;
import dsa.contacts.util.Logger;
import dsa.contacts.util.Util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class SettingsController {

    @FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;
    private User user;
    @FXML
    private ImageView profilePic;
    @FXML
    private void initialize() throws FileNotFoundException{
        user = Logger.loggedUser;
        userNameField.setText(user.getUserName());
        passwordField.setText(user.getPassword());
        if (user.getPic()!=null){profilePic.setImage(Util.loadImage(App.IMAGEPATH+user.getPic()));}
    }
    @FXML
    private void save(MouseEvent event) throws IOException {
        user.setUserName(userNameField.getText());
        user.setPassword(passwordField.getText());
        App.save();
        HomeController.settings.close();
    }

    @FXML
    private void setPic(MouseEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccione una imagen");

        // Configurar el filtro para mostrar solo archivos de imagen (por ejemplo, PNG, JPG)
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostrar el cuadro de diálogo de selección de archivo
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            String rutaArchivo = selectedFile.getAbsolutePath();
            String nombreCompleto = selectedFile.getName();
            int indexPunto = nombreCompleto.lastIndexOf('.');
            String nombre = nombreCompleto.substring(0, indexPunto);
            String rutaFinal = App.IMAGEPATH+nombre+"_copy.png";
            try {
            Util.copiarImagen(rutaArchivo, rutaFinal);
            profilePic.setImage(Util.loadImage(rutaFinal));
            user.setPic(nombre+"_copy.png");
            System.out.println("Imagen copiada con éxito."+rutaFinal);
        } catch (IOException e) {
            System.out.println("Error al copiar la imagen: " + e.getMessage());
            profilePic.setImage(Util.loadImage(rutaFinal));
            user.setPic(nombre+"_copy.png");
        }

        } else {
            System.out.println("Selección de foto cancelada por el usuario");
        }
    }
    
    
}
