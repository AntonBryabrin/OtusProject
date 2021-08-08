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

            config = ConfigFactory.create(TestConfig.class);
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

    }




