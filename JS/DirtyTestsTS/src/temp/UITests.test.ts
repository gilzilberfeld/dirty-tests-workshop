import { Builder, Browser, until, By, Key } from 'selenium-webdriver';
import { Options } from 'selenium-webdriver/chrome';

const URL = "C:\\GitHub\\Presentations\\Dirty-Tests-Workshop\\JS\\DirtyTestsTS\\src\\App\\CalculatorUI.html";
    
describe('UITests', () => {

    test('test_ui_1_plus_34', async () => {
        const options = new Options();
        options.addArguments('remote-allow-origins=*');
        let driver = await new Builder().forBrowser(Browser.CHROME).setChromeOptions(options).build();
        driver.manage().setTimeouts({ implicit: 10000 });

        driver.get(URL);
        let BUTTON_XPATH4 = "//*[@id=\"id" + "reset" + "\"]";
        let keyElement4 = await driver.findElement(By.xpath(BUTTON_XPATH4));
        await keyElement4.click();
        await new Promise(f => setTimeout(f, 500));

        let BUTTON_XPATH3 = "//*[@id=\"id" + "1" + "\"]";
        let keyElement3 = await driver.findElement(By.xpath(BUTTON_XPATH3));
        await keyElement3.click();
        await new Promise(f => setTimeout(f, 500));

        let BUTTON_XPATH2 = "//*[@id=\"id" + "plus" + "\"]";
        let keyElement2 = await driver.findElement(By.xpath(BUTTON_XPATH2));
        await keyElement2.click();
        await new Promise(f => setTimeout(f, 500));

        let BUTTON_XPATH1 = "//*[@id=\"id" + "3" + "\"]";
        let keyElement1 = await driver.findElement(By.xpath(BUTTON_XPATH1));
        await keyElement1.click();
        await new Promise(f => setTimeout(f, 500));

        let BUTTON_XPATH = "//*[@id=\"id" + "4" + "\"]";
        let keyElement = await driver.findElement(By.xpath(BUTTON_XPATH));
        await keyElement.click();
        await new Promise(f => setTimeout(f, 500));

        let BUTTON_XPATH5 = "//*[@id=\"id" + "Update" + "\"]";
        let keyElement5 = await driver.findElement(By.xpath(BUTTON_XPATH5));
        await keyElement5.click();
        await new Promise(f => setTimeout(f, 500));

        let DISPLAY_XPATH = "//*[@id=\"result\"]";
        let res =await driver.findElement(By.xpath(DISPLAY_XPATH));
        let result = await res.getAttribute("value");
        expect(result).toBe("34");
        
        await driver.quit();
    } , 20000);

    test('test_div_6_2', async () => {
        const options = new Options();
        options.addArguments('remote-allow-origins=*');
        let driver = await new Builder().forBrowser(Browser.CHROME).setChromeOptions(options).build();
        driver.manage().setTimeouts({ implicit: 10000 });

        driver.get(URL);
        let BUTTON_XPATH4 = "//*[@id=\"id" + "reset" + "\"]";
        let keyElement4 = await driver.findElement(By.xpath(BUTTON_XPATH4));
        await keyElement4.click();
        await new Promise(f => setTimeout(f, 500));
        
        let BUTTON_XPATH3 = "//*[@id=\"id" + "6" + "\"]";
        let keyElement3 = await driver.findElement(By.xpath(BUTTON_XPATH3));
        await keyElement3.click();
        await new Promise(f => setTimeout(f, 500));

        let BUTTON_XPATH2 = "//*[@id=\"id" + "div" + "\"]";
        let keyElement2 = await driver.findElement(By.xpath(BUTTON_XPATH2));
        await keyElement2.click();
        await new Promise(f => setTimeout(f, 500));

        let BUTTON_XPATH1 = "//*[@id=\"id" + "2" + "\"]";
        let keyElement1 = await driver.findElement(By.xpath(BUTTON_XPATH1));
        await keyElement1.click();
        await new Promise(f => setTimeout(f, 500));

        let BUTTON_XPATH = "//*[@id=\"id" + "eql" + "\"]";
        let keyElement = await driver.findElement(By.xpath(BUTTON_XPATH));
        await keyElement.click();
        await new Promise(f => setTimeout(f, 500));

        let BUTTON_XPATH5 = "//*[@id=\"id" + "Update" + "\"]";
        let keyElement5 = await driver.findElement(By.xpath(BUTTON_XPATH5));
        await keyElement5.click();
        await new Promise(f => setTimeout(f, 5000));

        let DISPLAY_XPATH = "//*[@id=\"result\"]";
        let res =await driver.findElement(By.xpath(DISPLAY_XPATH));
        let result = await res.getAttribute("value");
        expect(result).toBe("3");
        
        await driver.quit();
    } , 20000);

    test('test_ui_123', async () => {
        const options = new Options();
        options.addArguments('remote-allow-origins=*');
        let driver = await new Builder().forBrowser(Browser.CHROME).setChromeOptions(options).build();
        driver.manage().setTimeouts({ implicit: 10000 });

        driver.get(URL);
        let BUTTON_XPATH4 = "//*[@id=\"id" + "reset" + "\"]";
        let keyElement4 = await driver.findElement(By.xpath(BUTTON_XPATH4));
        await keyElement4.click();
        await new Promise(f => setTimeout(f, 500));
        
        let BUTTON_XPATH3 = "//*[@id=\"id" + "1" + "\"]";
        let keyElement3 = await driver.findElement(By.xpath(BUTTON_XPATH3));
        await keyElement3.click();
        await new Promise(f => setTimeout(f, 500));

        let BUTTON_XPATH2 = "//*[@id=\"id" + "2" + "\"]";
        let keyElement2 = await driver.findElement(By.xpath(BUTTON_XPATH2));
        await keyElement2.click();
        await new Promise(f => setTimeout(f, 500));

        let BUTTON_XPATH1 = "//*[@id=\"id" + "3" + "\"]";
        let keyElement1 = await driver.findElement(By.xpath(BUTTON_XPATH1));
        await keyElement1.click();
        await new Promise(f => setTimeout(f, 500));

        let BUTTON_XPATH5 = "//*[@id=\"id" + "Update" + "\"]";
        let keyElement5 = await driver.findElement(By.xpath(BUTTON_XPATH5));
        await keyElement5.click();
        await new Promise(f => setTimeout(f, 5000));

        let DISPLAY_XPATH = "//*[@id=\"result\"]";
        let res =await driver.findElement(By.xpath(DISPLAY_XPATH));
        let result = await res.getAttribute("value");
        expect(result).toBe("123");
        
        await driver.quit();
    } , 20000);

    test('test_5_plus_3', async () => {
        const options = new Options();
        options.addArguments('remote-allow-origins=*');
        let driver = await new Builder().forBrowser(Browser.CHROME).setChromeOptions(options).build();
        driver.manage().setTimeouts({ implicit: 10000 });

        driver.get(URL);
        let BUTTON_XPATH4 = "//*[@id=\"id" + "reset" + "\"]";
        let keyElement4 = await driver.findElement(By.xpath(BUTTON_XPATH4));
        await keyElement4.click();
        await new Promise(f => setTimeout(f, 500));
        
        let BUTTON_XPATH3 = "//*[@id=\"id" + "5" + "\"]";
        let keyElement3 = await driver.findElement(By.xpath(BUTTON_XPATH3));
        await keyElement3.click();
        await new Promise(f => setTimeout(f, 500));

        let BUTTON_XPATH2 = "//*[@id=\"id" + "plus" + "\"]";
        let keyElement2 = await driver.findElement(By.xpath(BUTTON_XPATH2));
        await keyElement2.click();
        await new Promise(f => setTimeout(f, 500));

        let BUTTON_XPATH1 = "//*[@id=\"id" + "3" + "\"]";
        let keyElement1 = await driver.findElement(By.xpath(BUTTON_XPATH1));
        await keyElement1.click();
        await new Promise(f => setTimeout(f, 500));

        let BUTTON_XPATH5 = "//*[@id=\"id" + "Update" + "\"]";
        let keyElement5 = await driver.findElement(By.xpath(BUTTON_XPATH5));
        await keyElement5.click();
        await new Promise(f => setTimeout(f, 5000));

        let DISPLAY_XPATH = "//*[@id=\"result\"]";
        let res =await driver.findElement(By.xpath(DISPLAY_XPATH));
        let result = await res.getAttribute("value");
        expect(result).toBe("3");
        
        await driver.quit();
    } , 20000);
});
