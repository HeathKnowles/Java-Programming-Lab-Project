package com.osint.javaprojectfinal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) {
        App.stage=stage;
        var root = new InformationFinder(); // Assuming InformationFinder is your custom UI component
        var scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    public static void closeWindow() {
        if (stage != null) {
            stage.close();
        }
    }
    
}