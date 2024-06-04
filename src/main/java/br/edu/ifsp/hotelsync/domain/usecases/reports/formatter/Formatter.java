package br.edu.ifsp.hotelsync.domain.usecases.reports.formatter;

import br.edu.ifsp.hotelsync.domain.usecases.reports.records.Exportable;

public interface Formatter {
    String format(Exportable report);
}
