import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


public class Lesson8_HW {
    private Logger logger = LogManager.getLogger(Lesson8_HW.class);
    protected static WebDriver driver;
    private TestConfig config;

    @Before
    public void startUp() {
        config = ConfigFactory.create(TestConfig.class);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");

    }

    @After
    public void end() {
        if (driver != null)
            driver.quit();
        logger.info("Драйвер закрыт");

    }

    @Test
    public void marketCompareItemsCheck() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 25);
        Actions action = new Actions(driver);

        By elektronikaLinkLocator = By.xpath("//a[@href='/catalog--elektronika/54440']");
        By smartsLinkLocator = By.xpath("//li/div/a[contains(text(),'Смартфоны')]");
        By filterSamsungLocator = By.xpath("//div/span[contains(text(), 'Samsung')]");
        By filterXiaomiLocator = By.xpath("//div/span[contains(text(), 'Xiaomi')]");
        By filterPriceLocator = By.xpath("//button[@data-autotest-id = 'dprice']");
        By samsungBlockLocator = By.xpath("//article[contains(@data-autotest-id, 'product-snippet') and .//span[contains(text(), 'Samsung')]][1]");
        By samsungBlockLinkNameLocator = By.xpath(".//h3/a[contains(@title,'Смартфон')]");
        By compareButtonLocator = By.xpath(".//div[contains(@aria-label, 'сравнению')]");
        By samsungPopupCompareLocator = By.xpath("//div[@data-apiary-widget-id = '/content/popupInformer']/div/div/div/div[contains(text(), samsungItemName)]");
        By xiaomiBlockLocator = By.xpath("//article[contains(@data-autotest-id, 'product-snippet') and .//span[contains(text(), 'Xiaomi')]][1]");
        By xiaomiBlockLinkNameLocator = By.xpath(".//h3/a[contains(@title,'Смартфон')]");
        By xiaomiPopupCompareLocator = By.xpath("//div[@data-apiary-widget-id = '/content/popupInformer']/div/div/div/div[contains(text(), xiaomiItemName)]");
        By compareLinkLocator = By.xpath("//a[@href = '/my/compare-lists']");
        By itemsLinksLocator = By.xpath("//a[contains(text(), 'Смартфон')]");
        By preloaderLocator = By.xpath("//body/div[2]/div[3]/div[3]/div[4]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]");

        driver.manage().window().maximize();
        driver.get(config.hostnameYandexMarket());
        logger.info("Открыта страница маркета");
        wait.until(ExpectedConditions.elementToBeClickable(elektronikaLinkLocator));
        driver.findElement(elektronikaLinkLocator).click();
        logger.info("Переход в электронику");
        wait.until(ExpectedConditions.visibilityOfElementLocated(smartsLinkLocator));
        driver.findElement(smartsLinkLocator).click();
        logger.info("Переход в смартфоны");

// Фильтры сортировка

        wait.until(ExpectedConditions.visibilityOfElementLocated(filterSamsungLocator));
        driver.findElement(filterSamsungLocator).click();
        logger.info("Отфильтровано по самсунгу");
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterXiaomiLocator));
        driver.findElement(filterXiaomiLocator).click();
        logger.info("Отфильтровано по сяоми");
        wait.until(ExpectedConditions.visibilityOfElementLocated(filterPriceLocator));
        driver.findElement(filterPriceLocator).click();
        logger.info("Отсортировано по цене");

// Работа в карточке товара Самсунг

        wait.until(ExpectedConditions.invisibilityOfElementLocated(preloaderLocator));
        WebElement samsungBlock = driver.findElement(samsungBlockLocator);
        logger.info("Найден блок самсунга");
        wait.until(ExpectedConditions.elementToBeClickable(samsungBlockLinkNameLocator));
        WebElement samsungBlockLinkName = samsungBlock.findElement(samsungBlockLinkNameLocator);
        logger.info("Найдена ссылка на товар в блоке самсунга");
        String samsungItemName = samsungBlockLinkName.getAttribute("title"); // Берем title так как ссылка содержит лишний текст
        logger.info("Найден tile товара");
        logger.info("samsungItemName = " + samsungItemName);
        action.moveToElement(samsungBlock).perform();
        logger.info("Перемещена мышка на блок самсунга");
        wait.until(ExpectedConditions.presenceOfElementLocated(compareButtonLocator));
        samsungBlock.findElement(compareButtonLocator).click();
        logger.info("нажатие на кнопку добавления к сравнению прошло");
        wait.until(ExpectedConditions.visibilityOfElementLocated(samsungPopupCompareLocator)); //Ждем появления всплывашки с именем добавленного товара
        logger.info("всплывашка добавления к сравнению появилась");

        // Работа в карточке товара Сяоми

        WebElement xiaomiBlock = driver.findElement(xiaomiBlockLocator);
        logger.info("Найден блок Xiaomi");
        WebElement xiaomiBlockLinkName = xiaomiBlock.findElement(xiaomiBlockLinkNameLocator);
        logger.info("Найден title товара в блоке xiaomi");
        String xiaomiItemName = xiaomiBlockLinkName.getAttribute("title");
        logger.info("xiaomiItemName = " + xiaomiItemName);
        action.moveToElement(xiaomiBlock).perform();
        logger.info("перемещена мышка на блок сяоми");
        wait.until(ExpectedConditions.presenceOfElementLocated(compareButtonLocator));
        xiaomiBlock.findElement(compareButtonLocator).click();
        logger.info("нажатие на кнопку добавления к сравнению прошло");
        wait.until(ExpectedConditions.visibilityOfElementLocated(xiaomiPopupCompareLocator)); //Ждем появления всплывашки с именем добавленного товара сяоми
        logger.info("всплывашка добавления к сравнению появилась");
        wait.until(ExpectedConditions.elementToBeClickable(compareLinkLocator));
        driver.findElement(compareLinkLocator).click();
        logger.info("Переход к сравнению произошел");

// Сравнение

        wait.until(ExpectedConditions.visibilityOfElementLocated(itemsLinksLocator));
        List<WebElement> nums2 = driver.findElements(itemsLinksLocator);
        int size = nums2.size();
        int expectedSize = 2;
        Assert.assertEquals(expectedSize, size);
        logger.info("Проверка что список сравнения содержит два элемента прошла");

    }

}


