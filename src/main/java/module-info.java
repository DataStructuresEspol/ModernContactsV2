module dsa.contacts {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.googlecode.ezvcard;

    opens dsa.contacts to javafx.fxml;
    opens dsa.contacts.controllers to javafx.fxml;
    exports dsa.contacts;
    exports dsa.contacts.controllers;
}
