package pages;

import org.openqa.selenium.By;

/**
 * Created by odiachuk on 07.07.17.
 */
public class ShopPage extends BasePage{

    private static ShopPage instance;
    public static ShopPage Instance = (instance != null) ? instance : new ShopPage();

    public ShopPage(){
        pageURL = "/shop";
    }

    /** UI Mappings */

    By shopOurMattressButton = By.xpath("(//a[text()='Shop Our Hybrid Mattress'])[1]");
    By shopOurFoamPillowButton = By.xpath("(//a[text()='SHOP OUR PILLOW'])[1]");
    By shopOurPlushPillowButton = By.xpath("(//a[text()='SHOP OUR PILLOW'])[2]");

    By shopOurTrackerButton = By.xpath("(//a[text()='SHOP OUR TRACKER'])[1]");
    By shopOurSheetsButton = By.xpath("(//a[text()='SHOP OUR SHEETS'])[1]");
    By shopOurComforterButton = By.xpath("(//a[text()='SHOP OUR COMFORTER'])[1]");
    By shopOurCoverButton = By.xpath("(//a[text()='SHOP OUR COVER'])[1]");
    By shopSheetsButton = By.xpath("(//a[text()='SHOP OUR SHEETS'])[1]");

    By shopOurDrapesButton = By.xpath("(//a[text()='SHOP OUR DRAPES'])[1]");
    By shopComforterButton = By.xpath("(//a[text()='SHOP OUR COMFORTER'])[1]");

    public String shopPageUrl = "https://www.tomorrowsleep.com/drapes";

    /** Page Methods */  //TODO remove close welcome message method

    public MattressesPage clickOnShopOurMattressButton() {
        closeWelcomeMessage();
        reporter.info("Click on Shop Our Mattress");
        scrollToShopElement(driver().findElement(shopOurMattressButton));
        findElement(shopOurMattressButton).click();
        return MattressesPage.Instance;
    }

    public FoamPillowPage clickOnShopOurFoamPillowButton() {
        closeWelcomeMessage();
        reporter.info("Click on Shop Our Pillow (Foam)");
        scrollToShopElement(driver().findElement(shopOurFoamPillowButton));
        findElement(shopOurFoamPillowButton).click();
        return FoamPillowPage.Instance;
    }

    public PlushPillowPage clickOnShopOurPlushPillowButton() {
        closeWelcomeMessage();
        reporter.info("Click on Shop Our Pillow (Plush)");
        scrollToShopElement(driver().findElement(shopOurPlushPillowButton));
        findElement(shopOurPlushPillowButton).click();
        return PlushPillowPage.Instance;
    }

    public MonitorPage clickOnShopOurMonitorButton() {
        closeWelcomeMessage();
        reporter.info("Click on Shop Our Monitor");
        scrollToShopElement(driver().findElement(shopOurTrackerButton));
        findElement(shopOurTrackerButton).click();
        return MonitorPage.Instance;
    }

    public SheetsetPage clickOnShopOurSheetsButton() {
        closeWelcomeMessage();
        reporter.info("Click on Shop Our Sheets");
        scrollToShopElement(driver().findElement(shopOurSheetsButton));
        findElement(shopOurSheetsButton).click();
        return SheetsetPage.Instance;
    }
    public FoundationPage clickOnShopFoundationButton(){
        closeWelcomeMessage();
        reporter.info("Click on Shop Foundation");
        clickOnElement(By.partialLinkText("Foundation"));
        return FoundationPage.Instance;
    }
    public AdjustablePage clickOnShopOurBaseButton() {
        closeWelcomeMessage();
        reporter.info("Click on Shop Our Adjustable Base");
        clickOnElement(By.partialLinkText("Adjustable Base"));
        return AdjustablePage.Instance;
    }

    public ComforterPage clickOnShopOurComforterButton() {
        closeWelcomeMessage();
        reporter.info("Click on Shop Our Comforter");
        scrollToShopElement(driver().findElement(shopOurComforterButton));
        findElement(shopOurComforterButton).click();
        return ComforterPage.Instance;
    }

    public MattressProtectorPage clickOnShopOurCoverButton() {
        closeWelcomeMessage();
        reporter.info("Click on Shop Our Cover");
        scrollToShopElement(driver().findElement(shopOurCoverButton));
        findElement(shopOurCoverButton).click();
        return MattressProtectorPage.Instance;
    }

    public DrapesPage clickOnShopOurDrapesButton() {
        closeWelcomeMessage();
        reporter.info("Click on Shop Our Drapes");
        scrollToShopElement(driver().findElement(shopOurDrapesButton));
        findElement(shopOurDrapesButton).click();
        return DrapesPage.Instance;
    }

    public SheetsetPage clickOnShopSheetsButton() {
        closeWelcomeMessage();
        scrollToShopElement(driver().findElement(shopSheetsButton));
        reporter.info("Click on Shop Our Sheets");
        findElement(shopSheetsButton).click();
        return SheetsetPage.Instance;
    }

    public ComforterPage clickOnShopComforterButton() {
        closeWelcomeMessage();
        scrollToShopElement(driver().findElement(shopComforterButton));
        reporter.info("Click on Shop Our Comforter");
        findElement(shopComforterButton).click();
        return ComforterPage.Instance;
    }

}
