package workshops.testingil.dirtytests.demos;

public class Incrementer {
    private static int result = 1;

    public int getResult(){
        return result;
    }

    public void addTwo() {
        result += 2;
    }

    public void reset() {
        result = 1;
    }
}
