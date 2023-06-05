import axios from "axios";
import * as fs from "fs";
import { Builder, Browser, until, By, Key } from 'selenium-webdriver';
import { Options } from 'selenium-webdriver/chrome';
import { Calculator } from "../App/Calculator";

const path_to_file = "/../../Resources/slow_unit_test_data.csv"
const URL = "C:\\GitHub\\Presentations\\Dirty-Tests-Workshop\\JS\\DirtyTestsTS\\src\\App\\CalculatorUI.html";

describe("tests1", () => {
    test('test_1_2_3', () => {
        const c = new Calculator();
        c.press("1");
        c.press("2");
        c.press("3");
        expect(c.getDisplay()).toEqual("123");
    });

    // skip(reason="no way of currently testing this")
    test("test_plus_and_2", async () => {

        await axios.post("http://localhost:8080/calc/reset");
        await axios.post("http://localhost:8080/calc/press", null, { params: { key: encodeURIComponent('+') } });
        await axios.post("http://localhost:8080/calc/press", null, { params: { key: 2 } });

        const response = await axios.get("http://localhost:8080/calc/display");
        expect(response.data.display).toBe('2');

    });

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
        await new Promise(f => setTimeout(f, 5000));

        let DISPLAY_XPATH = "//*[@id=\"result\"]";
        let res = await driver.findElement(By.xpath(DISPLAY_XPATH));
        let result = await res.getAttribute("value");
        expect(result).toBe("34");

        await driver.quit();
    }, 20000);

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
        let res = await driver.findElement(By.xpath(DISPLAY_XPATH));
        let result = await res.getAttribute("value");
        expect(result).toBe("3");

        await driver.quit();
    }, 20000);

    test.each(get_test_data())("test_multiple_vals", (input, expected) => {
        const c = new Calculator();
        for (var i = 0; i < input.length; i++) {
            c.press(input[i]);
        }
        expect(c.getDisplay()).toEqual(expected);

    });


    function get_test_data() {

        var records: [[string, string]] = [["", ""]];
        records.pop();

        var content = fs.readFileSync(__dirname + path_to_file, { encoding: 'utf-8' });
        let data = content.split(/\r?\n/);
        for (let i in data) {
            let record = data[i].split(",");
            records.push([record[0].replace(/['"]+/g, ''), record[1].replace(/['"]+/g, '')]);
        }

        return records;
    };

    test('test_nothing', () => {

        const c = new Calculator();

        expect(c.getDisplay()).toEqual("0");
    });

    // skip(reason="no way of currently testing this")
    test("test_1_and_plus", async () => {

        await axios.post("http://localhost:8080/calc/reset");
        await axios.post("http://localhost:8080/calc/press", null, { params: { key: 1 } });
        await axios.post("http://localhost:8080/calc/press", null, { params: { key: encodeURIComponent('+') } });

        const response = await axios.get("http://localhost:8080/calc/display");
        expect(response.data.display).toBe('1');

    });

    test('test_1', () => {
        const c = new Calculator();
        c.press("1")
        expect(c.getDisplay()).toEqual("1");
    });

    test("test_zero_zero", async () => {

        await axios.post("http://localhost:8080/calc/reset");
        await axios.post("http://localhost:8080/calc/press", null, { params: { key: 0 } });
        await axios.post("http://localhost:8080/calc/press", null, { params: { key: 0 } });

        const response = await axios.get("http://localhost:8080/calc/display");
        expect(response.data.display).toBe('0');

    });

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
        let res = await driver.findElement(By.xpath(DISPLAY_XPATH));
        let result = await res.getAttribute("value");
        expect(result).toBe("3");

        await driver.quit();
    }, 20000);

    test('test_1_and_C', () => {
        const c = new Calculator();
        c.press("1")
        c.press("C")
        expect(c.getDisplay()).toEqual("0");
    });

    test("test_0", async () => {

        await axios.post("http://localhost:8080/calc/press", null, { params: { key: 0 } })

        const response = await axios.get("http://localhost:8080/calc/display");
        expect(response.data.display).toBe('0');

    });

});