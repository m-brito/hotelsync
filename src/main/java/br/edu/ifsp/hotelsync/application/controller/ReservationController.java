package br.edu.ifsp.hotelsync.application.controller;

import br.edu.ifsp.hotelsync.application.repository.sqlite.dao.SqliteGuestDao;
import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.reservation.MethodPaymentComboSetup;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.application.util.reservation.OwnerReservationComboSetup;
import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Payment;
import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.usecases.guest.find.FindAllGuestUseCaseImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ReservationController {
    @FXML
    private Button addGuestBtn;

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
    private Button cancelAddGuestBtn;

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
    private ComboBox<Payment> methodPaymentCombo;

    @FXML
    private TextField nameField;

    @FXML
    private ComboBox<Guest> ownerReservationCombo;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField pronounsField;

    @FXML
    private ComboBox<Room> roomReservationCombo;

    @FXML
    private TextField ssnField;

    @FXML
    private DatePicker startDate;

    private final ExitHandler exitHandler =
            new ExitHandler();

    private final NavigationHandler navHandler =
            new NavigationHandler();

    @FXML
    public void initialize() {
        new MethodPaymentComboSetup(methodPaymentCombo).setup();

        new OwnerReservationComboSetup(ownerReservationCombo,
                new FindAllGuestUseCaseImpl(
                        new SqliteGuestDao())).setup();
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
    void handleCreateReservation(ActionEvent event) throws IOException {
        navHandler.handleCreateReservation();
    }

    @FXML
    void handleAddGuest(ActionEvent event) {
        nameField.
                setVisible(true);
        pronounsField.
                setVisible(true);
        ssnField.
                setVisible(true);
        birthdatePicker.
                setVisible(true);
    }

    @FXML
    void handleCancelAddGuestBtn(ActionEvent event) throws IOException {
        navHandler.navigateToReservationPage();
    }
}
