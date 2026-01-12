package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SecureAreaPage {

    private final Page page;
    private final Locator successMessage;
    private final Locator logoutButton;
    private final Locator pageTitle;

    public SecureAreaPage(Page page) {
        this.page = page;
        this.successMessage = page.locator("#flash.success");
        this.logoutButton = page.locator("a.button[href='/logout']");
        this.pageTitle = page.locator("h2");
    }

    public String getSuccessMessageText() {
        return successMessage.textContent();
    }

    public String getPageTitleText() {
        return pageTitle.textContent();
    }

    public LoginPage clickLogoutButton() {
        logoutButton.click();
        return new LoginPage(page);
    }
}
