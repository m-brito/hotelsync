package br.edu.ifsp.hotelsync.domain.usecases.reports.exporter;

import br.edu.ifsp.hotelsync.domain.usecases.reports.formatter.Formatter;
import br.edu.ifsp.hotelsync.domain.usecases.reports.records.Exportable;

public interface Exporter {
    void export(Exportable report, Formatter formatter);
}
