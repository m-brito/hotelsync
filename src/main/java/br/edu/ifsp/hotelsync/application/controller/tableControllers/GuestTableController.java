package br.edu.ifsp.hotelsync.application.controller.tableControllers;

import br.edu.ifsp.hotelsync.application.repository.sqlite.dao.SqliteGuestDao;
import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
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
    private final TableColumn<Guest, String> addressColumn;
    private final TableView<Guest> tableGuest;

    public GuestTableController(TableColumn<Guest, String> nameColumn, TableColumn<Guest, String> pronounsColumn,
                             TableColumn<Guest, LocalDate> birthdateColumn, TableColumn<Guest, String> phoneColumn,
                             TableColumn<Guest, String> cpfColumn, TableColumn<Guest, String> addressColumn,
                             TableView<Guest> tableGuest) {
        this.nameColumn = nameColumn;
        this.pronounsColumn = pronounsColumn;
        this.birthdateColumn = birthdateColumn;
        this.phoneColumn = phoneColumn;
        this.cpfColumn = cpfColumn;
        this.addressColumn = addressColumn;
        this.tableGuest = tableGuest;
    }

    public void populateTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        pronounsColumn.setCellValueFactory(new PropertyValueFactory<>("pronouns"));
        birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        Map<Long, Guest> guests = findAllGuestUseCase.findAll();
        ObservableList<Guest> guestList = FXCollections.observableArrayList(guests.values());
        tableGuest.setItems(guestList);
    }
}