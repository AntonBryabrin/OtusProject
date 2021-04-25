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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;




public class Lesson7_HW {
    private Logger logger = LogManager.getLogger(Lesson3_HW.class);
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
    public void contactsOtusAddressCheck() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        driver.get(config.hostnameOtus());
        logger.info("Открыта страница отус");
        driver.findElement(By.xpath("//a[@href='/contacts/']")).click();
        logger.info("Переход на контакты");
        String actual = driver.findElement(By.xpath("//div[contains(text(),'Адрес')]/following-sibling::div")).getText();
        Assert.assertEquals("125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02", actual);
    }

        @Test
        public void tele2Check() throws InterruptedException {
            WebDriverWait wait = new WebDriverWait(driver, 2);
            //driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get("https://msk.tele2.ru/shop/number");
            logger.info("Открыта страница tele2");
            String actual = driver.getTitle();
            Assert.assertEquals(config.titleTele2(), actual);
            driver.findElement(By.xpath("//input[@id='searchNumber']")).clear();
            driver.findElement(By.xpath("//input[@id='searchNumber']")).sendKeys("22");
            //Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.preloader-icon")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.preloader-icon")));





        }

    @Test
    public void otusFaqCheck() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 2);

        driver.get(config.hostnameOtus());
        logger.info("Открыта страница otus");
        driver.findElement(By.xpath("//a[@href='/faq/']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Где посмотреть программу интересующего курса?')]")));
        driver.findElement(By.xpath("//div[contains(text(),'Где посмотреть программу интересующего курса?')]")).click();
        String actual = driver.findElement(By.xpath("//div[contains(text(),'Где посмотреть программу интересующего курса?')]/following-sibling::div")).getText();
        Assert.assertEquals(config.otusFaqCheckAnswer(), actual);








    }


    }


