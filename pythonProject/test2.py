import pytest
import requests
import urllib.parse
from SequenceMessageHelper import SequenceMessageHelper
import http.client
import time
import json
import os
import csv
import jsonpickle
from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from chromedriver_py import binary_path
from App.Calculator import Calculator

path_to_file1 =os.getcwd() + ("\Resources\sequence_api_data.csv")
path_to_file2 =os.getcwd() + ("\Resources\slow_unit_test_data_2.csv")


def get_test_data1():
    with open(path_to_file1, 'r') as f:
        reader = csv.reader(f)
        data = {tuple(row) for row in reader}
    return data


@pytest.mark.parametrize("input, output", get_test_data1())
def test_multiple_sequences(input, output):
    conn = http.client.HTTPConnection('localhost',8080)

    conn.request("POST", "/calc/reset")
    conn.getresponse().read()

    message = SequenceMessageHelper()
    message.version = "1.0";
    message.addSequence(input);
    message.resetOnError = True;

    headers = {"Accept": "application/json"}
    msgJSON = jsonpickle.encode(message, unpicklable=False)

    conn.request("POST", "/calc/sequence", body=msgJSON, headers=headers)
    conn.getresponse().read()

    conn.request("GET", "/calc/display")
    response = conn.getresponse()
    result_json = json.loads(response.read())

    assert result_json['display'] == output

    conn.close()

def test_01():
    c = Calculator()
    c.press("0")
    c.press("1")
    assert (c.getDisplay() == "1") == True

def test_ui_123():
    options = webdriver.ChromeOptions()
    options.add_argument("start-remote-allow-origins=*")

    service_object = Service(binary_path)
    driver = webdriver.Chrome(options=options, service=service_object)
    driver.timeouts.implicit_wait = 10
    driver.get(os.getcwd() + ("\App\CalculatorUI.html"))

    BUTTON_XPATH4 = "//*[@id=\'id" + "reset" + "\']"
    keyElement4 = driver.find_element(By.XPATH, BUTTON_XPATH4)
    keyElement4.click()
    time.sleep(0.5)

    BUTTON_XPATH3 = "//*[@id=\'id" + "1" + "\']"
    keyElement3 = driver.find_element(By.XPATH, BUTTON_XPATH3)
    keyElement3.click()
    time.sleep(0.5)

    BUTTON_XPATH2 = "//*[@id=\'id" + "2" + "\']"
    keyElement2 = driver.find_element(By.XPATH, BUTTON_XPATH2)
    keyElement2.click()
    time.sleep(0.5)

    BUTTON_XPATH1 = "//*[@id=\'id" + "3" + "\']"
    keyElement1 = driver.find_element(By.XPATH, BUTTON_XPATH1)
    keyElement1.click()
    time.sleep(0.5)

    BUTTON_XPATH5 = "//*[@id=\'id" + "Update" + "\']"
    keyElement5 = driver.find_element(By.XPATH, BUTTON_XPATH5)
    keyElement5.click()
    time.sleep(5)

    DISPLAY_XPATH = "//*[@id=\"result\"]";
    res = driver.find_element(By.XPATH, DISPLAY_XPATH)
    result = res.get_attribute("value")

    assert result == "123"
    driver.quit()


def get_test_data2():
    with open(path_to_file1, 'r') as f:
        reader = csv.reader(f)
        data = {tuple(row) for row in reader}
    return data

@pytest.mark.parametrize("input, output", get_test_data2())
def test_more_sequences(input, output):
    conn = http.client.HTTPConnection('localhost',8080)

    conn.request("POST", "/calc/reset")
    conn.getresponse().read()

    message = SequenceMessageHelper()
    message.version = "1.0";
    message.addSequence(input);
    message.resetOnError = True;

    headers = {"Accept": "application/json"}
    msgJSON = jsonpickle.encode(message, unpicklable=False)

    conn.request("POST", "/calc/sequence", body=msgJSON, headers=headers)
    conn.getresponse().read()

    conn.request("GET", "/calc/display")
    response = conn.getresponse()
    result_json = json.loads(response.read())

    assert result_json['display'] == output

    conn.close()


def test_5_mul_and_mul():
    c = Calculator()
    c.press("5")
    c.press("*");
    c.press("*");
    assert c.getDisplay() ==  "5"

def test_5_plus_3():
    options = webdriver.ChromeOptions()
    options.add_argument("start-remote-allow-origins=*")

    service_object = Service(binary_path)
    driver = webdriver.Chrome(options=options, service=service_object)
    driver.timeouts.implicit_wait = 10
    driver.get(os.getcwd() + ("\App\CalculatorUI.html"))

    BUTTON_XPATH4 = "//*[@id=\'id" + "reset" + "\']"
    keyElement4 = driver.find_element(By.XPATH, BUTTON_XPATH4)
    keyElement4.click()
    time.sleep(0.5)

    BUTTON_XPATH3 = "//*[@id=\'id" + "5" + "\']"
    keyElement3 = driver.find_element(By.XPATH, BUTTON_XPATH3)
    keyElement3.click()
    time.sleep(0.5)

    BUTTON_XPATH2 = "//*[@id=\'id" + "plus" + "\']"
    keyElement2 = driver.find_element(By.XPATH, BUTTON_XPATH2)
    keyElement2.click()
    time.sleep(0.5)

    BUTTON_XPATH1 = "//*[@id=\'id" + "3" + "\']"
    keyElement1 = driver.find_element(By.XPATH, BUTTON_XPATH1)
    keyElement1.click()
    time.sleep(0.5)

    BUTTON_XPATH5 = "//*[@id=\'id" + "Update" + "\']"
    keyElement5 = driver.find_element(By.XPATH, BUTTON_XPATH5)
    keyElement5.click()
    time.sleep(5)

    DISPLAY_XPATH = "//*[@id=\"result\"]";
    res = driver.find_element(By.XPATH, DISPLAY_XPATH)
    result = res.get_attribute("value")

    assert result == "3"
    driver.quit()

def get_test_data2():
    with open(path_to_file2, 'r') as f:
        reader = csv.reader(f)
        data = {tuple(row) for row in reader}
    return data

@pytest.mark.parametrize("input, output", get_test_data2())
def test_multiple_vals_2(input, output):
    c = Calculator()
    c.pressAll(input)
    assert c.getDisplay() == output

def test_result():
    conn = http.client.HTTPConnection('localhost',8080)

    conn.request("POST", "/calc/reset")
    conn.getresponse().read()

    message = SequenceMessageHelper()
    message.version = "1.0";
    message.addSequence("5+2C");
    message.resetOnError = True;

    headers = {"Accept": "application/json"}
    msgJSON = jsonpickle.encode(message, unpicklable=False)

    conn.request("POST", "/calc/sequence", body=msgJSON, headers=headers)
    conn.getresponse().read()

    conn.request("GET", "/calc/display")
    response = conn.getresponse()
    result_json = response.read().decode("utf-8")

    ref_file = os.getcwd() + "\Resources\\reference_result.json"
    with open(ref_file, 'r') as f:
        json_reference = f.read()

    assert  result_json == json_reference