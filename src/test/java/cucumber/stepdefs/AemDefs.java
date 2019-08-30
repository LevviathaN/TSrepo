package cucumber.stepdefs;

import cucumber.api.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import pages.bps.AemBasePage;

public class AemDefs extends AemBasePage {

    @Given("^I am logged into AEM$")
    public void i_am_logged_into_aem() {
        logIn();
    }

    @When("^With \"([^\"]*)\" option i upload the file by path \"([^\"]*)\"$")
    public void upload_file(String uploadElement, String filePath) {
        Actions actions = new Actions(driver());
        actions.moveToElement(findByText(uploadElement));
        actions.click();
        actions.sendKeys(filePath);
        actions.build().perform();
    }

    @When("^I delete \"([^\"]*)\" asset$")
    public void delete_asset(String assetName) {
        hoverItem(By.xpath(".//*[text()='" + assetName + "']"));
        findElement(By.xpath(".//coral-quickactions[@aria-hidden='false']/button")).click();
        //findElement(By.xpath(".//coral-icon[@aria-label='more']")).click();
        clickOnAnyElement(By.xpath(".//coral-icon[@aria-label='more']"));
        //findElement(By.xpath(".//coral-ibutton-label[text()='Delete']")).click();
        clickOnAnyElement(By.xpath(".//coral-button-label[text()='Delete']"));
    }

    @Then("^\"([^\"]*)\" asset should be present$")
    public void upload_file(String assetName) {
        findByText(assetName).isEnabled();
    }
}
