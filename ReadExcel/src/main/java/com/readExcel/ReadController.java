package com.readExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadController {

	@GetMapping(value = "/excelNormal")
	public ResponseEntity<?> get() {
		try (FileInputStream file = new FileInputStream(new File("C:\\Users\\raghu\\Excel\\Books Of Mine.xlsx"))) {
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0);
			StringBuilder table = new StringBuilder();

			for (Row row : sheet) {
				for (Cell cell : row) {
					table.append(cell.toString()).append("\t" + "\t" + "\t");
				}
				table.append("\n" + "\n");
			}

			workbook.close();
			return ResponseEntity.ok(table.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading Excel file");
		}
	}

	@GetMapping(value = "/excel", produces = MediaType.TEXT_HTML_VALUE)
	public ResponseEntity<String> getExcel() {
		try (FileInputStream file = new FileInputStream(new File("C:\\Users\\raghu\\Excel\\Books Of Mine.xlsx"))) {
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0);
			StringBuilder table = new StringBuilder();

			table.append("<table border=\"1\">");
			for (Row row : sheet) {
				table.append("<tr>");
				for (Cell cell : row) {
					table.append("<td>").append(cell.toString()).append("</td>");
				}
				table.append("</tr>");
			}
			table.append("</table>");

			workbook.close();
			return ResponseEntity.ok(table.toString());
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading Excel file");
		}
	}
}