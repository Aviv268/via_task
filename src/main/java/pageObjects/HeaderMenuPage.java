package pageObjects;

import io.qameta.allure.Step;
import webDriver.ActionBot;

public class HeaderMenuPage extends AbstractPage{

    // Locators for page elements
    private static final String ABOUT_BTN = "ul[id='menu-home-page-menu'] a[href='https://ultimateqa.com/about/']";
    private static final String CONTACT_US = "ul[id='menu-home-page-menu'] a[href='https://ultimateqa.com/contact/']";


    public HeaderMenuPage(ActionBot bot) {
        super(bot);
        this.bot = bot;
    }

    @Step("Clicking on Contact Us Button")
    public ContactUsPage clickOnContactUsBtn(){
        bot.mouseHover(ABOUT_BTN);
        bot.click(CONTACT_US);
        return new ContactUsPage(bot);
    }


}
