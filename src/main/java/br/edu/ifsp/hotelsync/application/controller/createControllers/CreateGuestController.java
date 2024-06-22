package br.edu.ifsp.hotelsync.application.controller.createControllers;

import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.domain.entities.guest.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CreateGuestController {

    @FXML
    private DatePicker birthDateGuestPicker;

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
    private Button cancelGuestBtn;

    @FXML
    private TextField cepGuestField;

    @FXML
    private TextField cityGuestField;

    @FXML
    private TextField complementField;

    @FXML
    private TextField cpfField;

    @FXML
    private TextField districtField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField pronounsGuestField;

    @FXML
    private Button saveGuestBtn;

    @FXML
    private ComboBox<State> stateGuestCombo;

    private final ExitHandler exitHandler =
            new ExitHandler();

    private final NavigationHandler navHandler =
            new NavigationHandler();

    @FXML
    public void initialize() {
        stateGuestCombo.
                getItems().
                setAll(State.values());
    }

    @FXML
    void handleExit(ActionEvent event) {
        exitHandler.handleExit(event);
    }

    @FXML
    void handleGuestPage() throws IOException {
        navHandler.navigateToGuestPage();
    }

    @FXML
    void handleProductPage(ActionEvent event) throws IOException {
        navHandler.navigateToProductPage();
    }

    @FXML
    void handleReportPage(ActionEvent event) throws IOException {
        navHandler.navigateToReportPage();
    }

    @FXML
    void handleReservationPage(ActionEvent event) throws IOException {
        navHandler.navigateToReservationPage();
    }

    @FXML
    void handleRoomPage(ActionEvent event) throws IOException {
        navHandler.navigateToRoomPage();

    }

    @FXML
    void onAddGuestClick(ActionEvent event) {

    }

    @FXML
    void onCancelAddGuestClick(ActionEvent event) throws IOException {
        navHandler.navigateToGuestPage();
    }
}
