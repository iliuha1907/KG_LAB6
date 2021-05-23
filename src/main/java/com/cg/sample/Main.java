package com.cg.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("sample.fxml"));

        primaryStage.setTitle("Lab 6");
        primaryStage.setScene(new Scene(loader.load(), 800, 475));
        primaryStage.show();

        LetterViewController letterViewController = loader.getController();
        letterViewController.init();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
