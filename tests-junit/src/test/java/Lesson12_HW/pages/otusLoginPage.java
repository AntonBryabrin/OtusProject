package Lesson12_HW.pages;

import configuration.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;


public class otusLoginPage extends AbstractPage {
    TestConfig config;
    Logger logger = LogManager.getLogger(otusLoginPage.class);

    private By search = By.xpath("//input[@name='q']");
    private By loginButton = By.xpath("//button[@class='header2__auth js-open-modal']");
    private By emailField = By.xpath("//form[contains(@action, 'login')]/*/input");
    private By passwordField = By.xpath("//form[contains(@action, 'login')]/*//input[@name='password']");


    public otusLoginPage(WebDriver driver) {
        super(driver);
    }


    public otusLoginPage open() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        config = ConfigFactory.create(TestConfig.class);
        driver.get(config.hostnameOtus());
        logger.info("Открыта главная страница отуса");
        driver.findElement(loginButton).click();
        logger.info("Открыта страница логина");

        return this;
    }

    public otusLoginPage login(String login, String password) throws InterruptedException {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(emailField).sendKeys(login);
        logger.info("введен логин");
        driver.findElement(passwordField).sendKeys(password);
        logger.info("введен пароль");
        driver.findElement(By.xpath("//button[@class='new-button new-button_full new-button_blue new-button_md']")).click();


        return new otusLoginPage(driver);
    }

}
