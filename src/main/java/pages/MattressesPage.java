package pages;

import org.openqa.selenium.By;

/**
 * Created by odiachuk on 07.07.17.
 */
public class MattressesPage extends BaseProductPage{

        private static MattressesPage instance;
        public static MattressesPage Instance = (instance != null) ? instance : new MattressesPage();

    public MattressesPage(){
        pageURL = "/hybrid-mattress";
    }

    /** Common elements **/

    public PageHeader header = PageHeader.Instance;

        /** UI Mappings */

        By selectMattressSize = By.cssSelector("div.swatch-attribute.bed_size");
        //By mattressSizeSelectedByDefault = By.xpath("//div[@class='swatch-option text selected' and @aria-describedby='option-label-bed_size-177']");

        By mattressSizeSelectedByDefault = By.xpath("//div[@aria-checked='true' and @aria-describedby='option-label-bed_size-177']");
        By mattressFeelSelectedByDefault = By.xpath("//div[@aria-checked='true' and @aria-describedby='option-label-mattress_feel-178']");

     // By addToCartButton = By.id("product-addtocart-button");


        /** Page Methods */


//    public MattressesPage selectMattressSize(String value){
//        reporter.info("Select mattress size: " + value);
//        if (isElementPresentAndDisplay(selectMattressSize)){
//            scrollToElement(driver().findElement(selectMattressSize));
//            if (!value.equals("Queen")){
//                findElement(By.xpath("//div[@role='option' and contains(text(),'" + value + "')]")).click();
//            }
//            if (!findElement(selectMattressSize).getText().contains(value)){
//                reporter.pass("Item was not changed to: " + value);
//            }
//        }else {
//            findElement(By.xpath("//div[@class='bed-size-select cx-safewait-loaded cx-bed-size-select']//div[@class=contains(text(),'" + value + "')][1]")).click();
//        }
//        return this;
//    }


//        public MattressesPage selectMattressSize(String value){
//            //reporter.info("Select mattress size: " + value);
//            reporter.info(findElement(mattressSizeSelectedByDefault).getText() + " is selected by default");
//            if (isElementPresentAndDisplay(selectMattressSize) && findElement(mattressSizeSelectedByDefault).getText().toLowerCase().contains(value.toLowerCase())){
//                //reporter.info(value + " size is selected by default");
//            }else {
//                reporter.info("Switching mattress size to " + value);
//                //scrollToElement(driver().findElement(selectMattressSize));
//                findElement(By.xpath("//div[@role='option' and contains(text(),'" + value + "')]")).click();
//            }
//            return this;

    public MattressesPage selectMattressSize(String value) {
        if (isElementPresentAndDisplay(selectMattressSize)) {
            scrollToElement(driver().findElement(selectMattressSize));
            if (findElement(mattressSizeSelectedByDefault).getText().toLowerCase().contains(value.toLowerCase())) {
                reporter.info(findElement(mattressSizeSelectedByDefault).getText() + " is selected by default");
            } else {
                reporter.info("Select mattress size: " + value);
                findElement(By.xpath("//div[@role='option' and contains(text(),'" + value + "')]")).click();
            }
        }
        return this;
    }


    public MattressesPage selectMattressFeel(String mattressFeel) {
        if (isElementPresent(By.xpath("//div[@option-label='" + mattressFeel + "']"))) {
            if (findElement(mattressFeelSelectedByDefault).getText().toLowerCase().contains(mattressFeel.toLowerCase())){
                reporter.info(findElement(mattressFeelSelectedByDefault).getText() + " is selected by default");
            }else {
                reporter.info("Select mattress feel: " + mattressFeel);
                findElement(By.xpath("//div[@option-label='" + mattressFeel + "']")).click();
            }
        }
        return this;
    }

    public ViewCartPage clickAddToCart() {
        super.clickAddToCart();
        return ViewCartPage.Instance;
    }
}
