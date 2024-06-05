package br.edu.ifsp.hotelsync.domain.usecases.reports;

public class TerminalExportReportUseCase implements ExportReportUseCase {
    @Override
    public void exportReport(RequestModel request) {
        request.exporter().export(request.report(), request.formatter());
    }
}
