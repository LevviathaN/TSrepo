package pages.aem;


import org.openqa.selenium.By;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class AssetsPage extends AemBasePage{

    private static AssetsPage instance;
    public static AssetsPage Instance = (instance != null) ? instance : new AssetsPage();

    public AssetsPage(){ pageURL = "/assets.html/content/dam"; }

    /**__________________________________PAGE ATTRIBUTES_______________________________*/
    /*Fast actions icons locators*/
    By fastSelect = By.xpath(".//coral-icon[@aria-label='check']");
    By fastDownload = By.xpath(".//coral-icon[@aria-label='download']");
    By fastProperties = By.xpath(".//coral-icon[@aria-label='info circle']");
    By fastMore = By.xpath(".//coral-icon[@aria-label='more']");
    By fastToCollection = By.xpath(".//coral-icon[@aria-label='collection add']");




    /**___________________________________PAGE METHODS_________________________________*/

    public void uploadAsset(String localFilePath, String... givenAssetName){
        try {
            findElement(createBtn).click();
            findByText("Files").click();

            sleepFor(2000);

            //File Need to be imported
            File file = new File(localFilePath);
            StringSelection stringSelection= new StringSelection(file.getAbsolutePath());

            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            Robot robot = new Robot();

        // Cmd + Tab is needed since it launches a Java app and the browser looses focus
            reporter.info("Executing Cmd + Tab");
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_TAB);
            robot.delay(500);

        //Open Goto window
            reporter.info("Opening Goto Window");
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_G);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_G);

        //Paste the clipboard value
            reporter.info("Pasting from clipboard");
            robot.keyPress(KeyEvent.VK_META);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_META);
            robot.keyRelease(KeyEvent.VK_V);

        //Press Enter key to close the Goto window and Upload window
            reporter.info("\"Executing Cmd + Tab\"");
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
        catch(Exception e){
            reporter.info("Ooops");
        }
        sleepFor(1000);
        findByText("Upload").click();
        sleepFor(5000);
    }

    public void deleteSelectedAssets(){
        clickOnAnyElement(fastMore);
        findByText("Delete").click();
        clickOnAnyElement(byText("Delete"));
    }

    public void publishSelectedAssets(){
        clickOnAnyElement(fastMore);
        findByText("Quick Publish").click();
        clickOnAnyElement(byText("Publish to Brand Portal"));
        clickOnAnyElement(byText("Publish"));
    }

    public void unpublishAsset(String assetName){}

    public void selectAsset(String assetName){
        hoverItem(byText(assetName));
        clickOnAnyElement(fastSelect);
    }

    public void navigate(String path){
        reporter.info("Navigate to: " + path);
        openUrl(BASE_URL + pageURL + path);
    }

    /**________________________LOGICAL ASSERTIONS__________________________*/
    public boolean isAssetPresent(String astName){
        return isElementPresent(byText(astName));
    }
}
