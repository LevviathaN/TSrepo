package pages;

import org.openqa.selenium.By;
import utils.FileIO;

public class FoundationPage extends BaseProductPage{

    private static FoundationPage instance;
    public static FoundationPage Instance = (instance != null) ? instance : new FoundationPage();

    public FoundationPage(){
        pageURL = "/foundation";
    }

    /** Common elements **/

    public PageHeader header = PageHeader.Instance;

    /** UI Mappings */
    //By addToCartButton = By.id("product-addtocart-button");
    By selectFoundationSize = By.xpath("//div[@class='product-info-main loaded']//div[@class='bed-size-select']");

    /** Page Methods */

    public FoundationPage selectBaseSize(String size) {
        reporter.info("Select Foundation size: " + size);
        //waitForPageToLoad();
        header.closeCart();
        findElement(selectFoundationSize).click();
        findElement(By.xpath("//div[@class='product-info-main loaded']//div[@class='option' and contains(text(),'" + size + "')]")).click();
        if (!findElement(selectFoundationSize).getText().contains(size)){
            reporter.pass("Item was not changed to: " + size);
        }
        return this;
    }

    public FoundationPage clickAddToCart() {
        super.clickAddToCart();
        return this;
    }
}
