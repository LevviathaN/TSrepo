package ui.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;  //імпортуємо необхідні бібліотеки
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.*;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.logging.Level;

/**
 * Created by odiachuk on 13.12.16.
 */
public class DriverProvider {

    public static String OS_EXTENTION = (System.getProperty("os.name").toLowerCase().contains("win")) ? ".exe" :
            (System.getProperty("os.name").toLowerCase().contains("mac")) ? "_mac" :
                    "_linux";
    static String FIREFOX_PATH = "drivers/geckodriver" + OS_EXTENTION;
    static String CHROME_PATH = "drivers/chromedriver" + OS_EXTENTION;

    //private static WebDriver instance;
    public static ThreadLocal<WebDriver> instance = new ThreadLocal<WebDriver>();

    static String BROWSER_TYPE;

    static public FirefoxDriver getFirefox() {

        System.setProperty("webdriver.gecko.driver", FIREFOX_PATH);

        DesiredCapabilities caps = DesiredCapabilities.firefox();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        return new FirefoxDriver(caps);

    }

    static public ChromeDriver getChrome(){

        System.setProperty("webdriver.chrome.driver", CHROME_PATH);

        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.OFF);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--kiosk");
//        chromeOptions.addArguments("--start-maximized");
//        chromeOptions.addArguments("--start-fullscreen");
//        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=1920,1080");

        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return new ChromeDriver(caps);

    }

    static public ChromeDriver getChromeBStack(){

        //downloads folder to automatically save the downloaded files
        File folder = new File("downloads");
        folder.mkdir();

        System.setProperty("webdriver.chrome.driver", CHROME_PATH);

        DesiredCapabilities caps = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.OFF);
        caps.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--kiosk");
//        chromeOptions.addArguments("--start-maximized");
//        chromeOptions.addArguments("--start-fullscreen");
//        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=1920,1080");

        HashMap<String, Object> chromePreferences = new HashMap<>();
        chromePreferences.put("profile.password_manager_enabled", "false");
        chromePreferences.put("credentials_enable_service", "false");
        chromePreferences.put("profile.default_content_settings.popups", 0);
        chromePreferences.put("download.default_directory", folder.getAbsolutePath());
        chromeOptions.setCapability("chrome.prefs", chromePreferences);

        chromeOptions.setCapability("browserstack.debug", "true");
        chromeOptions.setCapability("browserstack.video", "true");
        chromeOptions.setCapability("browserstack.networkLogs", "true");
        chromeOptions.setCapability("build", "automation");
        chromeOptions.setCapability("browserstack.local", "true");
        chromeOptions.setCapability("browserstack.localIdentifier", "TestAutomation");
        chromeOptions.setCapability("browserstack.geoLocation", "GB");
        chromeOptions.setCapability("browserstack.timeouts", "{\"implicit\"=>0, \"pageLoad\"=>60000, \"script\"=>60000}");

        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);


        return new ChromeDriver(caps);

    }


    public static WebDriver getDriver() throws MalformedURLException {
        //if (instance == null)
        if (instance.get() == null)
            if (getCurrentBrowserName().equals(BrowserType.FIREFOX)) {
                //instance = getFirefox();
                instance.set(getFirefox());
            }
            else if (getCurrentBrowserName().equals(BrowserType.CHROME)){
                //instance = getChrome();
                instance.set(getChrome());
            }
            else if (getCurrentBrowserName().equals(BrowserType.CHROME)){
                //instance = getChrome();
                instance.set(getChromeBStack());
            }
        //return instance;
        return instance.get();
    }

    public static void closeDriver(){
        //instance.quit();
        instance.get().quit();
        //instance = null;
        instance.set(null);
    }

    public static String getCurrentBrowserName() {
        if (BROWSER_TYPE == null)
            if (FileIO.getConfigProperty("Driver").equals("firefox"))
                BROWSER_TYPE = BrowserType.FIREFOX;
            else if (FileIO.getConfigProperty("Driver").equals("chrome"))
                BROWSER_TYPE = BrowserType.CHROME;
            else if (FileIO.getConfigProperty("Driver").equals("bstack_chrome"))
                BROWSER_TYPE = BrowserType.CHROME;
        return BROWSER_TYPE;
    }
}
