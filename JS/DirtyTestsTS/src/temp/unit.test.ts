import { Calculator } from "../App/Calculator";

describe("unit tests", () => {

    test('test_nothing', () => {

        const c = new Calculator();

        expect(c.getDisplay()).toEqual("0");
    });

    test('test_1', () => {
        const c = new Calculator();
        c.press("1")
        expect(c.getDisplay()).toEqual("1");
    });

    test('test_1_and_C', () => {
        const c = new Calculator();
        c.press("1")
        c.press("C")
        expect(c.getDisplay()).toEqual("0");
    });

    test('test_01', () => {
        const c = new Calculator();
        c.press("0")
        c.press("1")
        expect(c.getDisplay()).toEqual("1");
    });

    test('test_5_mul_and_mul', () => {
        const c = new Calculator();
        c.press("5")
        c.press("*")
        c.press("*")
        expect(c.getDisplay()).toEqual("5");
    });

});
