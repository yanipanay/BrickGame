module com.example.brickgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.brickgame to javafx.fxml;
    exports com.example.brickgame;
}