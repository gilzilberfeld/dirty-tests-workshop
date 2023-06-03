import { Calculator } from "../App/Calculator";
import * as fs from "fs";
import * as csvParse from 'csv-parse';
const path_to_file = "/../../Resources/slow_unit_test_data.csv"
const path_to_file2 = "/../../Resources/slow_unit_test_data_2.csv"

describe("slow unit tests", () => {


    test('test_1_2_3', () => {
        const c = new Calculator();
        c.press("1");
        c.press("2");
        c.press("3");
        expect(c.getDisplay()).toEqual("123");
    });

    test.each(get_test_data())("test_multiple_vals", (input, expected) => {
        const c = new Calculator();
        for (var i = 0; i < input.length; i++) {
            c.press(input[i]);
          }
        expect(c.getDisplay()).toEqual(expected);

    });


    function get_test_data() {

        var records : [[string, string]] =[["",""]];
        records.pop();

        var content = fs.readFileSync(__dirname + path_to_file, { encoding: 'utf-8' });
        let data = content.split(/\r?\n/);
        for (let i in data) {
            let record  = data[i].split(",");
            console.log("\n" + record);
            records.push([record[0].replace(/['"]+/g, ''), record[1].replace(/['"]+/g, '')]);
        }

        return records;
    };

    test.each(get_test_data())("test_multiple_vals_2", (input, expected) => {
        const c = new Calculator();
        c.pressAll(input);
        expect(c.getDisplay()).toEqual(expected);

    });

    function get_test_data2() {

        var records : [[string, string]] =[["",""]];
        records.pop();

        var content = fs.readFileSync(__dirname + path_to_file2, { encoding: 'utf-8' });
        let data = content.split(/\r?\n/);
        for (let i in data) {
            let record  = data[i].split(",");
            console.log("\n" + record);
            records.push([record[0].replace(/['"]+/g, ''), record[1].replace(/['"]+/g, '')]);
        }

        return records;
    };

});