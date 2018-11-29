package pages;

import helpers.DriverFactory;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    public static final Logger logger = Logger.getLogger(BasePage.class);
    WebDriver driver;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        logger.info("Initialising elements of class " + this.getClass().toString());
        PageFactory.initElements(driver, this);
    }
}

