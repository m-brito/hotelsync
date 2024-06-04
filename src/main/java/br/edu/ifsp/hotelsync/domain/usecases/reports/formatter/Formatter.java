package br.edu.ifsp.hotelsync.domain.usecases.reports.formatter;

import br.edu.ifsp.hotelsync.domain.usecases.reports.records.Exportable;

public interface Formatter<K, V, T extends Exportable<K, V>> {
    String format(T exportable);
}
