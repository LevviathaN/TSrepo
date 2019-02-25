package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import utils.FileIO;

import java.util.List;

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
        By crossSalesWindow = By.cssSelector(".xs-modal");
        By viewCartButton = By.xpath("//a[@class='__to-checkout button']");

        /** Page Methods */

    public BaseProductPage selectOption(String value){
        if (!(value.isEmpty())) {
            reporter.info("Select option: " + value);
            if (!isOptionAColor(value))
                selectSizeOrType(value);
            else
                selectColor(value);
        }
        else
            reporter.info("Select option: *option is empty*");
        return this;
    }

    public BaseProductPage selectSizeOrType(String option) {
        if (!isOptionSelected(option))
            findElement(By.xpath("//div[@option-label and contains(text(),'" + option + "')]")).click();
        if (!isOptionSelected(option))
            reporter.fail("Item was not selected: " + option);
        return this;
    }

    public BaseProductPage selectColor(String color){
        if (!findElement(By.xpath("//div[@option-label='" + color + "']")).getAttribute("class").contains("selected"))
            findElement(By.xpath("//div[@option-label='" + color + "']")).click();
        if (!findElement(By.xpath("//div[@option-label='" + color + "']")).getAttribute("class").contains("selected"))
            reporter.fail("Item was not selected: " + color);
        return this;
    }

        public ViewCartPage clickAddToCart() {
            reporter.info("Click on \"Add to Cart\" button");
            header.closeCart();
            waitForPageToLoad();
            driver().findElement(addToCartButton).submit();
//            if (header.waitUntilItemWillBeDropedToCart() == false) {
//                driver().navigate().refresh();
//                waitForPageToLoad();
//                if (header.waitUntilItemWillBeDropedToCart() == false) {
//                    reporter.info("Second attempt to click Add to Cart");
//                    scrollToElement(driver().findElement(addToCartButton));
//                    clickOnElement(addToCartButton);
//                }
//            }
            if (isElementPresentAndDisplay(crossSalesWindow)){
                clickOnElement(viewCartButton);
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

    public WebElement searchForDisplayedElement(){
        waitForPageToLoad();
        By elements = By.xpath(".//div[@class='product-add-form']");
        List<WebElement> options = findElements(elements);
        for (WebElement element : options) {
            if (element.isDisplayed()) {
                return element;
            }
        }
        return null;
    }

    public boolean isOptionSelected(String option){
            return findElement(By.xpath("//div[@option-label and contains(text(),'" + option + "')]")).getAttribute("class").contains("selected");
    }

    public BaseProductPage isProductInStock(){
        if (isElementPresentAndDisplay(By.xpath("//a[@class='action alert']"))){
            reporter.skip("Product is out of stock");
        }
        return this;
    }
}