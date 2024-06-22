package br.edu.ifsp.hotelsync.application.controller.entitiesControllers;

import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class RoomController {
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
    private Button createRoomButton;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField searchRoom;

    @FXML
    private TableView<Room> tableRoom;

    @FXML
    private Button updateRoomButton;

    @FXML
    public void initialize() {
    }

    private final ExitHandler exitHandler =
            new ExitHandler();

    private final NavigationHandler navHandler =
            new NavigationHandler();

    @FXML
    void handleExit(ActionEvent event) {
        exitHandler.handleExit(event);
    }

    @FXML
    void handleGuestPage(ActionEvent event) throws IOException{
        navHandler.navigateToGuestPage();
    }

    @FXML
    void handleProductPage(ActionEvent actionEvent) throws IOException {
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
    void onCreateRoomClick(ActionEvent event) throws IOException {
        navHandler.navigateToCreateRoomPage();
    }

    public void handleUpdateRoom(ActionEvent actionEvent) {
    }
}
