package workshops.testingil.dirtytests.demos.d12.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TableArea {
    private final WebDriver driver;

    public TableArea(WebDriver driver) {
        this.driver = driver;
    }

    public String getItemTitle(int col) {
        return driver.findElement(By.xpath("/html/body/table/tbody/tr/td[" + col + "]")).getText();
    }
}
