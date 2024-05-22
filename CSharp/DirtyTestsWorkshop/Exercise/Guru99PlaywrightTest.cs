using NUnit.Framework;
using System.Text.RegularExpressions;
using Microsoft.Playwright;
using Microsoft.Playwright.NUnit;

namespace DirtyTestsWorkshop.Exercise
{
    [TestFixture]
    internal class Guru99PlaywrightTest : PageTest
    {
        [Test]
        public async Task registerAndCheckFlights()
        {
            await Page.GotoAsync("https://demo.guru99.com/test/newtours/register.php");
            await Page.Locator("input[name=\"firstName\"]").ClickAsync();
            await Page.Locator("input[name=\"firstName\"]").FillAsync("Jim");
            await Page.Locator("input[name=\"firstName\"]").PressAsync("Tab");
            await Page.Locator("input[name=\"lastName\"]").FillAsync("Smith");
            await Page.Locator("input[name=\"lastName\"]").PressAsync("Tab");
            await Page.Locator("input[name=\"phone\"]").PressAsync("Tab");
            await Page.Locator("#userName").FillAsync("jimsmith@google.com");
            await Page.Locator("#userName").PressAsync("Tab");
            await Page.Locator("input[name=\"address1\"]").ClickAsync();
            await Page.Locator("input[name=\"address1\"]").FillAsync("19 Hope st.");
            await Page.Locator("input[name=\"address1\"]").PressAsync("Tab");
            await Page.Locator("input[name=\"city\"]").FillAsync("New York City");
            await Page.Locator("input[name=\"city\"]").PressAsync("Tab");
            await Page.Locator("input[name=\"state\"]").FillAsync("New York");
            await Page.Locator("input[name=\"state\"]").PressAsync("Tab");
            await Page.Locator("input[name=\"postalCode\"]").PressAsync("Tab");
            await Page.GetByRole(AriaRole.Combobox).SelectOptionAsync("UNITED STATES");
            await Page.Locator("#email").ClickAsync();
            await Page.Locator("#email").FillAsync("jimsmith@google.com");
            await Page.Locator("#email").PressAsync("Tab");
            await Page.Locator("input[name=\"password\"]").FillAsync("pwd");
            await Page.Locator("input[name=\"password\"]").PressAsync("Tab");
            await Page.Locator("input[name=\"confirmPassword\"]").FillAsync("pwd");
            await Page.GetByRole(AriaRole.Button, new() { Name = "Submit" }).ClickAsync();

            await Expect(Page.Locator("body")).ToContainTextAsync("Dear Jim Smith,");
            await Expect(Page.Locator("body")).ToContainTextAsync("Note: Your user name is jimsmith@google.com.");
            await Page.GetByRole(AriaRole.Link, new() { Name = "sign-in" }).ClickAsync();

            await Page.Locator("input[name=\"userName\"]").ClickAsync();
            await Page.Locator("input[name=\"userName\"]").FillAsync("jimsmith@google.com");
            await Page.Locator("input[name=\"userName\"]").PressAsync("Tab");
            await Page.Locator("input[name=\"password\"]").FillAsync("pwd");
            await Page.GetByRole(AriaRole.Button, new() { Name = "Submit" }).ClickAsync();

            await Expect(Page.GetByRole(AriaRole.Cell, new() { Name = "Login Successfully" }).Nth(1)).ToBeVisibleAsync();
            await Expect(Page.Locator("p").Filter(new() { HasText = "Thank you for Loggin." })).ToBeVisibleAsync();
            await Page.GetByRole(AriaRole.Link, new() { Name = "Flights" }).ClickAsync();

            await Page.GotoAsync("https://demo.guru99.com/test/newtours/index.php");
            await Expect(Page.Locator("p:nth-child(4) > img")).ToBeVisibleAsync();
            await Expect(Page.GetByText("Atlanta to Las Vegas")).ToBeVisibleAsync();
            await Expect(Page.GetByText("Boston to San Francisco")).ToBeVisibleAsync();
            await Expect(Page.GetByText("Phoenix to San Francisco")).ToBeVisibleAsync();
            await Expect(Page.GetByText("New York to Chicago")).ToBeVisibleAsync();
            await Expect(Page.GetByText("Los Angeles to Chicago")).ToBeVisibleAsync();
            await Expect(Page.Locator("body")).ToContainTextAsync("$398");
            await Expect(Page.Locator("body")).ToContainTextAsync("$513");
            await Expect(Page.Locator("body")).ToContainTextAsync("$168");
            await Expect(Page.Locator("body")).ToContainTextAsync("$198");
            await Expect(Page.Locator("body")).ToContainTextAsync("$213");

            await Expect(Page.GetByRole(AriaRole.Link, new() { Name = "Cruises" })).ToBeVisibleAsync();
            await Expect(Page.GetByRole(AriaRole.Link, new() { Name = "Vacations" })).ToBeVisibleAsync();
            await Expect(Page.GetByRole(AriaRole.Link, new() { Name = "Hotels" })).ToBeVisibleAsync();
            await Expect(Page.GetByRole(AriaRole.Link, new() { Name = "CONTACT" })).ToBeVisibleAsync();
            await Expect(Page.GetByRole(AriaRole.Link, new() { Name = "SIGN-ON" })).ToBeVisibleAsync();
            await Expect(Page.GetByRole(AriaRole.Link, new() { Name = "Salon Travel" })).ToBeVisibleAsync();

            await Page.GotoAsync("https://demo.guru99.com/test/newtours/reservation.php");
            await Page.GetByRole(AriaRole.Radio).Nth(1).CheckAsync();
            await Page.Locator("select[name=\"passCount\"]").SelectOptionAsync("3");
            await Page.Locator("select[name=\"fromDay\"]").SelectOptionAsync("16");
            await Page.Locator("select[name=\"fromMonth\"]").SelectOptionAsync("9");
            await Page.Locator("select[name=\"toPort\"]").SelectOptionAsync("Paris");
            await Page.Locator("select[name=\"toMonth\"]").SelectOptionAsync("9");
            await Page.Locator("select[name=\"toDay\"]").SelectOptionAsync("17");
            await Page.GetByText("Economy class Business class").ClickAsync();
            await Page.GetByRole(AriaRole.Radio).Nth(3).CheckAsync();
            await Page.GetByRole(AriaRole.Button, new() { Name = "Submit" }).ClickAsync();

            await Expect(Page.Locator("body")).ToContainTextAsync("After flight finder - No Seats Avaialble");
            await Page.GotoAsync("https://demo.guru99.com/test/newtours/index.php");
            await Expect(Page.GetByRole(AriaRole.Link, new() { Name = "your destination" })).ToBeVisibleAsync();
            await Expect(Page.GetByRole(AriaRole.Link, new() { Name = "featured vacation destinations" })).ToBeVisibleAsync();
            await Expect(Page.Locator("form[name=\"home\"]")).ToContainTextAsync("Registered users can sign-in here");
            await Expect(Page.Locator("body")).ToContainTextAsync("Always carry a travel first aid kit");

        }
    }
}
