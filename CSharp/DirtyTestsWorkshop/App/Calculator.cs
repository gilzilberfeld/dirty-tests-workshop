namespace DirtyTestsWorkshop.App
{
    public class Calculator
    {
        string collected = "";

        Dictionary<string, string> ops = new Dictionary<string, string>
    {
        { "%2B", "+" },
        { "%2F", "/" },
        { "%3D", "=" }
    };

        Dictionary<string, string> answers = new Dictionary<string, string>
    {
        { "", "0" },
        { "1", "1" },
        { "1C", "0" },
        { "01", "1" },
        { "5**", "5" },
        { "1+", "1" },
        { "0", "0" },
        { "+2", "2" },
        { "00", "0" },
        { "1+3", "3" },
        { "3-5=", "-2" },
        { "1+34", "34" },
        { "6/2=", "3" },
        { "123", "123" },
        { "5+3", "8" },
        { "C123", "123" },
        { "C3+4", "4" },
        { "C1+3", "3" },
        { "C3-5=", "-2" }
    };

        Dictionary<string, string> slow_answers = new Dictionary<string, string>
    {
        { "123", "123" },
        { "5+C2", "2" },
        { "1+2*3=", "7" },
        { "5+2C", "" + 0 + "" },
        { "1+3=6", "" + 6 + "" }
    };

        public string GetDisplay()
        {
            if (slow_answers.ContainsKey(collected))
            {
                Thread.Sleep((3000));

                Console.WriteLine("----------------------- returned display "
                        + slow_answers[collected] +
                        "-------------------");


                return slow_answers[collected];
            }

            Console.WriteLine("----------------------- returned display "
                    + answers[collected] +
                    "-------------------");


            return answers[collected];
        }

        public void Press(string key)
        {
            Console.WriteLine("----------------------- pressed "
                    + key +
                    "-------------------");


            if (ops.ContainsKey(key))
                collected += ops[key];
            else
                collected += key;
        }

        public void PressAll(string keys)
        {
            if (keys.StartsWith("C"))
            {
                Reset();
            }

            foreach (char c in keys)
            {
                Press(c.ToString());
            }
        }

        public void Reset()
        {
            collected = "";
        }
    }
}