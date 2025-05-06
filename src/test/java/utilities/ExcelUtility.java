package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	String path;
	FileInputStream fis;
	FileOutputStream fos;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;

	public ExcelUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetname) throws IOException {

		fis = new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(sheetname);
		int row = sheet.getLastRowNum();

		wb.close();
		fis.close();
		return row;
	}

	public int getCellCount(String sheetname, int rownum) throws IOException {
		fis = new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(sheetname);
		row = sheet.getRow(rownum);
		int cell = row.getLastCellNum();

		wb.close();
		fis.close();
		return cell;
	}

	public String getCellData(String sheetname, int rownum, int cellnum) throws IOException {
		fis = new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		sheet = wb.getSheet(sheetname);
		row = sheet.getRow(rownum);
		cell = row.getCell(cellnum);
		String data;

		DataFormatter df = new DataFormatter();
		try {
			data = df.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}

		wb.close();
		fis.close();
		return data;
	}

	public void setCellData(String sheetname, int rownum, int cellnum, String data) throws IOException {
		File xfile = new File(path);

		if (!xfile.exists()) {
			fos = new FileOutputStream(path);
			wb = new XSSFWorkbook();
			wb.write(fos);
		}

		fis = new FileInputStream(path);
		wb = new XSSFWorkbook(fis);

		if (wb.getSheetIndex(sheetname) == -1) {
			wb.createSheet(sheetname);
		}
		sheet = wb.getSheet(sheetname);

		if (sheet.getRow(rownum) == null) {
			sheet.createRow(rownum);
		}
		row = sheet.getRow(rownum);

		cell = row.getCell(cellnum);
		cell.setCellValue(data);
		fos = new FileOutputStream(path);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();

	}

}
