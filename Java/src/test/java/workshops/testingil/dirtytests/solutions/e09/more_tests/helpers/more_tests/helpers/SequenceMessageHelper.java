package workshops.testingil.dirtytests.solutions.e09.more_tests.helpers.more_tests.helpers;

public class SequenceMessageHelper {
    public String version;

    public char[] sequence;
    public boolean resetOnError;

    public void addSequence(String sequence) {
        this.sequence = sequence.toCharArray();
    }
}
