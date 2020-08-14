package com.JGG.WeeklyScheduler;

import com.JGG.WeeklyScheduler.entity.HibernateConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class Main extends Application {
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/Calendar.fxml")));
        stage.setTitle("Calendar");
        stage.setScene(new Scene(root));
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }
}
