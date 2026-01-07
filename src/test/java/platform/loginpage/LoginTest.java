package platform.loginpage;

import app.getxray.xray.testng.annotations.XrayTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import platform.InitTest;


public class LoginTest extends InitTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData(){
        return new Object[][]{

                {"False_user@cx.com","#############",false},
                {"mohammed.gesreha@caresyntax.com","",false}
        };
    }

    @Test(description = "Test login functionality", dataProvider = "loginData")
    @XrayTest(key = "")
    public void test(String userNameData,String passwordData, boolean isValid){


    }


}