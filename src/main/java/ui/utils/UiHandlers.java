package ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pages.BasePage;

import java.util.HashMap;
import java.util.Map;
/**
 * @author rlevytskyi
 * <p>
 * Enum to handle different exceptions after click (e.c. spinners and modules that may overlay ui elements)
 * </p>
 */
public enum UiHandlers {

    PF_SPINNER_HANDLER((element, e) -> {
        BasePage page = new BasePage();
        BasePage.isHandled.put("pfSpinnerHandler", false);
        WebDriverWait wait = new WebDriverWait(BasePage.driver(), BasePage.DEFAULT_TIMEOUT,300);
        if (e.getMessage().contains("opacity: 1; transition: opacity 225ms cubic-bezier")){
            Reporter.log("Handling PF Spinner");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='progressbar']")));
            page.findElement(element).click();
            BasePage.isHandled.put("pfSpinnerHandler", true);
        }
    }),

    PF_SCROLL_HANDLER((element, e) -> {
        BasePage page = new BasePage();
        BasePage.isHandled.put("pfScrollHandler", false);
        if(e.getMessage().contains("Other element would receive the click: <button")){
            Reporter.log("Handling PF Edit button click overlay");
            BasePage.scrollBy(0,100);
            page.findElement(element).click();
            BasePage.isHandled.put("pfScrollHandler", true);
        }
    }),

    ACCEPT_ALERT((element, e) -> {
        BasePage page = new BasePage();
        BasePage.isHandled.put("acceptAert", false);
        if (e.getCause().toString().contains(" Are you sure want to review this application?")
                |e.getCause().toString().contains(" Please make sure that the EPA Gateway Time is set correctly for this application before continuing. Do you wish to proceed changing the application status?")) {
            Reporter.log("Handling JS Alert");
            page.acceptAlertMessage();
            BasePage.isHandled.put("acceptAlert", true);
        }
    }),

    DEFAULT_HANDLER((element, e) -> {
        boolean handled = false;
        for (Boolean value : BasePage.isHandled.values()) {
            if (value) handled = true;
        }
        if (!handled){
            Reporter.log("Default Handler: FAIL");
            Reporter.fail(Tools.getStackTrace(e));
            throw new RuntimeException("Failure clicking on element");
        }
    });

    private UiHandler handler;

    private static final Map<UiHandler, UiHandlers> lookup = new HashMap<UiHandler, UiHandlers>();

    static {
        for (UiHandlers handlers : UiHandlers.values()) {
            lookup.put(handlers.getHandler(), handlers);
        }
    }

    UiHandlers(UiHandler handler){
        this.handler = handler;
    }

    public UiHandler getHandler(){
        return handler;
    }

}
