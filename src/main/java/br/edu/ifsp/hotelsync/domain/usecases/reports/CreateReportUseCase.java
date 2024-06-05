package br.edu.ifsp.hotelsync.domain.usecases.reports;

import br.edu.ifsp.hotelsync.domain.usecases.reports.records.Exportable;
import br.edu.ifsp.hotelsync.domain.usecases.reports.exporter.Exporter;

import java.time.LocalDate;

public interface CreateReportUseCase {

    record RequestModel(LocalDate initialDate, LocalDate finalDate, Exporter exportTo){}

    Exportable createReport(RequestModel request);

}
