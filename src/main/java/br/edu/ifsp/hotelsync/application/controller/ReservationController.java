package br.edu.ifsp.hotelsync.application.controller;

import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.application.util.UIMode;
import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Reservation;
import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.usecases.reservation.create.CreateReservationUseCase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDate;

import static br.edu.ifsp.hotelsync.application.main.Main.*;

public class ReservationController {

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
    private Button cancelReservationBtn;

    @FXML
    private DatePicker checkInDate;

    @FXML
    private DatePicker checkOutDate;

    @FXML
    private Button createReservationButton;

    @FXML
    private Button doneAddGuestBtn;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<Guest> ownerReservationCombo;

    @FXML
    private Pane pnlOverview;

    @FXML
    private ComboBox<Room> roomReservationCombo;

    @FXML
    private TextField cpfField;

    @FXML
    private DatePicker startDate;

    @FXML
    private Label viewSubtitle;

    @FXML
    private Label viewTitle;

    @FXML
    private TableView<Guest> tableGuest;

    @FXML
    private TableColumn<Guest, Double> nameColumn;

    @FXML
    private TableColumn<Guest, Double> birthdateColumn;

    @FXML
    private TableColumn<Guest, Double> cpfColumn;

    private ObservableList<Guest> tableData;

    private Reservation reservation;

    private final ExitHandler exitHandler =
            new ExitHandler();

    private final NavigationHandler navHandler =
            new NavigationHandler();

    @FXML
    public void initialize() {
        ownerReservationCombo.getItems().addAll(findAllGuestUseCase.findAll().values());
        roomReservationCombo.getItems().addAll(findAllAvailableRoomUseCase.findAllAvailable().values());
    }

    public void setEntity(Reservation reservation, UIMode mode) {
        if (reservation == null)
            throw new IllegalArgumentException("Reservation can not be null.");

        this.reservation = reservation;
        setEntityIntoView();

        if(mode == UIMode.VIEW)
            configureViewMode();

        if(mode == UIMode.UPDATE)
            configureUpdateMode();
    }

    private void setEntityIntoView() {
        if (reservation != null) {
            viewTitle.setText("Update Reservation");
            startDate.setValue(reservation.getStartDate());
            checkInDate.setValue(reservation.getCheckInDate());
            checkOutDate.setValue(reservation.getCheckOutDate());
            endDate.setValue(reservation.getEndDate());
            ownerReservationCombo.setValue(reservation.getOwner());
            roomReservationCombo.setValue(reservation.getRoom());
            startDate.setDisable(true);
            endDate.setDisable(true);
            ownerReservationCombo.setDisable(true);
            roomReservationCombo.setDisable(true);
        }
    }

    private void configureViewMode() {
        startDate.setDisable(true);
        endDate.setDisable(true);
        ownerReservationCombo.setDisable(true);
        roomReservationCombo.setDisable(true);
    }

    private void configureUpdateMode() {
        nameField.setVisible(true);
        cpfField.setVisible(true);
        birthdatePicker.setVisible(true);
        viewSubtitle.setVisible(true);
        tableGuest.setVisible(true);
        doneAddGuestBtn.setVisible(true);
        bindTableViewToItemsList();
        bindColumnsToValuesSources();
        populateTable();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableGuest.setItems(tableData);
    }

    private void bindColumnsToValuesSources() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    }

    private void populateTable() {
        tableGuest.setItems(FXCollections.observableArrayList(reservation.getGuests()));
    }

    private void getEntityToView() {
        LocalDate start = startDate.getValue();
        LocalDate end = endDate.getValue();
        Guest owner = ownerReservationCombo.getValue();
        Room room = roomReservationCombo.getValue();

        if(reservation == null) {
            reservation = Reservation.createReservation(start, end, owner, room);
        } else {
            reservation.setRoom(room);
        }
    }

    private void showErrorAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText(errorMessage);
        alert.showAndWait();
    }

    private void saveOrUpdate() {
        try {
            getEntityToView();
            if (reservation == null) return;
            if (reservation.getId() == null) {
                createReservationUseCase.createReservation(
                        new CreateReservationUseCase.RequestModel(
                                reservation.getStartDate(),
                                reservation.getEndDate(),
                                reservation.getOwner(),
                                reservation.getRoom()));
            }

            navHandler.navigateToReservationManagementPage();
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }


    @FXML
    void handleExit(ActionEvent event) {
        exitHandler.handleExit(event);
    }

    @FXML
    void handleGuestPage(ActionEvent event) throws IOException {
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
    void onSaveReservation(ActionEvent event) throws IOException {
        saveOrUpdate();
    }

    @FXML
    void handleCancelAddGuestBtn(ActionEvent event) throws IOException {
        navHandler.navigateToReservationManagementPage();
    }

    public void addGuestBtn(ActionEvent actionEvent) {

    }
}
