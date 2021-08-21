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













}