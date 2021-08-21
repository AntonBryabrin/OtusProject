package OtusFinal_HW.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Locale;


public class WebDriverInit {



    private static String browser = System.getProperty("browser","chrome");
    private static String version = System.getProperty("version","90.0");



    public static WebDriver initDriver(){
        WebDriver driver = null;

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("browserName", browser.toLowerCase(Locale.ROOT)); //Доступные варианты chrome, opera, firefox
        capabilities.setCapability("browserVersion", version); //Доступные варианты chrome - (90.0,91.0), opera - (76.0,77.0), firefox - (89.0,90.0)
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        capabilities.setCapability("enableLogs", true);

        try {
            driver = new RemoteWebDriver(
                    URI.create("http://localhost:4444/wd/hub").toURL(),
                    capabilities

            );

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    return driver;
    }

}
