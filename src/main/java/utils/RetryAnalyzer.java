package utils;

import org.testng.ITestResult;

public class RetryAnalyzer implements annotations.RetryAnalyzer {

    int counter = 0;
    int limit = 3;
    @Override
    public boolean retry(ITestResult result) {
        if (counter < limit){
            counter++;
            return true;
        }
        return false;
    }
}
