package utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReport {
    public static ExtentReports extentreport = null;
    public static ThreadLocal<ExtentTest> extentlog = new ThreadLocal<>();


    // Initialize Extent Reports
    public static void initialize(String reportPath, String extentConfigXmlPath) {
        if (extentreport == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            extentreport = new ExtentReports();
            extentreport.attachReporter(sparkReporter);


            extentreport.setSystemInfo("Host Name", System.getProperty("user.name"));
            extentreport.setSystemInfo("Environment", "QA");
        }
    }


    // Create a test in the report
    public static void createTest(String testName) {
        ExtentTest test = extentreport.createTest(testName);
        extentlog.set(test);
<<<<<<< HEAD
        test.log(Status.PASS,"TestPassed is"+testName);
        
=======
>>>>>>> e9a8626 (NewBranchTest)
    }


    // Get the current test instance
    public static ExtentTest getTest() {
        return extentlog.get();
    }


    // Log INFO messages
    public static void logInfo(String message) {
        if (extentlog.get() != null) {
            extentlog.get().log(Status.INFO, message);
        }
    }


    // Log PASS messages
    public static void logPass(String message) {
        if (extentlog.get() != null) {
            extentlog.get().log(Status.PASS, message);
        }
    }


    // Log FAIL messages
    public static void logFail(String message) {
        if (extentlog.get() != null) {
            extentlog.get().log(Status.FAIL, message);
        }
    }


    // Flush reports at the end of execution
    public static void flush() {
        if (extentreport != null) {
            extentreport.flush();
        }
    }
}
