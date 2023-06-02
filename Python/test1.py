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


path_to_file =os.getcwd() + ("\Resources\slow_unit_test_data.csv")
path_to_file1 =os.getcwd() + ("\Resources\sequence_api_data.csv")
path_to_file2 =os.getcwd() + ("\Resources\slow_unit_test_data_2.csv")

def test_1_2_3():
    c = Calculator()
    c.press("1")
    c.press("2")
    c.press("3")
    assert c.getDisplay() == "123"


# @pytest.mark.skip(reason="no way of currently testing this")
def test_plus_and_2():
    conn = http.client.HTTPConnection('localhost',8080)

    conn.request("POST", "/calc/reset")
    conn.getresponse().read()

    conn.request("POST", "/calc/press?key=" + urllib.parse.quote('+'))
    conn.getresponse().read()

    conn.request("POST", "/calc/press?key=" + urllib.parse.quote('2'))
    conn.getresponse().read()


    conn.request("GET", "/calc/display")
    response = conn.getresponse()
    result_json = json.loads(response.read())

    assert result_json['display'] == "2"
    conn.close()

def test_ui_1_plus_34():
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

    BUTTON_XPATH2 = "//*[@id=\'id" + "plus" + "\']"
    keyElement2 = driver.find_element(By.XPATH, BUTTON_XPATH2)
    keyElement2.click()
    time.sleep(0.5)

    BUTTON_XPATH1 = "//*[@id=\'id" + "3" + "\']"
    keyElement1 = driver.find_element(By.XPATH, BUTTON_XPATH1)
    keyElement1.click()
    time.sleep(0.5)

    BUTTON_XPATH = "//*[@id=\'id" + "4" + "\']"
    keyElement = driver.find_element(By.XPATH, BUTTON_XPATH)
    keyElement.click()
    time.sleep(0.5)

    BUTTON_XPATH5 = "//*[@id=\'id" + "Update" + "\']"
    keyElement5 = driver.find_element(By.XPATH, BUTTON_XPATH5)
    keyElement5.click()
    time.sleep(0.5)

    DISPLAY_XPATH = "//*[@id=\"result\"]";
    res = driver.find_element(By.XPATH, DISPLAY_XPATH)
    result = res.get_attribute("value")

    assert result == "34"
    driver.quit()


def get_test_data():
    with open(path_to_file, 'r') as f:
        reader = csv.reader(f)
        data = {tuple(row) for row in reader}
    return data

@pytest.mark.parametrize("input, output", get_test_data())
def test_multiple_vals(input, output):
    c = Calculator()
    for ch in input:
        c.press(ch)
    assert c.getDisplay() == output


def test_nothing():
    c = Calculator()
    assert (c.getDisplay() == "0") == True


# @pytest.mark.skip(reason="no way of currently testing this")
def test_1_and_plus():
    conn = http.client.HTTPConnection('localhost',8080)

    conn.request("POST", "/calc/reset")
    conn.getresponse().read()

    conn.request("POST", "/calc/press?key=" + urllib.parse.quote('1'))
    conn.getresponse().read()

    conn.request("GET", "/calc/display")
    response = conn.getresponse()
    result_json = json.loads(response.read())

    assert result_json['display'] == "1"
    conn.close()


def test_1():
    c = Calculator()
    c.press("1")
    assert  (c.getDisplay() == "1") == True



def test_zero_zero():
    conn = http.client.HTTPConnection('localhost',8080)

    conn.request("POST", "/calc/reset")
    conn.getresponse().read()

    conn.request("POST", "/calc/press?key=" + urllib.parse.quote('0'))
    conn.getresponse().read()
    conn.request("POST", "/calc/press?key=" + urllib.parse.quote('0'))
    conn.getresponse().read()


    conn.request("GET", "/calc/display")
    response = conn.getresponse()
    result_json = json.loads(response.read())

    assert result_json['display'] == "0"
    conn.close()

def test_div_6_2():
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

    BUTTON_XPATH3 = "//*[@id=\'id" + "6" + "\']"
    keyElement3 = driver.find_element(By.XPATH, BUTTON_XPATH3)
    keyElement3.click()
    time.sleep(0.5)

    BUTTON_XPATH2 = "//*[@id=\'id" + "div" + "\']"
    keyElement2 = driver.find_element(By.XPATH, BUTTON_XPATH2)
    keyElement2.click()
    time.sleep(0.5)

    BUTTON_XPATH1 = "//*[@id=\'id" + "2" + "\']"
    keyElement1 = driver.find_element(By.XPATH, BUTTON_XPATH1)
    keyElement1.click()
    time.sleep(0.5)

    BUTTON_XPATH = "//*[@id=\'id" + "eql" + "\']"
    keyElement = driver.find_element(By.XPATH, BUTTON_XPATH)
    keyElement.click()
    time.sleep(0.5)

    BUTTON_XPATH5 = "//*[@id=\'id" + "Update" + "\']"
    keyElement5 = driver.find_element(By.XPATH, BUTTON_XPATH5)
    keyElement5.click()
    time.sleep(0.5)

    DISPLAY_XPATH = "//*[@id=\"result\"]";
    res = driver.find_element(By.XPATH, DISPLAY_XPATH)
    result = res.get_attribute("value")

    assert result == "3"
    driver.quit()


def test_1_and_C():
    c = Calculator()
    c.press("1")
    c.press("C")
    assert c.getDisplay() == "0"


def test_0():
    conn = http.client.HTTPConnection('localhost',8080)

    conn.request("POST", "/calc/press?key=" + urllib.parse.quote('0'))
    conn.getresponse().read()

    conn.request("GET", "/calc/display")
    response = conn.getresponse()
    result_json = json.loads(response.read())

    assert result_json['display'] == "0"
    conn.close()

