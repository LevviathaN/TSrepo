package pages;

import org.openqa.selenium.By;
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
    By selectProtectorSize = By.xpath("//div[@class='product-info-main loaded']//div[@class='bed-size-select']");

        /** Page Methods */

        public MattressProtectorPage selectProtectorSize(String size) {
            reporter.info("Select Protector size: " + size);
            header.closeCart();
            waitForPageToLoad();
            //findElement(selectProtectorSize).click();
            scrollToElement(driver().findElement(By.xpath("//div[@class='product-info-main loaded']//div[@class='option' and contains(text(),'" + size + "')]")));
            findElement(By.xpath("//div[@class='product-info-main loaded']//div[@class='option' and contains(text(),'" + size + "')]")).click();
            if (!findElement(selectProtectorSize).getText().contains(size)){
                reporter.fail("Item was not changed to: " + size);
            }
            return this;
        }

    public ViewCartPage clickAddToCart() {
        super.clickAddToCart();
        return ViewCartPage.Instance;
    }

}
