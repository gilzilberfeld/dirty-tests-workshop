using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DirtyTestsWorkshop.Exercise
{
    public class SequenceMessageHelper
    {
        public string version;

        public char[] sequence;
        public bool resetOnError;

        public void addSequence(String sequence)
        {
            this.sequence = sequence.ToCharArray();
        }
    }
}
