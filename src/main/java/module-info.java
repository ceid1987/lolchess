module com.example.lolchess {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lolchess to javafx.fxml;
    exports com.example.lolchess;
}