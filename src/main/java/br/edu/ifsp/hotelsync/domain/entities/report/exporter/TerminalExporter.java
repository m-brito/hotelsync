package br.edu.ifsp.hotelsync.domain.entities.report.exporter;

import br.edu.ifsp.hotelsync.domain.entities.report.formatter.Formatter;
import br.edu.ifsp.hotelsync.domain.entities.report.records.Exportable;

public class TerminalExporter implements Exporter {
    @Override
    public void export(Exportable report, Formatter formatter) {
        System.out.println(formatter.format(report));
    }
}
