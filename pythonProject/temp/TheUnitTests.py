import unittest
from App.Calculator import Calculator


class TheUnitTests(unittest.TestCase):
    def test_nothing(self):
        c = Calculator()
        self.assertTrue(c.getDisplay() == "0")

    def test_1(self):
        c = Calculator()
        c.press("1")
        self.assertTrue(c.getDisplay() == "1")

    def test_1_and_C(self):
        c = Calculator()
        c.press("1")
        c.press("C")
        self.assertEqual(c.getDisplay(), "0")
