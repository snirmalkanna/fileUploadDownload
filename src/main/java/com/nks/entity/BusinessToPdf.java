package com.nks.entity;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nks.rest.TestingRestController;

public class BusinessToPdf implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Business> business;

	private final static Logger log = LoggerFactory.getLogger(TestingRestController.class);

	public BusinessToPdf(List<Business> business) {
		this.business = business;
	}

	public void tableHeader(PdfPTable pdfPTable) {

		Stream.of("ID", "Person Name", "Business Name", "Business GST Number").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			pdfPTable.addCell(header);
		});
	}

	public void tableData(PdfPTable pdfPTable) {
		for (Business busi : business) {
			pdfPTable.addCell(String.valueOf(busi.getId()));
			pdfPTable.addCell(String.valueOf(busi.getPerson_name()));
			pdfPTable.addCell(String.valueOf(busi.getBusiness_name()));
			pdfPTable.addCell(String.valueOf(busi.getBusiness_gst_number()));
		}
	}

	public void export(HttpServletResponse httpServletResponse) throws DocumentException, IOException {
		log.info("Export Started for PDF generation.");
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, httpServletResponse.getOutputStream());
		document.open();
		Font font = FontFactory.getFont(FontFactory.COURIER);
		font.setColor(Color.DARK_GRAY);
		font.setSize(14);
		//Image imgSoc = Image.getInstance("C:\\...\\Logo.jpg");
        //imgSoc.scaleToFit(110,110);
        //imgSoc.setAbsolutePosition(390, 720);

		Paragraph paragraph = new Paragraph("List of Business Users", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(paragraph);

		PdfPTable pdfPTable = new PdfPTable(4);
		pdfPTable.setWidthPercentage(100);
		pdfPTable.setSpacingBefore(15);
		tableHeader(pdfPTable);
		tableData(pdfPTable);
		document.add(pdfPTable);
		document.close();
	}
}
