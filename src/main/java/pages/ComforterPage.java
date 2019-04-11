package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by odiachuk on 07.07.17.
 */
public class ComforterPage extends BaseProductPage{

        private static ComforterPage instance;
        public static ComforterPage Instance = (instance != null) ? instance : new ComforterPage();

    public ComforterPage(){
        pageURL = "/down-alternative-white-comforter";
    }

        /** Common elements **/

        public PageHeader header = PageHeader.Instance;

        /** UI Mappings */

        //By addToCartButton = By.id("product-addtocart-button");
        By selectComforterSize = By.cssSelector("div#product-options-wrapper.product-options-wrapper");
        By selectedOption = By.cssSelector(".swatch-attribute-selected-option");

        /** Page Methods */

        public ComforterPage selectComforterSize(String size) {
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
                findElement(By.xpath("//div[@class='swatch-option text' and contains(text(),'" + size + "')]")).click();
            }

            return this;
        }

    public ViewCartPage clickAddToCart() {
        super.clickAddToCart();
        return ViewCartPage.Instance;
    }
}
