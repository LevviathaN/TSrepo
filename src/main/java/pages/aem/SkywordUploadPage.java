package pages.aem;


import org.openqa.selenium.By;

public class SkywordUploadPage extends AemBasePage {

    private static SkywordUploadPage instance;
    public static SkywordUploadPage Instance = (instance != null) ? instance : new SkywordUploadPage();

    public SkywordUploadPage(){ pageURL = "/skywordupload.html"; }

    /**__________________________________PAGE ATTRIBUTES_______________________________*/
    /*Fast actions icons locators*/
    By pathSelect = By.xpath(".//coral-icon[@aria-label='select']");



    /**___________________________________PAGE METHODS_________________________________*/

    public void uploadSkywordXml(String destination, String file){
        selectDestination(destination);
        clickByText("Browse...");
        uploadFile(file);
        clickByText("Submit");
    }

    void selectDestination(String path){
        String _path = new String(path);
        String lastFol = "";
        clickOnElement(pathSelect);
        for(String fol : _path.split("/")){
            expandFolder(fol);
            lastFol = fol;
        }
        selectFolder(lastFol);
        clickByText("Select");
    }

    void selectFolder(String folderName){
        clickOnElement(By.xpath("//*[./coral-columnview-item-content[./div[text()='" + folderName
                + "']]]/coral-columnview-item-thumbnail"));
    }

    void expandFolder(String folderName){
        clickByText(folderName);
    }

    String[] getResults(){
        String[] parts = findElement(By.xpath("//pre")).getText().split("\n");
        for(String part : parts) {
            reporter.info("'"+part+"'");
        }
        return parts;
    }

    public boolean isSkywordErrors(){
        boolean res = false;
        for(String result :getResults()){
            res = result.contains("Already exists");
        }
        return res;
    }
}
