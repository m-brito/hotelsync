package br.edu.ifsp.hotelsync.application.controller;

import br.edu.ifsp.hotelsync.application.util.ExitHandler;
import br.edu.ifsp.hotelsync.application.util.NavigationHandler;
import br.edu.ifsp.hotelsync.application.util.UIMode;
import br.edu.ifsp.hotelsync.domain.entities.room.Room;
import br.edu.ifsp.hotelsync.domain.entities.room.RoomCategory;
import br.edu.ifsp.hotelsync.domain.entities.room.RoomStatus;
import br.edu.ifsp.hotelsync.domain.usecases.room.create.CreateRoomUseCase;
import br.edu.ifsp.hotelsync.domain.usecases.room.update.UpdateRoomUseCase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;

import static br.edu.ifsp.hotelsync.application.main.Main.*;

public class RoomController {
    @FXML
    private Button addProductBtn;

    @FXML
    private TextField areaRoomField;

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
    private Button cancelProductBtn;

    @FXML
    private TextField descriptionRoomField;

    @FXML
    private TextField numberOfBedsField;

    @FXML
    private TextField numberRoomField;

    @FXML
    private Pane pnlOverview;

    @FXML
    private ComboBox<RoomCategory> roomCategoryCombo;

    @FXML
    private ComboBox<RoomStatus> roomStatusCombo;

    @FXML
    private TextField typeOfBedField;

    private final ExitHandler exitHandler =
            new ExitHandler();

    private final NavigationHandler navHandler =
            new NavigationHandler();

    @FXML
    private Label viewTitle;


    private Room room;

    @FXML
    public void initialize() {
        roomCategoryCombo.
                getItems().
                setAll(RoomCategory.values());

        roomStatusCombo.
                getItems().
                setAll(RoomStatus.values());
    }

    public void setEntity(Room room, UIMode mode) {
        if (room == null)
            throw new IllegalArgumentException("Room can not be null.");

        this.room = room;
        setEntityIntoView();

        if (mode == UIMode.VIEW)
            configureViewMode();
    }

    private void setEntityIntoView() {
        if (room != null) {
            viewTitle.setText("Update Product");
            numberRoomField.setText(String.valueOf(room.getNumber()));
            numberOfBedsField.setText(String.valueOf(room.getNumberOfBeds()));
            typeOfBedField.setText(room.getTypeOfBed());
            roomCategoryCombo.setValue(RoomCategory.valueOf(room.getRoomCategory().name()));
            descriptionRoomField.setText(room.getDescription());
            roomStatusCombo.setValue(RoomStatus.valueOf(room.getRoomStatus().toString()));
            areaRoomField.setText(String.valueOf(room.getArea()));
        }
    }

    private void configureViewMode() {
        numberRoomField.setDisable(true);
        numberOfBedsField.setDisable(true);
        typeOfBedField.setDisable(true);
        roomCategoryCombo.setDisable(true);
        descriptionRoomField.setDisable(true);
        roomStatusCombo.setDisable(true);
        areaRoomField.setDisable(true);
    }

    private void getEntityToView() {
        int number = Integer.parseInt(numberRoomField.getText());
        int numberOfBeds = Integer.parseInt(numberOfBedsField.getText());
        String typeOfBed = typeOfBedField.getText();
        RoomCategory roomCategory = roomCategoryCombo.getValue();
        String description = descriptionRoomField.getText();
        RoomStatus roomStatus = roomStatusCombo.getValue();
        double area = Double.parseDouble(areaRoomField.getText());

        if (room == null) {
            room = Room.createRoom(number, numberOfBeds, typeOfBed, roomCategory, description, roomStatus, area);
        } else {
            room.setNumber(number);
            room.setNumberOfBeds(numberOfBeds);
            room.setTypeOfBed(typeOfBed);
            room.setRoomCategory(roomCategory);
            room.setDescription(description);
            room.setRoomStatus(roomStatus);
            room.setArea(area);
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
            if (room == null) return;

            if (room.getId() == null) {
                createRoomUseCase.createRoom(new CreateRoomUseCase.RequestModel(
                        room.getNumber(),
                        room.getNumberOfBeds(),
                        room.getTypeOfBed(),
                        room.getRoomCategory(),
                        room.getRoomStatus(),
                        room.getDescription(),
                        room.getArea()
                ));
            } else {
                updateRoomUseCase.updateRoom(new UpdateRoomUseCase.RequestModel(
                        room.getId(),
                        room.getNumber(),
                        room.getNumberOfBeds(),
                        room.getTypeOfBed(),
                        room.getRoomCategory(),
                        room.getDescription(),
                        room.getRoomStatus(),
                        room.getArea()
                ));
            }

            navHandler.navigateToRoomManagementPage();
        } catch (Exception e) {
            showErrorAlert(e.getMessage());
        }
    }

    @FXML
    public void onSaveRoom(ActionEvent event) {
        saveOrUpdate();
    }

    @FXML
    void handleCancelRoom(ActionEvent event) throws IOException {
        navHandler.navigateToRoomManagementPage();
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
    void handleProductPage(ActionEvent event) throws IOException {
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
}

