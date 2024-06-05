package br.edu.ifsp.hotelsync.domain.usecases.reports.records;

import java.util.Map;

public interface Exportable<K, V> {
    Map<K, V> getReport();

    String getParameterName();
}
