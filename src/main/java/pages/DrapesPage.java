package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.FileIO;

/**
 * Created by odiachuk on 07.07.17.
 */
public class DrapesPage extends BaseProductPage{

        private static DrapesPage instance;
        public static DrapesPage Instance = (instance != null) ? instance : new DrapesPage();

    public DrapesPage(){
        pageURL = "/blackout-curtains";
    }

    /** Common elements **/

    public PageHeader header = PageHeader.Instance;

    /** UI Mappings */

  //  By addToCartButton = By.id("product-addtocart-button");
    By selectDrapesSize = By.cssSelector("div.bed-size-select");

    /** Page Methods */

    public ViewCartPage clickAddToCart() {
        super.clickAddToCart();
        return ViewCartPage.Instance;
    }

    public DrapesPage selectDrapesSize(String size) {
        waitForPageToLoad();
        WebElement element = super.searchForDisplayedElement();
        if (element == null){
            reloadPage();
            element = super.searchForDisplayedElement();
        }

        scrollToElement(element);
        if (element.getText().contains(size)){
            reporter.info(size + " size is selected by default");
        } else {
            reporter.info("Changing size to " + size);
            element.click();
            findElement(By.xpath("//div[@class='product-info-main loaded']//div[@class='option' and contains(text(),'" + size + "')]")).click();
        }

        return this;
    }

    public DrapesPage selectDrapesColor(String color) {
        header.closeCart();
        reporter.info("Select Drapes color: " + color);
        scrollToElement(driver().findElement(By.xpath("//div[@option-label='" + color + "']")));
        clickOnElement(By.xpath("//main[@id='maincontent']//div[@option-label='" + color + "']"));
        return this;
    }
}
