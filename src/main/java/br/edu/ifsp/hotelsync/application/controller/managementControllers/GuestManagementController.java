package br.edu.ifsp.hotelsync.application.controller.managementControllers;

import br.edu.ifsp.hotelsync.application.controller.GuestController;
import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.application.util.UIMode;
import br.edu.ifsp.hotelsync.application.view.Home;
import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import static br.edu.ifsp.hotelsync.application.main.Main.findAllGuestUseCase;

public class GuestManagementController {
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
    private TableColumn<Guest, String> cepColumn;

    @FXML
    private TableColumn<Guest, String> cityColumn;

    @FXML
    private TableColumn<Guest, String> complementColumn;

    @FXML
    private TableColumn<Guest, String> cpfColumn;

    @FXML
    private Button createGuestButton;

    @FXML
    private TableColumn<Guest, String> districtColumn;

    @FXML
    private TableColumn<Guest, String> nameColumn;

    @FXML
    private TableColumn<Guest, String> phoneColumn;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TableColumn<Guest, String> pronounsColumn;

    @FXML
    private TableColumn<Guest, String> roadColumn;

    @FXML
    private TextField searchGuest;

    @FXML
    private TableColumn<Guest, String> stateColumn;

    @FXML
    private TableView<Guest> tableGuest;

    @FXML
    private Button updateGuestButton;

    private ObservableList<Guest> tableData;

    private final NavigationHandler navHandler =
            new NavigationHandler();

    private final ExitHandler exitHandler =
            new ExitHandler();


    @FXML
    public void initialize() {
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
        pronounsColumn.setCellValueFactory(new PropertyValueFactory<>("pronouns"));
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        roadColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getAddress() != null
                        ? cell.getValue().getAddress().getRoad() : null));
        cityColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getAddress() != null
                        ? cell.getValue().getAddress().getCity() : null));
        stateColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getAddress() != null
                        ? cell.getValue().getAddress().getState().getName() : null));
        cepColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getAddress() != null
                        ? cell.getValue().getAddress().cep() : null));
        districtColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getAddress() != null
                        ? cell.getValue().getAddress().getDistrict() : null));
        complementColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getAddress() != null
                        ? cell.getValue().getAddress().getComplement() : null));
    }

    public void populateTable() {
        Map<Long, Guest> guests = findAllGuestUseCase.findAll();
        tableData.clear();
        tableData.addAll(guests.values());
    }

    private void showGuestInMode(UIMode mode) throws IOException {
        Guest selectedItem = tableGuest.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            navHandler.navigateToGuestPage();
            GuestController controller = (GuestController) Home.getController();
            controller.setEntity(selectedItem, mode);
        }
    }

    @FXML
    public void handleUpdateGuest(ActionEvent actionEvent) throws IOException {
        showGuestInMode(UIMode.UPDATE);
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
    public void handleCreateGuest(ActionEvent actionEvent) throws IOException {
        navHandler.navigateToGuestPage();
    }
}
