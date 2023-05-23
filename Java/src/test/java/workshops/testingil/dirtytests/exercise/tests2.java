package workshops.testingil.dirtytests.exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import workshops.testingil.dirtytests.app.Calculator;
import workshops.testingil.dirtytests.app.ResultWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class tests2 {
    private WebDriver driver;
    WebClient client;

    public static final String URL = "C:\\GitHub\\Presentations\\Dirty-Tests-Workshop\\Java\\src\\UI\\CalculatorUI.html";

    @ParameterizedTest
    @CsvFileSource(resources = "/sequence_api_data.csv")
    public void test_multiple_sequences(String sequence, String expected) throws Exception {
        client = WebClient.create("http://localhost:8080");
        client.post()
                .uri(uriBuilder2 -> uriBuilder2
                        .path("/calc/reset")
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        SequenceMessageHelper message = new SequenceMessageHelper();
        message.version = "1.0";
        message.addSequence(sequence);
        message.resetOnError = true;
        ObjectMapper messageMapper = new ObjectMapper();

        String body = messageMapper.writeValueAsString(message);
        client.post()
                .uri(uriBuilder1 -> uriBuilder1
                        .path("/calc/sequence")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve().bodyToMono(Void.class).block();


        ResultWrapper result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/display")
                        .build())
                .retrieve().bodyToMono(ResultWrapper.class).block();

        assertEquals(expected, result.display);

    }

    @Test
    public void test_01(){
        Calculator c = new Calculator();
        c.press("0");
        c.press("1");
        assertTrue(c.getDisplay() == "1");
    }


    @Test
    public void test_ui_123() {
        WebDriverManager.chromedriver().setup();
        var options = new ChromeOptions();
        options.addArguments("remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
        String BUTTON_XPATH3 = "//*[@id=\"id" + "reset" + "\"]";
        WebElement keyElement3 = driver.findElement(By.xpath(BUTTON_XPATH3));
        keyElement3.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e3) {
            System.out.println("Timeout!!!");
        }
        String BUTTON_XPATH2 = "//*[@id=\"id" + "1" + "\"]";
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
        String BUTTON_XPATH = "//*[@id=\"id" + "3" + "\"]";
        WebElement keyElement = driver.findElement(By.xpath(BUTTON_XPATH));
        keyElement.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Timeout!!!");
        }
        String BUTTON_XPATH4 = "//*[@id=\"id" + "Update" + "\"]";
        WebElement keyElement4 = driver.findElement(By.xpath(BUTTON_XPATH4));
        keyElement4.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Timeout!!!");
        }

        String DISPLAY_XPATH = "//*[@id=\"result\"]";
        WebElement res = driver.findElement(By.xpath(DISPLAY_XPATH));
        String result = res.getAttribute("value");
        assertEquals("123", result);
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/slow_unit_test_data_2.csv")
    public void test_more_sequences(String sequence, String expected) throws Exception {
        client = WebClient.create("http://localhost:8080");
        client.post()
                .uri(uriBuilder2 -> uriBuilder2
                        .path("/calc/reset")
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        SequenceMessageHelper message = new SequenceMessageHelper();
        message.version = "1.0";
        message.addSequence(sequence);
        message.resetOnError = true;
        ObjectMapper messageMapper = new ObjectMapper();

        String body = messageMapper.writeValueAsString(message);
        client.post()
                .uri(uriBuilder1 -> uriBuilder1
                        .path("/calc/sequence")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve().bodyToMono(Void.class).block();


        ResultWrapper result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/display")
                        .build())
                .retrieve().bodyToMono(ResultWrapper.class).block();

        assertEquals(expected, result.display);

    }


    @Test
    public void test_5_mul_and_mul()
    {
        Calculator c = new Calculator();
        c.press("5");
        c.press("*");
        c.press("*");
        assertEquals(c.getDisplay(),"5");
    }
    @Test
    public void test_5_min_3() {
        WebDriverManager.chromedriver().setup();
        var options = new ChromeOptions();
        options.addArguments("remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
        String BUTTON_XPATH3 = "//*[@id=\"id" + "reset" + "\"]";
        WebElement keyElement3 = driver.findElement(By.xpath(BUTTON_XPATH3));
        keyElement3.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e3) {
            System.out.println("Timeout!!!");
        }
        String BUTTON_XPATH2 = "//*[@id=\"id" + "5" + "\"]";
        WebElement keyElement2 = driver.findElement(By.xpath(BUTTON_XPATH2));
        keyElement2.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e2) {
            System.out.println("Timeout!!!");
        }
        String BUTTON_XPATH1 = "//*[@id=\"id" + "plus" + "\"]";
        WebElement keyElement1 = driver.findElement(By.xpath(BUTTON_XPATH1));
        keyElement1.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e1) {
            System.out.println("Timeout!!!");
        }
        String BUTTON_XPATH = "//*[@id=\"id" + "3" + "\"]";
        WebElement keyElement = driver.findElement(By.xpath(BUTTON_XPATH));
        keyElement.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Timeout!!!");
        }
        String BUTTON_XPATH4 = "//*[@id=\"id" + "Update" + "\"]";
        WebElement keyElement4 = driver.findElement(By.xpath(BUTTON_XPATH4));
        keyElement4.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Timeout!!!");
        }

        String DISPLAY_XPATH = "//*[@id=\"result\"]";
        WebElement res = driver.findElement(By.xpath(DISPLAY_XPATH));
        String result = res.getAttribute("value");
        assertEquals("3", result);
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/slow_unit_test_data_2.csv")
    public void test_multiple_vals_2(String in, String out){
        Calculator c = new Calculator();
        c.pressAll(in);
        assertEquals(c.getDisplay(), out);
    }


    @Test
    public void test_result() throws Exception {
        client = WebClient.create("http://localhost:8080");
        client.post()
                .uri(uriBuilder2 -> uriBuilder2
                        .path("/calc/reset")
                        .build())
                .retrieve().bodyToMono(Void.class).block();

        SequenceMessageHelper message = new SequenceMessageHelper();
        message.version = "1.0";
        message.addSequence("5+2C");
        message.resetOnError = true;
        ObjectMapper messageMapper = new ObjectMapper();

        String body = messageMapper.writeValueAsString(message);
        client.post()
                .uri(uriBuilder1 -> uriBuilder1
                        .path("/calc/sequence")
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve().bodyToMono(Void.class).block();


        String result = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/calc/display")
                        .build())
                .retrieve().bodyToMono(String.class).block();

        String json="";
        try {
            String dir = System.getProperty("user.dir");

            System.out.println(dir);
            json = Files.readString(Paths.get(".\\src\\test\\resources\\reference_result.json"), StandardCharsets.UTF_8);
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        assertEquals(json, result);

    }
}
