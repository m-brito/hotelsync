package br.edu.ifsp.hotelsync.domain.usecases.reports.export;

import br.edu.ifsp.hotelsync.domain.entities.report.formatter.Formatter;
import br.edu.ifsp.hotelsync.domain.entities.report.records.Exportable;

public interface PdfExportUseCase {
    record RequestModel(Exportable report, Formatter formatter, String filepath){}

    void exportPdf(RequestModel request);
}
