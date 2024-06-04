package br.edu.ifsp.hotelsync.domain.usecases.reports.formatter;

import br.edu.ifsp.hotelsync.domain.usecases.reports.records.Exportable;

public class SimpleTextFormatter<K, V, T extends Exportable<K, V>> implements Formatter<K, V, T>{

    @Override
    public String format(T report) {
        StringBuilder sb = new StringBuilder();
        sb.append(report.getClass().getSimpleName()).append("\n");

        report.getReport().forEach((v, k) -> sb.append("Date: ")
                .append(k)
                .append(" ")
                .append(report.getParameterName())
                .append(": ")
                .append(v)
                .append("\n"));

        return sb.toString();
    }
}
