module com.example.javafxchat {
    requires javafx.controls;
    requires javafx.fxml;
    requires slf4j.api;


    opens com.example.javafxchat to javafx.fxml;
    exports com.example.javafxchat.server;
    opens com.example.javafxchat.server to javafx.fxml;
}