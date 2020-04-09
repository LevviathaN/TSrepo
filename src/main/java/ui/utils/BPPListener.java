package ui.utils;

import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.Iterator;

public class BPPListener extends TestListenerAdapter {

    @Override
    public void onFinish(ITestContext testContext) {
        Iterator<ITestResult> failedTests = testContext.getFailedTests().getAllResults().iterator();
        while (failedTests.hasNext()) {
            ITestResult failedTestCase = failedTests.next();
            ITestNGMethod method = failedTestCase.getMethod();
            if (testContext.getFailedTests().getResults(method).size() > 0) {
                BPPLogManager.getLogger().info("Failed Test Case: " + failedTestCase.getTestClass().getName());
                failedTests.remove();
            } else {
                if (testContext.getPassedTests().getResults(method).size() > 0) {
                    BPPLogManager.getLogger().info("Failed Test Case removed as pass: " + failedTestCase.getTestClass().toString());
                    failedTests.remove();
                }
            }
        }
    }
}
