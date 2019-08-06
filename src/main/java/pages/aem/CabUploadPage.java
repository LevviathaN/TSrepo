package pages.aem;

import org.openqa.selenium.By;

import java.lang.reflect.Member;

public class CabUploadPage extends AemBasePage {
    private static CabUploadPage instance;
    public static CabUploadPage Instance = (instance != null) ? instance : new CabUploadPage();

    public CabUploadPage(){ pageURL = "/cabelasUpload.html"; }

    /**__________________________________PAGE ATTRIBUTES_______________________________*/
    /*Fast actions icons locators*/
    By pathSelect = By.xpath(".//coral-icon[@aria-label='select']");



    /**___________________________________PAGE METHODS_________________________________*/

    public void uploadCabMetaCsv(String file){
        clickByText("Browse...");
        uploadFile(file);
        clickByText("Submit");
    }

    public boolean verifyCabMeta(String sku, String mediaSet, String upc){
        MetadataFormPage meta = MetadataFormPage.Instance;
        boolean ok = true;
        boolean skuB = false;
        boolean mediaSetB = false;
        boolean upcB = false;
        for(String value : meta.getMultifieldValues("CAB SKU")){
            if(value.equals(sku)){
                skuB = true;
            }
            ok = skuB;
            reporter.info("Expected CAB SKU - " + sku + "; Actual CAB SKU - " + value);
        }
        for(String value : meta.getMultifieldValues("CAB Media Set")){
            if(value.equals(mediaSet)){mediaSetB = true;}
            ok = mediaSetB;
            reporter.info("Expected CAB Media Set - " + mediaSet + "; Actual CAB Media Set - " + value);
        }
        for(String value : meta.getMultifieldValues("CAB UPC")){
            if(value.equals(upc)){upcB = true;}
            ok = upcB;
            reporter.info("Expected CAB UPC - " + upc + "; Actual CAB UPC - " + value);
        }
        return ok;
    }

    public boolean isCabErrors(){
        boolean res = false;
        for(String result :getResults()){
            res = result.contains("Already exists");
        }
        return res;
    }

    String[] getResults(){
        String[] parts = findElement(By.xpath("//pre")).getText().split("\n");
        for(String part : parts) {
            reporter.info("'"+part+"'");
        }
        return parts;
    }

}
