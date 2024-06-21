package br.edu.ifsp.hotelsync.application.util;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ExitHandler{
    private Alert createExitConfirmationAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Do you really want to leave?",
                ButtonType.YES,
                ButtonType.NO);
        alert.setTitle("Departure Confirmation");
        return alert;
    }

    public void handleExit(ActionEvent event) {
        Alert alert = createExitConfirmationAlert();
        alert.showAndWait()
                .filter(response -> response == ButtonType.YES)
                .ifPresent(response -> Platform.exit());
    }
}