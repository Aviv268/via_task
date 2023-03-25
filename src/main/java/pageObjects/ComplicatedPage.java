package pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;

public class ComplicatedPage {

    private Page page;
    private final String SECTION_OF_BUTTONS = ".et_pb_row_4col a.et_pb_button";
    private final String SECTION_OF_SOCIAL_MEDIA_FOLLOWS_FACEBOOK = ".et_pb_social_media_follow li a[href*='facebook.com']";
    private final String expectedFacebookHref = "https://www.facebook.com/Ultimateqa1/";
    private final String SECTION_OF_RANDOM_STUFF = ".et_pb_contact_form_0";
    private final String NAME_FIELD = "#et_pb_contact_name_0";
    private final String EMAIL_FIELD = "#et_pb_contact_email_0";
    private final String MESSAGE_FIELD = "#et_pb_contact_message_0";
    private final String MATH_EXERCISE_FIELD = ".et_pb_contact_form_0 .et_pb_contact_captcha_question";
    private final String MATH_EXERCISE_INPUT_FIELD = ".et_pb_contact_form_0 .et_pb_contact_captcha";
    private final String SUBMIT_BUTTON = ".et_pb_contact_submit";

    String captha = ".et_pb_contact_form_0 .et_pb_contact_captcha_question";


    public ComplicatedPage(Page page) {
        this.page = page;
    }

    public void waitForPageLoad() {
        page.waitForLoadState();
        page.waitForSelector(SECTION_OF_BUTTONS, new Page.WaitForSelectorOptions().setState(VISIBLE));
    }

    public void waitForRandomStuffPageLoad() {
        page.waitForLoadState();
        page.waitForSelector(SECTION_OF_RANDOM_STUFF, new Page.WaitForSelectorOptions().setState(VISIBLE));
    }

    public int getButtonCount() {
        Locator sectionOfButtons = page.locator(SECTION_OF_BUTTONS);
        return sectionOfButtons.count();
    }

    //.et_pb_social_media_follow li a[href*='facebook.com']
    public Locator getFacebookButtons() {
        Locator sectionOfSocialMediaFollows = page.locator(SECTION_OF_SOCIAL_MEDIA_FOLLOWS_FACEBOOK);
        return sectionOfSocialMediaFollows;
    }

    public boolean isFacebookHrefEqual() {
        Locator sectionOfSocialMediaFollows = page.locator(SECTION_OF_SOCIAL_MEDIA_FOLLOWS_FACEBOOK);
        for (int i = 0; i < sectionOfSocialMediaFollows.count(); i++) {
            String actualHref = sectionOfSocialMediaFollows.nth(i).getAttribute("href");
            if (!actualHref.equals(expectedFacebookHref)) {
                return false;
            }
        }
        return true;
    }

    public void fillContactForm(String name, String email, String message) {
        page.fill(NAME_FIELD, name);
        page.fill(EMAIL_FIELD, email);
        page.fill(MESSAGE_FIELD, message);
        String mathExercise = page.innerText(MATH_EXERCISE_FIELD);
        String[] parts = mathExercise.split("\\+");
        if (parts.length >= 2) {
            int operand1 = Integer.parseInt(parts[0].trim());
            int operand2 = Integer.parseInt(parts[1].trim());
            int answer = operand1 + operand2;
            page.fill(MATH_EXERCISE_INPUT_FIELD, String.valueOf(answer));
        } else {
            throw new IllegalStateException("Could not extract operands from math exercise");
        }
    }

    public void submitContactForm() {
        page.click(SUBMIT_BUTTON);
    }

    public String getSuccessMessage() {
        return page.innerText("#et_pb_contact_form_0");
    }

}
