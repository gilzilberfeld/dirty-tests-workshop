import pytest
import requests
import urllib.parse
from SequenceMessageHelper import SequenceMessageHelper
import http.client
import json
import os
import csv
import jsonpickle


path_to_file1 =os.getcwd() + ("\..\Resources\sequence_api_data.csv")
path_to_file2 =os.getcwd() + ("\..\Resources\sslow_unit_test_data_2.csv")

def test_0():
    conn = http.client.HTTPConnection('localhost',8080)

    conn.request("POST", "/calc/press?key=" + urllib.parse.quote('0'))
    conn.getresponse().read()

    conn.request("GET", "/calc/display")
    response = conn.getresponse()
    result_json = json.loads(response.read())

    assert result_json['display'] == "0"
    conn.close()

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

    ref_file = os.getcwd() + "\..\Resources\\reference_result.json"
    with open(ref_file, 'r') as f:
        json_reference = f.read()

    assert  result_json == json_reference