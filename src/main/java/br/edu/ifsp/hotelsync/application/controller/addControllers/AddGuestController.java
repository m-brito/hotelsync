package br.edu.ifsp.hotelsync.application.controller.addControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddGuestController {
    public Pane pnlOverview;
    public TextField nameField;
    public DatePicker birthDateDataPicker;
    public TextField cpfValueField;
    public Button btnOverview;
    public Button btnOrders;
    public Button btnCustomers;
    public Button btnMenus;
    public Button btnPackages;
    public Button btnSettings;
    public Button btnSignout;
    public Button registerButton;
    public Button cancelButton;
    public TextField reservationOwnerField;

    public void handleClicks(ActionEvent actionEvent) {

    }

    public void onAddGuestClick(ActionEvent actionEvent) {
        
    }
    public void onCancelAddGuestClick(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Creation");
        alert.setHeaderText("Are you sure you want to cancel reservation owner creation?");
        alert.setContentText("If you cancel, all unsaved changes will be lost.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/br/edu/ifsp/hotelsync/application/view/home.fxml"));
                    Scene homeScene = new Scene(fxmlLoader.load(), 800, 600);

                    Stage stage = (Stage) nameField.getScene().getWindow();
                    stage.setScene(homeScene);
                } catch (IOException e) {
                    //criar excecao
                }
            }
        });
    }
}
