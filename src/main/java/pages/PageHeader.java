package pages;

import entities.ItemEntity;
import org.openqa.selenium.*;
import utils.FileIO;
import utils.Tools;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by odiachuk on 12.07.17.
 *
 *
 * Class contains all methods related with Top menu items and Cart items
 *
 */
public class PageHeader extends BasePage {

    private static PageHeader instance;
    public static PageHeader Instance = (instance != null) ? instance : new PageHeader();
    //top menu
    //By topMenuItem_Shop = By.xpath("//ul[@role='menu']//a[@role='menuitem']//span[text()='Shop']");
    By topMenuItem_Shop = By.xpath("//a[@id='ui-id-2']//span[text()='Shop']");
    By topMenuItem_Sleep = By.xpath("//ul[@role='menu']//a[@role='menuitem']//span[text()='Sleep']");
    By topMenuItem_Magazine = By.xpath("//ul[@role='menu']//a[@role='menuitem']//span[text()='Magazine']");
    By topMagazineMenuItem_Magazine = By.xpath(".//*[@id='menu-main-1']/li/a[text()='Magazine']");
    By topMenuItem_FAQ = By.xpath(".//header[@class='page-header']//*[text()='FAQ']");
    By topMenuItem_SignIn = By.xpath("//ul[@class='header links']//a[contains(text(),'Sign In')]");
    By topMenuItem_SignInStage = By.xpath("//ul[@class='header links']//a[contains(text(),'Account')]");
    By topMenuItem_Reviews = By.xpath("(//SPAN[text()='REVIEWS'][text()='REVIEWS'])[1]");
    By topMenuItem_Account = By.xpath("//ul[@class='header links']//span[text()='Account']");
    By topMenuItem_SignOut = By.xpath("//ul[@class='header links']//a[contains(text(),'Sign Out')]");

    By topMenuItem_Mattress = By.partialLinkText("MATTRESS");
    By topMenuItem_Accessories = By.partialLinkText("ACCESSORIES");
        By topMenuMemoryFoamPillow = By.partialLinkText("Memory Foam Pillow");
        By topMenuPlushPillow = By.partialLinkText("Plush Pillow");
        By topMenuComforter = By.partialLinkText("Comforter");
        By topMenuSheetSet = By.partialLinkText("Sheet Set");
        By topMenuProtector = By.partialLinkText("Protector");
        By topMenuSleeptrackerMonitor = By.partialLinkText("Sleeptracker Monitor");
        By topMenuDrapes = By.partialLinkText("Drapes");
    By topMenuItem_Bases = By.partialLinkText("BASES");
        By topMenuAdjustableBase = By.partialLinkText("Adjustable Base");
        By topMenuFoundation = By.partialLinkText("Foundation");

    //cart
    By showCartButton = By.cssSelector("a.action.showcart");
    //cart
    By cartItems = By.cssSelector("div.product-item-details");
    By cartItemName = By.cssSelector("strong.product-item-name");
    By cartItemContent = By.cssSelector("div.content");
    By cartItemQty = By.xpath("./div/div/label[text()='Qty']/following::input[1]");
    By cartItemPrice = By.xpath("./div/div[@class='price-container'][1]");
//    By cartItemPrice = By.cssSelector("span.minicart-price span.price");
    By cartBox = By.xpath("//div[@data-role='dropdownDialog']");
    By cartCheckoutButton = By.cssSelector("button#top-cart-btn-checkout");
    By viewCartButton = By.cssSelector("a.action.viewcart");
    By deleteCartButton = By.cssSelector("a.action.delete");
    By acceptDeletingFromCartButton = By.cssSelector("button.action-primary.action-accept");
    By cartItemDetails = By.cssSelector("dl.product.options.list span");
    By closeCartButton = By.id("btn-minicart-close");
    By cartQtyIndex = By.cssSelector("span.counter-number");
    By LOADING_SPINNER = By.cssSelector("div.fotorama__spinner");
    By closeImproveWindow = By.xpath("//DIV[@class='close mteo-close']");
    By wgBanner = By.id("wgdelban");
    By closeBannerButton =By.xpath(".//*[@id='wgdelban']/div");

    By menuMobile = By.cssSelector(".navbar-toggler-content");
    By menuItem_Shop_Mobile = By.xpath("//li[@class='level0 nav-1 first level-top parent']//span[contains(text(),'Shop')]");//By.xpath("//li[@class='level-top active]");
    By menuItem_FAQ_Mobile = By.xpath("//li[@class='level0 level-top']");
    By menuItem_Reviews_Mobile = By.xpath("//li[@class='level0 nav-6 last level-top']");

    PageHeader() {
        waitForPageToLoad();
    }

    // loader
    // img alt="Loading..."

    /**
     * Menu Methods
     */

    public ShopPage clickShopMenuItem() {
        reporter.info("Click on SHOP menu item");
        try{
            scrollToElement(driver().findElement(topMenuItem_Shop));
            clickOnElement(topMenuItem_Shop);
        }catch (TimeoutException e){
            reporter.info("Second attempt to click on SHO{ menu item");
            clickOnElement(topMenuItem_Shop);
        }

        return ShopPage.Instance;
    }

