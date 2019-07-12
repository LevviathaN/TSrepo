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

    /*Content fragment creation*/
    By newContentFragmentTitleField = By.xpath(".//input[@data-bindtofield='name']");


    /**___________________________________PAGE METHODS_________________________________*/

    //Works only for mac. todo for windows
    public void uploadAsset(String localFilePath, String... givenAssetName){
        reporter.info("Uploading "+localFilePath+" to AEM");
//        if(isElementPresent(byText("Replace"))){
//            reporter.info(localFilePath + " is already uploaded. Replacing with new");
//            findByText("Replace").click();
//        }
        findElement(createBtn).click();
        clickByText("Files");
        sleepFor(2000);
        uploadFile(localFilePath);
        sleepFor(1000);
        clickByText("Upload");
        sleepFor(5000);
        reloadPage();
        sleepFor(2000);
    }

    public void deleteSelectedAssets(){
        reporter.info("Deleting selected assets");
        clickOnAnyElement(fastMore);
        clickByText("Delete");
        clickOnAnyElement(byText("Delete"));
        if(isElementPresentAndDisplay(byText("Force Delete"))){
            clickOnAnyElement(byText("Delete"));
            reporter.info("FORCE DELETE prompted");
            sleepFor(5000);
            clickOnAnyElement(byText("Delete"));
        }
    }

    public void publishSelectedAssets(){
        reporter.info("Publishing selected assets");
        clickOnAnyElement(fastMore);
        clickByText("Quick Publish");
        clickOnAnyElement(byText("Publish to Brand Portal"));
        clickOnAnyElement(By.xpath("//coral-button-label[text()='Publish']"));
    }

    public void unpublishAsset(String assetName){}

    public void addSelectedAssetsToCol(String colName){
        reporter.info("Adding selected assets to '" + colName + "' collection");
        clickOnAnyElement(fastMore);
        clickByText("To Collection");
        clickByText(colName);
        clickByText("Add");
        clickByText("Close");
    }

    public void downloadSelectedAssets(){
        reporter.info("Downloading selected assets");
//        clickOnAnyElement(fastMore);
        clickOnAnyElement(byText("Download"));
        clickOnElement(By.xpath("(//coral-button-label[text()='Download'])[3]"));
        sleepFor(2000);
    }

    public void deleteDownloadedAsset(String assetName){
        reporter.info("Deleting downloaded asset (cleanup)");
        File f = new File("/Users/ruslanlevytskyi/Downloads/" + assetName + ".zip"); //todo: path is hardcoded
        f.deleteOnExit();
    }

    public void selectAsset(String assetName){
        reporter.info("Selecting '"+assetName+"' asset");
        hoverItem(By.xpath("//coral-card-title[text()='" + assetName + "']"));
        clickOnAnyElement(fastSelect);
    }

    public void clickOnAsset(String name){
        reporter.info("Clicking on '" + name + "' asset");
        clickOnAnyElement(byText(name));
    }

    public void createContentFragment(String cfName){
        reporter.info("Creating Content Fragment with name '" + cfName + "'");
        findElement(createBtn).click();
        clickByText("Content Fragment");
        clickByText("Simple Fragment");
        clickByText("Next");
        setText(newContentFragmentTitleField, cfName);
        clickByText("Create");
        clickByText("Done");
    }

    public void createCfVariation(String varName){
        reporter.info("Creating variation named '" + varName + "' for current CF");
        clickByText("Create Variation");
        populateFieldByTitle("Title *",varName);
        clickByText("Add");
    }


    /**________________________LOGICAL ASSERTIONS__________________________*/
    public boolean isAssetPresent(String assetName){
        reporter.info("Verifying '" + assetName + "' asset presence");
        return isElementPresent(By.xpath("//coral-card-title[text()='" + assetName + "']"));
    }

    public boolean isAssetDownloaded(String assetName){
        reporter.info("Verifying that '" + assetName + "' asset was downloaded");
        File f = new File("/Users/ruslanlevytskyi/Downloads/" + assetName + ".zip"); //todo: path is hardcoded
        return f.exists();
    }
}
