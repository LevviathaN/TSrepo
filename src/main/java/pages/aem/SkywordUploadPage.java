package pages.aem;


import org.openqa.selenium.By;

public class SkywordUploadPage extends AemBasePage {

    private static SkywordUploadPage instance;
    public static SkywordUploadPage Instance = (instance != null) ? instance : new SkywordUploadPage();

    public SkywordUploadPage(){ pageURL = "/skywordupload.html"; }

    /**__________________________________PAGE ATTRIBUTES_______________________________*/
    /*Fast actions icons locators*/
    By pathSelect = By.xpath(".//coral-icon[@aria-label='select']");



    /**___________________________________PAGE METHODS_________________________________*/

    public void uploadSkywordXml(String destination, String file){
        clickOnElement(pathSelect);
        selectDestination(destination);
        clickByText("Browse...");
        uploadFile(file);
        clickByText("Submit");
    }



    String[] getResults(){
        String[] parts = findElement(By.xpath("//pre")).getText().split("\n");
        for(String part : parts) {
            reporter.info("'"+part+"'");
        }
        return parts;
    }

    public boolean isSkywordErrors(){
        boolean res = false;
        for(String result :getResults()){
            res = result.contains("Already exists");
        }
        return res;
    }
}
