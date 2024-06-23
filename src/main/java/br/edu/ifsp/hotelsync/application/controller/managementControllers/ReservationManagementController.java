package br.edu.ifsp.hotelsync.application.controller.managementControllers;

import br.edu.ifsp.hotelsync.application.controller.ProductController;
import br.edu.ifsp.hotelsync.application.controller.ReservationController;
import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.application.util.UIMode;
import br.edu.ifsp.hotelsync.application.view.Home;
import br.edu.ifsp.hotelsync.domain.entities.product.Product;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.Map;

import static br.edu.ifsp.hotelsync.application.main.Main.findAllProductUseCase;
import static br.edu.ifsp.hotelsync.application.main.Main.findAllReservationUseCase;

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
        populateTable();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableReservation.setItems(tableData);
    }

    private void bindColumnsToValuesSources() {
        startDateReservationField.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        checkInReservationField.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        endDateReservationField.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        checkOutReservationField.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        ownerReservationField.setCellValueFactory(cell ->
                new SimpleStringProperty(
                        cell.getValue().getOwner() != null ?
                                String.format("#%s - %s", cell.getValue().getOwner().getId(), cell.getValue().getOwner().getName()) : null));
        roomReservationField.setCellValueFactory(cell ->
                new SimpleStringProperty(
                        cell.getValue().getRoom() != null ?
                                String.format("#%s - %s", cell.getValue().getRoom().getId(), cell.getValue().getRoom().getNumber()) : null));

        statusReservationField.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getReservationStatus().name()));
        paymentMethodReservationField.setCellValueFactory(cell ->
                new SimpleStringProperty(
                        cell.getValue().getPayment() != null ?
                                cell.getValue().getPayment().toString() : null));
    }


    private void showProductInMode(UIMode mode) throws IOException {
        Reservation selectedItem = tableReservation.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            navHandler.navigateToReservationPage();
            ReservationController controller = (ReservationController) Home.getController();
            controller.setEntity(selectedItem, mode);
        }
    }

    public void populateTable() {
        Map<Long, Reservation> reservations = findAllReservationUseCase.findAll();
        tableData.clear();
        tableData.addAll(reservations.values());
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

    public void handleUpdateReservation(ActionEvent actionEvent) throws IOException {
        showProductInMode(UIMode.UPDATE);
    }
}
