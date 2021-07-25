using Microsoft.VisualStudio.TestTools.UnitTesting;
using System.IO;

namespace DirtyTests
{
    [TestClass]
    public class D4_Abstraction
    {
        private string lyrics;

        [TestMethod]
        public void Noisy_test()
        {
            lyrics = File.ReadAllText("BackInBlackLyrics.txt");
            Assert.IsTrue(lyrics.Contains("I'm back in black"));
        }

        [TestMethod]
        public void Readable_test()
        {
            ReadLyrics("BackInBlackLyrics.txt");
            shouldContain("I'm back in black");
        }
        private void ReadLyrics(string filename) 
        {
            lyrics = File.ReadAllText("BackInBlackLyrics.txt");
        }

        private void shouldContain(string line)
        {
            Assert.IsTrue(lyrics.Contains(line));
        }

}
