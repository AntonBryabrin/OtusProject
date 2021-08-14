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


    private By search = By.xpath("//input[@name='q']");
    private By loginButton = By.xpath("//button[@class='header2__auth js-open-modal']");
    private By emailField = By.xpath("//form[contains(@action, 'login')]/*/input");
    private By passwordField = By.xpath("//form[contains(@action, 'login')]/*//input[@name='password']");


    private By videoLinkLocator = By.xpath("//a[@class='nav-link' and @href='/video?f%5B0%5D%5Bmedia%5D%5B%5D=Video']");
    private By pastEventsLinkLocator = By.xpath("//a[contains(@class, 'evnt-tab-link nav-link') and .//span[contains(text(), 'Past Events')]]");
    private By eventCardLocator = By.xpath("//a/div[@class='evnt-card-wrapper']");
    private By upcomingCounterLocator = By.xpath("(//span[@class='evnt-tab-counter evnt-label small white'])[1]"); //Переделать на элемент без счета
    private By pastCounterLocator = By.xpath("(//span[@class='evnt-tab-counter evnt-label small white'])[2]"); //Переделать на элемент без счета

    private By languageLocator = By.xpath("//p[@class='language']/span");
    private By eventNameLocator = By.xpath("//div[@class='evnt-event-name']/*/span");
    private By eventNameLocator2 = By.xpath(".//h1/span");
    private By preloaderLocator = By.xpath("//div[@class='evnt-global-loader']");
    //private By eventDateLocator = By.xpath(".//span");
   private By eventDateLocator = By.xpath(".//div[@class='evnt-dates-cell dates']/*/span[@class='date']");
    private By eventStatusLocator = By.xpath("//div[@class='evnt-dates-cell dates']/*/span[contains(@class, 'status')]");
    private By eventSpeakersCellLocator = By.xpath("//div[@class='evnt-people-table']");
    private By eventSingleSpeakerLocator = By.xpath("//div[@class='evnt-speaker']");
    private By upcomingEventsLinkLocator = By.xpath("//a[contains(@class, 'evnt-tab-link nav-link') and .//span[contains(text(), 'Upcoming events')]]");
    private By locationDropdownLocalor = By.xpath("//div[@id='filter_location']");
    private By categoryDropdownLocalor = By.xpath("//div[@id='filter_category']");
    private By filtersLocator = By.xpath("//div[@class='evnt-toggle-filters-button evnt-button btn']");
    private By filterLanguageLocator = By.xpath("//div[@id='filter_language']");
    private By dropdownCanadaLocalor = By.xpath("//label[@data-value='Canada']");
    private By dropdownBelarusLocalor = By.xpath("//label[@data-value='Belarus']");
    private By dropdownEnglishLocalor = By.xpath("//label[@data-value='ENGLISH']");
    private By eventNameCardLocator = By.xpath(".//div[@class='evnt-talk-name']");
    private By eventLanguagePageLocator = By.xpath("//div[@class='evnt-talk-details language evnt-now-past-talk']");
    private By eventCountryPageLocator = By.xpath("//div[@class='evnt-talk-details location evnt-now-past-talk']/span");

    private By eventCategoryPageLocator = By.xpath("//div[@class='evnt-topics-wrapper']/div/label[contains(text(),'Testing')]");

    private By dropdownTestingLocalor = By.xpath("//label[@data-value='Testing']");

    private By searchFieldLocator = By.xpath("//input[@placeholder='Search by Talk Name']");




    public VideoPage(WebDriver driver) {
        super(driver);
    }




    public List<WebElement> getEventsCards() {
        List<WebElement> cards = driver.findElements(eventCardLocator);
        return cards;
    }




    public VideoPage openVideoPage() {

        config = ConfigFactory.create(TestConfig.class);
        driver.get(config.eventsPage());
        logger.info("Открыта главная страница");

        driver.findElement(videoLinkLocator).click();
        logger.info("Открыта страница Talks Library");

        return this;
    }

    public VideoPage filerByTesting() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.findElement(categoryDropdownLocalor).click();
        logger.info("Открыт Категория дропдаун");
        driver.findElement(dropdownTestingLocalor).click();
        logger.info("Выбрана категория Тестирование");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));


        return this;
    }

    public VideoPage filerByBelarus() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        driver.findElement(filtersLocator).click();
        logger.info("Открыты Дополнительные фильтры");
        driver.findElement(locationDropdownLocalor).click();
        logger.info("Открыт Гео дропдаун");
        driver.findElement(dropdownBelarusLocalor).click();
        logger.info("Выбрана беларусь");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));

        return this;
    }

    public VideoPage filerByEnglish() {
        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.findElement(filterLanguageLocator).click();
        logger.info("Открыт дропдаун Языков");
        driver.findElement(dropdownEnglishLocalor).click();
        logger.info("Выбран английский язык");
        driver.findElement(By.xpath("//input[@placeholder='Search by Talk Name']")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));

        return this;
    }

    public VideoPage openCard(){
        driver.findElement(eventNameCardLocator).click();




        return this;
    }

    public String getEventLanguageOnPage() {

        String language = driver.findElement(eventLanguagePageLocator).getText();
        logger.info("язык на странице: " + language);

        return language;
    }

    public String getEventCountryOnPage() {

        String location = driver.findElement(eventCountryPageLocator).getText();
        String[] array = location.split(", ");

        return array[3];
    }

    public Boolean getEventCategoryOnPageDisplayed() {
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

    public VideoPage getCardsLinks() {


        List<WebElement> cards = driver.findElements(By.xpath("//div[@class='evnt-talk-card']/a"));

        ArrayList<String> people = new ArrayList<String>();

        for (WebElement card : cards) {
            card.getText();

            String link = card.getAttribute("href");
            System.out.println(link);
            people.add(link);
        }

        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i));
            driver.get(people.get(i));

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return this;

    }

        public ArrayList<String> getCardsNames(){


           //List<WebElement> cards2 = driver.findElements(By.xpath("//div[@class='evnt-talk-card']"));
            List<WebElement> cards2 = getEventsCards();

            //System.out.println(cards2);

            ArrayList<String> people2 = new ArrayList<String>();

            for(WebElement card : cards2) {


                card.findElement(eventNameLocator2).getText();
                System.out.println(card.findElement(eventNameLocator2).getText());


                people2.add(card.findElement(eventNameLocator2).getText());
            }


      return people2;
    }

    public VideoPage searchText(){
        WebDriverWait wait = new WebDriverWait(driver, 1);
        driver.findElement(searchFieldLocator).click();
        driver.findElement(searchFieldLocator).sendKeys("QA");
        driver.findElement(searchFieldLocator).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));

        return this;
    }

    /*public Boolean getNameContainsValue(cardName) {
        return driver.findElement(eventCategoryPageLocator).isDisplayed();
    }*/







}
