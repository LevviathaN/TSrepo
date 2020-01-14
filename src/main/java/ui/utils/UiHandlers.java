package ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pages.BasePage;
import ui.utils.bpp.JavaScriptHelper;

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
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='progressbar']")));
        page.findElement(element).click();
    }),

    JAVASCRIPT_TO_UPDATE((element, e) -> {
        JavaScriptHelper.waitForJavaScriptToRun(BasePage.driver(),10);
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
