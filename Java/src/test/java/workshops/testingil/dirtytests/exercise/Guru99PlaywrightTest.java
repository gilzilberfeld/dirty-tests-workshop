package workshops.testingil.dirtytests.exercise;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.*;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Guru99PlaywrightTest {

    @Test
    public void registerAndCheckFlights() {

        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.navigate("https://demo.guru99.com/test/newtours/register.php");
            page.locator("input[name=\"firstName\"]").click();
            page.locator("input[name=\"firstName\"]").fill("Jim");
            page.locator("input[name=\"firstName\"]").press("Tab");
            page.locator("input[name=\"lastName\"]").fill("Smith");
            page.locator("input[name=\"lastName\"]").press("Tab");
            page.locator("input[name=\"phone\"]").press("Tab");
            page.locator("#userName").fill("jimsmith@google.com");
            page.locator("#userName").press("Tab");
            page.locator("input[name=\"address1\"]").click();
            page.locator("input[name=\"address1\"]").fill("19 Hope st.");
            page.locator("input[name=\"address1\"]").press("Tab");
            page.locator("input[name=\"city\"]").fill("New York City");
            page.locator("input[name=\"city\"]").press("Tab");
            page.locator("input[name=\"state\"]").fill("New York");
            page.locator("input[name=\"state\"]").press("Tab");
            page.locator("input[name=\"postalCode\"]").press("Tab");
            page.getByRole(AriaRole.COMBOBOX).selectOption("UNITED STATES");
            page.locator("#email").click();
            page.locator("#email").fill("jimsmith@google.com");
            page.locator("#email").press("Tab");
            page.locator("input[name=\"password\"]").fill("pwd");
            page.locator("input[name=\"password\"]").press("Tab");
            page.locator("input[name=\"confirmPassword\"]").fill("pwd");
            assertThat((page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")))).isEnabled();;
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();

            assertThat(page.locator("body")).containsText("Dear Jim Smith,");
            assertThat(page.locator("body")).containsText("Note: Your user name is jimsmith@google.com.");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("sign-in")).click();

            page.locator("input[name=\"userName\"]").click();
            page.locator("input[name=\"userName\"]").fill("jimsmith@google.com");
            page.locator("input[name=\"userName\"]").press("Tab");
            page.locator("input[name=\"password\"]").fill("pwd");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();

            assertThat(page.getByRole(AriaRole.CELL, new Page.GetByRoleOptions().setName("Login Successfully").setExact(true))).isVisible();
            assertThat(page.locator("p").filter(new Locator.FilterOptions().setHasText("Thank you for Loggin."))).isVisible();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Flights")).click();

            page.navigate("https://demo.guru99.com/test/newtours/index.php");
            assertThat(page.locator("p:nth-child(4) > img")).isVisible();
            assertThat(page.getByText("Atlanta to Las Vegas")).isVisible();
            assertThat(page.getByText("Boston to San Francisco")).isVisible();
            assertThat(page.getByText("Phoenix to San Francisco")).isVisible();
            assertThat(page.getByText("New York to Chicago")).isVisible();
            assertThat(page.getByText("Los Angeles to Chicago")).isVisible();
            assertThat(page.locator("body")).containsText("$398");
            assertThat(page.locator("body")).containsText("$513");
            assertThat(page.locator("body")).containsText("$168");
            assertThat(page.locator("body")).containsText("$198");
            assertThat(page.locator("body")).containsText("$213");

            assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Cruises"))).isVisible();
            assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Vacations"))).isVisible();
            assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Hotels"))).isVisible();
            assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("CONTACT"))).isVisible();
            assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("SIGN-ON"))).isVisible();
            assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Salon Travel"))).isVisible();

            page.navigate("https://demo.guru99.com/test/newtours/reservation.php");
            page.getByRole(AriaRole.RADIO).nth(1).check();
            page.locator("select[name=\"passCount\"]").selectOption("3");
            page.locator("select[name=\"fromDay\"]").selectOption("16");
            page.locator("select[name=\"fromMonth\"]").selectOption("9");
            page.locator("select[name=\"toPort\"]").selectOption("Paris");
            page.locator("select[name=\"toMonth\"]").selectOption("9");
            page.locator("select[name=\"toDay\"]").selectOption("17");
            page.getByText("Economy class Business class").click();
            page.getByRole(AriaRole.RADIO).nth(3).check();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();

            assertThat(page.locator("body")).containsText("After flight finder - No Seats Avaialble");
            page.navigate("https://demo.guru99.com/test/newtours/index.php");
            assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("your destination"))).isVisible();
            assertThat(page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("featured vacation destinations"))).isVisible();
            assertThat(page.locator("form[name=\"home\"]")).containsText("Registered users can sign-in here to find the lowest fare on participating airlines.");
            assertThat(page.locator("body")).containsText("Always carry a travel first aid kit with bandages, antacids, aspirin, bee sting wipes, and other basic necessities.");
        }
    }
}
