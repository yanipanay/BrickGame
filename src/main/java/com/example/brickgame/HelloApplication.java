package com.example.brickgame;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    static Stage globalstage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("start.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        globalstage=stage;
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void startButtonClick() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("levels.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        globalstage.setTitle("Game screen");
        globalstage.setScene(scene);
    }

    @FXML
    protected void EasyButtonClick() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gamescreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        globalstage.setTitle("Game screen");
        globalstage.setScene(scene);
    }
    @FXML
    protected void MedButtonClick() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gamescreenmed.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        globalstage.setTitle("Game screen");
        globalstage.setScene(scene);
    }

    @FXML
    protected void HardButtonClick() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gamescreenhard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        globalstage.setTitle("Game screen");
        globalstage.setScene(scene);
    }






    public static void main(String[] args) {
        launch();
    }
}