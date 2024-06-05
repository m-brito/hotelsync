package br.edu.ifsp.hotelsync.domain.entities.report.formatter;

import br.edu.ifsp.hotelsync.domain.entities.report.records.Exportable;

public class SimpleTextFormatter<K, V, T extends Exportable<K, V>> implements Formatter<K, V, T>{

    @Override
    public String format(T report) {
        StringBuilder sb = new StringBuilder();
        sb.append(report.getClass().getSimpleName()).append("\n");
        report.getReport().forEach((k, v) -> sb.append("Date: ")
                .append(k)
                .append(" ")
                .append(report.getParameterName())
                .append(": ")
                .append(v)
                .append("\n"));

        return sb.toString();
    }
}
