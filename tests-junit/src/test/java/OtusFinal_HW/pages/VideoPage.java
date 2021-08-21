package OtusFinal_HW.pages;

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

import java.util.ArrayList;
import java.util.List;


public class VideoPage extends AbstractPage {
    TestConfig config;
    Logger logger = LogManager.getLogger(VideoPage.class);


    private By videoLinkLocator = By.xpath("//a[@class='nav-link' and @href='/video?f%5B0%5D%5Bmedia%5D%5B%5D=Video']");
    private By eventCardLocator = By.xpath("//a/div[@class='evnt-card-wrapper']");
    private By eventNameLocator2 = By.xpath(".//h1/span");
    private By preloaderLocator = By.xpath("//div[@class='evnt-global-loader']");
    private By locationDropdownLocalor = By.xpath("//div[@id='filter_location']");
    private By categoryDropdownLocalor = By.xpath("//div[@id='filter_category']");
    private By filtersLocator = By.xpath("//div[@class='evnt-toggle-filters-button evnt-button btn']");
    private By filterLanguageLocator = By.xpath("//div[@id='filter_language']");
    private By dropdownBelarusLocalor = By.xpath("//label[@data-value='Belarus']");
    private By dropdownEnglishLocalor = By.xpath("//label[@data-value='ENGLISH']");
    private By eventNameCardLocator = By.xpath(".//div[@class='evnt-talk-name']");
    private By eventLanguagePageLocator = By.xpath("//div[@class='evnt-talk-details language evnt-now-past-talk']/span");
    private By eventCountryPageLocator = By.xpath("//div[@class='evnt-talk-details location evnt-now-past-talk']/span");
    private By eventCategoryPageLocator = By.xpath("//div[@class='evnt-topics-wrapper']/div/label[contains(text(),'Testing')]");
    private By dropdownTestingLocalor = By.xpath("//label[@data-value='Testing']");
    private By searchFieldLocator = By.xpath("//input[@placeholder='Search by Talk Name']");




    public VideoPage(WebDriver driver) {
        super(driver);
    }




    public List<WebElement> getEventsCards() { //Ищем карточки мероприятия
        List<WebElement> cards = driver.findElements(eventCardLocator);
        return cards;
    }

    public VideoPage openVideoPage() {

        config = ConfigFactory.create(TestConfig.class);
        driver.get(config.eventsPage());
        logger.info("main page opened");
        driver.findElement(videoLinkLocator).click();
        logger.info("Talks Library page opened");
        return this;
    }

    public VideoPage filerByTesting() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.findElement(categoryDropdownLocalor).click();
        logger.info("Category dropdown opened");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        driver.findElement(dropdownTestingLocalor).click();
        logger.info("Testing category selected");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));

        return this;
    }

    public VideoPage filerByBelarus() {

        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.findElement(filtersLocator).click();
        logger.info("Filters opened");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        driver.findElement(locationDropdownLocalor).click();
        logger.info("Geo dropdown opened");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        driver.findElement(dropdownBelarusLocalor).click();
        logger.info("Belarus selected");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));

        return this;
    }

    public VideoPage filerByEnglish() {
        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.findElement(filterLanguageLocator).click();
        logger.info("Language dropdown opened");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        driver.findElement(dropdownEnglishLocalor).click();
        logger.info("English language selected");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        driver.findElement(By.xpath("//input[@placeholder='Search by Talk Name']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        return this;
    }

    public VideoPage openCard(){
        driver.findElement(eventNameCardLocator).click();
        logger.info("Click on events link");
        return this;
    }

    public String getEventLanguageOnPage() { //Поиск языка мероприятия на странице
        WebDriverWait wait = new WebDriverWait(driver, 5);
        String language = driver.findElement(eventLanguagePageLocator).getText();
        logger.info("language on page: " + language);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        return language;
    }

    public String getEventCountryOnPage() { //Поиск страны мероприятия на странице
        String location = driver.findElement(eventCountryPageLocator).getText();
        return location;
    }

    public Boolean getEventCategoryOnPageDisplayed() {
        logger.info("Category is displayed: " + driver.findElement(eventCategoryPageLocator).isDisplayed());
               return driver.findElement(eventCategoryPageLocator).isDisplayed();
    }


    public VideoPage allpage(){

        VideoPage videoPage = new VideoPage(driver);
        videoPage.openVideoPage();
        videoPage.filerByTesting();
        videoPage.filerByBelarus();
        videoPage.filerByEnglish();;
        return this;
    }

    /*public ArrayList<String> getCardsLinks() {
        List<WebElement> cards = driver.findElements(By.xpath("//div[@class='evnt-talk-card']/a"));
        ArrayList<String> people = new ArrayList<String>();

        for (WebElement card : cards) {
            card.getText();
            String link = card.getAttribute("href");
            System.out.println(link);
            people.add(link);
        }
        return people;
    }

    public VideoPage openEventCard(String card){
        System.out.println("open link "+ card.toString());
            driver.get(card.toString());
        return this;


    }*/

        /*public ArrayList<String> getCardsNames(){

            List<WebElement> cards = getEventsCards();
            ArrayList<String> eventName = new ArrayList<String>();

            for(WebElement card : cards) {
                card.findElement(eventNameLocator2).getText();
                eventName.add(card.findElement(eventNameLocator2).getText());
            }

      return eventName;
    }*/

    public VideoPage searchText(String textForSearch){

        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.findElement(searchFieldLocator).click();
        logger.info("Click on search field");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        driver.findElement(searchFieldLocator).sendKeys(textForSearch);
        logger.info("String for search entered");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        driver.findElement(searchFieldLocator).sendKeys(Keys.ENTER);
        logger.info("Pressed Enter");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));

        return this;
    }


    public String getEventName(WebElement card) { //

        String eventName = card.findElement(eventNameLocator2).getText();
        logger.info("Event name: " + eventName);

        return eventName;
}





}
