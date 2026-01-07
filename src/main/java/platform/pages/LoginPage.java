package platform.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.Step;

public class LoginPage {
    private final Page page;
    private Locator userNameLocator;
    private Locator passwordLocator;
    private Locator loginButton;

    public LoginPage(Page page) {
        this.page = page;

    }

    @Step
    public LoginPage navigate(){
        return this;
    }


    @Step("Is log in page open?")
    public boolean isOpen(){
        loginButton.focus();
        return loginButton.isVisible();
    }
}