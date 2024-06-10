package workshops.testingil.dirtytests.demos.d11.split;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class BeforeSplit {


    @Test
    public void CalculatorTest() {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();

        // Need to rub the Clean Test server
        page.navigate("http://localhost:3000/calculator");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First")).fill("1");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Second")).fill("2");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Calculate")).click();
        assertThat(page.getByText("And the result is.... 3")).isVisible();
        page.goBack();
        page.getByText("Welcome to the Calculator!").waitFor();
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First")).fill("3");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Second")).fill("4");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Calculate")).click();
        assertThat(page.getByText("And the result is.... 7")).isVisible();

        playwright.close();
    }

}
