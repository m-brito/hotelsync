package br.edu.ifsp.hotelsync.domain.usecases.reports;

import br.edu.ifsp.hotelsync.domain.usecases.reports.exporter.Exporter;
import br.edu.ifsp.hotelsync.domain.usecases.reports.formatter.Formatter;
import br.edu.ifsp.hotelsync.domain.usecases.reports.records.Exportable;

public interface ExportReportUseCase {

    record RequestModel(Exportable report, Formatter formatter, Exporter exporter){}

    void exportReport(RequestModel request);

}
