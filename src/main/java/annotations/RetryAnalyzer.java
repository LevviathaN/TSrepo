package annotations;

import org.testng.ITestResult;

public interface RetryAnalyzer {

    public boolean retry (ITestResult result);
}
