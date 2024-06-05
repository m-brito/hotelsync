package br.edu.ifsp.hotelsync.domain.entities.report.exporter;

import br.edu.ifsp.hotelsync.domain.entities.report.formatter.Formatter;
import br.edu.ifsp.hotelsync.domain.entities.report.records.Exportable;

public interface Exporter {
    void export(Exportable report, Formatter formatter);
}
