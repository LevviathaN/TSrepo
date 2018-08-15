package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.FileIO;

/**
 * Created by odiachuk on 07.07.17.
 */
public class SheetsetPage extends BaseProductPage{

        private static SheetsetPage instance;
        public static SheetsetPage Instance = (instance != null) ? instance : new SheetsetPage();

    public SheetsetPage(){
        pageURL = "/white-sheet-set";
    }

    /** Common elements **/

    public PageHeader header = PageHeader.Instance;

    /** UI Mappings */
    By addToCartButton = By.id("product-addtocart-button");
    By selectSheetsetSize = By.cssSelector("div.bed-size-select");

    /** Page Methods */

    public SheetsetPage selectSheetsetSize(String size) {
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

    public ViewCartPage clickAddToCart() {
        super.clickAddToCart();
        return ViewCartPage.Instance;
    }
}
