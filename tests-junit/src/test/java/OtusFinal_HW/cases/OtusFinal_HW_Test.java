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
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class OtusFinal_HW_Test extends BaseHooks {
    private Logger logger = LogManager.getLogger(OtusFinal_HW_Test.class);

    TestConfig config;

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();


    @Test
    public void numberOfupcomingEventsTest() {                                        // ТЕСТ1
        config = ConfigFactory.create(TestConfig.class);
        logger.info("Test 1 started: ");
        EventsPage eventsPage = new EventsPage(driver);

        eventsPage.open();
        int actualNumber = eventsPage.countCards();
        int expectedNumber = eventsPage.getUpcomingValue();

        Assert.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void pastEventsTest() {                                              //ТЕСТ 2
        logger.info("Test 2 started: ");
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
    public void upcomingEventsTest() { //ТЕСТ 3
        logger.info("Test 3 started: ");

        EventsPage eventsPage = new EventsPage(driver);

        eventsPage.open();
        eventsPage.openUpcomingEvents();

        List<WebElement> cardsList = eventsPage.getEventsCards();

        for (WebElement card : cardsList) {

            assertTrue(eventsPage.getEventDateEnd(card) >= LocalDate.now().toEpochDay());
        }
    }

    @Test
    public void upcomingEventsCanadaTest() { //ТЕСТ 4
        logger.info("Test 4 started: ");

        EventsPage eventsPage = new EventsPage(driver);

        eventsPage.open();
        eventsPage.openPastEvents();
        eventsPage.filerByCanada();

        int actualNumber = eventsPage.countCards();
        int expectedNumber = eventsPage.getPastValue();

        softly.assertThat(expectedNumber).as("Check number of cards").isEqualTo(actualNumber);

        List<WebElement> cardsList = eventsPage.getEventsCards();

        for (WebElement card : cardsList) {

            softly.assertThat(eventsPage.getEventDateEnd(card) < LocalDate.now().toEpochDay());
        }
    }

    @Test
    public void categoryTest() {                                                          //ТЕСТ 5 (Вариант с проверкой одной карточки)
        logger.info("Test 5/1 started: ");
        VideoPage videoPage = new VideoPage(driver);

        videoPage.openVideoPage();
        videoPage.filerByTesting();
        videoPage.filerByBelarus();
        videoPage.filerByEnglish();
        videoPage.openCard();

        softly.assertThat(videoPage.getEventLanguageOnPage()).as("Check language").isEqualTo("ENGLISH");
        softly.assertThat(videoPage.getEventCountryOnPage()).as("Check location").contains("Belarus");
        softly.assertThat(videoPage.getEventCategoryOnPageDisplayed()).as("Check category").isEqualTo(true);

    }

    @Test
    public void categoryTestAllCards() {                                                   //ТЕСТ 5 (Вариант с проверкой всех карточек)
        logger.info("Test 5 started: ");
        VideoPage videoPage = new VideoPage(driver);

        videoPage.openVideoPage();
         videoPage.filerByTesting();
         videoPage.filerByBelarus();
         videoPage.filerByEnglish();

        ArrayList<String> cardsList = videoPage.getCardsLinks();

        for (String card : cardsList) {

            videoPage.openEventCard(card);

            softly.assertThat(videoPage.getEventLanguageOnPage()).as("Check language").isEqualTo("ENGLISH");
            softly.assertThat(videoPage.getEventCountryOnPage()).as("Check location").contains("Belarus");
            softly.assertThat(videoPage.getEventCategoryOnPageDisplayed()).as("Check category").isEqualTo(true);

        }
    }

    @Test
    public void searchTest() { //ТЕСТ 6
        logger.info("Test 6 started: ");

        VideoPage videoPage = new VideoPage(driver);

        videoPage.openVideoPage();
        config = ConfigFactory.create(TestConfig.class);
        String textForSearch = config.epamTextForSearch();
        videoPage.searchText(textForSearch);

        //videoPage.getCardsNames();
        List<WebElement> cardsList = videoPage.getEventsCards();

        for (WebElement card : cardsList) {
            assertTrue(videoPage.getEventName(card).contains("QA"));
        }
    }
}















