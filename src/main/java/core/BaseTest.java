package core;


import com.aventstack.extentreports.Status;
import helper.BaseTestHelper;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utlis.ExtentReport;


import java.io.IOException;


public class BaseTest {
    @BeforeSuite(alwaysRun = true)
    public void config() throws IOException {
        // Create the path for storing HTML reports
        String subfolderpath = System.getProperty("user.dir") + "/reports/" + BaseTestHelper.Timestamp();
        BaseTestHelper.CreateFolder(subfolderpath);


        // Initialize Extent Reports with report path
        ExtentReport.initialize(subfolderpath + "/API_Execution_Automation.html", null);
    }


    @BeforeMethod(alwaysRun = true)
    public void setupTest(ITestContext context) {
        // Create a new test entry for each test method
        ExtentReport.createTest(context.getName());
    }


    @AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result) {
        if (ExtentReport.getTest() == null) return;


        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                ExtentReport.logPass("Test Case: " + result.getName() + " passed.");
                break;


            case ITestResult.FAILURE:
                ExtentReport.logFail("Test Case: " + result.getName() + " failed.");
                ExtentReport.logFail("Failure Reason: " + result.getThrowable());
                break;


            case ITestResult.SKIP:
                ExtentReport.logInfo("Test Case: " + result.getName() + " was skipped.");
                break;
        }
    }


    @AfterSuite(alwaysRun = true)
    public void endReport() {
        ExtentReport.flush();
    }
}
