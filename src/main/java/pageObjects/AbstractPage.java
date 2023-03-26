package pageObjects;

import webDriver.ActionBot;

public class AbstractPage {

    protected ActionBot bot;

    public AbstractPage(ActionBot bot) {
        this.bot = bot;
    }
}