    public LoginPage clickSignInMenuItem() {
        reporter.info("Click on SIGN IN menu item");
        clickOnElement(topMenuItem_SignIn);
        return LoginPage.Instance;
    }

    public MagazinePage clickOnMagazineItem(){
        reporter.info("Click on MAGAZINE manu item");
        clickOnElement(topMenuItem_Magazine);
        return MagazinePage.Instance;
    }

    public MagazinePage clickOnMagazineItemMagPage() {
        reporter.info("Click on MAGAZINE menu item on the MAGAZINE page header");
        clickOnElement(topMagazineMenuItem_Magazine);
        return MagazinePage.Instance;
    }

    public ReviewsPage clickReviewsMenuItem(){
        reporter.info("Click on REVIEW menu item");
        if (FileIO.getConfigProperty("device").equals("mobile")) {
            waitForElement(menuMobile);
            findElement(menuMobile).click();

            waitForElement(menuItem_Reviews_Mobile);
            scrollToElement(driver().findElement(menuItem_Reviews_Mobile));
            findElement(menuItem_Reviews_Mobile).click();
        }
        else clickOnElement(topMenuItem_Reviews);
        return ReviewsPage.Instance;
    }

    public FaqPage clickFaqMenuItem() {
        reporter.info("Click on Help menu item");
        if (FileIO.getConfigProperty("device").equals("mobile")) {
            waitForElement(menuMobile);
            findElement(menuMobile).click();
            waitForElement(menuItem_FAQ_Mobile);
            findElement(menuItem_FAQ_Mobile).click();
        }

        else clickOnElement(topMenuItem_FAQ);
        return FaqPage.Instance;
    }

    /** Cart Methods */

    public PageHeader openCart() {
        reporter.info("Open Cart (Click on Show cart button)");
        driver().navigate().refresh();
        waitForPageToLoad();
        closeBanner();
        findElement(showCartButton).click();

        return this;
    }


    public boolean validateItemContentByTitle(String title, String... expectedContent) {
        boolean result = true;
        List<WebElement> currentCartItems = new ArrayList<WebElement>();
        String itemName = title;

        openCart();
        for (String expectedField : expectedContent) {
            currentCartItems = findElementsIgnoreException(cartItems);
            for (WebElement cartItem : currentCartItems) {
                if (cartItem.findElement(cartItemName).getText().contains(itemName)) {
                    String currentContent = cartItem.findElement(cartItemContent).getText();
                    if (currentContent.contains(expectedField)) {
                        reporter.pass("Current Item content: " + currentContent + ". Expected content: " + expectedField);
                        result = result && true;
                    } else {
                        reporter.fail("Current Item content: " + currentContent + ". Expected content: " + expectedField);
                        result = result && false;
                    }
                }
            }
        }
        if (currentCartItems.size() == 0) {
            reporter.fail("No Cart items were found");
            return false;
        }

        return result;
    }


    public ArrayList<ItemEntity> getAllCartItems() {
        ArrayList<ItemEntity> result = new ArrayList<>();

        openCart();

        List<WebElement> cartItemsList = findElementsIgnoreException(cartItems);
        for (WebElement cartItem : cartItemsList) {
            ItemEntity currentItem = new ItemEntity();

            currentItem.setTitle(cartItem.findElement(cartItemName).getText());
            currentItem.setQty(Integer.valueOf(cartItem.findElement(cartItemQty).getAttribute("data-item-qty")));
            currentItem.setPrice(Tools.convertStringPriceToFloat(cartItem.findElement(cartItemPrice).getText()));
            currentItem.setSize("");
            currentItem.setType("");

            List<WebElement> details = cartItem.findElements(cartItemDetails);

            for (WebElement elem : details) {
                String value = elem.getText();
                if (value.contains("(") && value.contains(")"))
                    currentItem.setSize(value);
                else
                    currentItem.setType(value);
            }

            result.add(currentItem);

        }
        if (cartItemsList.size() == 0) {
            reporter.info("No Cart items were found");
            //Assert.fail("No Cart items were found");
        }

        return result;
    }

    public boolean itemWasFoundInCart(ItemEntity item) {
        ArrayList<ItemEntity> items = getAllCartItems();
        reporter.info("Expected item: " + item.toString());
        return items.stream()
                .filter(cur -> item.getTitle().equals(cur.getTitle()))
                .filter(cur -> item.getQty() == cur.getQty())
                .filter(cur -> item.getPrice() == cur.getPrice())
                .filter(cur -> cur.getType().contains(item.getType()))
                .filter(cur -> cur.getSize().contains(item.getSize())).count() > 0;
    }

    public CheckoutPage clickOnCheckoutButton() {
        reporter.info("Click on Checkout button");
        openCart();
        if (FileIO.getConfigProperty("device").equals("mobile")){scrollToElement(driver().findElement(cartCheckoutButton));}
        clickOnElement(cartCheckoutButton);
        if (isElementPresent(closeImproveWindow)){
            clickOnElement(closeImproveWindow);
        }
        return CheckoutPage.Instance;
    }

