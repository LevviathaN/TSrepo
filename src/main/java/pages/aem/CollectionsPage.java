package pages.aem;

import org.openqa.selenium.By;

public class CollectionsPage extends AemBasePage {

    private static CollectionsPage instance;
    public static CollectionsPage Instance = (instance != null) ? instance : new CollectionsPage();

    public CollectionsPage(){ pageURL = "/mnt/overlay/dam/gui/content/collections.html/content/dam/collections"; }

    /**__________________________________PAGE ATTRIBUTES_______________________________*/
    /*Fast actions icons locators*/
    By collectionTitleField = By.xpath(".//*[@id='collectiontitle']");




    /**___________________________________PAGE METHODS_________________________________*/

    public void createCollection(String title){
        reporter.info("Creating collection with title '"+title+"'");
        findByText("Create").click();
        setText(collectionTitleField,title);
        findByText("Create").click();
        findByText("OK").click();
    }

    public boolean isCollectionPresent(String colName){
        reporter.info("Verifying " + colName + " collection presence");
        return isElementPresent(By.xpath("//coral-card-title[text()='" + colName + "']"));
    }
}
