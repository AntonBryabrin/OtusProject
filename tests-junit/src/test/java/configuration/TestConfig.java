package configuration;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:general.properties"})
public interface TestConfig extends Config {
    @Key("hostnameOtus")
    @DefaultValue("https://otus.ru/")
    String hostnameOtus();

    @Key("eventsPage")
    @DefaultValue("https://events.epam.com")
    String eventsPage();

    @Key("eventsVideoPage")
    @DefaultValue("https://events.epam.com")
    String eventsVideoPage();

    @Key("epamTextForSearch")
    @DefaultValue("QA")
    String epamTextForSearch();





    @Key("hostnameGit")
    @DefaultValue("https://git.com")
    String hostnameGit();

    @Key("titleOtus")
    @DefaultValue("")
    String titleOtus();

    @Key("titleTele2")
    @DefaultValue("")
    String titleTele2();

    @Key("otusFaqCheckAnswer")
    @DefaultValue("")
    String otusFaqCheckAnswer();

    @Key("hostnameYandexMarket")
    @DefaultValue("")
    String hostnameYandexMarket();





    @Key("loginOtus")
    @DefaultValue("otustest@yandex.ru")
    String loginOtus();

    @Key("passwordOtus")
    @DefaultValue("Otus1234")
    String passwordOtus();

    @Key("usernameOtus")
    @DefaultValue("Иван")
    String usernameOtus();

    @Key("usernameLatOtus")
    @DefaultValue("Ivan")
    String usernameLatOtus();

    @Key("userSecondnameOtus")
    @DefaultValue("Иванов")
    String userSecondnameOtus();

    @Key("userSecondnameLatOtus")
    @DefaultValue("Ivanov")
    String userSecondnameLatOtus();

    @Key("userBlognameOtus")
    @DefaultValue("Ivanov Bloger")
    String userBlognameOtus();

    @Key("userBirthdayOtus")
    @DefaultValue("07.05.2021")
    String userBirthdayOtus();

    @Key("usercontact1Otus")
    @DefaultValue("Facebook")
    String usercontact1Otus();

    @Key("usercontact1valueOtus")
    @DefaultValue("facebook")
    String usercontact1valueOtus();

    @Key("usercontact2Otus")
    @DefaultValue("Тelegram")
    String usercontact2Otus();

    @Key("usercontact2valueOtus")
    @DefaultValue("telegram")
    String usercontact2valueOtus();


}