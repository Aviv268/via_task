package tests.viaTest;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.ComplicatedPage;
import pageObjects.ContactUsPage;
import pageObjects.HeaderMenuPage;
import webDriver.ActionBot;

public class ComplicatedPageTest extends AbstractTest {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private ComplicatedPage complicatedPage;
    private HeaderMenuPage headerMenuPage;

    private static final String PAGE_URL = "https://ultimateqa.com/complicated-page";
    private static final int EXPECTED_BUTTON_COUNT = 12;
    private static final String EXPECTED_CONTACTUS_HEADER = "Contact Us";

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        bot = new ActionBot(page); // Initialize the ActionBot instance
        complicatedPage = new ComplicatedPage(bot);
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

    @Test(priority = 4)
    public void verifyContactUsHeader() {
        HeaderMenuPage headerPage = new HeaderMenuPage(bot);
        ContactUsPage contactUsPage = headerPage.clickOnContactUsBtn();
        String contactUsHeader = contactUsPage.getHeaderTextContext();
        Assert.assertEquals(contactUsHeader, EXPECTED_CONTACTUS_HEADER);
    }

    @AfterClass
    public void tearDown() {
        playwright.close();
    }

}
