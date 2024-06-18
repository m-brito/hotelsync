package br.edu.ifsp.hotelsync.application.controller.entitiesControllers;

import br.edu.ifsp.hotelsync.application.view.Home;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ReportController {
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
    private Button checkInButton;

    @FXML
    private Button checkOutButton;

    @FXML
    private Button dailyOcuppationButton;

    @FXML
    private Button financialButton;

    @FXML
    private Pane pnlOverview;

    @FXML
    public void initialize() {
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


}
