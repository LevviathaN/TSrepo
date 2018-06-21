package pages;

import org.openqa.selenium.By;
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
        reporter.info("Select Adjustable Base size: " + size);
        //waitForPageToLoad();
        header.closeCart();
        findElement(selectBaseSize).click();
        findElement(By.xpath("//div[@class='option' and contains(text(),'" + size + "')]")).click();
        if (!findElement(selectBaseSize).getText().contains(size)){
            reporter.fail("Item was not changed to: " + size);
        }
        return this;
    }

    public AdjustablePage clickAddToCart() {
        super.clickAddToCart();
        return this;
    }
}
