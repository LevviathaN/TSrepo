package ui.utils.bpp;

import com.autmatika.ExcelLocation;
import com.autmatika.LocalExcel;
import com.autmatika.MSOffice;
import com.autmatika.ReadExcel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Will store the information in two dimensional object
 *
 * @author tchin
 */
public class ExcelGetDataHelper {

    private static String testSpreadsheet;
    private static String sharepointRefreshToken;
    private static MSOffice officeObjectForTestSheet;


    public ExcelGetDataHelper(String testSpreadsheet) {
        PropertiesHelper propertiesHelper = new PropertiesHelper();
        ExcelGetDataHelper.testSpreadsheet = testSpreadsheet;
    }

    /**
     * @param folder - folder path where properties file exists
     * @return - returns the list off arrays. Each array contains
     * name, Property name, Descriptor (locator), Parent and Conditional Event
     * @throws IOException failed input output execution
     */
    public List<ArrayList<String>> getDataFromPropertiesSheet(String folder) throws IOException {


            String filePath = new File(folder, "LocatorPropertiesUI.xlsx").getAbsolutePath();
            LocalExcel localExcel = new LocalExcel(filePath);
            ReadExcel<LocalExcel> readExcel = new ReadExcel<>(localExcel, new String[]{"LocatorPropertiesUI.xlsx"});

            return readExcel.getExcelDataInStringFormat(localExcel, "Properties", "USEDRANGE");

    }


    /**
     * @return Map of Sheetname and Tests in them
     * @throws IOException
     */
}
