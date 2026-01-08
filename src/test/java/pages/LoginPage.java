package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {

    private final Page page;
    private final Locator usernameInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator errorMessage;
    private final Locator successMessage;

    private static final String URL = "https://the-internet.herokuapp.com/login";

    public LoginPage(Page page) {
        this.page = page;
        this.usernameInput = page.locator("#username");
        this.passwordInput = page.locator("#password");
        this.loginButton = page.locator("button[type='submit']");
        this.errorMessage = page.locator("#flash.error");
        this.successMessage = page.locator("#flash.success");
    }

    public LoginPage navigate() {
        page.navigate(URL);
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

    public SecureAreaPage clickLoginButtonAndNavigateToSecureArea() {
        loginButton.click();
        return new SecureAreaPage(page);
    }

    public String getErrorMessageText() {
        return errorMessage.textContent();
    }
}
