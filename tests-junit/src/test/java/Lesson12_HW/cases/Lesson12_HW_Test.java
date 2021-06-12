package Lesson12_HW.cases;


import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;
import configuration.TestConfig;
import Lesson12_HW.pages.*;
import Lesson12_HW.utils.BaseHooks;

public class Lesson12_HW_Test extends BaseHooks{
    private Logger logger = LogManager.getLogger(Lesson12_HW_Test.class);

    TestConfig config;

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();


    @Test
    public void marketCompareItemsCheck() throws InterruptedException {
       // WebDriverWait wait = new WebDriverWait(driver, 25);
        config = ConfigFactory.create(TestConfig.class);
        otusLoginPage loginPage = new otusLoginPage(driver);


        loginPage.open();
        logger.info("Step 1 Открыта страница логина");

        loginPage.login(config.loginOtus(), config.passwordOtus());
        logger.info("Step 2 Пользователь авторизован");

        otusCabinetPage cabinetPage = new otusCabinetPage(driver);
        cabinetPage.open();
        logger.info("Step 3 Открыта личная информация");

        cabinetPage.fillInfo();
        logger.info("Step 4 Личная информация заполнена");


        BaseHooks.restartDriver();
        logger.info("Перезапуск браузера");

        otusLoginPage loginPageRestarted = new otusLoginPage(driver);
        loginPageRestarted.open();
        logger.info("Step 5 Открыта страница логина");

        loginPageRestarted.login(config.loginOtus(), config.passwordOtus());
        logger.info("Step 6 Пользователь авторизован");

        otusCabinetPage cabinetPageRestarted = new otusCabinetPage(driver);
        cabinetPageRestarted.open();
        logger.info("Step 7 Открыта личная информация");

        //Thread.sleep(75000);

        softly.assertThat(cabinetPageRestarted.getUserName()).as("Compare name").isEqualTo(config.usernameOtus());
        softly.assertThat(cabinetPageRestarted.getUserNameLat()).as("Compare latin name").isEqualTo(config.usernameLatOtus());
        softly.assertThat(cabinetPageRestarted.getUserSecondName()).as("Compare second name").isEqualTo(config.userSecondnameOtus());
        softly.assertThat(cabinetPageRestarted.getUserSecondNameLat()).as("Compare latin second name").isEqualTo(config.userSecondnameLatOtus());
        softly.assertThat(cabinetPageRestarted.getUserBlogName()).as("Compare blog name").isEqualTo(config.userBlognameOtus());
        softly.assertThat(cabinetPageRestarted.getUserBirthday()).as("Compare birthday date").isEqualTo(config.userBirthdayOtus());
        softly.assertThat(cabinetPageRestarted.getUserfirstcontactType()).as("Compare contact 1").isEqualTo(config.usercontact1Otus());
        softly.assertThat(cabinetPageRestarted.getUserfirstcontactValue()).as("Compare contact value 1").isEqualTo(config.usercontact1valueOtus());
        softly.assertThat(cabinetPageRestarted.getUsersecondcontactType()).as("Compare contact 2").isEqualTo(config.usercontact2Otus());
        softly.assertThat(cabinetPageRestarted.getUsersecondcontactValue()).as("Compare contact value 2").isEqualTo(config.usercontact2valueOtus());
        cabinetPageRestarted.deleteContacts();


    }

}


