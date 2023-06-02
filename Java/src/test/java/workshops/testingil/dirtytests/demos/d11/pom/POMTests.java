package workshops.testingil.dirtytests.demos.d11.pom;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class POMTests {

    private WebDriver driver;
    public static final String URL = "C:\\GitHub\\Presentations\\Dirty-Tests-Workshop\\Java\\src\\test\\java\\workshops\\testingil\\dirtytests\\demos\\d11\\pom\\demo.html";

    @Test
    public void POMtest() {
        driver.get(URL);

        String item_title = new SelectionArea(driver).getItemLabel(0);
        String middle_item_in_table = new TableArea(driver).getItemTitle(2);

        assertThat(item_title).isEqualTo("First item");
        assertThat(middle_item_in_table).isEqualTo("Item2");
    }

    @BeforeEach
    public void setup() {
        WebDriverManager.chromedriver().setup();
        var options = new ChromeOptions();
        options.addArguments("remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterEach
    public void cleanup(){
        driver.quit();
    }
}
