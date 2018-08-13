package pages;

import org.openqa.selenium.By;
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
        reporter.info("Select Sheetset size: " + size);
        //waitForPageToLoad();
        header.closeCart();
        findElement(selectSheetsetSize).click();
        findElement(By.xpath("//div[@class='option' and contains(text(),'" + size + "')]")).click();
        if (!findElement(selectSheetsetSize).getText().contains(size)){
            reporter.fail("Item was not changed to: " + size);
        }
        return this;
    }

    public ViewCartPage clickAddToCart() {
        super.clickAddToCart();
        return ViewCartPage.Instance;
    }
}
