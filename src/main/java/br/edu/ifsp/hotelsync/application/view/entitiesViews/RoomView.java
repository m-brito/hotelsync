package br.edu.ifsp.hotelsync.application.view.entitiesViews;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class RoomView extends javafx.application.Application {
    private static Scene scene;
    private static Object controller;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("entitiesViews/room"));

        stage.getIcons().add(new Image("file:src/main/resources/br/edu/ifsp/hotelsync/application/view/icon.png"));

        stage.setMinWidth(1068);
        stage.setMinHeight(666);

        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(br.edu.ifsp.hotelsync.application.view.Home.class.getResource(fxml + ".fxml"));
        Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
        return parent;
    }

    public static Object getController() {
        return controller;
    }

    public static void main(String[] args) {
        launch();
    }
}