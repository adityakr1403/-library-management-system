package com.example.librarymanagementsystem;

import com.example.utils.HibernateUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("loginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("NEXS Library Management");
        stage.setResizable(false);
        Image icon = new Image("nexs-logo-removebg-preview.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        launch();

    }
}