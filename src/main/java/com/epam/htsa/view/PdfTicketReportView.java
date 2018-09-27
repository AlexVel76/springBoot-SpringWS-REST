package com.epam.htsa.view;

import com.epam.htsa.entity.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfLine;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Service(value = "pdfTicketReportView")
public class PdfTicketReportView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        PdfPTable table = new PdfPTable(6);
        table.addCell("ID");
        table.addCell("Seats");
        table.addCell("User");
        table.addCell("Event");
        table.addCell("Auditorium");
        table.addCell("Date Time");

        List<Ticket> tickets = (List<Ticket>) model.get("tickets");
        if (tickets != null) {
            for (Ticket ticket : tickets) {
                table.addCell(ticket.getId().toString());
                table.addCell(String.valueOf(ticket.getSeat()));
                table.addCell(String.valueOf(ticket.getUser().getFirstName()));
                table.addCell(String.valueOf(ticket.getEvent().getName()));
                table.addCell(String.valueOf(ticket.getEvent().getAuditorium().getName()));
                table.addCell(String.valueOf(ticket.getDateTime()));
            }
        }
        document.add(table);
    }
}