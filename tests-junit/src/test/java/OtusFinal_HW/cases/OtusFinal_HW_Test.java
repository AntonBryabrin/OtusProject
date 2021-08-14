package OtusFinal_HW.cases;


import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import configuration.TestConfig;
import OtusFinal_HW.pages.*;
import OtusFinal_HW.utils.BaseHooks;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;

public class OtusFinal_HW_Test extends BaseHooks{
    private Logger logger = LogManager.getLogger(OtusFinal_HW_Test.class);

    TestConfig config;

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();





    @Test
    public void numberOfupcomingEventsTest() {
        // WebDriverWait wait = new WebDriverWait(driver, 25);
        config = ConfigFactory.create(TestConfig.class);
        EventsPage eventsPage = new EventsPage(driver);

        eventsPage.open();
        int actualNumber = eventsPage.countCards();
        int expectedNumber = eventsPage.getUpcomingValue();

        Assert.assertEquals(expectedNumber, actualNumber);
    }

        @Test
        public void pastEventsTest() {

            //config = ConfigFactory.create(TestConfig.class);
            EventsPage eventsPage = new EventsPage(driver);

            eventsPage.open();
            eventsPage.openPastEvents();

            softly.assertThat(eventsPage.getEventLanguageDisplayed()).as("Check language").isEqualTo(true);
            softly.assertThat(eventsPage.getEventNameDisplayed()).as("Check event name").isEqualTo(true);
            softly.assertThat(eventsPage.getEventDateDisplayed()).as("Check event date").isEqualTo(true);
            softly.assertThat(eventsPage.getEventStatusDisplayed()).as("Check event status").isEqualTo(true);
            softly.assertThat(eventsPage.getEventSpeakersCellDisplayed()).as("Check speakers cell").isEqualTo(true);
            softly.assertThat(eventsPage.getEventSingleSpeakerDisplayed()).as("Check single speaker").isEqualTo(true);
        }






    @Test
    public void upcomingEventsTest(){

        EventsPage eventsPage = new EventsPage(driver);

        eventsPage.open();
        eventsPage.openUpcomingEvents();


        List<WebElement> cardsList = eventsPage.getEventsCards();

        for(WebElement card : cardsList) {

            Assert.assertTrue(eventsPage.getEventDateEnd(card) >= LocalDate.now().toEpochDay());

        }






    }

    @Test
    public void upcomingEventsCanadaTest() {
        
        EventsPage eventsPage = new EventsPage(driver);

        eventsPage.open();

        eventsPage.openPastEvents();
        eventsPage.filerByCanada();

        int actualNumber = eventsPage.countCards();
        int expectedNumber = eventsPage.getPastValue();

        Assert.assertEquals(expectedNumber, actualNumber);

        List<WebElement> cardsList = eventsPage.getEventsCards();

        for(WebElement card : cardsList) {

            Assert.assertTrue(eventsPage.getEventDateEnd(card) < LocalDate.now().toEpochDay());

        }


    }

    /*@Test
    public void categoryTest() {

        EventsPage eventsPage = new EventsPage(driver);

        eventsPage.open();
        eventsPage.filerByTesting(); //Переписать на передачу значения в параметре
        eventsPage.filerByBelarus(); //Переписать на передачу значения в параметре
        eventsPage.filerByEnglish(); //Переписать на передачу значения в параметре

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }*/

    @Test
    public void categoryTest() {

        VideoPage videoPage = new VideoPage(driver);

        videoPage.openVideoPage();
        videoPage.filerByTesting();
        videoPage.filerByBelarus();
        videoPage.filerByEnglish();
        videoPage.openCard();

        
        softly.assertThat(videoPage.getEventLanguageOnPage()).as("Check language").isEqualTo("ENGLISH");
        softly.assertThat(videoPage.getEventCountryOnPage()).as("Check location").isEqualTo("Belarus");
        softly.assertThat(videoPage.getEventCategoryOnPageDisplayed()).as("Check category").isEqualTo(true);


    }

    @Test
    public void allPageTest() {

        VideoPage videoPage = new VideoPage(driver);

        videoPage.openVideoPage();
       // videoPage.filerByTesting();
       // videoPage.filerByBelarus();
       // videoPage.filerByEnglish();
       // videoPage.openCard();

        videoPage.getCardsLinks();
    }

    @Test
    public void searchTest(){

        VideoPage videoPage = new VideoPage(driver);

        videoPage.openVideoPage();
        videoPage.searchText();

        videoPage.getCardsNames();



    }















    }















