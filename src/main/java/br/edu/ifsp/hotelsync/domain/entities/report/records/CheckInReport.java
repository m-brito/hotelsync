package br.edu.ifsp.hotelsync.domain.entities.report.records;

import java.time.LocalDate;
import java.util.Map;

public record CheckInReport(Map<LocalDate, Integer> report) implements Exportable<LocalDate, Integer> {

    @Override
    public Map<LocalDate, Integer> getReport() {
        return report;
    }

    @Override
    public String getParameterName() {
        return "Check In Number";
    }
}
