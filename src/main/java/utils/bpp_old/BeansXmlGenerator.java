//package utils.bpp_old;
//
//import utils.bpp_old.DoableBean;
//import org.w3c.dom.Attr;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import java.io.File;
//import java.io.IOException;
//import java.util.*;
//
//import static utils.bpp_old.NoahLogManager.getLogger;
//
///**
// * <p>Creates a bean.xml</p>
// *
// * @author tchin
// */
//public class BeansXmlGenerator {
//    String[][] excelBusinessProcessTable = null;
//
//    //protected static final Logger logger = Logger.getLogger("NoahLogger");
//
//    /**
//     * <p>Creates a bean xml that will generate based on user's input from excel
//     * file. The order of 'doables' executed and it's associated parameters will
//     * be created in the xml under the Execute Doables
//     * Row 0 is ignored for headers. </p>
//     *
//     * @param testSpreadsheet the test spreadsheet identified by the tester
//     * @param testSheet       the test sheet from the test spreadsheet
//     * @throws IOException ends test execution if invalid test criteria is identified
//     */
//    public void xmlGenerate(String testSpreadsheet, String testSheet) throws IOException {
//
//        try {
//
//            // Read from excel file, doable into ref bean attribute
//            ExcelGetDataHelper excelData = new ExcelGetDataHelper(testSpreadsheet);
//
//            // Prepare container for Business Processes
//            // Read Business Processes excel file one time and reuse its content for each sheet with tests
//            // whenever it is needed.
//            if (excelBusinessProcessTable == null) {
//                getLogger().info("Reading data from Business Process file");
//                excelBusinessProcessTable = excelData.getDataFromExcelBusinessProcessSheet();
//                logginBusinessProcessExcelRead();
//            }
//
//            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
//
//            // root elements
//            Document doc = docBuilder.newDocument();
//            Element beans = doc.createElement("beans");
//
//            Attr schemaLocation = doc.createAttribute("xsi:schemaLocation");
//            Attr xmlnsXsi = doc.createAttribute("xmlns:xsi");
//            Attr xmlns = doc.createAttribute("xmlns");
//
//            beans.setAttributeNode(schemaLocation);
//            beans.setAttributeNode(xmlnsXsi);
//            beans.setAttributeNode(xmlns);
//
//            schemaLocation.setValue(
//                    "http://www.springframework.org/schema/beans   http://www.springframework.org/schema/beans/spring-beans-4.0.xsd");
//            xmlnsXsi.setValue("http://www.w3.org/2001/XMLSchema-instance");
//            xmlns.setValue("http://www.springframework.org/schema/beans");
//
//            doc.appendChild(beans);
//
//            // RUNNER DOABLE - STATIC
//
//            // bean element for main Executable
//            Element bean = doc.createElement("bean");
//            // set attributes to the bean Executable
//            Attr beanId = doc.createAttribute("id");
//            Attr beanClass = doc.createAttribute("class");
//
//            bean.setAttributeNode(beanClass);
//            bean.setAttributeNode(beanId);
//
//            beanClass.setValue("com.apolloglobal.testing.api.impl.ExecuteDoable");
//            beanId.setValue("ExecuteDoable");
//
//            beans.appendChild(bean);
//
//            // elements of Element bean (tag - property)
//            Element property = doc.createElement("property");
//            Attr propertyName = doc.createAttribute("name");
//            property.setAttribute("name", "doables");
//            bean.appendChild(property);
//
//            // elements of property - list
//            Element list = doc.createElement("list");
//            property.appendChild(list);
//
//            // LOOP to put all doables as bean under child tags: list
//
//
//            //excelData.getDataFromExcelTestSheet(testSpreadsheet, testSheet);
//
//            // get object into string to use in xml creation into String
//            // testDoable
//            getLogger().info("Reading data for Excel Test files");
//            String[][] excelTestTable = excelData.getDataFromExcelTestSheet(testSpreadsheet, testSheet);
//            loggingTestExcelRead(excelTestTable);
//
//            //BeansXmlGenerator beanList = new BeansXmlGenerator();
//
//            // This map will contain all of the business processes
//            Map<String, List<String>> map = new HashMap<String, List<String>>();
//
//            // BEGIN looping through entire table to capture key value pairs in
//            List<DoableBean> doables = null;
//            Map<String, List<DoableBean>> doablesMap = new HashMap<String, List<DoableBean>>();
//            boolean newBusinessProcess = true;
//            String currentDoable = null;
//            // We start with i = 1 to account for the header row
//            int processTableBusinessProcessCol = 0;
//            // Col1 = Business Process
//            // Col2 = OnFail
//            // Col3 = Action
//            // Col4 = Logical Name/Element Location
//            // Col5 = Parameter
//            for (int i = 1; i < excelBusinessProcessTable.length; i++) {
//                if (newBusinessProcess) {
//                    currentDoable = excelBusinessProcessTable[i][processTableBusinessProcessCol].trim();
//                    doables = new ArrayList<DoableBean>();
//                    newBusinessProcess = false;
//                }
//                DoableBean db = new DoableBean(excelBusinessProcessTable[i][1], excelBusinessProcessTable[i][2],
//                        excelBusinessProcessTable[i][3], excelBusinessProcessTable[i][4],
//                        excelBusinessProcessTable[i][5]);
//                doables.add(db);
//                // Check if next row exists, and the business process cell is
//                // not empty
//                if (i + 1 < excelBusinessProcessTable.length
//                        && !excelBusinessProcessTable[i + 1][processTableBusinessProcessCol].isEmpty()) {
//                    newBusinessProcess = true;
//                }
//                // Check if next cell is a business process
//                if (newBusinessProcess || i + 1 == excelBusinessProcessTable.length) {
//                    doablesMap.put(currentDoable, doables);
//                }
//
//            }
//
//            // write the content into xml file
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource source = new DOMSource(doc);
//
//            // File is created
////			StreamResult result = new StreamResult(
////					new File(PropertiesHelper.getConfiguration("TestBeans", "TESTBEANS_XML")));
//
//            StreamResult result = new StreamResult(
//                    new File("TestDoablesBeans.xml"));
//            // int doablesColumn from Test Spreadsheet
//            int doablesColumn = 3;
//            int testColumn = 1;
//            int runColumn = 0;
//            int businessProcessColumn = 3;
//            String test = "";
//            String run = "";
//            int tempStartRowOfTest = 0;
//            boolean firstIteration = true;
//            boolean runTest = false;
//            boolean lastBusinessProcessInTest = false;
//            int iteration = 0;
//            int tagColumn = 4;
//            String tagValue = "";
//            String sheetName = "";
//
//            Map<String, List<String>> sheetAndTestsMap = ExcelGetDataHelper.getMapOfSheetAndTests(false);
//
//            // elements of list - bean
//            getLogger().debug("EXCEL TEST TABLE LENGTH = " + excelTestTable.length);
//
//            for (int i = 1; i < excelTestTable.length; ) {
//
//                String[] temp = excelTestTable[i];
//
//                if (doablesMap.containsKey(temp[doablesColumn].trim())) {
//                    doables = doablesMap
//                            .get(temp[doablesColumn].trim());
//                }
//
//                // Check if there is a new test then assign test and catalog to
//                // run or not
//                if (!(excelTestTable[i][testColumn].length() == 0)) {
//                    test = excelTestTable[i][testColumn];
//                    run = excelTestTable[i][runColumn];
//                    tagValue = excelTestTable[i][tagColumn];//
//
//                    if (run.contains("Y")) {
//                        sheetName = getSheetName(sheetAndTestsMap, test);
//                    } else {
//                        sheetName = "";
//                    }
//                }
//
//                // Test sheet can have in the run column: blank, Y (run), N
//                // (skip), Y# (# denotes how many times test will run)
//                String testStatus = "Test In Process";
//
//                if (excelTestTable[i][runColumn].length() > 0
//                        && excelTestTable[i][runColumn].substring(0, 1).equalsIgnoreCase("Y")) {
//                    testStatus = "Start";
//                }
//
//                if (excelTestTable[i][runColumn].length() > 0
//                        && excelTestTable[i][runColumn].substring(0, 1).equalsIgnoreCase("Y")
//                        && firstIteration) {
//                    runTest = true;
//                    firstIteration = false;
//                    tempStartRowOfTest = i;
//
//                    // if there are iterations are declared after Y store
//                    // iteration
//                    if (excelTestTable[i][runColumn].trim().length() > 1) {
//                        iteration = Integer.parseInt(excelTestTable[i][runColumn].substring(1).trim());
//                    } else {
//                        iteration = 1;
//                    }
//
//                    if (getLogger().isDebugEnabled())
//                        getLogger().debug("Number of iterations for this test = " + iteration);
//
//                } else if (excelTestTable[i][runColumn].length() > 0
//                        && excelTestTable[i][runColumn].substring(0, 1).equalsIgnoreCase("N")) {
//                    runTest = false;
//                }
//
//                // determine if last business process
//                if (i + 1 < excelTestTable.length && excelTestTable[i + 1][runColumn].length() > 0
//                        || i == excelTestTable.length - 1) {
//                    lastBusinessProcessInTest = true;
//                    if (iteration == 1) {
//                        firstIteration = true;
//                    }
//                } else {
//                    lastBusinessProcessInTest = false;
//                }
//
//                String businessProcess = excelTestTable[i][businessProcessColumn];
//
//                // create beans for i'th business process if we are running the test
//
//                Boolean flag = true;
//
//                for (String tagListItem : tagValue.split(";")) {
//
//                    if (flag && runTest && (System.getProperty("tagOrSheet").contains(sheetName) || (flag && !tagListItem.equals("") && System.getProperty("tagOrSheet").contains(tagListItem)))) {
//
//                        flag = false;
//
//                        if (getLogger().isDebugEnabled())
//                            getLogger().debug("Doable size is: " + doables.size());
//
//                        if (run.length() > 1 && !(excelTestTable[i][testColumn].length() == 0)) {
//                            test = test + "_" + iteration;
//                        }
//
//                        preprocessDoables(doables, excelTestTable, i, doables.size(), test, run, tagValue, testStatus,
//                                businessProcess, lastBusinessProcessInTest, iteration, sheetName);
//
//                        generateBeansXml(doables, docFactory, docBuilder, doc, list, iteration);
//                    }
//
//                }
//
//                i += 1;
//
//                // doables.size();
//
//                if (!firstIteration && iteration > 1 && lastBusinessProcessInTest) {
//                    i = tempStartRowOfTest;
//                    iteration--;
//                }
//            }
//
//            transformer.transform(source, result);
//
//            if (getLogger().isDebugEnabled())
//                getLogger().debug("BEANS XML created!");
//
//        } catch (ParserConfigurationException pce) {
//            pce.printStackTrace();
//        } catch (TransformerException tfe) {
//            tfe.printStackTrace();
//        } catch (Exception pce) {
//            pce.printStackTrace();
//        }
//
//    }
//
//    private void loggingTestExcelRead(String[][] excelTestTable) {
//
//        if (getLogger().isDebugEnabled())
//            getLogger().debug("This is data from Excel Test Sheet");
//
//        // Troubleshooting block TODO: delete block
//        for (int x = 0; x < excelTestTable.length; x++) {
//
//            if (getLogger().isDebugEnabled())
//                getLogger().debug("-- Row " + x + " --");
//
//            for (int y = 0; y < excelTestTable[x].length; y++) {
//                String basicTestToString = excelTestTable[x][y];
//                NoahLogManager.getLogger().debug("[" + x + "]" + "[" + y + "]: " + excelTestTable[x][y] + " ");
//            }
//        }
//    }
//
//    private void logginBusinessProcessExcelRead() {
//        // Troubleshooting block TODO: delete block
//        if (getLogger().isDebugEnabled())
//            getLogger().debug("This is data from Business Process Sheet");
//
//        for (int x = 0; x < excelBusinessProcessTable.length; x++) {
//
//            if (getLogger().isDebugEnabled())
//                getLogger().debug("-- Row " + x + " --");
//            for (int y = 0; y < excelBusinessProcessTable[x].length; y++) {
//                String basicTestToString = excelBusinessProcessTable[x][y];
//                getLogger().debug("[" + x + "]" + "[" + y + "]: " + excelBusinessProcessTable[x][y] + " ");
//            }
//        }
//        // TODO: end of delete block
//    }
//
//    /**
//     * <p>This will generate child bean under the list tag belonging to the
//     * ExecuteDoable bean. This list of beans will determine which doable will
//     * be run. This method belongs to a loop that reads the excel file.</p>
//     * <p>
//     * bean id="ExecuteDoable"
//     * class="com.apolloglobal.testing.api.impl.ExecuteDoable" property
//     * name="doables", ref bean="loginDoable", property
//     *
//     * @param doc    - xml document this is generating a reference bean for under
//     *               the list tag under parent bean tag for Execute Doable
//     * @param list   - element that is literally called list that is a tag under
//     *               the ExecuteDoable
//     * @param doable - indexing the excel front end, the business processes are fed
//     *               into this method
//     * @return beanDoable Element for xml generation
//     */
//    public Element createBeanList(Document doc, Element list, String doable) {
//        // elements of list - bean
//        Element beanDoable = doc.createElement("bean");
//        beanDoable.setAttribute("class", "com.apolloglobal.testing.api.impl." + doable.trim() + "Doable");
//        list.appendChild(beanDoable);
//
//        return beanDoable;
//    }
//
//    /**
//     * <p>This will generate child property under the bean residing within the list
//     * tag of the ExecuteDoable bean.
//     * The list of properties will determine which name value pairs will be used.
//     * This method belongs to a loop that reads the excel file row and any properties that exist
//     *
//     * @param doc                 - xml document this is generating a reference bean for under
//     *                            the list tag under parent bean tag for Execute Doable
//     * @param appendChildLocation - Element of the bean doable on the xml where we want each
//     *                            property to be a child element
//     * @param name                - the attribute for the key
//     * @param value               - the attribute for the value of the key</p>
//     */
//    public void createPropertyList(Document doc, Element appendChildLocation, String name, String value) {
//        // element of bean in list
//        Element property = doc.createElement("property");
//        property.setAttribute("name", name);
//        property.setAttribute("value", value);
//        appendChildLocation.appendChild(property);
//    }
//
//    /**
//     * <p>Build the xml with the following tags for each bean: setpId,
//     * doableOnFail, elementLocation, parameters, value.
//     *
//     * @param doables    List, DoableBean, of all doables for business process
//     * @param docFactory DocumentBuilderFactory for xml generation
//     * @param docBuilder DocumentBuilder for xml generation
//     * @param doc        Document for xml generation
//     * @param list       Element for xml generation
//     * @param iteration  integer used to indicate what itteration we are executing for a repeated test
//     *                   </p>
//     */
//    public static void generateBeansXml(List<DoableBean> doables, DocumentBuilderFactory docFactory,
//                                        DocumentBuilder docBuilder, Document doc, Element list, int iteration) {
//
//        BeansXmlGenerator beanList = new BeansXmlGenerator();
//        for (DoableBean doable : doables) {
//            // Bean
//            Element appendChildLocation = beanList.createBeanList(doc, list, doable.getUiAction());
//
//            // Corresponding properties
//            beanList.createPropertyList(doc, appendChildLocation, "stepId", doable.getStepId());
//            beanList.createPropertyList(doc, appendChildLocation, "doableOnFail", doable.getDoableOnFail());
//            beanList.createPropertyList(doc, appendChildLocation, "elementLocation", doable.getElementLocation());
//            beanList.createPropertyList(doc, appendChildLocation, "parameters", doable.getParameters());
//            beanList.createPropertyList(doc, appendChildLocation, "testOnFail", doable.getTestOnFail());
//            beanList.createPropertyList(doc, appendChildLocation, "test", doable.getTest());
//            beanList.createPropertyList(doc, appendChildLocation, "run", doable.getRun());
//            beanList.createPropertyList(doc, appendChildLocation, "testStatus", doable.getTestStatus());
//            beanList.createPropertyList(doc, appendChildLocation, "businessProcess", doable.getBusinessProcess());
//            beanList.createPropertyList(doc, appendChildLocation, "uiAction", doable.getUiAction());
//            beanList.createPropertyList(doc, appendChildLocation, "value", doable.getValue());
//            beanList.createPropertyList(doc, appendChildLocation, "iteration", doable.getIteration());
//            beanList.createPropertyList(doc, appendChildLocation, "testTagValue", doable.getTestTagValue());
//            beanList.createPropertyList(doc, appendChildLocation, "sheetName", doable.getSheetName());
//            if (getLogger().isDebugEnabled())
//                getLogger().debug("Bean created for action " + doable.getUiAction());
//        }
//    }
//
//    /**
//     * <p>Sets the doable (action's) value from the test workbook Example: assigns
//     * the url to the value of the GoToUrl doable.
//     *
//     * @param doables
//     * @param excelTestTable
//     * @param start
//     * @param count          </p>
//     */
//    private void preprocessDoables(List<DoableBean> doables, String[][] excelTestTable, int start, int count,
//                                   String test, String run, String tagValue, String testStatus, String businessProcess, boolean lastBusinessProcessInTest,
//                                   int iteration, String sheetName) {
//        assert (doables.size() == count);
//        int firstValueCol = 5;
//        int valueColIndex = 0;
//        int testOnFailCol = 2;
//        int testCol = 1;
//        String deliminate = "[Parameter]+";
//        for (int i = 0; i < count; i++) {
//            if (lastBusinessProcessInTest && i == count - 1) {
//                testStatus = "End";
//            }
//            DoableBean doable = doables.get(i);
//
//            if (doable.getParameters().contains("Parameter")) {
//                // Deliminate the word Parameter;
//                String[] parameterNumber = doable.getParameters().split(deliminate);
//                doable.setValue(excelTestTable[start][firstValueCol + valueColIndex]);
//                valueColIndex = Integer.valueOf(parameterNumber[1]);
//
//                if (getLogger().isDebugEnabled())
//                    getLogger().debug("Set doable with Value: " + excelTestTable[start][firstValueCol + valueColIndex]);
//
//            }
//            doable.setTestOnFail(excelTestTable[start][testOnFailCol]);
//            doable.setTest(test);
//            doable.setRun(run);
//            doable.setTestStatus(testStatus);
//            doable.setBusinessProcess(businessProcess);
//            doable.setIteration(Integer.toString(iteration));
//            doable.setTestTagValue(tagValue);
//            doable.setSheetName(sheetName);
//
//            testStatus = "Test in Process";
//
//            if (getLogger().isDebugEnabled())
//                getLogger().debug("Preprocessing business process " + doable.getBusinessProcess() + ". Doable: "
//                        + doable.getUiAction() + ". Action: " + (i + 1) + "/" + (count) + ", for test " + doable.getTest()
//                        + ", iteration " + iteration);
//
//        }
//
//        if (getLogger().isDebugEnabled())
//            getLogger().debug("Done with preprocessDoables");
//    }
//
//    private String getSheetName(Map<String, List<String>> map, String neededValue) {
//
//        try {
//            String foundKey = map.entrySet().stream()
//                    .filter(ent -> {
//                        List<String> values = ent.getValue();
//                        return values.stream().anyMatch(value -> value.trim().contains(neededValue.trim()));
//                    })
//                    .findFirst()
//                    .get().getKey();
//
//            return foundKey;
//        } catch (NoSuchElementException nsee) {
//            System.err.println(neededValue);
//            nsee.printStackTrace();
//            return "";
//        }
//    }
//}
