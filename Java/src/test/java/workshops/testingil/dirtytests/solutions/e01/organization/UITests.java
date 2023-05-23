package workshops.testingil.dirtytests.solutions.e01.organization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UITests {


    private WebDriver driver;
    public static final String URL = "C:\\GitHub\\Presentations\\Dirty-Tests-Workshop\\Java\\src\\UI\\CalculatorUI.html";

    @Test
    public void test_ui_1_plus_34() {
        WebDriverManager.chromedriver().setup();
        var options = new ChromeOptions();
        options.addArguments("remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(URL);
        String BUTTON_XPATH4 = "//*[@id=\"id" + "reset" + "\"]";
        WebElement keyElement4 = driver.findElement(By.xpath(BUTTON_XPATH4));
        keyElement4.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e4) {
            System.out.println("Timeout!!!");
        }
        String BUTTON_XPATH3 = "//*[@id=\"id" + "1" + "\"]";
        WebElement keyElement3 = driver.findElement(By.xpath(BUTTON_XPATH3));
        keyElement3.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e3) {
            System.out.println("Timeout!!!");
        }
        String BUTTON_XPATH2 = "//*[@id=\"id" + "plus" + "\"]";
        WebElement keyElement2 = driver.findElement(By.xpath(BUTTON_XPATH2));
        keyElement2.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e2) {
            System.out.println("Timeout!!!");
        }
        String BUTTON_XPATH1 = "//*[@id=\"id" + "3" + "\"]";
        WebElement keyElement1 = driver.findElement(By.xpath(BUTTON_XPATH1));
        keyElement1.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e1) {
            System.out.println("Timeout!!!");
        }
        String BUTTON_XPATH = "//*[@id=\"id" + "4" + "\"]";
        WebElement keyElement = driver.findElement(By.xpath(BUTTON_XPATH));
        keyElement.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Timeout!!!");
        }
        String BUTTON_XPATH5 = "//*[@id=\"id" + "Update" + "\"]";
        WebElement keyElement5 = driver.findElement(By.xpath(BUTTON_XPATH5));
        keyElement5.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Timeout!!!");
        }

        String DISPLAY_XPATH = "//*[@id=\"result\"]";
        WebElement res = driver.findElement(By.xpath(DISPLAY_XPATH));
        String result = res.getAttribute("value");
        assertEquals("34", result);
        driver.quit();
    }

    @Test
    public void test_div_6_2() {
        WebDriverManager.chromedriver().setup();
        var options = new ChromeOptions();
        options.addArguments("remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(URL);
        String BUTTON_XPATH4 = "//*[@id=\"id" + "reset" + "\"]";
        WebElement keyElement4 = driver.findElement(By.xpath(BUTTON_XPATH4));
        keyElement4.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e4) {
            System.out.println("Timeout!!!");
        }
        String BUTTON_XPATH3 = "//*[@id=\"id" + "6" + "\"]";
        WebElement keyElement3 = driver.findElement(By.xpath(BUTTON_XPATH3));
        keyElement3.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e3) {
            System.out.println("Timeout!!!");
        }
        String BUTTON_XPATH2 = "//*[@id=\"id" + "div" + "\"]";
        WebElement keyElement2 = driver.findElement(By.xpath(BUTTON_XPATH2));
        keyElement2.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e2) {
            System.out.println("Timeout!!!");
        }
        String BUTTON_XPATH1 = "//*[@id=\"id" + "2" + "\"]";
        WebElement keyElement1 = driver.findElement(By.xpath(BUTTON_XPATH1));
        keyElement1.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e1) {
            System.out.println("Timeout!!!");
        }
        String BUTTON_XPATH = "//*[@id=\"id" + "eql" + "\"]";
        WebElement keyElement = driver.findElement(By.xpath(BUTTON_XPATH));
        keyElement.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Timeout!!!");
        }
        String BUTTON_XPATH5 = "//*[@id=\"id" + "Update" + "\"]";
        WebElement keyElement5 = driver.findElement(By.xpath(BUTTON_XPATH5));
        keyElement5.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Timeout!!!");
        }

        String DISPLAY_XPATH = "//*[@id=\"result\"]";
        WebElement res = driver.findElement(By.xpath(DISPLAY_XPATH));
        String result = res.getAttribute("value");
        assertEquals("3", result);
        driver.quit();
    }


    @BeforeEach
    public void setup() {

    }

}