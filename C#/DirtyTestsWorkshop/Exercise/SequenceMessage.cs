namespace DirtyTestsWorkshop.Exercise
{
    public class SequenceMessage
    {
        public string version;

        public char[] sequence;
        public bool resetOnError;

        public void AddSequence(string sequence)
        {
            this.sequence = sequence.ToCharArray();
        }
    }
}

