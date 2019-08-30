package pages.bps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.CsvReader;

import java.util.ArrayList;
import java.util.List;

public class MetadataFormPage extends AemBasePage {

    private static MetadataFormPage instance;
    public static MetadataFormPage Instance = (instance != null) ? instance : new MetadataFormPage();


    /**__________________________________PAGE ATTRIBUTES_______________________________*/
    List<List<String>> tabsList = new ArrayList<>();
    List<String> fieldsList = new ArrayList<>();

    By tabs = By.xpath(".//coral-tab-label");
    By fieldTitles = By.xpath(".//label");


    /**___________________________________PAGE METHODS_________________________________*/

    public WebElement findMultifieldByTitle(String title){
        return findMultifieldByTitle(title, 1);
    }

    public WebElement findMultifieldByTitle(String title, int num){
        return findElement(By.xpath(".//div[./label[text()='" + title + "']]/coral-multifield/coral-multifield-item["
                + num + "]/coral-multifield-item-content/input"));
    }

    //todo: need to check if "Add" btn is available
    public void addValueToMultifield(String title, String text){
        WebElement multifield = findElement(By.xpath(".//div[./label[text()='" + title + "']]/coral-multifield"));
        if(isElementPresent(multifield.findElement(byText("Add")))){
            multifield.findElement(byText("Add")).click();
        }
        for (WebElement field : multifield.findElements(By.xpath("//coral-multifield-item-content"))){
            if(field.getText().isEmpty()){
                Actions actions = new Actions(driver());
                actions.moveToElement(field);
                actions.click();
                actions.sendKeys(text);
                actions.build().perform();
            }
        }

    }

    public boolean verifyMetadata(String itapPath){
        CsvReader csv = new CsvReader(itapPath);

        return false;
    }

    public List<String> getMetadataVal(){
        return new ArrayList<>();
    }

    public List<WebElement> getMetadataFields(){
        List<WebElement> tabList = findElements(tabs);
        List<WebElement> fieldTitleList = findElements(fieldTitles);
        List<WebElement> fieldList;
        for(WebElement element : fieldTitleList){
            fieldsList.add(element.getText());
            //todo
        }
        return new ArrayList<>();
    }

    public List<String> getMultifieldValues(String title){
        List<String> values = new ArrayList<>();
        for (WebElement field : findElements(By.xpath(".//div[./label[text()='" + title +
                "']]/coral-multifield/coral-multifield-item/coral-multifield-item-content/input"))){
            values.add(field.getAttribute("value"));
        }
        return values;
    }

    public boolean verifyMetadata(String... nameValues){
        boolean ok = true;
        for(String nameValue : nameValues){
            String[] nameAndValue = nameValue.split("/");
            for(String value : getMultifieldValues(nameAndValue[0])){
                boolean field = false;
                if(value.equals(nameAndValue[1])){
                    field = true;
                }
                reporter.info("Expected " + nameAndValue[0] + " - " + nameAndValue[1] + "; Actual " +
                        nameAndValue[0] + " - " + value);
                if(!field){ok=false;}
            }
        }
        return ok;
    }

    public boolean verifyRelation(String assetName, String type){
        clickByText("References");
        return findElement(By.xpath("//div[@data-fieldlabel='" + type +
                "']/div[@assetsreferences-wrapper]/a/coral-anchorbutton-label")).getText().equals(assetName);
    }
}
