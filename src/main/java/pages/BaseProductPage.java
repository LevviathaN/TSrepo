package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import utils.FileIO;

/**
 * Created by odiachuk on 07.07.17.
 */
public class BaseProductPage extends BasePage{

    /** Common elements **/

    public PageHeader header = PageHeader.Instance;

        /** UI Mappings */
        By selectMattressSize = By.cssSelector("div.bed-size-select");
        By addToCartButton = By.xpath("//main[@id='maincontent']//button[@id='product-addtocart-button']");
        By updateItemButton = By.xpath("//main[@id='maincontent']//button[@id='product-updatecart-button']");


        /** Page Methods */

//    public BaseProductPage selectSize(String value){
//        reporter.info("Select size: " + value);
//        findElement(selectMattressSize).click();
//        findElement(By.xpath("//div[@class='option' and contains(text(),'" + value + "')]")).click();
//        if (!findElement(selectMattressSize).getText().contains(value)){
//            reporter.fail("Item was not changed to: " + value);
//        }
//        return this;
//    }
//
//    public BaseProductPage selecType(String type) {
//        reporter.info("Select type: " + type);
//        findElement(By.xpath("//div[@option-label='" + type + "']")).click();
//        if (!findElement(By.xpath("//div[@option-label='" + type + "']")).getAttribute("class").contains("selected"))
//            reporter.fail("Item was not selected: " + type);
//        return this;
//    }
        public ViewCartPage clickAddToCart() {
            reporter.info("Click on \"Add to Cart\" button");
            header.closeCart();
            waitForPageToLoad();
            driver().findElement(addToCartButton).click();
            if (header.waitUntilItemWillBeDropedToCart() == false) {
                driver().navigate().refresh();
                waitForPageToLoad();
                if (header.waitUntilItemWillBeDropedToCart() == false) {
                    reporter.info("Second attempt to click Add to Cart");
                    scrollToElement(driver().findElement(addToCartButton));
                    clickOnElement(addToCartButton);
                }
            }
            return ViewCartPage.Instance;
        }

    public ViewCartPage clickUpdateCart() {
        reporter.info("Click on \"Update cart item\" button");
        //header.closeCart();
        //waitForPageToLoad();
        clickOnElement(updateItemButton);
        return ViewCartPage.Instance;
    }
}