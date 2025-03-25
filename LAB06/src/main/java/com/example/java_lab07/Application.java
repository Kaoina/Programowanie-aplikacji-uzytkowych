package com.example.java_lab07;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Lab07Builder.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Car Show Room Application");
        stage.setScene(scene);
        stage.show();
    }
}
