package br.edu.ifsp.hotelsync.application.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);        stage.setTitle("HotelSync");
        stage.getIcons().add(new Image("file:src/main/resources/br/edu/ifsp/hotelsync/application/view/icon.png"));
        stage.setMinWidth(1068);
        stage.setMinHeight(666);



        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}