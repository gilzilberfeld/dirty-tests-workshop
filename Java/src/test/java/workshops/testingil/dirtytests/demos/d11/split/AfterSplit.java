package workshops.testingil.dirtytests.demos.d11.split;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
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
    private Locator firstTextBox;
    private Locator secondTextBox;
    private Locator theButton;

    @BeforeEach
    public void setup() {
        playwright = Playwright.create();
        Browser browser = playwright.
                chromium().launch();
        page = browser.newPage();
        firstTextBox = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First"));
        secondTextBox = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Second"));
        theButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Calculate"));
        page.navigate("http://localhost:3000/calculator");
    }

    @AfterEach
    public void cleanup() {
        playwright.close();
    }

    @Test
    public void calculate1And2() {
        firstTextBox.fill("1");
        secondTextBox.fill("2");
        theButton.click();
        assertThat(page.getByText("And the result is.... 3")).isVisible();
    }

    @Test
    public void calculate3And4() {
        firstTextBox.fill("3");
        secondTextBox.fill("4");
        theButton.click();
        assertThat(page.getByText("And the result is.... 7")).isVisible();
    }

    @Test
    public void navigationBackFromResultDisplaysTitle() {
        page.navigate("http://localhost:3000/result");
        page.goBack();
        page.getByText("Welcome to the Calculator!").waitFor();
    }
}
