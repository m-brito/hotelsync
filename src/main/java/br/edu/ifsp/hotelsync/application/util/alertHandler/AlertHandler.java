package br.edu.ifsp.hotelsync.application.util.alertHandler;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertHandler implements IAlertHandler {
    @Override
    public Alert createExitConfirmationAlert() {
        Alert alert = new Alert(
                Alert.
                AlertType.CONFIRMATION,
                "Do you really want to leave?",
                ButtonType.YES,
                ButtonType.NO);
        alert.setTitle("Departure Confirmation");
        return alert;
    }
}