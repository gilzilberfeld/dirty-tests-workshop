from playwright.sync_api import Page, expect


def test_guru99_playwright(page: Page):
    page.goto("https://demo.guru99.com/test/newtours/register.php")
    page.locator("input[name=\"firstName\"]").click()
    page.locator("input[name=\"firstName\"]").fill("Jim")
    page.locator("input[name=\"firstName\"]").press("Tab")
    page.locator("input[name=\"lastName\"]").fill("Smith")
    page.locator("input[name=\"lastName\"]").press("Tab")
    page.locator("input[name=\"phone\"]").press("Tab")
    page.locator("#userName").fill("jimsmith@google.com")
    page.locator("#userName").press("Tab")
    page.locator("input[name=\"address1\"]").click()
    page.locator("input[name=\"address1\"]").fill("19 Hope st.")
    page.locator("input[name=\"address1\"]").press("Tab")
    page.locator("input[name=\"city\"]").fill("New York City")
    page.locator("input[name=\"city\"]").press("Tab")
    page.locator("input[name=\"state\"]").fill("New York")
    page.locator("input[name=\"state\"]").press("Tab")
    page.locator("input[name=\"postalCode\"]").press("Tab")
    page.locator("select[name=\"country\"]").select_option("UNITED STATES")
    page.locator("#email").click()
    page.locator("#email").fill("jimsmith@google.com")
    page.locator("#email").press("Tab")
    page.locator("input[name=\"password\"]").fill("pwd")
    page.locator("input[name=\"password\"]").press("Tab")
    page.locator("input[name=\"confirmPassword\"]").fill("pwd")
    expect(page.get_by_role("button", name="Submit")).to_be_enabled()
    page.get_by_role("button", name="Submit").click()

    expect(page.locator("body")).to_contain_text("Dear Jim Smith,")
    expect(page.locator("body")).to_contain_text("Note: Your user name is jimsmith@google.com.")
    page.get_by_role("link", name="sign-in").click()

    page.locator("input[name=\"userName\"]").click()
    page.locator("input[name=\"userName\"]").fill("jimsmith@google.com")
    page.locator("input[name=\"userName\"]").press("Tab")
    page.locator("input[name=\"password\"]").fill("pwd")
    page.get_by_role("button", name="Submit").click()

    expect(page.get_by_role("cell", name="Login Successfully", exact=True)).to_be_visible()
    expect(page.locator("p").filter(has_text="Thank you for Loggin.")).to_be_visible()
    page.get_by_role("link", name="Flights").click()

    page.goto("https://demo.guru99.com/test/newtours/index.php")
    expect(page.locator("p:nth-child(4) > img")).to_be_visible()
    expect(page.get_by_text("Atlanta to Las Vegas")).to_be_visible()
    expect(page.get_by_text("Boston to San Francisco")).to_be_visible()
    expect(page.get_by_text("Phoenix to San Francisco")).to_be_visible()
    expect(page.get_by_text("New York to Chicago")).to_be_visible()
    expect(page.get_by_text("Los Angeles to Chicago")).to_be_visible()
    expect(page.locator("body")).to_contain_text("$398")
    expect(page.locator("body")).to_contain_text("$513")
    expect(page.locator("body")).to_contain_text("$168")
    expect(page.locator("body")).to_contain_text("$198")
    expect(page.locator("body")).to_contain_text("$213")

    page.goto("https://demo.guru99.com/test/newtours/index.php")
    expect(page.get_by_role("link",name="Cruises")).to_be_visible()
    expect(page.get_by_role("link",name="Vacations")).to_be_visible()
    expect(page.get_by_role("link",name="Hotels")).to_be_visible()
    expect(page.get_by_role("link",name="CONTACT")).to_be_visible()
    expect(page.get_by_role("link",name="SIGN-ON")).to_be_visible()
    expect(page.get_by_role("link",name="Salon Travel")).to_be_visible()

    page.goto("https://demo.guru99.com/test/newtours/reservation.php")
    page.get_by_role('radio').nth(1).check()
    page.locator("select[name=\"passCount\"]").select_option("3")
    page.locator("select[name=\"fromDay\"]").select_option("16")
    page.locator("select[name=\"fromMonth\"]").select_option("9")
    page.locator("select[name=\"toPort\"]").select_option("Paris")
    page.locator("select[name=\"toMonth\"]").select_option("9")
    page.locator("select[name=\"toDay\"]").select_option("17")
    page.get_by_text("Economy class Business class").click()
    page.get_by_role("radio").nth(3).check()
    page.get_by_role("button", name="Submit").click()

    expect(page.locator("body")).to_contain_text("After flight finder - No Seats Avaialble")
    page.goto("https://demo.guru99.com/test/newtours/index.php")
    expect(page.get_by_role("link", name="your destination")).to_be_visible()
    expect(page.get_by_role("link", name="featured vacation destinations")).to_be_visible()
    expect(page.locator("form[name=\"home\"]")).to_contain_text("Registered users can sign-in here to find the lowest fare on participating airlines.")
    expect(page.locator("body")).to_contain_text("Always carry a travel first aid kit with bandages, antacids, aspirin, bee sting wipes, and other basic necessities.")
