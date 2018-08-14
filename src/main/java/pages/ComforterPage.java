package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;

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
        By selectComforterSize = By.xpath("//div[@class='product-info-main loaded']//div[@class='bed-size-select']");

        /** Page Methods */

        public ComforterPage selectComforterSize(String size) {
            header.closeCart();
            waitForPageToLoad();
            reporter.info("Select Comforter size: " + size);
            scrollToElement(driver().findElement(selectComforterSize));
            //findElement(selectComforterSize).click();
                findElement(By.xpath("//div[@class='product-info-main loaded']//div[@class='option' and contains(text(),'" + size + "')]")).click();
                if (!findElement(selectComforterSize).getText().contains(size)){
                        reporter.fail("Item was not changed to: " + size);
                }
                return this;
        }

    public ViewCartPage clickAddToCart() {
        super.clickAddToCart();
        return ViewCartPage.Instance;
    }
}
