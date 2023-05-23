package workshops.testingil.dirtytests.solutions.e06.refactoring.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class CalculatorUITestHelper {
    public static final String PAGE_URL = "C:\\GitHub\\Presentations\\Dirty-Tests-Workshop\\Java\\src\\UI\\CalculatorUI.html";
    public static final String BUTTON_ID_PREFIX = "//*[@id=\"id";
    public static final String DISPLAY_XPATH = "//*[@id=\"result\"]";
    private final WebDriver driver;

    public CalculatorUITestHelper(){
        WebDriverManager.chromedriver().setup();
        var options = new ChromeOptions();
        options.addArguments("remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(CalculatorUITestHelper.PAGE_URL);
    }

    public String read_display() {
        WebElement res = driver.findElement(By.xpath(DISPLAY_XPATH));
        return res.getAttribute("value");
    }

    public void click_on(String key) {
        String BUTTON_XPATH5 = BUTTON_ID_PREFIX + key + "\"]";
        WebElement key_element = driver.findElement(By.xpath(BUTTON_XPATH5));
        key_element.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e5) {
            System.out.println("Timeout!!!");
        }
    }

    public void close() {
        driver.quit();
    }
}
