package tests.viaTest;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pageObjects.ComplicatedPage;
import pageObjects.HeaderMenuPage;
import webDriver.ActionBot;

public class AbstractTest {

    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;
    private ComplicatedPage complicatedPage;
    private HeaderMenuPage headerMenuPage;

    protected ActionBot bot;
    private static final String PAGE_URL = "https://ultimateqa.com/complicated-page";

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setSlowMo(400).setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        bot = new ActionBot(page); // Initialize the ActionBot instance
        complicatedPage = new ComplicatedPage(bot);
        page.navigate(PAGE_URL);
    }

    @AfterClass
    public void tearDown() {
        playwright.close();
    }
}
