package webDriver;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

import static com.microsoft.playwright.options.WaitForSelectorState.VISIBLE;

public class ActionBot {

    private final Page page;

    public ActionBot(final Page page){
        this.page = page;
    }

    @Step("Mouse hover element")
    public void mouseHover(String element){
        page.hover(element);
    }

    @Step("Mouse click on element")
    public void click(String element){
        page.click(element);
    }

    @Step("Fill text in element")
    public void typeTo(String element,String text){
        page.fill(element,text);
    }

    @Step("Get text from element")
    public String getText(String element){
        return page.innerText(element);
    }

    @Step("Wait for load state")
    public void waitForLoadState() {
        page.waitForLoadState();
    }

    @Step("Wait for selector to be visible")
    public void waitForSelector(String selector) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions().setState(VISIBLE));
    }

    @Step("Get count number of elements")
    public int getNumberOfElements(String element){
        return page.locator(element).count();
    }

    @Step("Get page element")
    public Locator getPageElement(String element){
        return page.locator(element);
    }
}
