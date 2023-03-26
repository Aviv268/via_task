package tests.viaTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ComplicatedPage;
import pageObjects.ContactUsPage;
import pageObjects.HeaderMenuPage;

public class ComplicatedPageTest extends AbstractTest {

    private static final int EXPECTED_BUTTON_COUNT = 12;
    private static final String EXPECTED_CONTACTUS_HEADER = "Contact Us1";

    @Test(priority = 1)
    public void testButtonCount() {
        ComplicatedPage complicatedPage = new ComplicatedPage(bot);
        complicatedPage.waitForPageLoad();
        int actualButtonCount = complicatedPage.getButtonCount();
        Assert.assertEquals(actualButtonCount, EXPECTED_BUTTON_COUNT, "Button count doesn't match expected count");
    }

    @Test(priority = 2)
    public void testFacebookButtonsHref() {
        ComplicatedPage complicatedPage = new ComplicatedPage(bot);
        complicatedPage.waitForPageLoad();
        boolean isFacebookHrefEqual = complicatedPage.isFacebookHrefEqual();
        Assert.assertTrue(isFacebookHrefEqual, "Facebook href doesn't match expected href");
    }

    @Test(priority = 3)
    public void testContactFormSubmission() {
        ComplicatedPage complicatedPage = new ComplicatedPage(bot);
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

}
