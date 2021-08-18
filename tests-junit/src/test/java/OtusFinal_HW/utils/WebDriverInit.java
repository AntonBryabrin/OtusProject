package OtusFinal_HW.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;


public class WebDriverInit {
    public static WebDriver initDriver(){
        WebDriver driver = null;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "91.0");
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
