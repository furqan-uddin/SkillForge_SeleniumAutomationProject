package pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import utilities.WaitHelper;

public class LoginPage {
    private final WebDriver driver;
    private final WaitHelper wait;
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    // locators
    private final By navLogin = By.xpath("//a[normalize-space()='Login']");
    private final By email = By.cssSelector("input[type='email']");
    private final By password = By.cssSelector("input[type='password']");
    private final By submit = By.xpath("//button[normalize-space()='Login']");
    private final By dashboard = By.xpath("//*[contains(text(),'Dashboard')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelper(driver, 15);
    }

    public void clickLoginFromNavbar() {
        wait.waitForClickable(navLogin).click();
        log.info("Clicked Login from navbar");
    }

    public void performLogin(String user, String pass) {
        wait.waitForVisible(email).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(submit).click();
        log.info("Login form submitted with user: " + user);
    }

    public boolean isLoginSuccessful() {
        try {
            wait.waitForVisible(dashboard);
            log.info("Dashboard detected - login successful");
            return true;
        } catch (TimeoutException e) {
            log.error("Dashboard not found after login");
            return false;
        }
    }
}
