package br.edu.ifsp.hotelsync.application.controller.createControllers;

import br.edu.ifsp.hotelsync.application.util.exitHandler.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.navigationHandler.NavigationHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CreateGuestController {

    @FXML
    private DatePicker birthDateDataPicker;

    @FXML
    private Button btnGuest;

    @FXML
    private Button btnProduct;

    @FXML
    private Button btnReport;

    @FXML
    private Button btnReservation;

    @FXML
    private Button btnRoom;

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

    private final ExitHandler exitHandler = new ExitHandler();
    private final NavigationHandler navHandler = new NavigationHandler();

    @FXML
    void handleExit(ActionEvent event) {
        exitHandler.handleExit(event);
    }

    @FXML
    void handleGuestPage() throws IOException {
        navHandler.navigateToGuestPage();
    }

    @FXML
    void handleProductPage(ActionEvent event) {
    }

    @FXML
    void handleReportPage(ActionEvent event) {

    }

    @FXML
    void handleReservationPage(ActionEvent event) {

    }

    @FXML
    void handleRoomPage(ActionEvent event) {

    }

    @FXML
    void onAddGuestClick(ActionEvent event) {

    }

    @FXML
    void onCancelAddGuestClick(ActionEvent event) {

    }

    @FXML
    public void onCreateGuestClick(ActionEvent actionEvent) throws IOException {
        navHandler.handleCreateGuest();
    }
}
