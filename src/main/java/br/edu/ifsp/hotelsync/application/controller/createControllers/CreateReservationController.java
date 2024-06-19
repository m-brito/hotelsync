package br.edu.ifsp.hotelsync.application.controller.createControllers;

import br.edu.ifsp.hotelsync.application.view.Home;
import br.edu.ifsp.hotelsync.domain.entities.reservation.Payment;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.io.IOException;

public class CreateReservationController {
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
    private ComboBox<?> ownerReservationCombo;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField pronounsField;

    @FXML
    private ComboBox<?> roomReservationCombo;

    @FXML
    private TextField ssnField;

    @FXML
    private DatePicker startDate;

    @FXML
    public void initialize() {
        methodPaymentCombo.getItems().addAll(Payment.values());
        methodPaymentCombo.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Payment item, boolean empty) {
                super.updateItem(item, empty);
                setText((item == null || empty) ? null : item.toString());
            }
        });
        methodPaymentCombo.setButtonCell(methodPaymentCombo.getCellFactory().call(null));
    }

    @FXML
    void handleExit(ActionEvent event) {
        Alert alert = new Alert(Alert
                .AlertType
                .CONFIRMATION,
                "Do you really want to leave?",
                ButtonType.YES,
                ButtonType.NO);
        alert.setTitle("Departure Confirmation");
        alert.setHeaderText("Bye! \uD83D\uDC4B");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
    }

    @FXML
    void handleGuestPage(ActionEvent event) throws IOException {
        Home.setRoot("views/entitiesViews/guest");
    }

    @FXML
    void handleProductPage(ActionEvent actionEvent) throws IOException {
        Home.setRoot("views/entitiesViews/product");
    }

    @FXML
    void handleReportPage(ActionEvent event) throws IOException {
        Home.setRoot("views/useCasesViews/reportViews/generateReports");

    }

    @FXML
    void handleReservationPage(ActionEvent event) throws IOException {
        Home.setRoot("views/entitiesViews/reservation");

    }

    @FXML
    void handleRoomPage(ActionEvent event) throws IOException {
        Home.setRoot("views/entitiesViews/room");
    }


    @FXML
    void handleCreateReservation(ActionEvent event) throws IOException {
        Home.setRoot("views/useCasesViews/createViews/createReservation");
    }

    @FXML
    void handleAddGuest(ActionEvent event) {
        nameField.setVisible(true);
        pronounsField.setVisible(true);
        ssnField.setVisible(true);
        birthdatePicker.setVisible(true);
    }
}
