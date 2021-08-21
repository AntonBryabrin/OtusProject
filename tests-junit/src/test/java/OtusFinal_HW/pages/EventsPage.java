package OtusFinal_HW.pages;


import configuration.TestConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;



public class EventsPage extends AbstractPage {
    TestConfig config;
    Logger logger = LogManager.getLogger(EventsPage.class);

    private By eventsLinkLocator = By.xpath("//a[@class='nav-link' and @href='/events']");
    private By pastEventsLinkLocator = By.xpath("//a[contains(@class, 'evnt-tab-link nav-link') and .//span[contains(text(), 'Past Events')]]");
    private By eventCardLocator = By.xpath("//a/div[@class='evnt-card-wrapper']");
    private By upcomingCounterLocator = By.xpath("(//span[@class='evnt-tab-counter evnt-label small white'])[1]"); //Переделать на элемент без счета
    private By pastCounterLocator = By.xpath("(//span[@class='evnt-tab-counter evnt-label small white'])[2]"); //Переделать на элемент без счета
    private By languageLocator = By.xpath("//p[@class='language']/span");
    private By eventNameLocator = By.xpath("//div[@class='evnt-event-name']/*/span");
    private By preloaderLocator = By.xpath("//div[@class='evnt-global-loader']");
    private By eventDateLocator = By.xpath(".//div[@class='evnt-dates-cell dates']/*/span[@class='date']");
    private By eventStatusLocator = By.xpath("//div[@class='evnt-dates-cell dates']/*/span[contains(@class, 'status')]");
    private By eventSpeakersCellLocator = By.xpath("//div[@class='evnt-people-table']");
    private By eventSingleSpeakerLocator = By.xpath("//div[@class='evnt-speaker']");
    private By upcomingEventsLinkLocator = By.xpath("//a[contains(@class, 'evnt-tab-link nav-link') and .//span[contains(text(), 'Upcoming events')]]");
    private By locationDropdownLocalor = By.xpath("//div[@id='filter_location']");
    private By filtersLocator = By.xpath("//div[@class='evnt-toggle-filters-button evnt-button btn']");
    private By filterLanguageLocator = By.xpath("//div[@id='filter_language']");
    private By dropdownCanadaLocalor = By.xpath("//label[@data-value='Canada']");
    private By dropdownEnglishLocalor = By.xpath("//label[@data-value='ENGLISH']");




    public EventsPage(WebDriver driver) {
        super(driver);
    }

    public Boolean getEventLanguageDisplayed() {

        WebElement EventCardWebElement = driver.findElement(eventCardLocator);
        logger.info("language in card: " + EventCardWebElement.findElement(languageLocator).isDisplayed());
        return EventCardWebElement.findElement(languageLocator).isDisplayed();
    }

    public Boolean getEventNameDisplayed() {

        WebElement EventCardWebElement = driver.findElement(eventCardLocator);
        logger.info("name in card: " + EventCardWebElement.findElement(eventNameLocator).isDisplayed());
        return EventCardWebElement.findElement(eventNameLocator).isDisplayed();
    }

    public Boolean getEventDateDisplayed() {

        WebElement EventCardWebElement = driver.findElement(eventCardLocator);
        logger.info("Date in card: " + EventCardWebElement.findElement(eventDateLocator).isDisplayed());
        return EventCardWebElement.findElement(eventDateLocator).isDisplayed();
    }

    public Boolean getEventStatusDisplayed() {

        WebElement EventCardWebElement = driver.findElement(eventCardLocator);
        logger.info("Status in card: " + EventCardWebElement.findElement(eventStatusLocator).isDisplayed());
        return EventCardWebElement.findElement(eventStatusLocator).isDisplayed();
    }

    public Boolean getEventSpeakersCellDisplayed() { //Проверка что отображается блок спикеров

        WebElement EventCardWebElement = driver.findElement(eventCardLocator);
        logger.info("Speakers block in card is displayed: " + EventCardWebElement.findElement(eventSpeakersCellLocator).isDisplayed());
        return EventCardWebElement.findElement(eventSpeakersCellLocator).isDisplayed();
    }

    public Boolean getEventSingleSpeakerDisplayed() { //Проверка что отображается хотя бы один спикер в карточке

        WebElement EventCardWebElement = driver.findElement(eventCardLocator);
        logger.info("Speaker in card displayed: " + EventCardWebElement.findElement(eventSingleSpeakerLocator).isDisplayed());
        return EventCardWebElement.findElement(eventSingleSpeakerLocator).isDisplayed();
    }

    public Long getEventDateEnd(WebElement card) { //  Используется в upcomingEventsTest

        String dateText = card.findElement(eventDateLocator).getText();
        logger.info("Date on event: " + dateText);

        if (!dateText.matches("\\d{2} ([\\S]+) \\d{4}$")) {
            String[] array = dateText.split(" - ");
            dateText = array[1];

        } else {
            Long dateEpoch = getEpochDate(dateText);
            logger.info("Date in epoch " + dateEpoch);
        }
        Long dateEpoch = getEpochDate(dateText);
        logger.info("Date in epoch " + dateEpoch);
        return dateEpoch;
    }

    public Long getEpochDate (String dateText){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM u", Locale.ENGLISH);
        logger.info("End date is (not epoch) " + dateText);
        LocalDate date = LocalDate.parse(dateText, dateFormatter);
        Long dateEpoch = date.toEpochDay();
        return dateEpoch;
    }

    public List<WebElement> getEventsCards() {
        List<WebElement> cards = driver.findElements(eventCardLocator);
        return cards;
    }

    public EventsPage open() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        config = ConfigFactory.create(TestConfig.class);
        driver.get(config.eventsPage());
        logger.info("Main page opened");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        driver.findElement(eventsLinkLocator).click();
        logger.info("Events page opened");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        return this;
    }

    public EventsPage openPastEvents() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.findElement(pastEventsLinkLocator).click();
        logger.info("Go to previous events");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        return this;
    }

    public EventsPage openUpcomingEvents() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.findElement(upcomingEventsLinkLocator).click();
        logger.info("Go to future events");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        return this;
    }

    public int countCards() {
        int upcomingCardsNumber = driver.findElements(eventCardLocator).size();
        logger.info("Get number of events cards " + upcomingCardsNumber);
        return upcomingCardsNumber;
    }

    public int getUpcomingValue() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        String upcomingValueString = driver.findElement(upcomingCounterLocator).getText();
        int upcomingValueInt = Integer.parseInt(upcomingValueString);
        logger.info("Get number of future events cards " + upcomingValueInt);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        return upcomingValueInt;
    }

    public int getPastValue() {
        String pastValueString = driver.findElement(pastCounterLocator).getText();
        int pastValueInt = Integer.parseInt(pastValueString);
        logger.info("Got number of past events " + pastValueInt);
        return pastValueInt;
    }

    public EventsPage filerByCanada() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.findElement(locationDropdownLocalor).click();
        logger.info("Geo dropdown opened");
        driver.findElement(dropdownCanadaLocalor).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        driver.findElement(By.xpath("//div[@class='evnt-events-wrapper']")).click();
        return this;
    }

    public EventsPage filerByEnglish() {

        driver.findElement(filtersLocator).click();
        logger.info("Открыты Дополнительные фильтры");
        driver.findElement(filterLanguageLocator).click();
        logger.info("Открыт дропдаун Языков");
        driver.findElement(dropdownEnglishLocalor).click();
        logger.info("Выбран английский язык");
        driver.findElement(By.xpath("//input[@placeholder='Search by Event Name']")).click();
        return this;
    }

}
