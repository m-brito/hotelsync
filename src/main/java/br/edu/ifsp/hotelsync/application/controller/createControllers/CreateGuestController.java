package br.edu.ifsp.hotelsync.application.controller.createControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateGuestController {
    @FXML
    private DatePicker birthDateDataPicker;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField cpfValueField;

    @FXML
    private TextField nameField;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Button registerButton;

    @FXML
    private TextField reservationOwnerField;

    @FXML
    public void handleClicks(ActionEvent actionEvent) { }

    @FXML
    public void onAddGuestClick(ActionEvent actionEvent) { }

    @FXML
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
