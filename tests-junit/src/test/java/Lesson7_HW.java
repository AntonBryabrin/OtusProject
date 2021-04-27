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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
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
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);





    }

    @After
    public void end() {
        if (driver != null)
            driver.quit();
        logger.info("Драйвер закрыт");

    }


    @Test
    public void otusContactsAddressCheck() throws InterruptedException {

        driver.get(config.hostnameOtus());
        logger.info("Открыта страница отус");
        driver.findElement(By.xpath("//a[@href='/contacts/']")).click();
        logger.info("Переход на контакты выполнен");
        String actual = driver.findElement(By.xpath("//div[contains(text(),'Адрес')]/following-sibling::div")).getText();
        Assert.assertEquals("125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02", actual);
        logger.info("Адрес проверен");

    }

    @Test
    public void tele2Check() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 2);

        driver.manage().window().maximize();
        driver.get("https://msk.tele2.ru/shop/number");
        logger.info("Открыта страница tele2");
        String actual = driver.getTitle();
        Assert.assertEquals(config.titleTele2(), actual);
        logger.info("Title проверен");
        driver.findElement(By.xpath("//input[@id='searchNumber']")).clear();
        driver.findElement(By.xpath("//input[@id='searchNumber']")).sendKeys("22");
        logger.info("Введен номер");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.preloader-icon"))); // Ожидание что после ввода номера отобразился прелоадер
        logger.info("Отображен прелоадер");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.preloader-icon"))); // Ожидание что прелоадер скрылся и подгружены новые результаты

    }

    @Test
    public void otusFaqCheck() {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        driver.get(config.hostnameOtus());
        logger.info("Открыта страница otus");
        driver.findElement(By.xpath("//a[@href='/faq/']")).click();
        logger.info("Открыт FAQ");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(),'Где посмотреть программу интересующего курса?')]")));
        driver.findElement(By.xpath("//div[contains(text(),'Где посмотреть программу интересующего курса?')]")).click();
        logger.info("Дропдаун открыт");
        String actual = driver.findElement(By.xpath("//div[contains(text(),'Где посмотреть программу интересующего курса?')]/following-sibling::div")).getText();
        Assert.assertEquals(config.otusFaqCheckAnswer(), actual);
        logger.info("Ответ проверен");

    }

    @Test
    public void otusSubscribeСheck() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        driver.get(config.hostnameOtus());
        logger.info("Открыта страница отус");
        driver.findElement(By.cssSelector("input.input.footer2__subscribe-input")).clear();
        driver.findElement(By.cssSelector("input.input.footer2__subscribe-input")).sendKeys("qwe@qwe.qwe");
        logger.info("Введен email");
        driver.findElement(By.cssSelector("button.footer2__subscribe-button")).click();
        logger.info("Нажата кнопка подписки");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.subscribe-modal__success")));
        logger.info("Вы успешно подписались - отображено");

    }
}


