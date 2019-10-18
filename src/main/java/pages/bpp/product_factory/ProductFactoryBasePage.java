package pages.bpp.product_factory;

import org.openqa.selenium.By;
import pages.BasePage;

public class ProductFactoryBasePage extends BasePage {

    private static ProductFactoryBasePage instance;
    public static ProductFactoryBasePage Instance = (instance != null) ? instance : new ProductFactoryBasePage();

    /**__________________________________PAGE ATTRIBUTES_______________________________*/

    /*__________________Credentials_________________*/
    String pfBaseUrl = "http://pf-services-qa-1697893966.eu-west-2.elb.amazonaws.com/login";
    String aemLogin = "samuelslade@bpp.com";
    String aemPass = "Password1";



    /**___________________________________PAGE METHODS_________________________________*/

    public void logIn(){
        reporter.info("Logging into ProductFactory");
        driver().get(pfBaseUrl);
        clickByText("Sign in with Auth0");
        findElement(byAttribute("name","email")).sendKeys(aemLogin);
        findElement(byAttribute("name","password")).sendKeys(aemPass);
        findElement(byAttribute("name","submit")).click();
    }

    public void createNewBody(String bodyShortName, String bodyName){
        reporter.info("Creating new body: " + bodyName + " (" + bodyShortName + ")");
        clickByText("Bodies");
        clickByText("Create");
        setText(pfField("Short Name"), bodyShortName);
        setText(pfField("Name"), bodyName);
        clickByText("Save");
    }



    public By pfField(String fieldName){
        return By.xpath("//label[text()='" + fieldName + "']/following-sibling::div/input");
    }
}
