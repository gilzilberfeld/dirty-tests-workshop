from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from chromedriver_py import binary_path # this will get you the path variable
import pytest


def test_1():
    service_object = Service(binary_path)
    driver = webdriver.Chrome(service=service_object)
    driver.get("http://www.python.org")
    assert "Python" in driver.title

