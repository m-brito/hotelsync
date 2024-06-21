package br.edu.ifsp.hotelsync.application.controller.createControllers;

import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.domain.entities.room.RoomCategory;
import br.edu.ifsp.hotelsync.domain.entities.room.RoomStatus;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CreateRoomController {
    @FXML
    private Button addProductBtn;

    @FXML
    private TextField areaRoomField;

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
    private Button cancelProductBtn;

    @FXML
    private TextField descriptionRoomField;

    @FXML
    private TextField numberOfBedsField;

    @FXML
    private TextField numberRoomField;

    @FXML
    private Pane pnlOverview;

    @FXML
    private ComboBox<RoomCategory> roomCategoryCombo;

    @FXML
    private ComboBox<RoomStatus> roomStatusCombo;

    @FXML
    private TextField typeOfBedField;

    private final ExitHandler exitHandler =
            new ExitHandler();

    private final NavigationHandler navHandler =
            new NavigationHandler();

    @FXML
    public void initialize() {
        roomCategoryCombo.
                getItems().
                setAll(RoomCategory.values());

        roomStatusCombo.
                getItems().
                setAll(RoomStatus.values());
    }

    @FXML
    public void handleAddRoom(ActionEvent event) {
    }

    @FXML
    void handleCancelRoom(ActionEvent event) {

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
}

