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
    private static ReadExcel<MSOffice> readSharePointExcelWorkbook;
    private static MSOffice officeObjectForTestSheet;
    private static String sharepointApplicationId;


    public ExcelGetDataHelper(String testSpreadsheet) {
        PropertiesHelper propertiesHelper = new PropertiesHelper();
        sharepointRefreshToken = propertiesHelper.getProperties().getProperty("sharepoint_refresh_token");
        ExcelGetDataHelper.testSpreadsheet = testSpreadsheet;
        sharepointApplicationId = propertiesHelper.getProperties().getProperty("sharepoint_application_id");
    }


    /**
     * <p>
     * Gets data from excel spreadsheet that determines in top down order which
     * test is run and what business processes belong to the test
     * </p>
     * <p>
     * Column A Run Y or N
     * </p>
     * <p>
     * Column B Test Name and description
     * </p>
     * <p>
     * Column C On Fail, EndTest EndTestCase Continue,
     * </p>
     * <p>
     * Column D Business Process
     * </p>
     * <p>
     * Column E Action
     * </p>
     * <p>
     * Column F Logical Name
     * </p>
     * <p>
     * Column G Value
     * </p>
     *
     * @param testSpreadsheet test spreadsheet to be read
     * @param testSheet       test sheet from test spreadsheet to be read
     * @return String[][] excel data from test spreadsheet
     * @throws IOException Signals that an I/O exception of some sort has occurred. This
     *                     class is the general class of exceptions produced by failed
     *                     or interrupted I/O operations.
     */
    public String[][] getDataFromExcelTestSheet(String testSpreadsheet, String testSheet) throws IOException {

        if (System.getProperties().containsKey("sharepoint") && System.getProperty("sharepoint").toUpperCase().equals("TRUE")) {

            List<String> listOfSheetsToIgnore = Arrays.asList("TestPlan");

            MSOffice msOffice = new MSOffice(
                    ExcelLocation.SHAREPOINT,
                    sharepointApplicationId,
                    "",
                    sharepointRefreshToken);
            ReadExcel<MSOffice> readExcel = new ReadExcel<>(msOffice, new String[]{testSpreadsheet});

            return readExcel.getExcelDataInStringArray(msOffice, "UsedRange", listOfSheetsToIgnore);
        } else {
            String filePath = new File(PreProcessFiles.TEST_INPUT_FILES_FOLDER_PATH, testSpreadsheet).getAbsolutePath();

            LocalExcel localExcel = new LocalExcel(filePath);
            ReadExcel<LocalExcel> readExcel = new ReadExcel<>(localExcel, new String[]{testSpreadsheet});

            return readExcel.getExcelDataInStringArray(localExcel,
                    "UsedRange",
                    Arrays.asList("TestPlan", "BP_LIST"));
        }
    }

    /**
     * <p>
     * Reads the Excel for Business Process definitions.
     * <p>
     * First row is reserved for headers. Column A: Business Process, Column B:
     * Action, Column C: Logical Name, Column D: Element Location
     * </p>
     *
     * @return String[][] excel business process data
     * @throws IOException ends test execution if incorrect data is read from business
     *                     process xlsx
     */
    public String[][] getDataFromExcelBusinessProcessSheet() throws IOException {

        if (System.getProperties().containsKey("sharepoint") && System.getProperty("sharepoint").toUpperCase().equals("TRUE")) {

            MSOffice msOffice = new MSOffice(
                    ExcelLocation.SHAREPOINT,
                    sharepointApplicationId,
                    "",
                    sharepointRefreshToken);
            ReadExcel<MSOffice> readExcel = new ReadExcel<>(msOffice, new String[]{"BusinessProcess.xlsx"}, Arrays.asList("ActionsDescription"));

            return readExcel.getExcelDataInStringArray(msOffice,
                    "UsedRange",
                    Arrays.asList("ActionsDescription"));

        } else {
            String filePath = new File(PreProcessFiles.TEST_INPUT_FILES_FOLDER_PATH, "BusinessProcess.xlsx").getAbsolutePath();

            LocalExcel localExcel = new LocalExcel(filePath);
            ReadExcel<LocalExcel> readExcel = new ReadExcel<>(localExcel, new String[]{"BusinessProcess.xlsx"});


            return readExcel.getExcelDataInStringArray(localExcel, "UsedRange", Arrays.asList("ActionsDescription"));
        }

    }

    /**
     * @param folder - folder path where properties file exists
     * @return - returns the list off arrays. Each array contains
     * name, Property name, Descriptor (locator), Parent and Conditional Event
     * @throws IOException failed input output execution
     */
    public List<ArrayList<String>> getDataFromPropertiesSheet(String folder) throws IOException {

        if (System.getProperties().containsKey("sharepoint") && System.getProperty("sharepoint").toUpperCase().equals("TRUE")) {

            MSOffice msOffice = new MSOffice(
                    ExcelLocation.SHAREPOINT,
                    sharepointApplicationId,
                    "",
                    sharepointRefreshToken);
            ReadExcel<MSOffice> readExcel = new ReadExcel<>(msOffice, new String[]{"LocatorPropertiesUI.xlsx"});

            return readExcel.getExcelDataInStringFormat(msOffice,
                    "Properties",
                    "UsedRange");
        } else {
            String filePath = new File(folder, "LocatorPropertiesUI.xlsx").getAbsolutePath();
            LocalExcel localExcel = new LocalExcel(filePath);
            ReadExcel<LocalExcel> readExcel = new ReadExcel<>(localExcel, new String[]{"LocatorPropertiesUI.xlsx"});

            return readExcel.getExcelDataInStringFormat(localExcel, "Properties", "USEDRANGE");
        }
    }


    /**
     * @return Map of Sheetname and Tests in them
     * @throws IOException
     */
    public static Map<String, List<String>> getMapOfSheetAndTests(boolean considerIteration) throws Exception {

        Map<String, List<String>> sheetAndTestMap = new LinkedHashMap<>();

        if (System.getProperties().containsKey("sharepoint") && System.getProperty("sharepoint").toUpperCase().equals("TRUE")) {

            MSOffice msOffice = new MSOffice(
                    ExcelLocation.SHAREPOINT,
                    sharepointApplicationId,
                    "",
                    sharepointRefreshToken);
            ReadExcel<MSOffice> readExcel = new ReadExcel<>(msOffice, new String[]{"Test.xlsx"});

            Map<String, List<ArrayList<String>>> sheetsDataMap = readExcel.getMapOfSheetsAndData(msOffice, "UsedRange", Arrays.asList("TestPlan"));

            Map<String, String> flagAndTest = new HashMap<>();

            for (Map.Entry entry : sheetsDataMap.entrySet()) {

                List<ArrayList<String>> rowsInASheet = (ArrayList) entry.getValue();

                flagAndTest = new HashMap<>();
                for (ArrayList<String> row : rowsInASheet) {

                    if (!row.get(1).trim().equals(""))
                        flagAndTest.put(row.get(1), row.get(0));
                }

                //removing the data of first row of every sheet
                flagAndTest.remove("Test");

                ArrayList<Map.Entry> list = (ArrayList) flagAndTest.entrySet().stream().filter(r -> r.getValue().contains("Y")).collect(Collectors.toList());
                List<String> listOfTests = list.stream().map(Map.Entry::getKey).collect(Collectors.toList()).stream().map(Object::toString).collect(Collectors.toList());

                sheetAndTestMap.put(entry.getKey().toString(), listOfTests);
            }

            return sheetAndTestMap;

        } else {
            //Read local excel sheets
            String[][] object = null;
            ExcelFileAndSheetIdentificationHelper file = new ExcelFileAndSheetIdentificationHelper();

            ArrayList<String> sheetsList = file.getAllSheets(PreProcessFiles.TEST_INPUT_FILES_FOLDER_PATH, testSpreadsheet);

            for (String sheet : sheetsList) {

                List<String> testNamesList;

                if (!sheet.equalsIgnoreCase("TestPlan")) {

                    Sheet excelSheet = file.readExcel(PreProcessFiles.TEST_INPUT_FILES_FOLDER_PATH, testSpreadsheet, sheet);
                    int rowsCount = excelSheet.getLastRowNum() + 1;
                    testNamesList = new ArrayList<>();

                    for (int i = 1; i < rowsCount; i++) {
                        Cell testName = excelSheet.getRow(i).getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                        Cell runFlag = excelSheet.getRow(i).getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                        if (testName != null && !testName.getStringCellValue().startsWith(" ") && runFlag.getStringCellValue().toUpperCase().contains("Y")) {
                            String iterationsNumber = runFlag.getStringCellValue().toUpperCase().replaceAll("[^0-9]", "");

                            if (considerIteration && !iterationsNumber.isEmpty() && Character.isDigit(iterationsNumber.toCharArray()[0])) {
                                for (int it = 1; it <= Integer.parseInt(iterationsNumber); it++) {
                                    testNamesList.add(testName.getStringCellValue().trim() + "_" + it);
                                }
                            } else {
                                testNamesList.add(testName.getStringCellValue().trim());
                            }
                        }
                    }
                    sheetAndTestMap.put(sheet.trim(), testNamesList);
                }
            }
        }

        return sheetAndTestMap;
    }
}
