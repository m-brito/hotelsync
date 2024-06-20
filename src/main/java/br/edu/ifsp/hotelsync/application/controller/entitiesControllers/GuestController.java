package br.edu.ifsp.hotelsync.application.controller.entitiesControllers;

import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.application.view.Home;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class GuestController {
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
    private Button createGuestButton;

    @FXML
    private VBox pnItems;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField searchGuest;

    @FXML
    private Button updateGuestButton;

    private final NavigationHandler navHandler = new NavigationHandler();
    private final ExitHandler exitHandler = new ExitHandler();

    @FXML
    void handleExit(ActionEvent event) {
        exitHandler.handleExit(event);
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
    public void handleCreateGuest(ActionEvent actionEvent) throws IOException {
        navHandler.handleCreateGuest();
    }
}
