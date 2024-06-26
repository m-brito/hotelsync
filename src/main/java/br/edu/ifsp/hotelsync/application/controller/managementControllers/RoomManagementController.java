package br.edu.ifsp.hotelsync.application.controller.managementControllers;

import br.edu.ifsp.hotelsync.application.controller.RoomController;
import br.edu.ifsp.hotelsync.application.util.AlertHelper;
import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.application.util.UIMode;
import br.edu.ifsp.hotelsync.application.view.Home;
import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.usecases.room.find.FindAllRoomByNumberUseCase;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static br.edu.ifsp.hotelsync.application.main.Main.*;

public class RoomManagementController {
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
    private Button createRoomButton;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TextField searchRoom;

    @FXML
    private TableView<Room> tableRoom;

    @FXML
    private TableColumn<Room, Double> numberColumn;

    @FXML
    private TableColumn<Room, Double> numberOfBedsColumn;

    @FXML
    private TableColumn<Room, Double> typeOfBedColumn;

    @FXML
    private TableColumn<Room, String> categoryColumn;

    @FXML
    private TableColumn<Room, String> descriptionColumn;

    @FXML
    private TableColumn<Room, String> statusColumn;

    @FXML
    private TableColumn<Room, Double> areaColumn;

    @FXML
    private Button updateRoomButton;

    private ObservableList<Room> tableData;

    @FXML
    public void initialize() {
        bindTableViewToItemsList();
        bindColumnsToValuesSources();
        populateTable();
    }

    private void bindTableViewToItemsList() {
        tableData = FXCollections.observableArrayList();
        tableRoom.setItems(tableData);
    }

    private void bindColumnsToValuesSources() {
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        numberOfBedsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfBeds"));
        typeOfBedColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfBed"));
        categoryColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getRoomCategory().getDescription()));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        statusColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getRoomStatus().toString()));
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));
    }

    private void showProductInMode(UIMode mode) throws IOException {
        Room selectedItem = tableRoom.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            navHandler.navigateToRoomPage();
            RoomController controller = (RoomController) Home.getController();
            controller.setEntity(selectedItem, mode);
        }
    }

    public void populateTable() {
        Map<Long, Room> rooms = findAllRoomUseCase.findAll();
        tableData.clear();
        tableData.addAll(rooms.values());
    }

    private final NavigationHandler navHandler =
            new NavigationHandler();

    @FXML
    public void handleImageClick(MouseEvent mouseEvent) {
        if(isNumeric(searchRoom.getText()) || Objects.equals(searchRoom.getText(), "")) {
            Map<Long, Room> rooms = findAllRoomByNumberUseCase.findAllByNumber(
                    new FindAllRoomByNumberUseCase.RequestModel(searchRoom.getText()));
            searchRoom.setText("");
            tableData.clear();
            tableData.addAll(rooms.values());
        } else {
            AlertHelper.showErrorAlert("Warning", "Warning", "It must be a number");
        }
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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
    void onCreateRoomClick(ActionEvent event) throws IOException {
        navHandler.navigateToRoomPage();
    }

    public void handleUpdateRoom(ActionEvent actionEvent) throws IOException {
        showProductInMode(UIMode.UPDATE);
    }
}
