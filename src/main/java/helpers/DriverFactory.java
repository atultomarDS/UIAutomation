package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

    public static final Logger logger = Logger.getLogger(DriverFactory.class);
    public static WebDriver driver = null;
    private static String browser = FileLoader.getBrowser();
    private static String executionMode = FileLoader.getExecutionMode();
    private static String seleniumHubURL = FileLoader.getSeleniumHubURL();

    public DriverFactory() {
        initDriver();
    }

    private static void initDriver() {
        logger.info("Initializing the WebDriver ...");
        switch (browser) {
            case "FIREFOX":
                logger.info("Initializing Firefox driver ....");
                if (executionMode.equalsIgnoreCase("Local")) {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                } else try {
                    DesiredCapabilities cap = DesiredCapabilities.firefox();
                    cap.setBrowserName("firefox");
                    cap.setPlatform(Platform.LINUX);
                    driver = new RemoteWebDriver(new URL(seleniumHubURL), cap);
                } catch (MalformedURLException e) {
                    e.getMessage();
                    e.printStackTrace();
                }
                break;
            case "CHROME":
                logger.info("Initializing Chrome driver ...");
                if (executionMode.equalsIgnoreCase("Local")) {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                } else try {
                    logger.info("Initializing headless chrome driver ....");
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("start-maximized");
                    options.addArguments("--headless");
                    driver = new RemoteWebDriver(new URL(seleniumHubURL), options);
                } catch (MalformedURLException e) {
                    e.getMessage();
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;

    }

}
