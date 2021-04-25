
import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources({"classpath:general.properties"})
public interface TestConfig extends Config {
    @Config.Key("hostnameOtus")
    @DefaultValue("")
    String hostnameOtus();

    @Config.Key("titleOtus")
    @DefaultValue("")
    String titleOtus();

    @Config.Key("titleTele2")
    @DefaultValue("")
    String titleTele2();

    @Config.Key("otusFaqCheckAnswer")
    @DefaultValue("")
    String otusFaqCheckAnswer();

}