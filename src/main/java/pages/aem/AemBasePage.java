package pages.aem;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;

/**
 * Created by Ruslan Levytskyi on 05/27/19.
 */
public class AemBasePage extends BasePage {

    /**__________________________________PAGE ATTRIBUTES_______________________________*/
    /*__________________Credentials_________________*/
    String aemBaseUrl = "https://author-bass-assets-stage2.adobecqms.net";
    String bpBaseUrl = "https://basspro.brand-portal.adobe.com/mediaportal.html/content/dam/mac/basspro";
    String aemLogin = "rlevytskyi";
    String aemPass = "basspro111@damM";

    String bpLogin = "ruslan.levytskyi@avionos.com";
    String bpPass = "BillyJoe1998";

    /*_________________Login page elements________________*/
    By loginField = By.xpath(".//input[@id='username']");
    By passField = By.xpath(".//input[@id='password']");
    By submitBtn = By.xpath(".//button[@id='submit-button']");

    By bpLoginField = By.xpath(".//input[@id='adobeid_username']");
    By bpPassField = By.xpath(".//input[@id='adobeid_password']");
    By bpSubmitBtn = By.xpath(".//button[@id='sign_in']");

    /*_______________Common elements_______________*/
    By homeLink = By.xpath(".//coral-shell-homeanchor-label");
    By leftMenuDrp = By.xpath(".//coral-icon[@icon='railLeft']");
    By upperBreadcrumb = By.xpath(".//betty-breadcrumbs[@class='granite-collection-navigator']");
    By createBtn = By.xpath(".//coral-button-label[text()='Create']");
    By ratingPopupIframe = By.xpath("//div[@id='omg_surveyContainer']/div/iframe");
    By closeRatingPopupBtn = By.xpath("//div[@id='omg_close']");




    /**___________________________________PAGE METHODS__________________________________*/
    public void logIn() {
        reporter.info("Logging into AEM");
        driver().get(aemBaseUrl);
        findElement(loginField).sendKeys(aemLogin);
        findElement(passField).sendKeys(aemPass);
        findElement(submitBtn).click();
    }

    public void logIntoBP() {
        reporter.info("Logging into Brand Portal");
        driver().get(bpBaseUrl);
        clickByText("Sign In");
        findElement(bpLoginField).sendKeys(bpLogin);
        findElement(bpPassField).click();
        findElement(bpPassField).sendKeys(bpPass);
        findElement(bpSubmitBtn).click();
    }

    public void closeRatingPopup(){
        sleepFor(1000*10);
        reloadPage();

//        reporter.info("Checking if Adobe rating popup appears");
//        if(isElementPresent(ratingPopupIframe)){
//            reporter.info("Rating popup appeared!");
//            reloadPage();
//
//            /*F*ck that, it's not working anyway*/
//            if(isElementPresent(closeRatingPopupBtn)){
//                clickOnElement(closeRatingPopupBtn);
//            } else{
//                switchToFrame(ratingPopupIframe);
//                clickOnElement(closeRatingPopupBtn);
//                switchToDefaultContent();
//                clickOnAnyElement(closeRatingPopupBtn);
//            }
//        }
    }

    public void navigate(String path){
        reporter.info("Navigate to: " + path);
        openUrl(BASE_URL + pageURL + path);
    }

    public void bpNavigate(String path){
        reporter.info("Navigate to: " + path);
        openUrl(bpBaseUrl + path);
    }

    public void checkLagoStatus(int itemNumber, String expectedValue){
        openUrl("https://author-bass-assets-stage2.adobecqms.net/bin/motheship/endpoint?itemNumber=" + itemNumber);
        //todo JSON response
    }

    public WebElement findFieldByTitle(String title){
        return findElement(By.xpath(".//div[./label[text()='" + title + "']]/input"));
    }

    public void populateFieldByTitle(String title, String text){
        findFieldByTitle(title).sendKeys(text);
    }

    //todo: create separate class BaseUploadPage to be extended by Skyword, AS400, Cab and other upload pages
    //put below methods to that class
    public void selectDestination(String path){
        reporter.info("Destinatio path is: " + path);
        String _path = new String(path);
        String lastFol = "";
        for(String fol : _path.split("/")){
            reporter.info("Expand " + fol + " folder");
            expandFolder(fol);
            lastFol = fol;
        }
        reporter.info("Select " + lastFol + " folder");
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
