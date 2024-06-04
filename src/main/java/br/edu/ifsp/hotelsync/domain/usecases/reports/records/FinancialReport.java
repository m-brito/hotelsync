package br.edu.ifsp.hotelsync.domain.usecases.reports.records;

import java.time.LocalDate;
import java.util.Map;

public record FinancialReport(Map<LocalDate, Double> report) implements Exportable<LocalDate, Double> {

    @Override
    public Map<LocalDate, Double> getReport() {
        return report;
    }

    @Override
    public String getParameterName() {
        return "Earnings";
    }
}
