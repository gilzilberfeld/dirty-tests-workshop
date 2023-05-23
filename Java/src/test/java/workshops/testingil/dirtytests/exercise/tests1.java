package workshops.testingil.dirtytests.exercise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.web.reactive.function.client.WebClient;
import workshops.testingil.dirtytests.app.Calculator;
import workshops.testingil.dirtytests.app.ResultWrapper;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class tests1 {

    private WebDriver driver;
    public static final String URL = "C:\\GitHub\\Presentations\\Dirty-Tests-Workshop\\Java\\src\\UI\\CalculatorUI.html";


    @Test
    public void test_1_2_3()
    {
        Calculator c = new Calculator();
        c.press("1");
        c.press("2");
        c.press("3");
        assertEquals(c.getDisplay(),"123");
    }


    @Test
//@Disabled
    public void test_plus_and_2() {

        WebClient client = WebClient.create("http://localhost:8080");
        ResultWrapper result;

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/reset")
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/press")
                        .queryParam("key", URLEncoder.encode("+", StandardCharsets.UTF_8))
                        .build())
                .retrieve().bodyToMono(Void.class).block();


        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/press")
                        .queryParam("key", "2")
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/display")
                        .build())
                .retrieve().bodyToMono(ResultWrapper.class).block();

        assertEquals("2", result.display);

    }

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

    @ParameterizedTest
    @CsvFileSource(resources = "/slow_unit_test_data.csv")
    public void test_multiple_vals(String in, String out){
        Calculator c = new Calculator();
        in.chars()
                .forEach(ch -> c.press(String.valueOf((char)ch)));
        assertEquals(c.getDisplay(), out);
    }

    @Test
    public void test_nothing(){
        Calculator c = new Calculator();
        assertTrue(c.getDisplay() == "0");
    }


    @Test
    //@Disabled
    public void test_1_and_plus() {

        WebClient client = WebClient.create("http://localhost:8080");
        ResultWrapper result;

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/reset")
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/press")
                        .queryParam("key", "1")
                        .build())
                .retrieve().bodyToMono(Void.class).block();


        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/press")
                        .queryParam("key", URLEncoder.encode("+", StandardCharsets.UTF_8))
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/display")
                        .build())
                .retrieve().bodyToMono(ResultWrapper.class).block();

        assertEquals("1", result.display);

    }

    @Test
    public void test_1(){
        Calculator c = new Calculator();
        c.press("1");
        assertTrue(c.getDisplay() == "1");
    }

    @Test
    public void test_zero_zero() {

        WebClient client = WebClient.create("http://localhost:8080");
        ResultWrapper result;

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/reset")
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/press")
                        .queryParam("key", "0")
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/press")
                        .queryParam("key", "0")
                        .build())
                .retrieve().bodyToMono(Void.class).block();


        result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/display")
                        .build())
                .retrieve().bodyToMono(ResultWrapper.class).block();

        assertEquals("0", result.display);

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


    @Test
    public void test_1_and_C()
    {
        Calculator c = new Calculator();
        c.press("1");
        c.press("C");
        assertEquals(c.getDisplay(),"0");
    }

    @Test
    public void test_0() {
        WebClient client = WebClient.create("http://localhost:8080");
        ResultWrapper result;

        client.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/press")
                        .queryParam("key", "0")
                        .build())
                .retrieve().bodyToMono(Void.class).block();


        result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/display")
                        .build())
                .retrieve().bodyToMono(ResultWrapper.class).block();

        assertEquals("0", result.display);
    }

}
