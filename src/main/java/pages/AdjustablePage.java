package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.FileIO;

public class AdjustablePage extends BaseProductPage{

    private static AdjustablePage instance;
    public static AdjustablePage Instance = (instance != null) ? instance : new AdjustablePage();

    public AdjustablePage(){
        pageURL = "/adjustable-bed";
        pageTitle = "SmartMotion™ Bases | Optimized Sleep | Beautyrest";
    }

    /** Common elements **/

    public PageHeader header = PageHeader.Instance;

    /** UI Mappings */
    //By addToCartButton = By.id("product-addtocart-button");
    By selectBaseSize = By.cssSelector("div.bed-size-select");

    /** Page Methods */

    public AdjustablePage selectBaseSize(String size) {
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
