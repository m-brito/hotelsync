package br.edu.ifsp.hotelsync.domain.entities.report.exporter;

import br.edu.ifsp.hotelsync.domain.entities.report.formatter.Formatter;
import br.edu.ifsp.hotelsync.domain.entities.report.records.Exportable;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

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
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

            HeaderFooterPageEvent event = new HeaderFooterPageEvent();
            writer.setPageEvent(event);

            document.open();

            PdfContentByte canvas = writer.getDirectContentUnder();
            canvas.setColorFill(BaseColor.LIGHT_GRAY);
            canvas.rectangle(0, 0, PageSize.A4.getWidth(), PageSize.A4.getHeight());
            canvas.fill();

            Font titleFont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, BaseColor.BLACK);
            Paragraph title = new Paragraph(report.getClass().getSimpleName(), titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));

            Font contentFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
            Paragraph content = new Paragraph(formattedContent, contentFont);
            document.add(content);

            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    class HeaderFooterPageEvent extends PdfPageEventHelper {
        public void onStartPage(PdfWriter writer, Document document) {
            PdfContentByte canvas = writer.getDirectContentUnder();
            canvas.setColorFill(BaseColor.LIGHT_GRAY);
            canvas.rectangle(0, 0, PageSize.A4.getWidth(), PageSize.A4.getHeight());
            canvas.fill();

            PdfPTable header = new PdfPTable(1);
            header.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell(new Phrase("Relatório", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
            header.addCell(cell);
            try {
                document.add(header);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }

        public void onEndPage(PdfWriter writer, Document document) {
            PdfPTable footer = new PdfPTable(1);
            footer.setWidthPercentage(100);
            PdfPCell cell = new PdfPCell(new Phrase("Página " + writer.getPageNumber(), new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL)));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(Rectangle.NO_BORDER);
            footer.addCell(cell);
            try {
                document.add(footer);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }


}