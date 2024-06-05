package br.edu.ifsp.hotelsync.domain.entities.report.exporter;

import br.edu.ifsp.hotelsync.domain.entities.report.formatter.Formatter;
import br.edu.ifsp.hotelsync.domain.entities.report.records.Exportable;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfExporterImpl implements Exporter {
    private final String filePath;

    public PdfExporterImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void export(Exportable report, Formatter formatter) {
        String formattedContent = formatter.format(report);

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph title = new Paragraph(report.getClass().getSimpleName(), titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));

            Font contentFont = new Font(Font.FontFamily.HELVETICA, 12);
            Paragraph content = new Paragraph(formattedContent, contentFont);
            document.add(content);

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}