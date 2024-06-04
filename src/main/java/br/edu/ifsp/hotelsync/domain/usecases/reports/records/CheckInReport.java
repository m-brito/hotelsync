package br.edu.ifsp.hotelsync.domain.usecases.reports.records;

import java.time.LocalDate;
import java.util.Map;

public record CheckInReport(Map<LocalDate, Double> report) implements Exportable {

    @Override
    public Map<LocalDate, Double> getReport() {
        return report;
    }

    @Override
    public String getParameterName() {
        return "Check In Number";
    }
}
