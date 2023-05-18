package workshops.testingil.dirtytests.part2;

public class SequenceMessageHelper {
    public String version;

    public char[] sequence;
    public boolean resetOnError;

    public void addSequence(String sequence) {
        this.sequence = sequence.toCharArray();
    }
}
