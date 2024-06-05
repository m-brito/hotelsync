package br.edu.ifsp.hotelsync.domain.entities.report.formatter;

import br.edu.ifsp.hotelsync.domain.entities.report.records.Exportable;

public interface Formatter<K, V, T extends Exportable<K, V>> {
    String format(T exportable);
}
