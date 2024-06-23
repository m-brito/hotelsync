package br.edu.ifsp.hotelsync.application.controller.tableControllers;

import br.edu.ifsp.hotelsync.application.repository.sqlite.dao.SqliteGuestDao;
import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.Map;

import static br.edu.ifsp.hotelsync.application.main.Main.findAllGuestUseCase;

public class GuestTableController {

    private final TableColumn<Guest, String> nameColumn;
    private final TableColumn<Guest, String> pronounsColumn;
    private final TableColumn<Guest, LocalDate> birthdateColumn;
    private final TableColumn<Guest, String> phoneColumn;
    private final TableColumn<Guest, String> cpfColumn;
    private final TableColumn<Guest, String> roadColumn;
    private final TableColumn<Guest, String> cityColumn;
    private final TableColumn<Guest, String> stateColumn;
    private final TableColumn<Guest, String> cepColumn;
    private final TableColumn<Guest, String> districColumn;
    private final TableColumn<Guest, String> complementColumn;
    private final TableView<Guest> tableGuest;

    public GuestTableController(TableColumn<Guest, String> nameColumn, TableColumn<Guest, String> pronounsColumn,
                             TableColumn<Guest, LocalDate> birthdateColumn, TableColumn<Guest, String> phoneColumn,
                             TableColumn<Guest, String> cpfColumn, TableColumn<Guest, String> roadColumn,
                                TableColumn<Guest, String> cityColumn, TableColumn<Guest, String> stateColumn,
                                TableColumn<Guest, String> cepColumn, TableColumn<Guest, String> districColumn, TableColumn<Guest, String> complementColumn,
                             TableView<Guest> tableGuest) {
        this.nameColumn = nameColumn;
        this.pronounsColumn = pronounsColumn;
        this.birthdateColumn = birthdateColumn;
        this.phoneColumn = phoneColumn;
        this.cpfColumn = cpfColumn;
        this.roadColumn = roadColumn;
        this.cityColumn = cityColumn;
        this.stateColumn = stateColumn;
        this.cepColumn = cepColumn;
        this.districColumn = districColumn;
        this.complementColumn = complementColumn;
        this.tableGuest = tableGuest;
    }

    public void populateTable() {
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
        districColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getAddress() != null
                        ? cell.getValue().getAddress().getDistrict() : null));
        complementColumn.setCellValueFactory(cell ->
                new SimpleStringProperty(cell.getValue().getAddress() != null
                        ? cell.getValue().getAddress().getComplement() : null));

        Map<Long, Guest> guests = findAllGuestUseCase.findAll();
        ObservableList<Guest> guestList = FXCollections.observableArrayList(guests.values());
        tableGuest.setItems(guestList);
    }
}