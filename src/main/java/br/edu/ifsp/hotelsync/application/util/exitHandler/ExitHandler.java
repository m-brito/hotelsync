package br.edu.ifsp.hotelsync.application.util.exitHandler;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ExitHandler implements IExitHandler {
    private Alert createExitConfirmationAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Do you really want to leave?",
                ButtonType.YES,
                ButtonType.NO);
        alert.setTitle("Departure Confirmation");
        alert.setHeaderText("Bye! \uD83D\uDC4B");
        return alert;
    }

    @Override
    public void handleExit(ActionEvent event) {
        Alert alert = createExitConfirmationAlert();
        alert.showAndWait()
                .filter(response -> response == ButtonType.YES)
                .ifPresent(response -> Platform.exit());
    }
}