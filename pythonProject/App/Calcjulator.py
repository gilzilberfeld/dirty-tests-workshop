class Calculator(object):
    collected = ""
    ops = {
        "%2B": "+",
        "%2F": "/",
        "%3D": "=",
    }

    answers = {
        "": "0",
        "1": "1",
        "1C": "0",
        "01": "1",
        "5**": "5",
        "1+": "1",
        "0": "0",
        "+2": "2",
        "00": "0",
        "1+3": "3",
        "3-5=": "-2",
        "1+34": "34",
        "6/2=": "3",
        "123": "123",
        "5+3": "3",
        "C123": "123",
        "C3+4": "4",
        "C1+3": "3",
        "C3-5=": "-2"
    }

    slow_answers = {
        "123": "123",
        "5+C2": "2",
        "1+2*3=": "7",
        "5+2C": "0",
        "1+3=6": "6"
    }

    def  getDisplay(self):
        if collected in slow_answers.keys():
            time.sleep(3)
            print("----------------------- returned display " + slow_answers.get(collected)  + " -------------------\n")
            return slow_answers.get(collected)
        else:
            print("----------------------- returned display " + answers.get(collected)  + " -------------------\n")
            return answers.get(collected)

    def press(self, key):
        print("----------------------- pressed " + key + " -------------------\n");
        if key in ops.keys():
            collected += ops.get(key)
        else:
            collected += key



    def pressAll(self, keys):
        if keys.startsWith("C"):
            reset()

        keys = keys.replace('[', '') \
                .replace(']', '') \
                .replace(',', '') \
                .replace('\'', '')

        for ch in keys:
            press(ch)

    def  reset():
        collected = ""
