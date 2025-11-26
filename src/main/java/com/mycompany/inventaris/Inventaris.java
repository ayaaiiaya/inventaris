package com.mycompany.inventaris;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.mycompany.inventaris.view.MainPage;

public class Inventaris extends Application {

    @Override

    public void start(Stage stage) {
        MainPage root = new MainPage(stage);
        Scene scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}
