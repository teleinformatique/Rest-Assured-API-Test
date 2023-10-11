package api.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter extentSparkReporter;
    public ExtentReports extentReports;
    public ExtentTest extentTest;

    public String repName;

    public void onStart(ITestContext iTestContext){
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Tests-Report-" + timeStamp + ".html";

        extentSparkReporter = new ExtentSparkReporter("./reports/" + repName);
        extentSparkReporter.config().setDocumentTitle("RestAssuedAutomationProject");
        extentSparkReporter.config().setReportName("Pet Store Users Api");
        extentSparkReporter.config().setTheme(Theme.DARK);

        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Application", "Pest Store Users API");
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("User name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("User", "Khadim");
    }


    public void onTestSuccess(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.createNode(result.getName());
        extentTest.log(Status.PASS, "Test Passed");
    }


    public void onTestFailure(ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.createNode(result.getName());
        extentTest.log(Status.FAIL, "Test Failed");
        extentTest.log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onTestSkipped (ITestResult result) {
        extentTest = extentReports.createTest(result.getName());
        extentTest.assignCategory(result.getMethod().getGroups());
        extentTest.createNode(result.getName());
        extentTest.log(Status.SKIP, "Test Skiped");
        extentTest.log(Status.SKIP, result.getThrowable().getMessage());
    }


    public void onFinish(ITestContext context) {
        extentReports.flush();
    }
}