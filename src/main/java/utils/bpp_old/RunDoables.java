//package utils.bpp_old;
//
//import com.apolloglobal.testing.api.PreProcessFiles;
//import com.apolloglobal.testing.api.impl.ExecuteDoable;
//import com.apolloglobal.testing.api.util.*;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.FileSystemXmlApplicationContext;
//
//import java.util.*;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Collectors;
//
//public class RunDoables {
//
//    private static final Map<String, String> executionEnvironment = new HashMap<>();
//    private static final Object lock = new Object();
//
//    //parallel execution
//    static Map<String, List<Doable>> testAndDoablesMap = new LinkedHashMap<>();
//    static LinkedHashMap<String, List<Doable>> tagAndTestMap = new LinkedHashMap<>();
//    static ThreadLocal<AbstractDoable> abstractDoable = new ThreadLocal<>();
//    static Map<String, List<String>> tagsMap = new LinkedHashMap<>();
//
//    public static void setExecutionEnvironmentInfo() {
//        synchronized (lock) {
//            if (!executionEnvironment.isEmpty()) {
//                return;
//            }
//            executionEnvironment.put("Operating System", System.getProperty("os.name"));
//            executionEnvironment.put("Project Version", PropertiesHelper.getVersionFromFile());
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//
//        System.out.println("START - NOAH AUTOMATION PROJECT");
//
//        // Generate TestBeans.xml from xlsx input & identify files
//        PreProcessFiles preProcess = new PreProcessFiles();
//        boolean startTestExecution = preProcess.preProcessTestConfiguration(args);
//
//        Reporter.instantiate();
//        KeywordsHandler.instantiate();
//        MetaDataHandler.instantiate();
//
//        if (startTestExecution) {
//
//            if (System.getProperty("driverType") != null) {
//                for (String driverT : System.getProperty("driverType").split(";") ) {
//                    initiateTestRun(driverT);
//                }
//            } else {
//                initiateTestRun( new PropertiesHelper().getProperties().get("driver_id").toString());
//            }
//
//            System.out.println("EXECUTIONS HAVE FINISHED");
//
//            preProcess.getXlsxFileConvertToCsv();
//            Reporter.setSystemInfo(executionEnvironment);
//            if (!Reporter.getBuildStatus()) {
//                System.out.println("Build status is : " + Reporter.getBuildStatus());
//                throw new RuntimeException("One of the test failed \n" + Reporter.getFailureBucket().toString());
//            }
//        }
//    }
//
//    private static void populateTagsMap() {
//
//        for (Map.Entry<String, List<Doable>> entry : testAndDoablesMap.entrySet()) {
//
//            for (String tagListItem : ((AbstractDoable) entry.getValue().get(0)).getTestTagValue().toUpperCase().split(";")) {
//
//                if (!tagsMap.containsKey(tagListItem)) {
//                    List<String> testList = new ArrayList<>();
//                    testList.add(entry.getKey());
//                    tagsMap.put(tagListItem.toUpperCase(), testList);
//
//                } else {
//                    tagsMap.get(tagListItem.toUpperCase()).add(entry.getKey());
//                }
//
//            }
//
//            tagsMap.remove("");
//        }
//    }
//
//    private static void initiateTestRun(String driverT) throws Exception {
//
//        GlobalDataBridge.getInstance().setBufferValueByKey("current_browser", driverT);
//
//        ApplicationContext context = new FileSystemXmlApplicationContext("TestDoablesBeans.xml");
//
//        // Run through the Beans xml
//        ExecuteDoable mainDoable = (ExecuteDoable) context.getBean("ExecuteDoable");
//
//        List<Doable> doablesList = new ArrayList<>();
//        List<Doable> mainDoablesList = mainDoable.getDoables();
//        Map<String, Integer> mapOfIterations = new HashMap<>();
//
//        /**
//         * Creation of test and doables map
//         * 0 = {LinkedHashMap$Entry@5169} "Lead Account BPP" -> " size = 71"
//         1 = {LinkedHashMap$Entry@5170} "Create Student End To End BPP" -> " size = 79"
//         2 = {LinkedHashMap$Entry@5171} "ULA Student Creation From Lead WA" -> " size = 56"
//         3 = {LinkedHashMap$Entry@5172} "Open Bravo Login And Main Dashboard" -> " size = 13"
//         4 = {LinkedHashMap$Entry@5173} "Open Bravo Dashboard Widgets" -> " size = 41"
//         5 = {LinkedHashMap$Entry@5174} "Open Bravo Edit on Several Reference Data types" -> " size = 31"
//         6 = {LinkedHashMap$Entry@5175} "Login Admin IACC" -> " size = 5"
//         */
//
//        testAndDoablesMap = new LinkedHashMap<>();
//
//        for (int index = 0; index < mainDoablesList.size(); index++) {
//            abstractDoable.set((AbstractDoable) mainDoablesList.get(index));
//
//            if (!abstractDoable.get().getTest().equalsIgnoreCase(" ")) {
//                if (!testAndDoablesMap.containsKey(abstractDoable.get().getTest())) {
//                    doablesList = new ArrayList<>();
//                    testAndDoablesMap.put(abstractDoable.get().getTest(), doablesList);
//                    doablesList.add(mainDoablesList.get(index));
//                } else {
//                    doablesList.add(mainDoablesList.get(index));
//                    testAndDoablesMap.put(abstractDoable.get().getTest(), doablesList);
//                }
//            }
//        }
//
//
//        if (System.getProperties().containsKey("tagOrSheet")) {
//
//            //fill tags map with data
//            populateTagsMap();
//
////              Adding sheets Map to Tags map
//            Map<String, List<String>> sheetAndTestsMap = ExcelGetDataHelper.getMapOfSheetAndTests(true);
//            tagsMap.putAll(sheetAndTestsMap);
//            tagsMap = tagsMap.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().toUpperCase(), e -> e.getValue()));
//
//            String userTag = System.getProperty("tagOrSheet").toUpperCase();
//            String[] givenTags = userTag.split(";");
//
//
//            //Creating a set filtering out the test cases
//            Set<String> parallelTestsSet = new HashSet<>();
//            Set<String> serialTestsSet = new HashSet<>();
//
//            for (String tag : givenTags) {
//                tag = tag.toUpperCase();
//                if (tag.toUpperCase().endsWith("(S)")) {
//                    serialTestsSet.addAll(tagsMap.get(tag.substring(0, tag.indexOf("(")).toUpperCase()));
////                        serialTestsSet.addAll(tagsMap.get(tag.toUpperCase()));
//                }
//
//                if (tagsMap.keySet().contains(tag) && !(tag.toUpperCase().endsWith("(S)"))) {
//                    parallelTestsSet.addAll(tagsMap.get(tag.toUpperCase()));
//                }
//
//                if (serialTestsSet.size() < 1 && parallelTestsSet.size() < 1) {
//                    throw new Exception("No test enabled with the specified tag\n" +
//                            "Tags associated with enabled tests: " + tagsMap.keySet().toString() + "\n" +
//                            "User given tag: " + tag);
//                }
//            }
//
//            tagsMap = new LinkedHashMap<>();
//
//            //Separate Parallel and Serial tests
//            List<Doable> serialTestDoables = new ArrayList<>();
//            List<String> testListToSerialize = new ArrayList<>();
//
//            for (String test : serialTestsSet) {
//                serialTestDoables.addAll(testAndDoablesMap.get(test));
//            }
//
//            testAndDoablesMap.keySet().retainAll(parallelTestsSet);
//
//            if (!serialTestDoables.isEmpty()) {
//                testAndDoablesMap.put("Serial", serialTestDoables);
//            }
//
//        }
//
//        int parallelInstances = 10;
//        if (System.getProperties().containsKey("parallel_instances")) {
//            parallelInstances = Integer.parseInt(System.getProperty("parallel_instances"));
//        }
//        ExecutorService executor = Executors.newFixedThreadPool(parallelInstances);
//
//        for (Map.Entry<String, List<Doable>> entry : testAndDoablesMap.entrySet()) {
//            Runnable runnable = new ExecuteDoable(entry.getValue(), entry.getKey());
//            executor.execute(runnable);
//        }
//
//        try {
//            executor.shutdown();
//            executor.awaitTermination(2, TimeUnit.HOURS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    }
//}