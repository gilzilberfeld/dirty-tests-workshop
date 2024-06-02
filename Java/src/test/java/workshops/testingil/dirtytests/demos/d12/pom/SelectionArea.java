package workshops.testingil.dirtytests.demos.d12.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SelectionArea {
    private final WebDriver driver;

    public SelectionArea(WebDriver driver) {
        this.driver = driver;
    }

    public String getItemLabel(int id) {
        return driver.findElement(By.xpath("/html/body/ol/li[" + id+ "1]")).getText();
    }
}
