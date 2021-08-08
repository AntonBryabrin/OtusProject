package OtusFinal_HW.pages;

import configuration.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class otusCabinetPage extends AbstractPage {
    TestConfig config;
    Logger logger = LogManager.getLogger(otusCabinetPage.class);

    private By userMenu = By.xpath("//p[@class='header2-menu__item-text header2-menu__item-text__username']");
    private By lkLink = By.xpath("//a[@href='/learning/']");
    private By personal = By.xpath("//div[@class='nav__items']/a[@href='/lk/payment/']/following::a[@href='/lk/biography/personal/']");
    private By userName = By.xpath("//label[text()='Имя']/following::input[@name='fname']");
    private By userNameLat = By.xpath("//label[text()='Имя (латиницей)']/following::input[@name='fname_latin']");
    private By userSecondName = By.xpath("//label[text()='Фамилия']/following::input[@name='lname']");
    private By userSecondNameLat = By.xpath("//label[text()='Фамилия (латиницей)']/following::input[@name='lname_latin']");
    private By userBlogName = By.xpath("//label[text()='Имя (в блоге)']/following::input[@name='blog_name']");
    private By userBirthday = By.xpath("//label[text()='Имя (в блоге)']/following::div/input[@title='День рождения']");
    private By userCountry = By.xpath("//div[@data-ajax-slave='/lk/biography/cv/lookup/cities/by_country/']");
    private By userCountryButton = By.xpath("//button[@title='Россия']");
    private By userCity = By.xpath("//input[@name = 'city']/following::div[1]/span");
    private By userCityButton = By.xpath("//button[@title='Азов']");
    private By userEnglish = By.xpath("//input[@name = 'english_level']/following::div[1]");
    private By userEnglishButton = By.xpath("//button[@title='Средний (Intermediate)']");
    private By addButton = By.xpath("//button[text()='Добавить']");
    private By contactChecker = By.xpath("//span[text()='Способ связи']");
    private By contactFacebook = By.xpath("//button[@title='Facebook']");
    private By contactTelegram = By.xpath("(//button[@title='Тelegram'])[2]");
    private By contactValue0 = By.xpath("//input[contains(@id, 'contact') and not (@type = 'hidden') and @type = 'text']");
    private By contactValue1 = By.xpath("//input[@id='id_contact-1-value']");
    private By firstcontactType = By.xpath("//input[@name='contact-0-service']/following::div[1]");
    private By secondcontactType = By.xpath("//input[@name='contact-1-service']/following::div[1]");
    private By firstcontactValue = By.xpath("//input[@name='contact-0-service']");
    private By secondcontactValue = By.xpath("//input[@name='contact-1-service']");
    private By deleteButton = By.xpath("//div[not(contains(@class, 'container__col_md-0'))]/div[@class=\"lk-cv-block__input-alignment\"]/button[text()='Удалить']");
    private By saveButton = By.xpath("//button[@title=\"Сохранить и заполнить позже\"]");

    public otusCabinetPage(WebDriver driver) {
        super(driver);
    }


    public otusCabinetPage open() {
        Actions action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        config = ConfigFactory.create(TestConfig.class);

        WebElement userMenue = driver.findElement(userMenu);
        action.moveToElement(userMenue).perform();
        driver.findElement(lkLink).click();
        logger.info("Открыт личный кабинет");
        driver.findElement(personal).click();
        logger.info("Открыта личная информация");


        return this;
    }

    public otusCabinetPage fillInfo(){

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        config = ConfigFactory.create(TestConfig.class);
        Actions action = new Actions(driver);

        driver.findElement(userName).clear();
        driver.findElement(userName).sendKeys(config.usernameOtus());
        logger.info("заполнено имя");
        driver.findElement(userName).sendKeys(Keys.TAB);
        driver.findElement(userNameLat).clear();
        driver.findElement(userNameLat).sendKeys(config.usernameLatOtus());
        logger.info("заполнено имя латиница");
        driver.findElement(userNameLat).sendKeys(Keys.TAB);
        driver.findElement(userSecondName).clear();
        driver.findElement(userSecondName).sendKeys(config.userSecondnameOtus());
        logger.info("заполнена фамилия");
        driver.findElement(userSecondName).sendKeys(Keys.TAB);
        driver.findElement(userSecondNameLat).clear();
        driver.findElement(userSecondNameLat).sendKeys(config.userSecondnameLatOtus());
        logger.info("заполнена фамилия латиница");
        driver.findElement(userSecondNameLat).sendKeys(Keys.TAB);
        driver.findElement(userBlogName).clear();
        driver.findElement(userBlogName).sendKeys(config.userBlognameOtus());
        logger.info("заполнено имя в блоге");
        driver.findElement(userBlogName).sendKeys(Keys.TAB);
        driver.findElement(userBirthday).clear();
        driver.findElement(userBirthday).sendKeys(config.userBirthdayOtus());
        logger.info("заполнено ДР");
        driver.findElement(userCountry).click();
        driver.findElement(userCountryButton).click();
        driver.findElement(userCity).click();
        driver.findElement(userCityButton).click();
        driver.findElement(userEnglish).click();
        driver.findElement(userEnglishButton).click();
        driver.findElement(addButton).click();
        driver.findElement(contactChecker).click();
        driver.findElement(contactFacebook).click();
        logger.info("выбран тип контакта фейсбук");
        driver.findElement(contactValue0).sendKeys(config.usercontact1valueOtus());
        logger.info("заполнен контакт фейсбук");
        driver.findElement(addButton).click();
        logger.info("нажата кнопка добавления контакта");
        driver.findElement(contactChecker).click();
        driver.findElement(contactTelegram).click();
        logger.info("выбран тип контакта телеграм");
        driver.findElement(contactValue1).sendKeys(config.usercontact2valueOtus());
        logger.info("заполнен контакт телеграм");
        driver.findElement(saveButton).click();
        logger.info("Изменения сохранены");

        return this;
    }

    public String getUserName() {
        return driver.findElement(userName).getAttribute("value");
    }

    public String getUserSecondName() {
        return driver.findElement(userSecondName).getAttribute("value");
    }

    public String getUserNameLat() {
        return driver.findElement(userNameLat).getAttribute("value");
    }

    public String getUserSecondNameLat() {
        return driver.findElement(userSecondNameLat).getAttribute("value");
    }

    public String getUserBlogName() {
        return driver.findElement(userBlogName).getAttribute("value");
    }

    public String getUserBirthday() {
        return driver.findElement(userBirthday).getAttribute("value");
    }

    public String getUserfirstcontactType() {
        return driver.findElement(firstcontactType).getText();
    }

    public String getUserfirstcontactValue() {return driver.findElement(firstcontactValue).getAttribute("value");}

    public String getUsersecondcontactType() {
        return driver.findElement(secondcontactType).getText();
    }
    public String getUsersecondcontactValue() {return driver.findElement(secondcontactValue).getAttribute("value");}




    public otusCabinetPage deleteContacts() {

        List<WebElement> deleteButtons = driver.findElements(deleteButton);

        for (WebElement we : deleteButtons) {
            we.click();
            logger.info("Удален контакт");
        }

        driver.findElement(saveButton).click();
        logger.info("Изменения сохранены");
        return this;
    }


}
