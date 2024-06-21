package br.edu.ifsp.hotelsync.application.util.reservation;

import br.edu.ifsp.hotelsync.domain.entities.reservation.Payment;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

public class MethodPaymentComboSetup {

    private final ComboBox<Payment> methodPaymentCombo;

    public MethodPaymentComboSetup(ComboBox<Payment> methodPaymentCombo) {
        this.methodPaymentCombo = methodPaymentCombo;
    }

    public void setup() {
        methodPaymentCombo.getItems().addAll(Payment.values());
        methodPaymentCombo.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Payment item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.toString());
            }
        });
    }
}