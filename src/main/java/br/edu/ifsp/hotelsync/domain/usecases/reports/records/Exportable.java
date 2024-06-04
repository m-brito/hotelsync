package br.edu.ifsp.hotelsync.domain.usecases.reports.records;

import java.time.LocalDate;
import java.util.Map;

public interface Exportable {
    Map<LocalDate, Double> getReport();

    String getParameterName();
}
