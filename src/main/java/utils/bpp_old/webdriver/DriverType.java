package utils.bpp_old.webdriver;

import utils.bpp_old.PreProcessFiles;
import utils.bpp_old.PropertiesHelper;
import utils.bpp_old.Reporter;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hemantojha
 *         <p>
 *         DriverType implements DriverSetup interface
 *         <p>
 *         It has enums for all the types of Driver Types
 *         This is what is passed from System Property variable as
 *         driver_id=FIREFOX
 *         </p>
 */
public enum DriverType implements DriverSetup {

    FIREFOX {

        public FirefoxOptions getDesiredDriverOptions() {
            FirefoxOptions options =
                    new FirefoxOptions();

            return options;
        }

        public <T> WebDriver getWebDriverObject(T instanceOptions) {
            try {

                configureGecko();

                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.folderList", 2);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                        "image/jpeg, application/pdf, application/octet-stream, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                profile.setPreference("pdfjs.disabled", true);

                if (determineEffectivePropertyValue("enable_ff_java").equalsIgnoreCase("true")) {
                    profile.setPreference("plugin.state.java", 2);
                }

                FirefoxOptions options = (FirefoxOptions) instanceOptions;

                options.setCapability(FirefoxDriver.PROFILE, profile);

                return new FirefoxDriver(options);
            } catch (Exception e) {
                throw new WebDriverException("Unable to launch the browser", e);
            }
        }
    },
    CHROME {
        public ChromeOptions getDesiredDriverOptions() {
            //downloads folder to automatically save the downloaded files
            File folder = new File("downloads");
            folder.mkdir();

            configureChrome();

            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--test-type");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-browser-side-navigation");
            options.addArguments("--disable-gpu");
            options.addArguments("enable-automation");
            options.setPageLoadStrategy(PageLoadStrategy.NONE);

           options.setCapability(ChromeOptions.CAPABILITY, options);
           options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

            options.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            HashMap<String, Object> chromePreferences = new HashMap<>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            chromePreferences.put("credentials_enable_service", "false");
            chromePreferences.put("profile.default_content_settings.popups", 0);
            chromePreferences.put("download.default_directory", folder.getAbsolutePath());

            options.setCapability("chrome.prefs", chromePreferences);

            return options;
        }

