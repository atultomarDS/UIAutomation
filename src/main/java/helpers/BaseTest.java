package helpers;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static final Logger logger = Logger.getLogger(BasePage.class);
    public static WebDriver driver = null;

    @AfterClass
    public static void tearDown() {
        if (driver == null) {
            return;
        }
        logger.info("Quitting browser... ");
        driver.quit();
        driver = null;
    }

    private static void maximizeWindow() {
        driver = DriverFactory.getDriver();
        logger.info("Maximizing Browser Window ... ");
        driver.manage().timeouts().implicitlyWait(FileLoader.getTimeout(), TimeUnit.SECONDS);
        logger.info("Entering fullscreen mode... ");
        driver.manage().window().fullscreen();
    }

    public void goToURL(String URL) {
        driver.get(URL);
    }

    public void verifyCurrentURL(String URL) {
        Assert.assertEquals("Url is not as expected", driver.getCurrentUrl(), FileLoader.getBaseURL() + URL);
    }

    @Before
    public void setup() {
        maximizeWindow();
        driver.get(FileLoader.getBaseURL());
    }
}
