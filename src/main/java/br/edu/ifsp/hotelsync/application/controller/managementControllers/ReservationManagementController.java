package br.edu.ifsp.hotelsync.application.controller.managementControllers;

import br.edu.ifsp.hotelsync.application.controller.ReservationController;
import br.edu.ifsp.hotelsync.application.util.*;
import br.edu.ifsp.hotelsync.application.view.Home;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Payment;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.find.FindAllReservationByOwnerUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.CheckInUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.update.interfaces.CheckOutUseCase;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import static br.edu.ifsp.hotelsync.application.main.Main.*;

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
    private Button checkInBtn;

    @FXML
    private Button checkOutBtn;

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
    private TableColumn<Reservation, String> totalReservationField;

    @FXML
    private TableView<Reservation> tableReservation;

    private ObservableList<Reservation> tableData;

    private final DateFormatter dateFormatter = new DateFormatter("dd/MM/yyyy");

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
        startDateReservationField.setCellValueFactory(cell ->
                new SimpleStringProperty(dateFormatter.format(cell.getValue().getStartDate())));
        checkInReservationField.setCellValueFactory(cell ->
                new SimpleStringProperty(dateFormatter.format(cell.getValue().getCheckInDate())));
        endDateReservationField.setCellValueFactory(cell ->
                new SimpleStringProperty(dateFormatter.format(cell.getValue().getEndDate())));
        checkOutReservationField.setCellValueFactory(cell ->
                new SimpleStringProperty(dateFormatter.format(cell.getValue().getCheckOutDate())));
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
        totalReservationField.setCellValueFactory(cell -> new SimpleStringProperty(
                String.format("R$ %.2f", cell.getValue().calculateTotalToPay())));
    }


    private void showProductInMode(UIMode mode) throws IOException {
        Reservation selectedItem = tableReservation.getSelectionModel().getSelectedItem();
        if(selectedItem.getCheckOutDate() != null || LocalDate.now().isAfter(selectedItem.getEndDate())){
            AlertHelper.showErrorAlert
            ("Error Dialog", "Reservation Error",
            "This reservation has already been checked out or has expired.");
            return;
        }
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

    public void handleUpdateReservation(ActionEvent actionEvent) throws IOException {
        Reservation selectedItem = tableReservation.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            AlertHelper.showErrorAlert(
                    "Error Dialog", "No Selection",
                    "No Reservation Selected. Please select a Reservation in the table.");
        } else {
            showProductInMode(UIMode.UPDATE);
        }
    }

    @FXML
    public void handleCheckIn() {
        Reservation selectedItem = tableReservation.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            AlertHelper.showErrorAlert(
                    "Error Dialog", "No Selection",
                    "No Reservation Selected. Please select a Reservation in the table.");
        } else {
            try {
                checkInUseCase.doCheckIn(new CheckInUseCase.RequestModel(selectedItem.getId()));
                populateTable();
            } catch (IllegalStateException e) {
                AlertHelper.showErrorAlert(
                        "Error Dialog",
                        "Check-in Error",
                        e.getMessage());
            }
        }
    }

    @FXML
    public void handleCheckOut() {
        Reservation selectedItem = tableReservation.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            AlertHelper.showErrorAlert(
                    "Error Dialog", "No Selection",
                    "No Reservation Selected. Please select a Reservation in the table.");
        } else {
            double totalReservation = selectedItem.calculateTotalToPay();
            CheckOutDialog dialog = new CheckOutDialog(totalReservation);
            Optional<Pair<String, String>> result = dialog.showAndWait();

            result.ifPresent(paymentAndDate -> {
                try {
                    CheckOutUseCase.RequestModel requestModel =
                            new CheckOutUseCase.RequestModel(
                                    selectedItem.getId(),
                                    Payment.
                                            fromDescription(paymentAndDate.
                                                    getKey()));

                    checkOutUseCase.doCheckOut(requestModel);

                    populateTable();
                } catch (IllegalStateException e) {
                    AlertHelper.showErrorAlert("Error Dialog", "Check-out Error", e.getMessage());
                }
            });
        }
    }

    @FXML
    public void handleImageClick(MouseEvent mouseEvent) {
        Map<Long, Reservation> reservations = findAllReservationByOwnerUseCase.findAllByOwner(
                new FindAllReservationByOwnerUseCase.RequestModel(searchReservation.getText())
        );
        searchReservation.setText("");
        tableData.clear();
        tableData.addAll(reservations.values());
    }

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
}

