package workshops.testingil.dirtytests.exercise;

public class SequenceMessage {
    public String version;

    public char[] sequence;
    public boolean resetOnError;

    public void addSequence(String sequence) {
        this.sequence = sequence.toCharArray();
    }
}
