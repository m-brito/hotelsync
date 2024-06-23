package br.edu.ifsp.hotelsync.application.util;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.util.Pair;

import java.time.LocalDate;

public class CheckOutDialog extends Dialog<Pair<String, String>> {

    public CheckOutDialog() {
        setTitle("Check-out Dialog");
        setHeaderText("Select a payment method and finalize the check-out.");

        ButtonType finalizeButtonType = new ButtonType("Finalize", ButtonBar.ButtonData.OK_DONE);
        getDialogPane().getButtonTypes().addAll(finalizeButtonType, ButtonType.CANCEL);

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Cash", "Credit Card", "Debit");
        comboBox.setMaxWidth(50);

        getDialogPane().setContent(comboBox);

        setResultConverter(dialogButton -> {
            if (dialogButton == finalizeButtonType) {
                return new Pair<>(comboBox.getValue(), LocalDate.now().toString());
            }
            return null;
        });
    }
}