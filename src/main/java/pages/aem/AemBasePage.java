package pages.aem;

import org.openqa.selenium.By;
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




    /**___________________________________PAGE METHODS__________________________________*/
    public void logIn() {
        reporter.info("Logging into AEM");
        driver().get(aemBaseUrl);
        findElement(loginField).sendKeys(aemLogin);
        findElement(passField).sendKeys(aemPass);
        findElement(submitBtn).click();
    }

    public void closeRatingPopup(){
        reporter.info("Checking if Adobe rating popup appears");
        if(isElementPresent(By.xpath("//div[@id='omg_surveyContainer']/div/iframe"))){
            reporter.info("Closing popup");
            switchToFrame(By.xpath("//div[@id='omg_surveyContainer']/div/iframe"));
            clickOnElement(By.xpath("//div[@id='omg_close']"));
            switchToDefaultContent();
            clickOnAnyElement(By.xpath("//div[@id='omg_close']"));
        }
    }
}
