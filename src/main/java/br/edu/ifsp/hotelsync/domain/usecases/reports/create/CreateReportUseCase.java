package br.edu.ifsp.hotelsync.domain.usecases.reports.create;

import br.edu.ifsp.hotelsync.domain.entities.report.records.Exportable;
import br.edu.ifsp.hotelsync.domain.entities.report.exporter.Exporter;

import java.time.LocalDate;

public interface CreateReportUseCase {

    record RequestModel(LocalDate initialDate, LocalDate finalDate, Exporter exportTo){}

    Exportable createReport(RequestModel request);

}
