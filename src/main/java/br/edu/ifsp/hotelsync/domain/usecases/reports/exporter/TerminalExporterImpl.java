package br.edu.ifsp.hotelsync.domain.usecases.reports.exporter;

import br.edu.ifsp.hotelsync.domain.usecases.reports.formatter.Formatter;
import br.edu.ifsp.hotelsync.domain.usecases.reports.records.Exportable;

public class TerminalExporterImpl implements Exporter {
    @Override
    public void export(Exportable report, Formatter formatter) {
        System.out.println(formatter.format(report));
    }
}
