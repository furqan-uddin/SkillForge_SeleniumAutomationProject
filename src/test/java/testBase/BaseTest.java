package testBase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utilities.ConfigReader;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver;
    private static final Logger log = LogManager.getLogger(BaseTest.class);

    @BeforeClass
    public void setUp() {
        String browser = ConfigReader.get("browser");
        String url = ConfigReader.get("baseURL");

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            throw new RuntimeException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Long.parseLong(ConfigReader.get("implicitWait"))));
        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(Long.parseLong(ConfigReader.get("pageLoadTimeout"))));

        log.info("Opening SkillForge app: " + url);
        driver.get(url);
        ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            log.info("Closing browser...");
            driver.quit();
        }
    }
}
