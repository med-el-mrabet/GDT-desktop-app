module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports DAO;
    opens DAO to javafx.fxml;
    exports model;
    opens model to javafx.fxml;
}