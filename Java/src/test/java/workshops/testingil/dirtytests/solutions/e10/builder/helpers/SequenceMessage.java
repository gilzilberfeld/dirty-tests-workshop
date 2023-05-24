package workshops.testingil.dirtytests.solutions.e10.builder.helpers;

public class SequenceMessage {
    public String version;
    public char[] sequence;
    public boolean resetOnError;

    public void addSequence(String sequence) {
        this.sequence = sequence.toCharArray();
    }
}
