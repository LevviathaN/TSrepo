package ui.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

/**
 * Class created to handle high-level operations with Google Spreadsheets
 * @author ruslanlevytskyi
 * */
public class SpreadsheetsHelper {

    //A map of literal and numeric ordinal numbers of spreadsheet columns
    private static Map<Integer,String> columns;
    private static Map<String,String> squads;
    public static void initializeColumns() {
        squads = new HashMap<>();
        squads.put("ProductFactory","Terra");
        squads.put("Salesforce","Titan");
        squads.put("DirectApps","Venus");
        columns = new HashMap<>();
        columns.put(0,"A");
        columns.put(1,"B");
        columns.put(2,"C");
        columns.put(3,"D");
        columns.put(4,"E");
        columns.put(5,"F");
        columns.put(6,"G");
        columns.put(7,"H");
        columns.put(8,"I");
        columns.put(9,"J");
        columns.put(10,"K");
        columns.put(11,"L");
        columns.put(12,"M");
        columns.put(13,"N");
        columns.put(14,"O");
        columns.put(15,"P");
        columns.put(16,"Q");
        columns.put(17,"R");
        columns.put(18,"S");
        columns.put(19,"T");
        columns.put(20,"U");
        columns.put(21,"V");
        columns.put(22,"W");
        columns.put(23,"X");
        columns.put(24,"Y");
        columns.put(25,"Z");
        columns.put(26,"AA");
        columns.put(27,"AB");
        columns.put(28,"AC");
        columns.put(29,"AD");
        columns.put(30,"AE");
        columns.put(31,"AF");
        columns.put(32,"AG");
        columns.put(33,"AH");
        columns.put(34,"AI");
        columns.put(35,"AJ");
        columns.put(36,"AK");
        columns.put(37,"AL");
        columns.put(38,"AM");
        columns.put(39,"AN");
        columns.put(40,"AO");
        columns.put(41,"AP");
        columns.put(42,"AQ");
        columns.put(43,"AR");
        columns.put(44,"AS");
        columns.put(45,"AT");
        columns.put(46,"AU");
        columns.put(47,"AV");
        columns.put(48,"AW");
        columns.put(49,"AX");
        columns.put(50,"AY");
        columns.put(51,"AZ");
    }

    public static void updateRegressionResults(String application) {
        //getting all regression dates
        List<List<Object>> values = SpreadsheetsAPI.getValues(squads.get(application) + "!A1:1","1tlA52m--hmgHW5hm5xLmraHiokUWsS8vLgpPfgqM0C0");
        int qtt = values.get(0).size();
        String last = values.get(0).get(qtt-1).toString();
        System.out.println(last);

        //getting all tests from the spreadsheet
        List<List<Object>> testNamesColumn = SpreadsheetsAPI.getValues(squads.get(application) + "!B2:B","1tlA52m--hmgHW5hm5xLmraHiokUWsS8vLgpPfgqM0C0");
        List<String> testNames = new ArrayList<>();
        for (List<Object> row : testNamesColumn) {
            if (!row.isEmpty()) {
                testNames.add(row.get(0).toString());
            }
        }

        //getting report date
        String filePath = Reporter.getReportPath().toString().concat("/report.html");
        String[] path = filePath.split("/report");
        String fileName = path[path.length - 2];
        String date = fileName.substring(1,11);
        String[] dateFrag = date.split("-");
        String correctDate = dateFrag[2] + "." + dateFrag[1] + "." + dateFrag[0];

        //checking actuality of tests in spreadsheet
        Map<String,String> testResults = RetryAnalyzer.passMap;
        List<List<Object>> valuesToUpdate = new ArrayList<>();
        valuesToUpdate.add(Arrays.asList(correctDate));
        List<String> availableScenarios = getAvailableTests(application);
        List<String> excessTestsSpreadsh = new ArrayList<>();
        for (String name : testNames) {
            if (testResults.containsKey(name)) {
                valuesToUpdate.add(Arrays.asList(testResults.get(name).substring(0, 1).toUpperCase() + testResults.get(name).substring(1) + "ed"));
                testResults.remove(name);
            } else {
                valuesToUpdate.add(Arrays.asList("N/A"));
            }
            if (availableScenarios.contains(name)) {
                availableScenarios.remove(name);
            } else {
                excessTestsSpreadsh.add(name);
            }
        }

        //Console log about actuality of test cases in spreadsheet
        if (!excessTestsSpreadsh.isEmpty()) {
            System.out.println("\n_________________________________________________________________");
            System.out.println("Following tests availale in framework, but absent in spreadsheet:\n");
            for (String name : availableScenarios) {
                System.out.println(name);
            }
            System.out.println("_________________________________________________________________");
        }

        if (!excessTestsSpreadsh.isEmpty()) {
            System.out.println("\n_________________________________________________________________");
            System.out.println("Following tests availale in spreadsheet, but absent in framework:\n");
            for (String name : excessTestsSpreadsh) {
                System.out.println(name);
            }
            System.out.println("_________________________________________________________________");
        }

        if (!testResults.isEmpty()) {
            System.out.println("\n_________________________________________________________________");
            System.out.println("Following test results was not updated due to absence in spreadsheet:\n");
            for (String name : testResults.keySet()) {
                System.out.print(name);
                System.out.println(" - " + testResults.get(name));
            }
            System.out.println("_________________________________________________________________");
        }

        //writing date and results right after last regression column in spreadsheet
        SpreadsheetsAPI.updateTable(squads.get(application) + "!" + columns.get(qtt) + "1","1tlA52m--hmgHW5hm5xLmraHiokUWsS8vLgpPfgqM0C0", valuesToUpdate);
    }

    public static void manageSpreadsheet(String application) {
        initializeColumns();
        SpreadsheetsAPI.initiate();
        updateRegressionResults(application);
    }

    /**
     * Get list of all test cases created in framework
     * todo currently only for PF
     * @return list of steps of reusable scenario with specified name
     * @author Ruslan Levytskyi
     */
    private static synchronized List<String> getAvailableTests(String application) {

        List<String> availableReusableStepsList = new ArrayList<>();

        try {
            File inputFile = new File("src/main/resources/data/bpp/PFScenarios.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            availableReusableStepsList = new ArrayList<>();

            Node reusablesNode = doc.getElementsByTagName("scenarios").item(0);
            Element reusablesElement = (Element) reusablesNode;

            NodeList reusablesList = reusablesElement.getElementsByTagName("scenario");
            for (int i = 0; i < reusablesList.getLength(); i++) {
                Node reusableNode = reusablesList.item(i);
                Element reusableElement = (Element) reusableNode;
                NodeList tagsList = reusableElement.getElementsByTagName("tag");
                boolean correctApp = false;
                for (int j = 0; j < tagsList.getLength(); j++) {
                    Node tag = tagsList.item(j);
                    correctApp = tag.getTextContent().contains(application) || correctApp;
                }
                if (correctApp) availableReusableStepsList.add(reusableElement.getAttribute("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return availableReusableStepsList;
    }
}
