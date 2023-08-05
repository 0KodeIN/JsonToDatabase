module com.example.jsontodatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.jsontodatabase to javafx.fxml;
    exports com.example.jsontodatabase;
}