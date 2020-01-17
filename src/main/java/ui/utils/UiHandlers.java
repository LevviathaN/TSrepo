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
 * Enum to handle spinners and modules that may overlay ui elements
 * </p>
 */
public enum UiHandlers {

    PF_SPINNER_HANDLER((element, e) -> {
        BasePage page = new BasePage();
        WebDriverWait wait = new WebDriverWait(BasePage.driver(), BasePage.DEFAULT_TIMEOUT,300);
        if (e.getMessage().contains("opacity: 1; transition: opacity 225ms cubic-bezier")){
            Reporter.log("Handling PF Spinner");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='progressbar']")));
            page.findElement(element).click();
        }
    }),

    PF_SCROLL_HANDLER((element, e) -> {
        BasePage page = new BasePage();
        if(e.getMessage().contains("Other element would receive the click: <")){
            Reporter.log("Handling PF Edit button click overlay");
            BasePage.scrollToBottomOfPage();
        }
    }),

    ACCEPT_ALERT((element, e) -> {
        BasePage page = new BasePage();
        if (e.getCause().toString().contains(" Are you sure want to review this application?")) {
            Reporter.log("Handling JS Alert");
            page.acceptAlertMessage();
        }
    }),

    DEFAULT_HANDLER((element, e) -> {
        Reporter.fail(Tools.getStackTrace(e));
        throw new RuntimeException("Failure clicking on element");
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
