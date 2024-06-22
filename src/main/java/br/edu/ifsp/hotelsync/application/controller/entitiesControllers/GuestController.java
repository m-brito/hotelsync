package br.edu.ifsp.hotelsync.application.controller.entitiesControllers;

import br.edu.ifsp.hotelsync.application.controller.createControllers.CreateGuestController;
import br.edu.ifsp.hotelsync.application.controller.tableControllers.GuestTableController;
import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.application.util.UIMode;
import br.edu.ifsp.hotelsync.application.view.Home;
import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.time.LocalDate;

public class GuestController {
    @FXML
    private TableColumn<Guest, String> addressColumn;

    @FXML
    private TableColumn<Guest, LocalDate> birthdateColumn;

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
    private TableColumn<Guest, String> cpfColumn;

    @FXML
    private Button createGuestButton;

    @FXML
    private TableColumn<Guest, String> nameColumn;

    @FXML
    private TableColumn<Guest, String> phoneColumn;

    @FXML
    private VBox pnItems;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TableColumn<Guest, String> pronounsColumn;

    @FXML
    private TextField searchGuest;

    @FXML
    private TableView <Guest> tableGuest;

    @FXML
    private Button updateGuestButton;

    private final NavigationHandler navHandler =
            new NavigationHandler();

    private final ExitHandler exitHandler =
            new ExitHandler();


    @FXML
    public void initialize() {
        GuestTableController guestTableHandler = new GuestTableController(
                nameColumn,
                pronounsColumn,
                birthdateColumn,
                phoneColumn,
                cpfColumn,
                addressColumn,
                tableGuest);
        guestTableHandler.populateTable();
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
    public void handleCreateGuest(ActionEvent actionEvent) throws IOException {
        navHandler.handleCreateGuest();
    }

    public void handleUpdateGuest(ActionEvent actionEvent) throws IOException {
        showGuestInMode(UIMode.UPDATE);
    }

    private void showGuestInMode(UIMode mode) throws IOException {
        Guest selectedItem = tableGuest.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            navHandler.handleCreateGuest();
            CreateGuestController controller = (CreateGuestController) Home.getController();
            controller.setEntity(selectedItem, mode);
        }
    }
}
