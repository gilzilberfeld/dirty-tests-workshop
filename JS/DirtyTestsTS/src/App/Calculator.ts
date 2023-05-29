export const sleep = async (waitTime: number) =>
  new Promise(resolve =>
    setTimeout(resolve, waitTime));

let ops = new Map<string, string>([
    ["%2B", "+"],
    ["%2F", "/"],
    ["%3D", "="]
]);

let answers = new Map<string, string>([
    ["", "0"],
    ["1", "1"],
    ["1C", "0"],
    ["01", "1"],
    ["5**", "5"],
    ["1+", "1"],
    ["0", "0"],
    ["+2", "2"],
    ["00", "0"],
    ["1+3", "3"],
    ["3-5=", "-2"],
    ["1+34", "34"],
    ["6/2=", "3"],
    ["123", "123"],
    ["5+3","3"],
    ["C123", "123"],
    ["C3+4", "4"],
    ["C1+3", "3"],
    ["C3-5=", "-2"]
]);


let slow_answers = new Map<string, string>([
    ["123", "123"],
    ["5+C2", "2"],
    ["1+2*3=", "7"],
    ["5+2C", "0"],
    ["1+3=6", "6"]
]);

export class Calculator {
    collected="";

       

    getDisplay(): string {
        if (slow_answers.has(this.collected))
        {
            sleep(3000);
            console.log("----------------------- returned display " + slow_answers.get(this.collected)  + " -------------------\n")
            return slow_answers.get(this.collected) as string;
        }
        else{
            console.log("----------------------- returned display " + answers.get(this.collected)   + " -------------------\n")
            return answers.get(this.collected) as string;
        }
    }

    press(key: string) {
        console.log("----------------------- pressed " + key + " -------------------\n");
        if (ops.has(key)){
            this.collected += ops.get(key);
        }
        else {
            this.collected += key;
        }
    }
    
    pressAll(keys:string) {
        if (keys.startsWith("C"))
            this.reset();
        keys = keys.replace('[', '').replace(']','').replace(',','').replace('\'','');

        for (const ch of keys) {
            this.press(ch);
        }
    }

    reset() {
       this.collected ="";     
    }
}