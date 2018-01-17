package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;  //імпортуємо необхідні бібліотеки
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

/**
 * Created by odiachuk on 13.12.16.
 */
public class DriverProvider {

    static String OS_EXTENTION = (System.getProperty("os.name").toLowerCase().contains("win")) ? ".exe" :
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
        chromeOptions.addArguments("--kiosk");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--start-fullscreen");

        caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return new ChromeDriver(caps);

    }

    static public RemoteWebDriver getMobileSafari() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("udid", "auto");
        capabilities.setCapability("deviceName", "iPhone 6s");
        capabilities.setCapability("platformName", "ios");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "safari");
        capabilities.setCapability("startIWDP", "true");
        capabilities.setCapability("ensureCleanSession", true);
        capabilities.setCapability("newCommandTimeout", 300);

        return new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);

    }

    static public RemoteWebDriver getMobileChrome() throws MalformedURLException{

        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("device","Android");
        capabilities.setCapability("udid", "3RP4C17B14005745");//honor
        //capabilities.setCapability("udid", "ad0616030849a4d2e1");//S7
        capabilities.setCapability("platformVersion", "7.1.1");
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("newCommandTimeout", 300);

        return new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"),capabilities);

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
            else if (getCurrentBrowserName().equals("ios"))
                instance.set(getMobileSafari());
            else if (getCurrentBrowserName().equals("android"))
                instance.set(getMobileChrome());

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
            else if (FileIO.getConfigProperty("Driver").equals("safari"))
                BROWSER_TYPE = "ios";
            else
                BROWSER_TYPE = "android";
        return BROWSER_TYPE;
    }
}
