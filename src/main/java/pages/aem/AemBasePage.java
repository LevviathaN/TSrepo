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
    String aemBaseUrl = "https://author-bass-assets-stage65.adobecqms.net";
    String aemLogin = "rlevytskyi";
    String aemPass = "basspro111@damM";

    /*_________________Login page elements________________*/
    By loginField = By.xpath(".//input[@id='username']");
    By passField = By.xpath(".//input[@id='password']");
    By submitBtn = By.xpath(".//button[@id='submit-button']");

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

    public void checkLagoStatus(int itemNumber, String expectedValue){
        openUrl("https://author-bass-assets-stage65.adobecqms.net/bin/motheship/endpoint?itemNumber=" + itemNumber);
        //todo JSON response
    }

    public WebElement findFieldByTitle(String title){
        return findElement(By.xpath(".//div[./label[text()='" + title + "']]/input"));
    }

    public void populateFieldByTitle(String title, String text){
        findFieldByTitle(title).sendKeys(text);
    }
}
