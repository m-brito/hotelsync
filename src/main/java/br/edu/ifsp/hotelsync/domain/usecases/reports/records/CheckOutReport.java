package br.edu.ifsp.hotelsync.domain.usecases.reports.records;

import java.time.LocalDate;
import java.util.Map;

public record CheckOutReport(Map<LocalDate, Integer> report) implements Exportable<LocalDate, Integer> {

    @Override
    public Map<LocalDate, Integer> getReport() {
        return report;
    }

    @Override
    public String getParameterName() {
        return "Check Out Number";
    }
}
