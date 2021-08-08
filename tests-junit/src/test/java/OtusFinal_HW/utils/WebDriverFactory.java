package OtusFinal_HW.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;


public class WebDriverFactory {

    public static WebDriver createDriverWithOptions(WebDriverType wdType, String arguments) {
        arguments = arguments.toLowerCase();
        switch (wdType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments(arguments);
                return new ChromeDriver(chromeOptions);

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments(arguments);
                return new FirefoxDriver(firefoxOptions);

            case OPERA:
                WebDriverManager.operadriver().setup();
                FirefoxOptions operaOptions = new FirefoxOptions();
                operaOptions.addArguments(arguments);
                return new OperaDriver(operaOptions);



            default:
                return null;
        }
    }

    public static WebDriver createDriver(WebDriverType wdType) {
        switch (wdType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case OPERA:
                WebDriverManager.operadriver().setup();
                return new OperaDriver();


            default:
                return null;
        }
    }

}
