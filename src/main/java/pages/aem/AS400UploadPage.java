package pages.aem;

import org.openqa.selenium.By;

public class AS400UploadPage extends AemBasePage {

    private static AS400UploadPage instance;
    public static AS400UploadPage Instance = (instance != null) ? instance : new AS400UploadPage();

    public AS400UploadPage(){ pageURL = "/as400metadata.html"; }

    /**__________________________________PAGE ATTRIBUTES_______________________________*/
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
//        clickOnElement(typeSelect);
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
}
