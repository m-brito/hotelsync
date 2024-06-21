package br.edu.ifsp.hotelsync.application.util.reservation;

import br.edu.ifsp.hotelsync.domain.entities.guest.Guest;
import br.edu.ifsp.hotelsync.domain.usecases.guest.find.FindAllGuestUseCase;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

public class OwnerReservationComboSetup {

    private final ComboBox<Guest> ownerReservationCombo;
    private final FindAllGuestUseCase findAllGuestUseCase;

    public OwnerReservationComboSetup(ComboBox<Guest> ownerReservationCombo, FindAllGuestUseCase findAllGuestUseCase) {
        this.ownerReservationCombo = ownerReservationCombo;
        this.findAllGuestUseCase = findAllGuestUseCase;
    }

    public void setup() {
        ownerReservationCombo.getItems().addAll(findAllGuestUseCase.findAll().values());
        ownerReservationCombo.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Guest item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getName());
            }
        });


        ownerReservationCombo.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                ownerReservationCombo.setPromptText(newVal.getName());
            }
        });
    }
}