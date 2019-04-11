package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import utils.FileIO;

/**
 * Created by odiachuk on 07.07.17.
 */
public class MonitorPage extends BaseProductPage{

        private static MonitorPage instance;
        public static MonitorPage Instance = (instance != null) ? instance : new MonitorPage();

    public MonitorPage(){
        pageURL = "/sleep-tracker-sleep-monitoring-system";
    }

    /** Common elements **/

    public PageHeader header = PageHeader.Instance;


        /** UI Mappings */

        By addToCartButton = By.id("product-addtocart-button");


        /** Page Methods */

    public MonitorPage selectMonitorType(String monitorType) {
        reporter.info("Select monitor type: " + monitorType);
        header.closeCart();
        if (!isOptionSelected(monitorType))
        findElement(By.xpath("//div[@option-label='" + monitorType + "']")).click();
        if (!isOptionSelected(monitorType))
            reporter.fail("Item was not selected: " + monitorType);
        return this;
    }

//    public MonitorPage clickAddToCart() {
//        reporter.info("Click Add to cart button");
//        clickOnElement(addToCartButton);
//        return this;
//    }
}
