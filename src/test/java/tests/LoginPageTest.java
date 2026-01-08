package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.SecureAreaPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTest {

    private Playwright playwright;
    private Browser browser;
    private Page page;

    @BeforeEach
    void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        page = browser.newPage();
    }

    @AfterEach
    void tearDown() {
        if (page != null) {
            page.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    @Test
    @DisplayName("Verify successful login with valid credentials")
    void testSuccessfulLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(page);
        SecureAreaPage secureAreaPage = loginPage
                .navigate()
                .enterUsername("tomsmith")
                .enterPassword("SuperSecretPassword!")
                .clickLoginButtonAndNavigateToSecureArea();

        String successMessage = secureAreaPage.getSuccessMessageText();
        assertTrue(successMessage.contains("You logged into a secure area!"),
                "Success message should indicate successful login");
    }

    @Test
    @DisplayName("Verify error message is displayed for invalid credentials")
    void testErrorMessageDisplayedForInvalidCredentials() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.navigate()
                .enterUsername("invaliduser")
                .enterPassword("invalidpassword")
                .clickLoginButton();

        String errorMessage = loginPage.getErrorMessageText();
        assertTrue(errorMessage.contains("Your username is invalid!"),
                "Error message should indicate invalid credentials");
    }
}
