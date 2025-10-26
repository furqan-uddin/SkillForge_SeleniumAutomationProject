package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RegisterPage;
import testBase.BaseTest;
import java.util.Random;

public class RegisterTest extends BaseTest {

    @Test(description = "Verify SkillForge registration flow")
    public void verifyUserRegistration() {
        RegisterPage reg = new RegisterPage(driver);
        reg.clickRegisterFromNavbar();

        String randomEmail = "user" + new Random().nextInt(99999) + "@gmail.com";
        reg.fillForm("TestUser", randomEmail, "SkillForge@123");
        reg.submitForm();

        Assert.assertTrue(reg.isRegistrationSuccessful(), "❌ Registration failed or redirect missing");
    }
}
