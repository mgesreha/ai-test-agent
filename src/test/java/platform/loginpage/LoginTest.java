package platform.loginpage;

import com.microsoft.playwright.Page;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import platform.InitTest;
import platform.pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Feature("Login")
@Story("User Login")
public class LoginTest extends InitTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        Page page = getPage();
        loginPage = new LoginPage(page);
    }

    @Test
    @Description("Verify successful login with valid credentials")
    public void testSuccessfulLoginWithValidCredentials() {
        loginPage.navigate()
                .login("tomsmith", "SuperSecretPassword!");

        assertThat(loginPage.getSuccessMessage()).isVisible();
        assertThat(loginPage.getSuccessMessage()).containsText("You logged into a secure area!");
    }

    @Test
    @Description("Verify error message is displayed with invalid username")
    public void testLoginWithInvalidUsername() {
        loginPage.navigate()
                .login("invaliduser", "SuperSecretPassword!");

        assertThat(loginPage.getErrorMessage()).isVisible();
        assertThat(loginPage.getErrorMessage()).containsText("Your username is invalid!");
    }

    @Test
    @Description("Verify error message is displayed with invalid password")
    public void testLoginWithInvalidPassword() {
        loginPage.navigate()
                .login("tomsmith", "wrongpassword");

        assertThat(loginPage.getErrorMessage()).isVisible();
        assertThat(loginPage.getErrorMessage()).containsText("Your password is invalid!");
    }
}
