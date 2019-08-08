package pages.aem;

import org.openqa.selenium.By;
import java.util.HashMap;
import java.util.Map;

public class AS400UploadPage extends AemBasePage {

    private static AS400UploadPage instance;
    public static AS400UploadPage Instance = (instance != null) ? instance : new AS400UploadPage();

    public AS400UploadPage(){
        pageURL = "/as400metadata.html";
        //Initialise dictionary
        //Product data
//        metadata.put("INUMBR", "SKU Numbers");
//        metadata.put("SNUMBR", "Master SKU");
//        metadata.put("IDESCR", "SKU Description");
//        metadata.put("DSPNCD", "SKU Status");
//        metadata.put("ISTYPE", "SKU Type");
//        metadata.put("ISTYLN", "Style Number");
//        metadata.put("IDEPT", "Department Number");
//        metadata.put("ICLAS", "Class");
//        metadata.put("ISCLAS", "Sub-Class");
//        metadata.put("DEPTNM", "Department Name");
//        metadata.put("SDEPTNM", "Sub-Department");
//        metadata.put("ASNUM", "Vendor Number");
//        metadata.put("IVNDPN", "Model Number");
//        metadata.put("IUPC", "UPC Number");
//        metadata.put("SSTYLE", "Style Description");
//        metadata.put("WEBID", "Web ID");
//        //Initialise reversed dictionary
//        revMetadata.put("SKU Numbers", "INUMBR");
//        revMetadata.put("SKU Description","IDESCR");
//        revMetadata.put("SKU Status","DSPNCD");
//        revMetadata.put("SKU Type","ISTYPE");
//        revMetadata.put("Style Number","ISTYLN");
//        revMetadata.put("Department Number","IDEPT");
//        revMetadata.put("Class","ICLAS");
//        revMetadata.put("Sub-Class","ISCLAS");
//        revMetadata.put("Department Name","DEPTNM");
//        revMetadata.put("Sub-Department","SDEPTNM");
//        revMetadata.put("Vendor Number","ASNUM");
//        revMetadata.put("Model Number","IVNDPN");
//        revMetadata.put("UPC Number","IUPC");
//        revMetadata.put("Style Description","SSTYLE");
//        revMetadata.put("Web ID","WEBID");
    }

    /**__________________________________PAGE ATTRIBUTES_______________________________*/
    //Metadata fields dictionary
//    public static Map<String,String> metadata = new HashMap<String,String>();
//    public static Map<String,String> revMetadata = new HashMap<String,String>();
    //Initialise dictionary

    /*Fast actions icons locators*/
    By typeSelect = By.xpath(".//coral-icon[@aria-label='chevron down']");



    /**___________________________________PAGE METHODS_________________________________*/

    public void as400Upload(String type, String file){
        selectType(type);
        clickByText("Browse...");
        uploadFile(file);
        clickByText("Submit");
    }
    private void selectType(String type){
        clickByText("Select the type of file to import.");
        clickByText(type);
    }

    String[] getResults(){
        String[] parts = findElement(By.xpath("//pre")).getText().split("\n");
        for(String part : parts) {
            reporter.info("'"+part+"'");
        }
        return parts;
    }

    public boolean isAS400Errors(){
        boolean res = false;
        for(String result :getResults()){
            res = result.contains("Already exists");
        }
        return res;
    }

    public boolean verifyItapSimple(){
        MetadataFormPage meta = MetadataFormPage.Instance;
        boolean ok = true;
        boolean skuB = false;
        boolean mediaSetB = false;
        boolean upcB = false;
        for(String value : meta.getMultifieldValues("Master SKU")){
            if(value.equals("")){
                skuB = true;
            }
            ok = skuB;
            reporter.info("Expected CAB SKU - " + "" + "; Actual CAB SKU - " + value);
        }
        for(String value : meta.getMultifieldValues("CAB Media Set")){
            if(value.equals("")){mediaSetB = true;}
            ok = mediaSetB;
            reporter.info("Expected CAB Media Set - " + "" + "; Actual CAB Media Set - " + value);
        }
        for(String value : meta.getMultifieldValues("CAB UPC")){
            if(value.equals("")){upcB = true;}
            ok = upcB;
            reporter.info("Expected CAB UPC - " + "" + "; Actual CAB UPC - " + value);
        }
        return ok;
    }

    public boolean verifyDmat(String tag){
        return isElementPresent(By.xpath("//coral-tag-label[text()='" + tag + "']"));
    }
}
