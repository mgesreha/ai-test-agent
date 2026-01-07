package platform.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 1;

    public boolean retry(ITestResult result) {
        System.out.println("[Auto-Message] This is the retry number " + retryCount);
        if (retryCount < MAX_RETRY_COUNT) {
            retryCount++;
            return true;
        }
        return false;
    }
}
