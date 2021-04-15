
import org.aeonbits.owner.Config;

import java.net.URL;

@Config.Sources({"classpath:general.properties"})
public interface TestConfig extends Config {
    @Config.Key("host")
    @DefaultValue("")
    String hostname();

    @DefaultValue("")
    String title();

}