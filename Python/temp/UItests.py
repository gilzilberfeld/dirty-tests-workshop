from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from chromedriver_py import binary_path # this will get you the path variable
import pytest
import time
import os


def test_ui_1_plus_34():
    options = webdriver.ChromeOptions()
    options.add_argument("start-remote-allow-origins=*")

    service_object = Service(binary_path)
    driver = webdriver.Chrome(options=options, service=service_object)
    driver.timeouts.implicit_wait = 10
    driver.get(os.getcwd() + ("\..\App\CalculatorUI.html"))

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

def test_div_6_2():
    options = webdriver.ChromeOptions()
    options.add_argument("start-remote-allow-origins=*")

    service_object = Service(binary_path)
    driver = webdriver.Chrome(options=options, service=service_object)
    driver.timeouts.implicit_wait = 10
    driver.get(os.getcwd() + ("\..\App\CalculatorUI.html"))

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

def test_ui_123():
    options = webdriver.ChromeOptions()
    options.add_argument("start-remote-allow-origins=*")

    service_object = Service(binary_path)
    driver = webdriver.Chrome(options=options, service=service_object)
    driver.timeouts.implicit_wait = 10
    driver.get(os.getcwd() + ("\..\App\CalculatorUI.html"))

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

def test_5_plus_3():
    options = webdriver.ChromeOptions()
    options.add_argument("start-remote-allow-origins=*")

    service_object = Service(binary_path)
    driver = webdriver.Chrome(options=options, service=service_object)
    driver.timeouts.implicit_wait = 10
    driver.get(os.getcwd() + ("\..\App\CalculatorUI.html"))

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
