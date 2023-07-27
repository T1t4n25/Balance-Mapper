package com.zeyadh.balancemapper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;



public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene maincene = new Scene(fxmlLoader.load());
        stage.setTitle("حاسب الميزانية");

        Image icon = new Image("moneyicon.png");
        stage.getIcons().add(icon);

        stage.setScene(maincene);
        stage.show();
    }

    public static void main(String[] args) {
        DBConnection.getConnect();
        launch();
    }
}