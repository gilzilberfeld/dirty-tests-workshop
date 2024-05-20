package workshops.testingil.dirtytests.demos.dxx.split;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class AfterSplit {

    private Playwright playwright;
    private Page page;

    @BeforeEach
    public void setup() {
        playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        page = browser.newPage();
    }

    @AfterEach
    public void cleanup() {
        playwright.close();
    }

    @Test
    public void calculate1And2() {
        page.navigate("/calculator");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First")).fill("1");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Second")).fill("2");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Calculate")).click();
        assertThat(page.getByText("And the result is.... 3")).isVisible();
    }

    @Test
    public void calculate3And4() {
        page.navigate("/calculator");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First")).fill("3");
        page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Second")).fill("4");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Calculate")).click();
        assertThat(page.getByText("And the result is.... 7")).isVisible();
    }

    @Test
    public void navigationBackFromResultDisplaysTitle() {
        page.navigate("/result");
        page.goBack();
        page.getByText("Welcome to the Calculator!").waitFor();
    }
}