        public <T> WebDriver getWebDriverObject(T instanceOptions) {
            return new ChromeDriver((ChromeOptions) instanceOptions);
        }
    },

    BSTACK_FIREFOX {
        public FirefoxOptions getDesiredDriverOptions() {

            //Creating a profile
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                    "image/jpeg, application/pdf, application/octet-stream, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            profile.setPreference("pdfjs.disabled", true);
            //To skip the insecured password behaviour
            profile.setPreference("security.insecure_password.ui.enabled", false);
            profile.setPreference("security.insecure_field_warning.contextual.enabled", false);


            if (determineEffectivePropertyValue("enable_ff_java").equalsIgnoreCase("true")) {
                profile.setPreference("plugin.state.java", 2);
            }

            //Create Desired Capability Instance
            FirefoxOptions options = new FirefoxOptions();
            //configure capability with firefox version
            String ff_version = determineEffectivePropertyValue("ff_version");
            options.setCapability("browser_version", determineEffectivePropertyValue("ff_version"));
            options.setCapability("os", determineEffectivePropertyValue("os"));
            options.setCapability("os_version", determineEffectivePropertyValue("os_version"));
            options.setCapability("resolution", "1920x1080");
            options.setCapability("browserstack.debug", "true");
            options.setCapability("browserstack.video", "true");
            options.setCapability("browserstack.networkLogs", "true");
            options.setCapability("build", "automation");
            options.setCapability("browserstack.local", "true");
            options.setCapability("browserstack.localIdentifier", "TestAutomation");
            options.setCapability("browserstack.geoLocation", "GB");

            //configure capability for setting up Test Case name for Sauce Jobs
            String testName = Reporter.getCurrentTestName();
            options.setCapability("name", testName);
            options.setCapability(FirefoxDriver.PROFILE, profile);

            return options;
        }

        public <T> WebDriver getWebDriverObject(T instanceOptions) {

            try {
                RemoteWebDriver driver = new RemoteWebDriver(new URL(PropertiesHelper.determineEffectivePropertyValue("browserStackURL")), (FirefoxOptions) instanceOptions);
                driver.setFileDetector(new LocalFileDetector());
                return driver;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }
    },
    BSTACK_CHROME {
        public ChromeOptions getDesiredDriverOptions() {
            //downloads folder to automatically save the downloaded files
            File folder = new File("downloads");
            folder.mkdir();

            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);

            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--test-type");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-save-password-bubble");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-browser-side-navigation");
            options.addArguments("--disable-gpu");
            options.addArguments("enable-automation");
            options.setPageLoadStrategy(PageLoadStrategy.NONE);

            options.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
            HashMap<String, Object> chromePreferences = new HashMap<>();
            chromePreferences.put("profile.password_manager_enabled", "false");
            chromePreferences.put("credentials_enable_service", "false");
            chromePreferences.put("profile.default_content_settings.popups", 0);
            chromePreferences.put("download.default_directory", folder.getAbsolutePath());
            options.setCapability("chrome.prefs", chromePreferences);
            options.setCapability("browser_version", determineEffectivePropertyValue("ch_version"));
            options.setCapability("os", determineEffectivePropertyValue("os"));
            options.setCapability("os_version", determineEffectivePropertyValue("os_version"));
            options.setCapability("resolution", "1920x1080");
            options.setCapability("browserstack.debug", "true");
            options.setCapability("browserstack.video", "true");
            options.setCapability("browserstack.networkLogs", "true");
            options.setCapability("build", "automation");
            options.setCapability("browserstack.local", "true");
            options.setCapability("browserstack.localIdentifier", "TestAutomation");
            options.setCapability("browserstack.geoLocation", "GB");
            options.setCapability("browserstack.timeouts", "{\"implicit\"=>0, \"pageLoad\"=>60000, \"script\"=>60000}");
            options.setCapability(ChromeOptions.CAPABILITY, options);
            options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            options.setCapability(ChromeOptions.CAPABILITY, options);

            //configure capability to set the job name with Test Case name
            String testName = Reporter.getCurrentTestName();
            options.setCapability("name", testName);

            return options;
        }

        public <T> WebDriver getWebDriverObject(T instanceOptions) {

            try {
                RemoteWebDriver driver = new RemoteWebDriver(new URL(PropertiesHelper.determineEffectivePropertyValue("browserStackURL")), (ChromeOptions) instanceOptions);
                driver.setFileDetector(new LocalFileDetector());
                return driver;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;
        }
    },
    BSTACK_IE {
        public InternetExplorerOptions getDesiredDriverOptions() {
            InternetExplorerOptions options = new InternetExplorerOptions();

            options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
                    true);
            options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,
                    true);
            options.setCapability("requireWindowFocus",
                    true);
            options.setCapability("javascriptEnabled", true);
            options.setCapability("ignoreZoomSetting", true);
            options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            options.setCapability("nativeEvents", false);
            options.setCapability("unexpectedAlertBehaviour", "ignore");
            options.setCapability("ignoreProtectedModeSettings", true);
            options.setCapability("disable-popup-blocking", true);
            options.setCapability("enablePersistentHover", true);
            options.setCapability("browser_version", determineEffectivePropertyValue("ie_version"));
            options.setCapability("os", determineEffectivePropertyValue("os"));
            options.setCapability("os_version", determineEffectivePropertyValue("os_version"));
            options.setCapability("resolution", "1920x1080");
            options.setCapability("browserstack.debug", "true");
            options.setCapability("browserstack.video", "true");
            options.setCapability("browserstack.networkLogs", "true");
            options.setCapability("build", "automation");
            options.setCapability("browserstack.local", "true");
            options.setCapability("browserstack.localIdentifier", "TestAutomation");

            //configure capability to set the job name with Test Case name
            String testName = Reporter.getCurrentTestName();
            options.setCapability("name", testName);

            return options;
        }

        public <T> WebDriver getWebDriverObject(T instanceOptions) {

            try {
                RemoteWebDriver driver = new RemoteWebDriver(new URL(PropertiesHelper.determineEffectivePropertyValue("browserStackURL")), (InternetExplorerOptions) instanceOptions);
                driver.setFileDetector(new LocalFileDetector());
                return driver;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return null;

        }
    },
    IE {
        public InternetExplorerOptions getDesiredDriverOptions() {

            /** -------IE Driver should be properly configured in the OS to be fully functional. Below is the guide with the necessary configurations-------
             *
             * - The IEDriverServer exectuable must be downloaded and placed in your PATH.
             *
             * - On IE 7 or higher on Windows Vista or Windows 7, you must set the Protected Mode settings for each zone to be the same value.
             *   The value can be on or off, as long as it is the same for every zone. To set the Protected Mode settings, choose "Internet Options..."
             *   from the Tools menu, and click on the Security tab. For each zone, there will be a check box at the bottom of the tab labeled "Enable Protected Mode".
             *
             * - Additionally, "Enhanced Protected Mode" must be disabled for IE 10 and higher. This option is found in the Advanced tab of the Internet Options dialog.
             *
             * - The browser zoom level must be set to 100% so that the native mouse events can be set to the correct coordinates.
             *
             * - For Windows 10, you also need to set "Change the size of text, apps, and other items" to 100% in display settings.
             *
             * - For IE 11 only, you will need to set a registry entry on the target computer so that the driver can maintain a connection to the instance of Internet Explorer it creates.
             *   For 32-bit Windows installations, the key you must examine in the registry editor is HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE.
             *   For 64-bit Windows installations, the key is HKEY_LOCAL_MACHINE\SOFTWARE\Wow6432Node\Microsoft\Internet Explorer\Main\FeatureControl\FEATURE_BFCACHE.
             *   Please note that the FEATURE_BFCACHE subkey may or may not be present, and should be created if it is not present.
             *   Important: Inside this key, create a DWORD value named iexplore.exe with the value of 0.
             *   */

            configureIE();

            InternetExplorerOptions options =
                    new InternetExplorerOptions();
            options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION,
                    true);
            options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,
                    true);
            options.setCapability("requireWindowFocus",true);
            options.setCapability("nativeEvents", false);
            options.setCapability("unexpectedAlertBehaviour", "ignore");
            options.setCapability("ignoreProtectedModeSettings", true);
            options.setCapability("disable-popup-blocking", true);
            options.setCapability("enablePersistentHover", true);

            return options;
        }

        public <T> WebDriver getWebDriverObject(T instanceOptions) {
            return new InternetExplorerDriver((InternetExplorerOptions) instanceOptions);
        }
    },

    SAFARI {
        public SafariOptions getDesiredDriverOptions() {
            SafariOptions options =
                    new SafariOptions();
            options.setCapability("safari.cleansession",
                    true);

            return options;
        }

        public <T> WebDriver getWebDriverObject(T instanceOptions) {
            return new SafariDriver((SafariOptions) instanceOptions);
        }
    };

    /**
     * It configures the Gecko driver
     */
    private static void configureGecko() {

        String ff_version = determineEffectivePropertyValue("ff_version");
        if (null != ff_version && Double.parseDouble(ff_version) < 48) {
            System.setProperty("webdriver.firefox.marionette", "false");
        } else {
            String os = System.getProperty("os.name").toLowerCase();
            String geckoPath = null;
            if (os.indexOf("mac") >= 0) {
                geckoPath = PreProcessFiles.ROOT_FOLDER_PATH + "/vendors/gecko/mac/geckodriver";
            } else if (os.indexOf("win") >= 0) {
                geckoPath = PreProcessFiles.ROOT_FOLDER_PATH + "/vendors/gecko/win/geckodriver.exe";
            } else {
                throw new IllegalArgumentException("Operating System : " + os + " is not supported");
            }
            System.setProperty("webdriver.gecko.driver", geckoPath);
        }
    }

    /**
     * It configures the Chrome driver
     */
    private static void configureChrome() {
        String os = System.getProperty("os.name").toLowerCase();
        String chromePath = null;
        if (os.indexOf("mac") >= 0) {
            chromePath = PreProcessFiles.ROOT_FOLDER_PATH + "/vendors/chrome/mac/chromedriver";
        } else if (os.indexOf("win") >= 0) {
            chromePath = PreProcessFiles.ROOT_FOLDER_PATH + "/vendors/chrome/win/chromedriver.exe";
        } else {
            throw new IllegalArgumentException("Operating System : " + os + " is not supported");
        }
        System.setProperty("webdriver.chrome.driver", chromePath);
    }

    /**
     * It configures the Internet Explorer driver
     */
    private static void configureIE() {
        String os = System.getProperty("os.name").toLowerCase();
        String ieDriverPath = null;
        if (os.indexOf("mac") >= 0) {
            throw new IllegalArgumentException("Internet Explorer not available on Mac");
        } else if (os.indexOf("win") >= 0) {
            ieDriverPath = PreProcessFiles.ROOT_FOLDER_PATH + "/vendors/ie/IEDriverServer.exe";
        } else {
            throw new IllegalArgumentException("Operating System : " + os + " is not supported");
        }
        System.setProperty("webdriver.ie.driver", ieDriverPath);
    }


    /**
     * It returns the property value specified in either environment variable or configuration.properties
     * It gives priority to the property specified in Java environment variable For e.g. -Ddriver_id=FIREFOX
     *
     * @param key
     * @return
     */
    private static String determineEffectivePropertyValue(String key) {

        PropertiesHelper propertiesHelper = new PropertiesHelper();

        if (null != System.getProperty(key)) {
            return System.getProperty(key);
        } else {
            return propertiesHelper.getProperties().getProperty(key);
        }
    }
}