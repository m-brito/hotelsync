package br.edu.ifsp.hotelsync.domain.usecases.reports.export;

import br.edu.ifsp.hotelsync.domain.entities.report.exporter.TerminalExporter;

public class TerminalExportReportUseCaseImpl implements ExportReportUseCase {

    private final TerminalExporter exporter;

    public TerminalExportReportUseCaseImpl(TerminalExporter exporter) {
        this.exporter = exporter;
    }

    @Override
    public void exportReport(RequestModel request) {
        exporter.export(request.report(), request.formatter());
    }
}
