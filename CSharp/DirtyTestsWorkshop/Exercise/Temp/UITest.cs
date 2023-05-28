
using DirtyTestsWorkshop.App;
using NUnit.Framework;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using System;

namespace DirtyTestsWorkshop.Exercise.Temp
{
    [TestFixture]
    public class UITests
    {
        private WebDriver driver;

        [Test]
        public void test_ui_1_plus_34()
        {
            var URL = "file:///" + Environment.CurrentDirectory + "/CalculatorUI.html";
            ChromeOptions options = new ChromeOptions();
            options.AddArguments("remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(10);
            driver.Url = URL;

            String BUTTON_XPATH4 = "//*[@id=\'id" + "reset" + "\']";
            IWebElement keyElement4 = driver.FindElement(By.XPath(BUTTON_XPATH4));
            keyElement4.Click();

            Thread.Sleep(500);

            String BUTTON_XPATH3 = "//*[@id=\'id" + "1" + "\']";
            IWebElement keyElement3 = driver.FindElement(By.XPath(BUTTON_XPATH3));
            keyElement3.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH2 = "//*[@id=\'id" + "plus" + "\']";
            IWebElement keyElement2 = driver.FindElement(By.XPath(BUTTON_XPATH2));
            keyElement2.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH1 = "//*[@id=\'id" + "3" + "\']";
            IWebElement keyElement1 = driver.FindElement(By.XPath(BUTTON_XPATH1));
            keyElement1.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH = "//*[@id=\'id" + "4" + "\']";
            IWebElement keyElement = driver.FindElement(By.XPath(BUTTON_XPATH));
            keyElement.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH5 = "//*[@id=\'id" + "Update" + "\']";
            IWebElement keyElement5 = driver.FindElement(By.XPath(BUTTON_XPATH5));
            keyElement5.Click();
            Thread.Sleep(500);

            String DISPLAY_XPATH = "//*[@id=\"result\"]";
            IWebElement res = driver.FindElement(By.XPath(DISPLAY_XPATH));
            String result = res.GetAttribute("value");
            Assert.AreEqual("34", result);

            driver.Quit();
        }

        [Test]
        public void test_div_6_2()
        {
            var URL = "file:///" + Environment.CurrentDirectory + "/CalculatorUI.html";
            ChromeOptions options = new ChromeOptions();
            options.AddArguments("remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(10);
            driver.Url = URL;

            String BUTTON_XPATH4 = "//*[@id=\'id" + "reset" + "\']";
            IWebElement keyElement4 = driver.FindElement(By.XPath(BUTTON_XPATH4));
            keyElement4.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH3 = "//*[@id=\'id" + "6" + "\']";
            IWebElement keyElement3 = driver.FindElement(By.XPath(BUTTON_XPATH3));
            keyElement3.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH2 = "//*[@id=\'id" + "div" + "\']";
            IWebElement keyElement2 = driver.FindElement(By.XPath(BUTTON_XPATH2));
            keyElement2.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH1 = "//*[@id=\'id" + "2" + "\']";
            IWebElement keyElement1 = driver.FindElement(By.XPath(BUTTON_XPATH1));
            keyElement1.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH = "//*[@id=\'id" + "eql" + "\']";
            IWebElement keyElement = driver.FindElement(By.XPath(BUTTON_XPATH));
            keyElement.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH5 = "//*[@id=\'id" + "Update" + "\']";
            IWebElement keyElement5 = driver.FindElement(By.XPath(BUTTON_XPATH5));
            keyElement5.Click();
            Thread.Sleep(500);

            String DISPLAY_XPATH = "//*[@id=\'result\']";
            IWebElement res = driver.FindElement(By.XPath(DISPLAY_XPATH));
            String result = res.GetAttribute("value");
            Assert.AreEqual("3", result);
            driver.Quit();
        }

        [Test]
        public void test_ui_123()
        {
            var URL = "file:///" + Environment.CurrentDirectory + "/CalculatorUI.html";
            ChromeOptions options = new ChromeOptions();
            options.AddArguments("remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(10);
            driver.Url = URL;

            String BUTTON_XPATH4 = "//*[@id=\'id" + "reset" + "\']";
            IWebElement keyElement4 = driver.FindElement(By.XPath(BUTTON_XPATH4));
            keyElement4.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH3 = "//*[@id=\'id" + "1" + "\']";
            IWebElement keyElement3 = driver.FindElement(By.XPath(BUTTON_XPATH3));
            keyElement3.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH2 = "//*[@id=\'id" + "2" + "\']";
            IWebElement keyElement2 = driver.FindElement(By.XPath(BUTTON_XPATH2));
            keyElement2.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH1 = "//*[@id=\'id" + "3" + "\']";
            IWebElement keyElement1 = driver.FindElement(By.XPath(BUTTON_XPATH1));
            keyElement1.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH5 = "//*[@id=\'id" + "Update" + "\']";
            IWebElement keyElement5 = driver.FindElement(By.XPath(BUTTON_XPATH5));
            keyElement5.Click();
            Thread.Sleep(5000);

            String DISPLAY_XPATH = "//*[@id=\'result\']";
            IWebElement res = driver.FindElement(By.XPath(DISPLAY_XPATH));
            String result = res.GetAttribute("value");
            Assert.AreEqual("123", result);
            driver.Quit();
        }

        [Test]
        public void test_5_plus_3()
        {
            var URL = "file:///" + Environment.CurrentDirectory + "/CalculatorUI.html";
            ChromeOptions options = new ChromeOptions();
            options.AddArguments("remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(10);
            driver.Url = URL;

            String BUTTON_XPATH4 = "//*[@id=\'id" + "reset" + "\']";
            IWebElement keyElement4 = driver.FindElement(By.XPath(BUTTON_XPATH4));
            keyElement4.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH3 = "//*[@id=\'id" + "5" + "\']";
            IWebElement keyElement3 = driver.FindElement(By.XPath(BUTTON_XPATH3));
            keyElement3.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH2 = "//*[@id=\'id" + "plus" + "\']";
            IWebElement keyElement2 = driver.FindElement(By.XPath(BUTTON_XPATH2));
            keyElement2.Click();
            Thread.Sleep(500);


            String BUTTON_XPATH1 = "//*[@id=\'id" + "3" + "\']";
            IWebElement keyElement1 = driver.FindElement(By.XPath(BUTTON_XPATH1));
            keyElement1.Click();
            Thread.Sleep(500);

            String BUTTON_XPATH5 = "//*[@id=\'id" + "Update" + "\']";
            IWebElement keyElement5 = driver.FindElement(By.XPath(BUTTON_XPATH5));
            keyElement5.Click();
            Thread.Sleep(5000);

            String DISPLAY_XPATH = "//*[@id=\'result\']";
            IWebElement res = driver.FindElement(By.XPath(DISPLAY_XPATH));
            String result = res.GetAttribute("value");
            Assert.AreEqual("3", result);
            driver.Quit();
        }
    }

}
