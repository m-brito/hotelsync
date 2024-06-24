package br.edu.ifsp.hotelsync.application.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatter {
    private final DateTimeFormatter formatter;

    public DateFormatter(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    public String format(LocalDate date) {
        if (date == null) {
            return null;
        }
        return date.format(this.formatter);
    }
}