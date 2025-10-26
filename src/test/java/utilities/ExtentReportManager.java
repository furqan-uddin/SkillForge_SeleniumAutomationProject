package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getReportInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            String reportName = "SkillForge_TestReport_" + timestamp + ".html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + reportName);

            sparkReporter.config().setDocumentTitle("SkillForge Automation Report");
            sparkReporter.config().setReportName("SkillForge Functional Testing");
            sparkReporter.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Application", "SkillForge");
            extent.setSystemInfo("Browser", ConfigReader.get("browser"));
            extent.setSystemInfo("Tester", "QA Engineer");
        }
        return extent;
    }

    public static ExtentTest createTest(String testName) {
        test = getReportInstance().createTest(testName);
        return test;
    }
}
