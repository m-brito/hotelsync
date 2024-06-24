package br.edu.ifsp.hotelsync.application.util;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.time.LocalDate;

public class CheckOutDialog extends Dialog<Pair<String, String>> {
    public CheckOutDialog(double totalReservation) {
        setTitle("Check-out");
        setHeaderText("Select a payment method and finalize the check-out:");

        ButtonType finalizeButtonType = new ButtonType("Finalize", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(finalizeButtonType, ButtonType.CANCEL);

        RadioButton cashButton = new RadioButton("Cash");
        RadioButton creditCardButton = new RadioButton("Credit Card");
        RadioButton debitButton = new RadioButton("Debit");

        ToggleGroup group = new ToggleGroup();
        cashButton.setToggleGroup(group);
        creditCardButton.setToggleGroup(group);
        debitButton.setToggleGroup(group);

        Label totalLabel = new Label("Total: R$ " + totalReservation);

        VBox vbox = new VBox(cashButton, creditCardButton, debitButton, totalLabel);
        getDialogPane().setContent(vbox);

        setResultConverter(dialogButton -> {
            if (dialogButton == finalizeButtonType) {
                RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
                return new Pair<>(selectedRadioButton.getText(), LocalDate.now().toString());
            }
            return null;
        });
    }
}