package br.edu.ifsp.hotelsync.application.controller.updateControllers;

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

public class UpdateGuestController {
    @FXML
    private DatePicker birthDateGuestPickerUpdate;

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
    private Button cancelGuestBtnUpdate;

    @FXML
    private TextField cepGuestFieldUpdate;

    @FXML
    private TextField cityGuestFieldUpdate;

    @FXML
    private TextField complementFieldUpdate;

    @FXML
    private TextField cpfFieldUpdate;

    @FXML
    private TextField districtFieldUpdate;

    @FXML
    private TextField nameFieldUpdate;

    @FXML
    private TextField phoneFieldUpdate;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField pronounsGuestFieldUpdate;

    @FXML
    private Button saveGuestBtnUpdate;

    @FXML
    private ComboBox<State> stateGuestComboUpdate;

    private final ExitHandler exitHandler =
            new ExitHandler();

    private final NavigationHandler navHandler =
            new NavigationHandler();

    @FXML
    public void initialize() {
        stateGuestComboUpdate.getItems().addAll(State.values());
    }

    @FXML
    void handleExit(ActionEvent event) {
        exitHandler.handleExit(event);
    }

    @FXML
    void handleGuestPage(ActionEvent event) throws IOException {
        navHandler.navigateToGuestPage();
    }

    @FXML
    void handleProductPage(ActionEvent event)  throws IOException {
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

}


