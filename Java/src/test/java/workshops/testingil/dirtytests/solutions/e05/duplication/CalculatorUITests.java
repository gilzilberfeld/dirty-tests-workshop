package workshops.testingil.dirtytests.solutions.e05.duplication;

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

public class CalculatorUITests {


    private WebDriver driver;

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        var options = new ChromeOptions();
        options.addArguments("remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    @Test
    public void pressing_1plus34_displays34() {

        driver.get(Consts.PAGE_URL);
        click_on("reset");
        click_on("1");
        click_on("plus");
        click_on("3");
        click_on("4");
        click_on("Update");

        String result = read_display();
        assertEquals("34", result);
    }

    @Test
    public void calculating_6_div_2_displays_3() {

        driver.get(Consts.PAGE_URL);
        click_on("reset");
        click_on("6");
        click_on("div");
        click_on("2");
        click_on("eql");
        click_on("Update");

        String result = read_display();
        assertEquals("3", result);
    }

    private String read_display() {
        WebElement res = driver.findElement(By.xpath(Consts.DISPLAY_XPATH));
        String result = res.getAttribute("value");
        return result;
    }

    private void click_on(String reset) {
        String BUTTON_XPATH5 = Consts.BUTTON_ID_PREFIX + reset + "\"]";
        WebElement keyElement5 = driver.findElement(By.xpath(BUTTON_XPATH5));
        keyElement5.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e5) {
            System.out.println("Timeout!!!");
        }
    }
}
