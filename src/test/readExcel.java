package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readExcel{
	
	public static void main(String[] args) throws Exception {}

	/*private static void showExcelData(List sheetData) {
		//
		// Iterates the data and print it out to the console.
		//
		for (int i = 0; i < sheetData.size(); i++) {
			List list = (List) sheetData.get(i);
			for (int j = 0; j < list.size(); j++) {
				Cell cell = (Cell) list.get(j);
				if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					System.out.print(cell.getNumericCellValue());
				} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					System.out.print(cell.getRichStringCellValue());
				} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
					System.out.print(cell.getBooleanCellValue());
				}
				if (j < list.size() - 1) {
					System.out.print(", ");
				}
			}
			System.out.println("");
		}
	}*/
	
	public static ArrayList getSheetDataAsArrayList(String filePath) throws IOException {
		
		ArrayList sheetData = new ArrayList();
		FileInputStream fis = null;
		
		try {
			//
			// Create a FileInputStream that will be use to read the excel file.
			//
			fis = new FileInputStream(filePath);
			
			// TODO: Implement file validation and logic here
			String extension = filePath.substring(filePath.lastIndexOf(".") + 1, filePath.length());
			if(extension.equals("xls")) {
				sheetData = getHssfDataAsArrayList(fis);
			}
			else if(extension.equals("xlsx")) {
				sheetData = getXssfDataAsArrayList(fis);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
		
		return sheetData;
	}
	
	private static ArrayList getXssfDataAsArrayList(FileInputStream fis) {
		
		// Create an ArrayList to store the data read from excel sheet.
		ArrayList sheetData = new ArrayList();
		
		try {
			//
			// Create an excel workbook from the file system.
			//
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			//
			// Get the first sheet on the workbook.
			//
			XSSFSheet sheet = workbook.getSheetAt(0);
		
			//
			// When we have a sheet object in hand we can iterator on
			// each sheet's rows and on each row's cells. We store the
			// data read on an ArrayList so that we can printed the
			// content of the excel to the console.
			//
			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {
				XSSFRow row = (XSSFRow) rows.next();
				Iterator cells = row.cellIterator();
			
				ArrayList data = new ArrayList();
				while (cells.hasNext()) {
					XSSFCell cell = (XSSFCell) cells.next();
					data.add(cell);
				}
			
				sheetData.add(data);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return sheetData;
	}
	
	private static ArrayList getHssfDataAsArrayList(FileInputStream fis) {
		
		//
		// Create an ArrayList to store the data read from excel sheet.
		//
		ArrayList sheetData = new ArrayList();
		
		try {
			//
			// Create an excel workbook from the file system.
			//
			HSSFWorkbook workbook = new HSSFWorkbook(fis);
			//
			// Get the first sheet on the workbook.
			//
			HSSFSheet sheet = workbook.getSheetAt(0);
		
			//
			// When we have a sheet object in hand we can iterator on
			// each sheet's rows and on each row's cells. We store the
			// data read on an ArrayList so that we can printed the
			// content of the excel to the console.
			//
			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {
				HSSFRow row = (HSSFRow) rows.next();
				Iterator cells = row.cellIterator();
			
				ArrayList data = new ArrayList();
				while (cells.hasNext()) {
					HSSFCell cell = (HSSFCell) cells.next();
					data.add(cell);
				}
			
				sheetData.add(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		  }
		
		return sheetData;
	}
	
}