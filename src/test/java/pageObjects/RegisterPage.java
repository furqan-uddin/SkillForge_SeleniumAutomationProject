package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import utilities.WaitHelper;

public class RegisterPage {
    private final WebDriver driver;
    private final WaitHelper wait;
    private static final Logger log = LogManager.getLogger(RegisterPage.class);

    private final By navRegister = By.xpath("//a[normalize-space()='Register']");
    private final By name = By.cssSelector("input[name='name']");
    private final By email = By.cssSelector("input[name='email']");
    private final By password = By.cssSelector("input[name='password']");
    private final By submit = By.xpath("//button[@type='submit']//span[normalize-space()='Register']");
    private final By loginRedirect = By.xpath("//*[contains(text(),'Login here')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver, 15);
    }

    public void clickRegisterFromNavbar() {
        wait.waitForClickable(navRegister).click();
        log.info("Clicked Register from navbar");
    }

    public void fillForm(String userName, String userEmail, String userPass) {
        wait.waitForVisible(name).sendKeys(userName);
        driver.findElement(email).sendKeys(userEmail);
        driver.findElement(password).sendKeys(userPass);
        log.info("Filled registration form for: " + userEmail);
    }

    public void submitForm() {
        wait.waitForClickable(submit).click();
        log.info("Clicked Register submit");
    }

    public boolean isRegistrationSuccessful() {
        try {
            wait.waitForVisible(loginRedirect);
            log.info("Registration redirect detected");
            return true;
        } catch (TimeoutException e) {
            log.error("No redirect detected after registration");
            return false;
        }
    }
}
