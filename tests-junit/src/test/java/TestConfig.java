
import org.aeonbits.owner.Config;

import java.net.URL;

public interface TestConfig extends Config {
    @DefaultValue("https://otus.ru")
    String hostname();

    @DefaultValue("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям")
    String title();

}