package br.edu.ifsp.hotelsync.domain.usecases.reports.export;

import br.edu.ifsp.hotelsync.domain.entities.report.exporter.PdfExporterImpl;

public class PdfExportUseCaseImpl implements PdfExportUseCase{
    @Override
    public void exportPdf(RequestModel request) {
        PdfExporterImpl exporter = new PdfExporterImpl(request.filepath());
        exporter.export(request.report(), request.formatter());
    }
}
