package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.FileIO;

/**
 * Created by odiachuk on 07.07.17.
 */
public class MattressProtectorPage extends BaseProductPage{


        private final static String pageTitle = "";
        private static MattressProtectorPage instance;
        public static MattressProtectorPage Instance = (instance != null) ? instance : new MattressProtectorPage();

    public MattressProtectorPage(){
        pageURL = "/waterproof-mattress-protector";
    }

    /** Common elements **/

    public PageHeader header = PageHeader.Instance;

    /** UI Mappings */

 //   By addToCartButton = By.id("product-addtocart-button");
    By selectProtectorSize = By.xpath("//div[@class='product-info-main loaded']//div[@class='option']");

        /** Page Methods */

        public MattressProtectorPage selectProtectorSize(String size) {
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
