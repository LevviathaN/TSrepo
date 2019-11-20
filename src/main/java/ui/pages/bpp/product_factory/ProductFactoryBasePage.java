package ui.pages.bpp.product_factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.pages.BasePage;

public class ProductFactoryBasePage extends BasePage {

    private static ProductFactoryBasePage instance;
    public static ProductFactoryBasePage Instance = (instance != null) ? instance : new ProductFactoryBasePage();

    /**__________________________________PAGE ATTRIBUTES_______________________________*/

    /*__________________Credentials_________________*/
    String pfBaseUrl = "http://pf-services-qa-1697893966.eu-west-2.elb.amazonaws.com/login";
    String SPSLogin = "samuelslade@bpp.com";
    String SPSPass = "Password1";
    String PSLogin = "craigolivant@bpp.com";
    String PSPass = "Password1";



    /**___________________________________PAGE METHODS_________________________________*/

    public void logIn(String username, String password){
        reporter.info("Logging into ProductFactory");
        driver().get(pfBaseUrl);
        clickByText("Sign in with Auth0");
        findElement(byAttribute("name","email")).sendKeys(username);
        findElement(byAttribute("name","password")).sendKeys(password);
        findElement(byAttribute("name","submit")).click();
    }

    public void logInAs(String role){
        switch(role){
            case "Senior Product Setter":
                reporter.info("as Senior Product Setter");
                logIn(SPSLogin, SPSPass);
                break;
            case "Product Setter":
                reporter.info("as Product Setter");
                logIn(PSLogin, PSPass);
                break;
        }
    }

    public By pfField(String fieldName){
        return By.xpath("//label[text()='" + fieldName + "']/following-sibling::div/input");
    }

    public By pfEdit(String fieldName){
        return By.xpath("//td[text()='" + fieldName + "']/following-sibling::td/button");
    }

    public By pfChangeButton(String fieldName){
        return By.xpath("//span[text()='" + fieldName + "']/following-sibling::button");
    }

    public void pfSelectFromModal(String value){
        clickOnElement(By.xpath("//td[text()='" + value + "']/following-sibling::td/button/span[text()='Select']"));
    }

    public void pfCheckbox(String element, boolean shouldBeChecked){
        WebElement checkbox = findElement(By.xpath("//td[text()='" + element + "']/preceding-sibling::td/span"));
        if((!checkbox.isSelected() & shouldBeChecked) || (checkbox.isSelected() & !shouldBeChecked)){
            checkbox.click();
        }
    }

    public void pfToggle(String element, boolean shouldBeChecked){
        WebElement checkbox = findElement(By.xpath("//span[text()='" + element + "']/preceding-sibling::span/span/span/input"));
        if((!checkbox.isSelected() & shouldBeChecked) || (checkbox.isSelected() & !shouldBeChecked)){
            checkbox.click();
        }
    }
}
