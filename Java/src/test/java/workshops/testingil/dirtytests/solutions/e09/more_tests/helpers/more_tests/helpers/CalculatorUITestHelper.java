package workshops.testingil.dirtytests.solutions.e09.more_tests.helpers.more_tests.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorUITestHelper {
    public static final String PAGE_URL = "C:\\GitHub\\Presentations\\Dirty-Tests-Workshop\\Java\\src\\UI\\CalculatorUI.html";
    public static final String BUTTON_ID_PREFIX = "//*[@id=\"id";
    public static final String DISPLAY_XPATH = "//*[@id=\"result\"]";
    private final WebDriver driver;
    private String keys;

    public CalculatorUITestHelper(){
        WebDriverManager.chromedriver().setup();
        var options = new ChromeOptions();
        options.addArguments("remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(CalculatorUITestHelper.PAGE_URL);

        keys ="";
    }

    public String read_display() {
        WebElement res = driver.findElement(By.xpath(DISPLAY_XPATH));
        return res.getAttribute("value");
    }

    public void click_on(String key) {
        String BUTTON_XPATH5 = BUTTON_ID_PREFIX + key + "\"]";
        WebElement key_element = driver.findElement(By.xpath(BUTTON_XPATH5));
        key_element.click();
        wait_a_bit();
        keys += key;
    }

    public void wait_a_bit() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e5) {
            System.out.println("Timeout!!!");
        }
    }

    public void close() {
        driver.quit();
    }

    public void should_display(String expected_display) {
        String actual_display = read_display();
        assertThat(actual_display)
                .isEqualTo(expected_display)
                .withFailMessage("Wrong display result. After clicking %s, instead of %s we got %s",
                keys, expected_display, actual_display);
    }
}
