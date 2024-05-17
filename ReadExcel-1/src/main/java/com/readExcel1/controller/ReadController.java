package com.readExcel1.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadController {

//	public List<List<String>> get() throws IOException {
//		String filepath="C:\\Users\\raghu\\Excel\\Books Of Mine.xlsx";
//		return getTable(filepath);
//	}

	@GetMapping("/table")
	public List<List<String>> getTable(String filepath) throws IOException {
		filepath = "C:\\Users\\raghu\\Excel\\Books Of Mine.xlsx";
		List<List<String>> tab = new ArrayList<>();

		FileInputStream file = new FileInputStream(filepath);

		XSSFWorkbook wb = new XSSFWorkbook(file);

		XSSFSheet sh = wb.getSheetAt(0);

		for (Row row : sh) {
			List<String> rowData = new ArrayList<>();
			for (Cell cell : row) {
				rowData.add(cell.toString());
			}
			tab.add(rowData);
		}
		return tab;

	}

}
