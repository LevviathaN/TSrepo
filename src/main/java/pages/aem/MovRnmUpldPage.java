package pages.aem;

import org.openqa.selenium.By;

public class MovRnmUpldPage extends AemBasePage {

    private static MovRnmUpldPage instance;
    public static MovRnmUpldPage Instance = (instance != null) ? instance : new MovRnmUpldPage();

    public MovRnmUpldPage(){ pageURL = "/moveAssets.html"; }

    /**__________________________________PAGE ATTRIBUTES_______________________________*/
    /*Fast actions icons locators*/
    By pathSelect = By.xpath(".//coral-icon[@aria-label='select']");



    /**___________________________________PAGE METHODS_________________________________*/

    public void uploadCsv(String destination, String file){
        selectDestination(destination);
        clickByText("Browse...");
        uploadFile(file);
        clickByText("Submit");
    }
}
