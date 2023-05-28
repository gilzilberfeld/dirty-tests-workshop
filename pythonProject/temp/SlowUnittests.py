import csv
import os

import pytest
from App.Calculator import Calculator

path_to_file =os.getcwd() + ("\..\Resources\slow_unit_test_data.csv")
path_to_file2 =os.getcwd() + ("\..\Resources\slow_unit_test_data_2.csv")


def test_1_2_3():
    c = Calculator()
    c.press("1")
    c.press("2")
    c.press("3")
    assert c.getDisplay() == "123"


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