package br.edu.ifsp.hotelsync.application.util;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class DatePickerInitializer {
    private final DatePicker datePicker;
    private final DatePicker startDatePicker;

    public DatePickerInitializer(DatePicker datePicker, DatePicker startDatePicker) {
        this.datePicker = datePicker;
        this.startDatePicker = startDatePicker;
    }

    public void disablePastDates() {
        datePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                LocalDate startDate = startDatePicker.getValue() != null ? startDatePicker.getValue().plusDays(1) : today;

                setDisable(empty || date.isBefore(startDate));
            }
        });
    }
}