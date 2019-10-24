package utils.bpp_old;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static utils.bpp_old.PreProcessFiles.TEST_INPUT_FILES_FOLDER_PATH;

/**
 *
 * The class helps in converting the .xlsx file to .csv
 * 
 * @author sarora
 *
 */
public class ExcelToCsvHelper {

	private static List<List<XSSFCell>> cellGrid;
	//protected static final Logger logger = Logger.getLogger("NoahLogger");

	/**
	 * <p>
	 * This function helps to convert the .xlsx file to .csv format
	 * </p>
	 * 
	 * @param strFileName
	 *            file name of the xlsx
	 * @throws IOException
	 *             Signals that an I/O exception of some sort has occurred. This
	 *             class is the general class of exceptions produced by failed
	 *             or interrupted I/O operations.
	 */
	public static void convertExcelToCsv(String strFileName) throws IOException {
		try {
			cellGrid = new ArrayList<>();

			// Create a object of File class to open xlsx file
			File file = new File(TEST_INPUT_FILES_FOLDER_PATH, strFileName);

			FileInputStream myInput = new FileInputStream(file);
			Workbook excelWorkbook;
			excelWorkbook = new XSSFWorkbook(myInput);
			Sheet excelSheet = excelWorkbook.getSheetAt(0);
			Iterator<?> rowIter = excelSheet.rowIterator();

			while (rowIter.hasNext()) {
				XSSFRow myRow = (XSSFRow) rowIter.next();
				Iterator<?> cellIter = myRow.cellIterator();
				List<XSSFCell> cellRowList = new ArrayList<>();
				while (cellIter.hasNext()) {
					XSSFCell myCell = (XSSFCell) cellIter.next();
					cellRowList.add(myCell);
				}
				cellGrid.add(cellRowList);
			}
		} catch (FileNotFoundException e) {
			NoahLogManager.getLogger().error("FAILURE: " + e);
		} catch (NullPointerException e) {
			NoahLogManager.getLogger().error("FAILURE: " + e);
		}

		File file = new File(Reporter.getReportPath() + "/",
				strFileName.substring(0, strFileName.indexOf(".")) + ".csv");
		PrintStream stream = new PrintStream(file);
		for (int i = 0; i < cellGrid.size(); i++) {
			List<XSSFCell> cellRowList = cellGrid.get(i);
			for (int j = 0; j < cellRowList.size(); j++) {
				XSSFCell myCell = cellRowList.get(j);
				String stringCellValue = myCell.toString();
				stream.print(stringCellValue + ",");
			}
			stream.println("");
		}
	}

}
