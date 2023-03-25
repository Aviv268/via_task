package tests.viaTest;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.ComplicatedPage;

public class ComplicatedPageTest {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private ComplicatedPage complicatedPage;

    private static final String PAGE_URL = "https://ultimateqa.com/complicated-page";
    private static final int EXPECTED_BUTTON_COUNT = 12;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        complicatedPage = new ComplicatedPage(page);
        page.navigate(PAGE_URL);
    }

    @Test(priority = 1)
    public void testButtonCount() {
        complicatedPage.waitForPageLoad();
        int actualButtonCount = complicatedPage.getButtonCount();
        Assert.assertEquals(actualButtonCount, EXPECTED_BUTTON_COUNT, "Button count doesn't match expected count");
    }

    @Test(priority = 2)
    public void testFacebookButtonsHref() {
        complicatedPage.waitForPageLoad();
        boolean isFacebookHrefEqual = complicatedPage.isFacebookHrefEqual();
        Assert.assertTrue(isFacebookHrefEqual, "Facebook href doesn't match expected href");
    }

    @Test(priority = 3)
    public void testContactFormSubmission() {
        complicatedPage.waitForPageLoad();
        complicatedPage.fillContactForm("Test User", "test@example.com", "Test message");
        complicatedPage.submitContactForm();
        String successMessage = complicatedPage.getSuccessMessage();
        Assert.assertEquals(successMessage, "Thanks for contacting us");
    }

    @AfterClass
    public void tearDown() {
        playwright.close();
    }

}
