package com.nks.entity;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nks.rest.TestingRestController;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BusinessToExcel implements Serializable {

	private static final long serialVersionUID = 1L;

	private XSSFWorkbook xssfWorkbook;
	private XSSFSheet xssfSheet;
	
	private List<Business> business;

	private final static Logger log = LoggerFactory.getLogger(TestingRestController.class);

	public BusinessToExcel(List<Business> business) {
		this.business = business;
		xssfWorkbook = new XSSFWorkbook();
		xssfSheet = xssfWorkbook.createSheet("Business Users");
	}

	public void tableHeader() {

			Row row = xssfSheet.createRow(0);
			Cell cell = row.createCell(0);
			cell.setCellValue("ID");
			cell = row.createCell(1);
			cell.setCellValue("Person Name");
			cell = row.createCell(2);
			cell.setCellValue("Business Name");
			cell = row.createCell(3);
			cell.setCellValue("Business GST Number");
	}

	public void tableData() {
		int count =1;
		for (Business busi : business) {
			Row row = xssfSheet.createRow(count);
			Cell cell = row.createCell(0);
			cell.setCellValue(String.valueOf(busi.getId()));
			cell = row.createCell(1);
			cell.setCellValue(busi.getPerson_name());
			cell = row.createCell(2);
			cell.setCellValue(busi.getBusiness_name());
			cell = row.createCell(3);
			cell.setCellValue(busi.getBusiness_gst_number());
			count ++;
		}
	}

	public void export(HttpServletResponse httpServletResponse) throws DocumentException, IOException {
		tableHeader();
		tableData();
		ServletOutputStream outputStream = httpServletResponse.getOutputStream();
		xssfWorkbook.write(outputStream);
		xssfWorkbook.close();
		outputStream.close();
		
	}
}
