import pytest
from App.Calculator import Calculator


def test_nothing():
    c = Calculator()
    assert (c.getDisplay() == "0") == True

def test_1():
    c = Calculator()
    c.press("1")
    assert  (c.getDisplay() == "1") == True

def test_1_and_C():
    c = Calculator()
    c.press("1")
    c.press("C")
    assert c.getDisplay() == "0"

def test_01():
    c = Calculator()
    c.press("0")
    c.press("1")
    assert (c.getDisplay() == "1") == True

def test_5_mul_and_mul():
    c = Calculator()
    c.press("5")
    c.press("*");
    c.press("*");
    assert c.getDisplay() ==  "5"
