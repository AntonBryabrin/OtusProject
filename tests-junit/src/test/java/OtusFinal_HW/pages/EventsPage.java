package OtusFinal_HW.pages;

import OtusFinal_HW.utils.BaseHooks;
import configuration.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class EventsPage extends AbstractPage {
    TestConfig config;
    Logger logger = LogManager.getLogger(EventsPage.class);


    private By search = By.xpath("//input[@name='q']");
    private By loginButton = By.xpath("//button[@class='header2__auth js-open-modal']");
    private By emailField = By.xpath("//form[contains(@action, 'login')]/*/input");
    private By passwordField = By.xpath("//form[contains(@action, 'login')]/*//input[@name='password']");


    private By eventsLinkLocator = By.xpath("//a[@class='nav-link' and @href='/events']");
    private By pastEventsLinkLocator = By.xpath("//a[contains(@class, 'evnt-tab-link nav-link') and .//span[contains(text(), 'Past Events')]]");
    private By eventCardLocator = By.xpath("//a/div[@class='evnt-card-wrapper']");
    private By upcomingCounterLocator = By.xpath("(//span[@class='evnt-tab-counter evnt-label small white'])[1]"); //Переделать на элемент без счета
    private By pastCounterLocator = By.xpath("(//span[@class='evnt-tab-counter evnt-label small white'])[2]"); //Переделать на элемент без счета

    private By languageLocator = By.xpath("//p[@class='language']/span");
    private By eventNameLocator = By.xpath("//div[@class='evnt-event-name']/*/span");
    private By preloaderLocator = By.xpath("//div[@class='evnt-global-loader']");
    //private By eventDateLocator = By.xpath(".//span");
   private By eventDateLocator = By.xpath(".//div[@class='evnt-dates-cell dates']/*/span[@class='date']");
    private By eventStatusLocator = By.xpath("//div[@class='evnt-dates-cell dates']/*/span[contains(@class, 'status')]");
    private By eventSpeakersCellLocator = By.xpath("//div[@class='evnt-people-table']");
    private By eventSingleSpeakerLocator = By.xpath("//div[@class='evnt-speaker']");
    private By upcomingEventsLinkLocator = By.xpath("//a[contains(@class, 'evnt-tab-link nav-link') and .//span[contains(text(), 'Upcoming events')]]");
    private By locationDropdownLocalor = By.xpath("//div[@id='filter_location']");
    private By dropdownCanadaLocalor = By.xpath("//label[@data-value='Canada']");



    public EventsPage(WebDriver driver) {
        super(driver);
    }

    public Boolean getEventLanguageDisplayed() {

        WebElement EventCardWebElement = driver.findElement(eventCardLocator);
        logger.info("язык в карточке: " + EventCardWebElement.findElement(languageLocator).isDisplayed());
        return EventCardWebElement.findElement(languageLocator).isDisplayed();
    }

    public Boolean getEventNameDisplayed() {

        WebElement EventCardWebElement = driver.findElement(eventCardLocator);
        logger.info("Название в карточке: " + EventCardWebElement.findElement(eventNameLocator).isDisplayed());
        return EventCardWebElement.findElement(eventNameLocator).isDisplayed();
    }

    public Boolean getEventDateDisplayed() {

        WebElement EventCardWebElement = driver.findElement(eventCardLocator);
        logger.info("Дата в карточке: " + EventCardWebElement.findElement(eventDateLocator).isDisplayed());
        return EventCardWebElement.findElement(eventDateLocator).isDisplayed();
    }

    public Boolean getEventStatusDisplayed() {

        WebElement EventCardWebElement = driver.findElement(eventCardLocator);
        logger.info("Статус в карточке: " + EventCardWebElement.findElement(eventStatusLocator).isDisplayed());
        return EventCardWebElement.findElement(eventStatusLocator).isDisplayed();
    }

    public Boolean getEventSpeakersCellDisplayed() { //Проверка что отображается блок спикеров

        WebElement EventCardWebElement = driver.findElement(eventCardLocator);
        logger.info("Блок спикеров в карточке: " + EventCardWebElement.findElement(eventSpeakersCellLocator).isDisplayed());
        return EventCardWebElement.findElement(eventSpeakersCellLocator).isDisplayed();
    }

    public Boolean getEventSingleSpeakerDisplayed() { //Проверка что отображается хотя бы один спикер в карточке

        WebElement EventCardWebElement = driver.findElement(eventCardLocator);
        logger.info("Спикер в карточке: " + EventCardWebElement.findElement(eventSingleSpeakerLocator).isDisplayed());
        return EventCardWebElement.findElement(eventSingleSpeakerLocator).isDisplayed();
    }

    public Long getEventDateEnd(WebElement card) { //

        String dateText = card.findElement(eventDateLocator).getText();
        logger.info("Даты мероприятия: " + dateText);

        if (!dateText.matches("\\d{2} ([\\S]+) \\d{4}$")){
            String[] array = dateText.split(" - ");
            dateText = array[1];

        }

        else{
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM u", Locale.ENGLISH); //!!!!Переделать на методы
            LocalDate date = LocalDate.parse(dateText, dateFormatter);
            Long dateEpoch = date.toEpochDay();

        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM u", Locale.ENGLISH); //!!!!Переделать на методы
        LocalDate date = LocalDate.parse(dateText, dateFormatter);
        Long dateEpoch = date.toEpochDay();

        return dateEpoch;
    }


    public List<WebElement> getEventsCards() {
        List<WebElement> cards = driver.findElements(eventCardLocator);
        return cards;
    }




    public EventsPage open() {

        config = ConfigFactory.create(TestConfig.class);
        driver.get(config.eventsPage());
        logger.info("Открыта главная страница");
        driver.findElement(eventsLinkLocator).click();
        logger.info("Открыта страница мероприятий");

        return this;
    }

    public EventsPage openPastEvents() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.findElement(pastEventsLinkLocator).click();
        logger.info("Переход на прошедшие мероприятия");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        return this;
    }

    public EventsPage openUpcomingEvents() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.findElement(upcomingEventsLinkLocator).click();
        logger.info("Переход на предстоящие мероприятия");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        return this;
    }



    public int countCards() {

        int upcomingCardsNumber = driver.findElements(eventCardLocator).size();
        logger.info("Получено количество карточек мероприятий " + upcomingCardsNumber);

        return upcomingCardsNumber;
    }

    public int getUpcomingValue() {

        String upcomingValueString = driver.findElement(upcomingCounterLocator).getText();
        int upcomingValueInt = Integer.parseInt(upcomingValueString);
        logger.info("Получено количество будущих мероприятий " + upcomingValueInt);


        return upcomingValueInt;
    }

    public int getPastValue() {

        String pastValueString = driver.findElement(pastCounterLocator).getText();
        int pastValueInt = Integer.parseInt(pastValueString);
        logger.info("Получено количество прошедших мероприятий " + pastValueInt);


        return pastValueInt;
    }




    public EventsPage openCountrySelector() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.findElement(locationDropdownLocalor).click();
        logger.info("Открыт Гео дропдаун");
        driver.findElement(dropdownCanadaLocalor).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        driver.findElement(By.xpath("//div[@class='evnt-events-wrapper']")).click();




        return this;
    }







    public EventsPage login(String login, String password){


        driver.findElement(emailField).sendKeys(login);
        logger.info("введен логин");
        driver.findElement(passwordField).sendKeys(password);
        logger.info("введен пароль");
        driver.findElement(By.xpath("//button[@class='new-button new-button_full new-button_blue new-button_md']")).click();


        return new EventsPage(driver);
    }

}
