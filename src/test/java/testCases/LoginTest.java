package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import testBase.BaseTest;
import utilities.ConfigReader;

public class LoginTest extends BaseTest {

    @Test(description = "Verify SkillForge login using valid credentials")
    public void verifyLogin() {
        LoginPage page = new LoginPage(driver);

        page.clickLoginFromNavbar();
        page.performLogin(ConfigReader.get("email"), ConfigReader.get("password"));

        Assert.assertTrue(page.isLoginSuccessful(), "Login failed - Dashboard not visible");
    }
}
