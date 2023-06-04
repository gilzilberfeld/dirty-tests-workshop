import axios from 'axios';
import * as fs from "fs";


const path_to_file1 = "/../../Resources/sequence_api_data.csv"
const path_to_file2 = "/../../Resources/slow_unit_test_data_2.csv"

class SequenceMessageHelper {

  version: string = "";
  sequence: string[] = []
  resetOnError: boolean = false;

  addSequence(sequence: string) {
    this.sequence = sequence.split('');
  }
}


describe("API tests", () => {

  test("test_0", async () => {

    await axios.post("http://localhost:8080/calc/press", null, { params: { key: 0 } })

    const response = await axios.get("http://localhost:8080/calc/display");
    expect(response.data.display).toBe('0');

  });

  // skip(reason="no way of currently testing this")
  test("test_1_and_plus", async () => {

    await axios.post("http://localhost:8080/calc/reset");
    await axios.post("http://localhost:8080/calc/press", null, { params: { key: 1 } });
    await axios.post("http://localhost:8080/calc/press", null, { params: { key: encodeURIComponent('+') } });

    const response = await axios.get("http://localhost:8080/calc/display");
    expect(response.data.display).toBe('1');

  });

  // skip(reason="no way of currently testing this")
  test("test_plus_and_2", async () => {

    await axios.post("http://localhost:8080/calc/reset");
    await axios.post("http://localhost:8080/calc/press", null, { params: { key: encodeURIComponent('+') } });
    await axios.post("http://localhost:8080/calc/press", null, { params: { key: 2 } });

    const response = await axios.get("http://localhost:8080/calc/display");
    expect(response.data.display).toBe('2');

  });

  test("test_zero_zero", async () => {

    await axios.post("http://localhost:8080/calc/reset");
    await axios.post("http://localhost:8080/calc/press", null, { params: { key: 0 } });
    await axios.post("http://localhost:8080/calc/press", null, { params: { key: 0 } });

    const response = await axios.get("http://localhost:8080/calc/display");
    expect(response.data.display).toBe('0');

  });

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