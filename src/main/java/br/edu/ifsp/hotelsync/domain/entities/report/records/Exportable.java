package br.edu.ifsp.hotelsync.domain.entities.report.records;

import java.util.Map;

public interface Exportable<K, V> {
    Map<K, V> getReport();

    String getParameterName();
}
