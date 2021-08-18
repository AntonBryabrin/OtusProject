package OtusFinal_HW.utils;

import configuration.TestConfig;
import org.aeonbits.owner.ConfigFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static OtusFinal_HW.utils.WebDriverInit.initDriver;

public class BaseHooks {
    protected static WebDriver driver;


    @BeforeClass
    public static void setup() {

       // driver = WebDriverFactory.createDriverWithOptions(WebDriverType.CHROME, "start-maximized");
        driver = initDriver();
        TestConfig config;
        config = ConfigFactory.create(configuration.TestConfig.class);

        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }

    }

    @AfterClass
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @After
    public void cleanUp() {
        driver.manage().deleteAllCookies();
    }


    public static void restartDriver() {
        driver.manage().deleteAllCookies();         // Clear Cookies on the browser
        driver.quit();
        driver = null;
        driver = WebDriverFactory.createDriver(WebDriverType.CHROME);

    }





}
