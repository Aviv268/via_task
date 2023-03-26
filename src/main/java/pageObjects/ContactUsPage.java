package pageObjects;

import io.qameta.allure.Step;
import webDriver.ActionBot;

public class ContactUsPage extends AbstractPage{

    // Locators for page elements
    private static final String CONTACT_US_HEADER = "div[class='et_pb_text_inner'] h1";

    public ContactUsPage(ActionBot bot) {
        super(bot);
    }

    @Step("Gets Header Text")
    public String getHeaderTextContext(){
        return bot.getText(CONTACT_US_HEADER);
    }
}
