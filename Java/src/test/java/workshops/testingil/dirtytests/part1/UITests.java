package workshops.testingil.dirtytests.part1;

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
        driver.get(URL);
        press("1");
        press("plus");
        press("3");
        press("4");
        press_update();
        String result = get_display();
        assertEquals("34", result);
        }

    private void press_update() {
        String BUTTON_XPATH = "//*[@id=\"id" + "Update" + "\"]";
        WebElement keyElement = driver.findElement(By.xpath(BUTTON_XPATH));
        keyElement.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Timeout!!!");
        }
    }

    private String get_display() {

        String DISPLAY_XPATH = "//*[@id=\"result\"]";
        WebElement res = driver.findElement(By.xpath(DISPLAY_XPATH));
        return res.getAttribute("value");
    }

    private void press(String key) {
        String BUTTON_XPATH = "//*[@id=\"id" + key + "\"]";
        WebElement keyElement = driver.findElement(By.xpath(BUTTON_XPATH));
        keyElement.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Timeout!!!");
        }
    }


    @BeforeEach
    public void setup(){
        WebDriverManager.chromedriver().setup();
        var options = new ChromeOptions();
        options.addArguments("remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void teardown(){
        driver.quit();
    }

    @Test
    public void test_div_6_2() {
        driver.get(URL);
        press("6");
        press("div");
        press("2");
        press("eql");
        press_update();
        String result = get_display();
        assertEquals("3", result);
    }
}
