package platform.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {

    private final Page page;
    private final String baseUrl;

    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator errorMessage;
    private final Locator successMessage;

    public LoginPage(Page page) {
        this.page = page;
        this.baseUrl = "https://the-internet.herokuapp.com";

        this.usernameInput = page.locator("#username");
        this.passwordInput = page.locator("#password");
        this.loginButton = page.locator("button[type='submit']");
        this.errorMessage = page.locator("#flash.error");
        this.successMessage = page.locator("#flash.success");
    }

    public LoginPage navigate() {
        page.navigate(baseUrl + "/login");
        return this;
    }

    public LoginPage enterUsername(String username) {
        usernameInput.fill(username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        passwordInput.fill(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    public LoginPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return this;
    }

    public Locator getErrorMessage() {
        return errorMessage;
    }

    public Locator getSuccessMessage() {
        return successMessage;
    }

    public Page getPage() {
        return page;
    }
}
