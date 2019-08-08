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

    void selectDestination(String path){
        String _path = new String(path);
        String lastFol = "";
        clickOnElement(pathSelect);
        for(String fol : _path.split("/")){
            expandFolder(fol);
            lastFol = fol;
        }
        selectFolder(lastFol);
        clickByText("Select");
    }

    void selectFolder(String folderName){
        clickOnElement(By.xpath("//*[./coral-columnview-item-content[./div[text()='" + folderName
                + "']]]/coral-columnview-item-thumbnail"));
    }

    void expandFolder(String folderName){
        clickByText(folderName);
    }
}
