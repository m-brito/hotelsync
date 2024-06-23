package br.edu.ifsp.hotelsync.application.controller.managementControllers;

import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ReservationManagementController {
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
    private Button createReservationButton;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField searchReservation;

    @FXML
    private TableColumn<Reservation, String> startDateReservationField;

    @FXML
    private TableColumn<Reservation, String> checkInReservationField;

    @FXML
    private TableColumn<Reservation, String> endDateReservationField;

    @FXML
    private TableColumn<Reservation, String> checkOutReservationField;

    @FXML
    private TableColumn<Reservation, String> ownerReservationField;

    @FXML
    private TableColumn<Reservation, String> roomReservationField;

    @FXML
    private TableColumn<Reservation, String> statusReservationField;

    @FXML
    private TableColumn<Reservation, String> paymentMethodReservationField;

    @FXML
    private TableView<Reservation> tableReservation;

    private ObservableList<Reservation> tableData;

    @FXML
    public void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValuesSources();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableReservation.setItems(tableData);
    }

    private void bindColumnsToValuesSources() {
    }

    private final NavigationHandler navHandler =
            new NavigationHandler();

    @FXML
    void handleExit(ActionEvent event) {
        new ExitHandler().handleExit(event);
    }

    @FXML
    void handleGuestPage(ActionEvent event) throws IOException{
        navHandler.navigateToGuestManagementPage();
    }

    @FXML
    void handleProductPage(ActionEvent actionEvent) throws IOException {
        navHandler.navigateToProductManagementPage();
    }

    @FXML
    void handleReportPage(ActionEvent event) throws IOException {
        navHandler.navigateToReportPage();
    }

    @FXML
    void handleReservationPage(ActionEvent event) throws IOException {
        navHandler.navigateToReservationManagementPage();
    }

    @FXML
    void handleRoomPage(ActionEvent event) throws IOException {
        navHandler.navigateToRoomManagementPage();
    }

    @FXML
    void handleCreateReservation(ActionEvent event) throws IOException {
        navHandler.navigateToReservationPage();
    }

    public void handleUpdateReservation(ActionEvent actionEvent) {
    }
}
