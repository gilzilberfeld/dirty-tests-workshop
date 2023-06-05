import axios from 'axios';
import * as fs from "fs";
import { Builder, Browser, until, By, Key } from 'selenium-webdriver';
import { Options } from 'selenium-webdriver/chrome';
import { Calculator } from "../App/Calculator";

const URL = "C:\\GitHub\\Presentations\\Dirty-Tests-Workshop\\JS\\DirtyTestsTS\\src\\App\\CalculatorUI.html";
const path_to_file1 = "/../../Resources/sequence_api_data.csv"
const path_to_file2 = "/../../Resources/slow_unit_test_data_2.csv"

class SequenceMessageHelper {

    version: string = "";
    sequence: string[] = []
    resetOnError: boolean = false;

    addSequence(sequence: string) {
        this.sequence = sequence.split('');
    }
};

describe("tests2", () => {

    test.each(get_test_data1())("test_multiple_sequences", async (input, output) => {

        await axios.post("http://localhost:8080/calc/reset");

        const message = new SequenceMessageHelper();
        message.version = "1.0";
        message.addSequence(input);
        message.resetOnError = true;


        await axios.post("http://localhost:8080/calc/sequence", { data: JSON.stringify(message) }, { headers: { Accept: "application/json" }, params: { key: 0 } });

        const response = await axios.get("http://localhost:8080/calc/display");
        expect(response.data.display).toBe(output);

    });

    function get_test_data1() {

        var records: [[string, string]] = [["", ""]];
        records.pop();

        var content = fs.readFileSync(__dirname + path_to_file1, { encoding: 'utf-8' });
        let data = content.split(/\r?\n/);
        for (let i in data) {
            let record = data[i].split(",");
            records.push([record[0].replace(/['"]+/g, ''), record[1].replace(/['"]+/g, '')]);
        }

        return records;
    };

    test('test_01', () => {
        const c = new Calculator();
        c.press("0")
        c.press("1")
        expect(c.getDisplay()).toEqual("1");
    });

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
        let res = await driver.findElement(By.xpath(DISPLAY_XPATH));
        let result = await res.getAttribute("value");
        expect(result).toBe("123");

        await driver.quit();
    }, 20000);

    test.each(get_test_data2())("test_more_sequences", async (input, output) => {

        await axios.post("http://localhost:8080/calc/reset");

        const message = new SequenceMessageHelper();
        message.version = "1.0";
        message.addSequence(input);
        message.resetOnError = true;


        await axios.post("http://localhost:8080/calc/sequence", { data: JSON.stringify(message) }, { headers: { Accept: "application/json" }, params: { key: 0 } });

        const response = await axios.get("http://localhost:8080/calc/display");
        expect(response.data.display).toBe(output);

    });

    function get_test_data2() {

        var records: [[string, string]] = [["", ""]];
        records.pop();

        var content = fs.readFileSync(__dirname + path_to_file2, { encoding: 'utf-8' });
        let data = content.split(/\r?\n/);
        for (let i in data) {
            let record = data[i].split(",");
            records.push([record[0].replace(/['"]+/g, ''), record[1].replace(/['"]+/g, '')]);
        }

        return records;
    };

    test('test_5_mul_and_mul', () => {
        const c = new Calculator();
        c.press("5")
        c.press("*")
        c.press("*")
        expect(c.getDisplay()).toEqual("5");
    });

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
        let res = await driver.findElement(By.xpath(DISPLAY_XPATH));
        let result = await res.getAttribute("value");
        expect(result).toBe("3");

        await driver.quit();
    }, 20000);

    test.each(get_test_data2())("test_multiple_vals_2", (input, expected) => {
        const c = new Calculator();
        c.pressAll(input);
        expect(c.getDisplay()).toEqual(expected);

    });

    test("test_result", async () => {

        await axios.post("http://localhost:8080/calc/reset");
    
        const message = new SequenceMessageHelper();
        message.version = "1.0";
        message.addSequence("5+2C");
        message.resetOnError = true;
    
        await axios.post("http://localhost:8080/calc/sequence", { data: JSON.stringify(message) }, { headers: { Accept: "application/json" }, params: { key: 0 } });
    
        const response = await axios.get("http://localhost:8080/calc/display");
      
        var content = fs.readFileSync(__dirname + "/../../Resources/reference_result.json", { encoding: 'utf-8' });
        expect(JSON.stringify( response.data)).toBe(content);
    
      });
    
});