import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class Lesson12_HW {
    private Logger logger = LogManager.getLogger(Lesson12_HW.class);
    protected static WebDriver driver;
    private TestConfig config;

    @Before
    public void startUp() {
        config = ConfigFactory.create(TestConfig.class);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");

    }

    @After
    public void end() {
        if (driver != null)
            driver.quit();
        logger.info("Драйвер закрыт");

    }

    @Test
    public void marketCompareItemsCheck() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 25);


    }

}


