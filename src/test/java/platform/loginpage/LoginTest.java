package platform.loginpage;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;
import platform.InitTest;
import platform.pages.LoginPage;

@Feature("Login")
public class LoginTest extends InitTest {

    private static final String VALID_USERNAME = "tomsmith";
    private static final String VALID_PASSWORD = "SuperSecretPassword!";
    private static final String INVALID_USERNAME = "invalidUser";
    private static final String INVALID_PASSWORD = "invalidPassword";

    @Test
    @Story("User Login")
    @Description("Verify that a user can successfully log in with valid credentials")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(getPage());
        loginPage.navigate()
                .login(VALID_USERNAME, VALID_PASSWORD);

        Assert.assertTrue(loginPage.getSuccessMessage().isVisible(),
                "Success message should be displayed after successful login");
        Assert.assertTrue(loginPage.getSuccessMessage().textContent().contains("You logged into a secure area!"),
                "Success message should contain expected text");
    }

    @Test
    @Story("User Login")
    @Description("Verify that an error message is displayed when logging in with invalid credentials")
    public void testLoginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(getPage());
        loginPage.navigate()
                .login(INVALID_USERNAME, INVALID_PASSWORD);

        Assert.assertTrue(loginPage.getErrorMessage().isVisible(),
                "Error message should be displayed for invalid credentials");
        Assert.assertTrue(loginPage.getErrorMessage().textContent().contains("Your username is invalid!"),
                "Error message should indicate invalid username");
    }

    @Test
    @Story("User Login")
    @Description("Verify that an error message is displayed when logging in with invalid password")
    public void testLoginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(getPage());
        loginPage.navigate()
                .login(VALID_USERNAME, INVALID_PASSWORD);

        Assert.assertTrue(loginPage.getErrorMessage().isVisible(),
                "Error message should be displayed for invalid password");
        Assert.assertTrue(loginPage.getErrorMessage().textContent().contains("Your password is invalid!"),
                "Error message should indicate invalid password");
    }
}
