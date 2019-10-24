package utils.bpp;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/** 
 * 
 * <p>Identifies Excel file path, file name, and sheet name</p>
 * @author tchin
 * 
 */
public class ExcelFileAndSheetIdentificationHelper {

	/**
	 * <p>Finds the Excel file to be used to extract data from
	 * The Excel file will dictate what 'doables' will run with specified associated parameters
	 * 
	 * @param filePath file path of xlsx
	 * @param fileName file name of xlsx
	 * @param sheetName sheet name of xlsx
	 * @return excelSheet excel xlsx
	 * @throws IOException exits runtime if test configuration is not correct 
	 * </p>
	 */
	public Sheet readExcel(String filePath, String fileName, String sheetName) throws IOException{
		
		// Create a object of File class to open xlsx file
		File file = new File (filePath , fileName);
		
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook excelWorkbook;
		excelWorkbook = new XSSFWorkbook(inputStream);
		inputStream.close();
		
		// Read sheet inside the workbook by its name
        return excelWorkbook.getSheet(sheetName);
	}

	public ArrayList<String> getAllSheets(String filePath, String fileName) throws IOException {

		// Create a object of File class to open xlsx file
		File file = new File(filePath, fileName);

		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook excelWorkbook;
		excelWorkbook = new XSSFWorkbook(inputStream);
		inputStream.close();

		ArrayList<String> sheetList = new ArrayList<>();

		for (int sheetIndex = 0; sheetIndex < excelWorkbook.getNumberOfSheets(); sheetIndex++) {
			sheetList.add(excelWorkbook.getSheetAt(sheetIndex).getSheetName());
		}

		return sheetList;

	}
}
