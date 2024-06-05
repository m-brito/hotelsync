package br.edu.ifsp.hotelsync.domain.entities.report.exporter;

import br.edu.ifsp.hotelsync.domain.entities.report.formatter.Formatter;
import br.edu.ifsp.hotelsync.domain.entities.report.records.Exportable;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfExporterImpl implements Exporter {
    private String filePath;

    public PdfExporterImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void export(Exportable report, Formatter formatter) {
        String formattedContent = formatter.format(report);

        Document document = new Document();
        try{
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
//            document.add(new Paragraph("Relatório: "+ report.getClass().getName()));

            document.add(new Paragraph(formattedContent));

            document.close();

        } catch (DocumentException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
