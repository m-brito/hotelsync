package br.edu.ifsp.hotelsync.application.controller.updateControllers;

import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class UpdateReservationController {
    @FXML
    private Button addGuestBtnUpdate;

    @FXML
    private DatePicker birthdatePicker;

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
    private Button cancelAddGuestBtnUpdate;

    @FXML
    private Button cancelReservationBtnUpdate;

    @FXML
    private DatePicker checkInDateUpdate;

    @FXML
    private DatePicker checkOutDateUpdate;

    @FXML
    private Button createReservationButtonUpdate;

    @FXML
    private Button doneAddGuestBtnUpdate;

    @FXML
    private DatePicker endDateUpdate;

    @FXML
    private ComboBox<?> methodPaymentComboUpdate;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<?> ownerReservationComboUpdate;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField pronounsField;

    @FXML
    private ComboBox<?> roomReservationComboUpdate;

    @FXML
    private TextField ssnField;

    @FXML
    private DatePicker startDateUpdate;

    private final NavigationHandler navHandler =
            new NavigationHandler();

    private final ExitHandler exitHandler =
            new ExitHandler();

    @FXML
    void handleExit(ActionEvent event) {
        exitHandler.handleExit(event);
    }

    @FXML
    void handleAddGuest(ActionEvent event) {
    }

    @FXML
    void handleCreateReservation(ActionEvent event) throws IOException {
        navHandler.navigateToReservationPage();
    }

    @FXML
    void handleGuestPage(ActionEvent event) throws IOException{
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

}


