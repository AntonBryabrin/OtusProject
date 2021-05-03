import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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
    private Logger logger = LogManager.getLogger(Lesson3_HW.class);
    protected static WebDriver driver;
    private TestConfig config;


    @Before
    public void startUp() {
        config = ConfigFactory.create(TestConfig.class);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


    }

    @After
    public void end() {
        if (driver != null)
            driver.quit();
        logger.info("Драйвер закрыт");

    }


    @Test
    public void otusContactsAddressCheck() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        Actions action = new Actions(driver);
        driver.get(config.hostnameYandexMarket());
        logger.info("Открыта страница маркета");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@href='/catalog--elektronika/54440']")).click();
        Thread.sleep(2000);
        logger.info("Переход в электронику");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li/div/a[contains(text(),'Смартфоны')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div/span[contains(text(), 'Samsung')]")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//div/span[contains(text(), 'Xiaomi')]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[@data-autotest-id = 'dprice']")).click();
        logger.info("сортировка");
        Thread.sleep(2000);




        WebElement samsungBlock = driver.findElement(By.xpath("//article[contains(@data-autotest-id, 'product-snippet') and .//span[contains(text(), 'Samsung')]][1]"));
        logger.info("Найден блок самсунга");
        WebElement samsungBlockName = samsungBlock.findElement(By.xpath("//h3/a[contains(@title,'Смартфон Samsung')]"));
        logger.info("Найден title товара в блоке самсунга");
        String samsungItemName = samsungBlockName.getAttribute("title");
        logger.info("samsungItemName = "+ samsungItemName);
        Thread.sleep(2000);
        action.moveToElement(samsungBlock).perform();
        logger.info("перемещена мышка на блок самсунга");
        Thread.sleep(4000);
        samsungBlock.findElement(By.xpath("//div[contains(@aria-label, 'сравнению')]")).click();
        logger.info("нажатие на кнопку добавления к сравнению");
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-apiary-widget-id = '/content/popupInformer']/div/div/div/div[contains(text(), samsungItemName)]")));
        logger.info("всплывашка добавления к сравнению появилась");

        /*
        Thread.sleep(2000);
        String samsungPopupName = driver.findElement(By.xpath("//div[@data-apiary-widget-id = '/content/popupInformer']/div/div/div/div[contains(text(), 'добавлен')]")).getText();
        logger.info("samsungPopupName = "+ samsungPopupName);




        assertTrue(samsungPopupName.contains(samsungItemName));
        logger.info("Проверка что всплывашка содержит имя добавленного самсунга");
*/
        WebElement xiaomiBlock = driver.findElement(By.xpath("//article[contains(@data-autotest-id, 'product-snippet') and .//span[contains(text(), 'Xiaomi')]][1]"));
        logger.info("Найден блок Xiaomi");
        WebElement xiaomiBlockName = xiaomiBlock.findElement(By.xpath("//h3/a[contains(@title,'Смартфон Xiaomi')]"));
        Thread.sleep(2000);
        logger.info("Найден title товара в блоке xiaomi");
        String xiaomiItemName = xiaomiBlockName.getAttribute("title");
        logger.info("xiaomiItemName = "+ xiaomiItemName);
        Thread.sleep(2000);
        action.moveToElement(xiaomiBlock).perform();
        logger.info("перемещена мышка на блок самсунга");
        Thread.sleep(2000);
        xiaomiBlock.findElement(By.xpath("//div[contains(@aria-label, 'сравнению')]")).click();
        logger.info("нажатие на кнопку добавления к сравнению");
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-apiary-widget-id = '/content/popupInformer']/div/div/div/div[contains(text(), xiaomiItemName)]")));
        logger.info("всплывашка добавления к сравнению появилась");
        Thread.sleep(2000);
          
        /*
        String xiaomiPopupName = driver.findElement(By.xpath("//div[@data-apiary-widget-id = '/content/popupInformer']/div/div/div/div[contains(text(), 'добавлен')]")).getText();
        logger.info("xiaomiPopupName = "+ xiaomiPopupName);
        Thread.sleep(4000);
        assertTrue(xiaomiPopupName.contains(xiaomiItemName));
        logger.info("Проверка что всплывашка содержит имя добавленного сяоми");

        */

        driver.findElement(By.xpath("//a[@href = '/my/compare-lists']")).click();
        List<WebElement> nums2 = driver.findElements(By.xpath("//a[contains(text(), 'Смартфон')]"));
        Thread.sleep(4000);
        int size = nums2.size();
        Thread.sleep(2000);

        int expectedSize = 2;
        Assert.assertEquals(expectedSize, size);
        logger.info("Проверка что список сравнения содержит два элемента");
        Thread.sleep(8000);





        /*
        String actual = driver.findElement(By.xpath("//div[contains(text(),'Адрес')]/following-sibling::div")).getText();
        Assert.assertEquals("125167, г. Москва, Нарышкинская аллея., д. 5, стр. 2, тел. +7 499 938-92-02", actual);
        logger.info("Адрес проверен");

         */

    }


}


