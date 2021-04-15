
import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources({"classpath:general.properties"})
public interface TestConfig extends Config {
    @Config.Key("host")
    @DefaultValue("https://otus.ru")
    String hostname();

    @DefaultValue("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям")
    String title();

}