    public ViewCartPage clickOnViewCartButton() {
        reporter.info("Click on View Cart button");
        openCart();
        if(FileIO.getConfigProperty("device").equals("mobile")){scrollToElement(driver().findElement(viewCartButton));}
        clickOnElement(viewCartButton);
        return ViewCartPage.Instance;
    }

    public void clickOnDeleteCartButton(ItemEntity item) {
        closeCart();
        openCart();
        List<WebElement> cartItemsList = findElementsIgnoreException(cartItems);
        for (int i = 0; i < cartItemsList.size(); i++) {
            WebElement cartItem = cartItemsList.get(i);
            if (cartItem.findElement(cartItemName).getText().contains(item.getTitle()) &&
                    Tools.convertStringPriceToFloat(cartItem.findElement(cartItemPrice).getText()) == item.getPrice() &&
                    cartItem.findElement(cartItemQty).getAttribute("data-item-qty").equals(String.valueOf(item.getQty()))) {
                cartItem.findElement(deleteCartButton).click();

                clickOnElement(acceptDeletingFromCartButton);
            }
        }
        reporter.info("Click on Delete Cart button");

    }

    public int getCountOfGoodsFromCartIcon() {
        reporter.info("Getting count of goods from cart's item");
        String[] result = findElement(showCartButton).getText().split("\n");
        reporter.info("Items on cart icon are equal to " + Integer.valueOf(result[0]));
        return Integer.valueOf(result[0]);
    }

    public int getCountOfGoodsInCart() {
        reporter.info("Counting sum of goods in the cart");
        openCart();
        int count = 0;
        List<WebElement> cartItemsList = findElementsIgnoreException(cartItems);
        for (int i = 0; i < cartItemsList.size(); i++) {
            WebElement cartItem = cartItemsList.get(i);
            count = count + Integer.valueOf(cartItem.findElement(cartItemQty).getAttribute("data-item-qty"));
        }
        reporter.info("Sum of goods in cart equals to " + count);
        closeCart();
        return count;
    }

    public void openMenuByItemName(String itemName) {
        switch (itemName){
            case "Mattress":
                hoverItem(By.xpath("//a[@id='ui-id-2']//span[text()='Shop']"));
                clickOnElement(topMenuItem_Mattress);
                break;
            case "Memory Foam Pillow":
                hoverItem(By.xpath("//a[@id='ui-id-2']//span[text()='Shop']"));
                clickOnElement(topMenuMemoryFoamPillow);
                break;
            case "Plush Pillow":
                hoverItem(By.xpath("//a[@id='ui-id-2']//span[text()='Shop']"));
                clickOnElement(topMenuPlushPillow);
                break;
            case "Comforter":
                hoverItem(By.xpath("//a[@id='ui-id-2']//span[text()='Shop']"));
                clickOnElement(topMenuComforter);
                break;
            case "Sheet Set":
                hoverItem(By.xpath("//a[@id='ui-id-2']//span[text()='Shop']"));
                clickOnElement(topMenuSheetSet);
                break;
            case "Protector":
                hoverItem(By.xpath("//a[@id='ui-id-2']//span[text()='Shop']"));
                clickOnElement(topMenuProtector);
                break;
            case "Sleeptracker Monitor":
                hoverItem(By.xpath("//a[@id='ui-id-2']//span[text()='Shop']"));
                clickOnElement(topMenuSleeptrackerMonitor);
                break;
            case "Drapes":
                hoverItem(By.xpath("//a[@id='ui-id-2']//span[text()='Shop']"));
                clickOnElement(topMenuDrapes);
                break;
            case "Adjustable Base":
                hoverItem(By.xpath("//a[@id='ui-id-2']//span[text()='Shop']"));
                clickOnElement(topMenuAdjustableBase);
                break;
            case "Tomorrow Foundation":
                hoverItem(By.xpath("//a[@id='ui-id-2']//span[text()='Shop']"));
                clickOnElement(topMenuFoundation);
                break;

            default:
                assert false : "There is no "+ itemName + " item ";
        }
    }

    public boolean waitUntilItemWillBeDropedToCart() {
        return isElementPresentAndDisplay(cartQtyIndex);
    }

    public void waitForLoading() {  // TODO finish
        if (isElementPresentAndDisplay(LOADING_SPINNER)) ;
    }

    public void closeCart() {
        if (isElementDisplayedRightNow(closeCartButton)) {
            reporter.info("Closing cart");
            clickOnElementIgnoreException(closeCartButton);
        }
        ;
    }
    public void closeBanner(){
        if (isElementDisplayedRightNow(wgBanner)){
            reporter.info("Closing banner");
            clickOnElementIgnoreException(closeBannerButton);
        }
        return;
    }

    public void clickSignOutMenuItem() {
        reporter.info("Click on SIGN Out menu item");
        findElement(topMenuItem_Account).click();
        clickOnElement(topMenuItem_SignOut);
    }
}
