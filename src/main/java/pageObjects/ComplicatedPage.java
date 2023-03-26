package pageObjects;

import com.microsoft.playwright.Locator;
import io.qameta.allure.Step;
import webDriver.ActionBot;

public class ComplicatedPage extends AbstractPage{

    // Locators for page elements
    private static final String SECTION_OF_BUTTONS = ".et_pb_row_4col a.et_pb_button";
    private static final String SECTION_OF_SOCIAL_MEDIA_FOLLOWS_FACEBOOK = ".et_pb_social_media_follow li a[href*='facebook.com']";
    private static final String EXPECTED_FACEBOOK_HREF = "https://www.facebook.com/Ultimateqa1/";
    private static final String NAME_FIELD = "#et_pb_contact_name_0";
    private static final String EMAIL_FIELD = "#et_pb_contact_email_0";
    private static final String MESSAGE_FIELD = "#et_pb_contact_message_0";
    private static final String MATH_EXERCISE_FIELD = ".et_pb_contact_form_0 .et_pb_contact_captcha_question";
    private static final String MATH_EXERCISE_INPUT_FIELD = ".et_pb_contact_form_0 .et_pb_contact_captcha";
    private static final String SUBMIT_BUTTON = ".et_pb_contact_submit";
    private static final String SUBMIT_SUCCESS_MESSAGE = "#et_pb_contact_form_0 .et-pb-contact-message";



    public ComplicatedPage(ActionBot bot) {
        super(bot);
    }

    @Step("Wait for complicated page to load")
    public void waitForPageLoad() {
        bot.waitForLoadState();
        bot.waitForSelector(SECTION_OF_BUTTONS);
    }

    @Step("Get the number of elements")
    public int getButtonCount() {
        return bot.getNumberOfElements(SECTION_OF_BUTTONS);
    }

    public boolean isFacebookHrefEqual() {
        Locator sectionOfSocialMediaFollows = bot.getPageElement(SECTION_OF_SOCIAL_MEDIA_FOLLOWS_FACEBOOK);
        for (int i = 0; i < sectionOfSocialMediaFollows.count(); i++) {
            String actualHref = sectionOfSocialMediaFollows.nth(i).getAttribute("href");
            if (!actualHref.equals(EXPECTED_FACEBOOK_HREF)) {
                return false;
            }
        }
        return true;
    }

    public void fillContactForm(String name, String email, String message) {
        bot.typeTo(NAME_FIELD, name);
        bot.typeTo(EMAIL_FIELD, email);
        bot.typeTo(MESSAGE_FIELD, message);
        String mathExercise = bot.getText(MATH_EXERCISE_FIELD);
        String[] parts = mathExercise.split("\\+");
        if (parts.length >= 2) {
            int operand1 = Integer.parseInt(parts[0].trim());
            int operand2 = Integer.parseInt(parts[1].trim());
            int answer = operand1 + operand2;
            bot.typeTo(MATH_EXERCISE_INPUT_FIELD, String.valueOf(answer));
        } else {
            throw new IllegalStateException("Could not extract operands from math exercise");
        }
    }

    public void submitContactForm() {
        bot.click(SUBMIT_BUTTON);
    }

    public String getSuccessMessage() {
        bot.waitForSelector(SUBMIT_SUCCESS_MESSAGE);
        return bot.getText(SUBMIT_SUCCESS_MESSAGE);
    }

}
