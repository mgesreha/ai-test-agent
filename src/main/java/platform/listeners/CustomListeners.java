package platform.listeners;

import org.testng.ITestListener;


public class CustomListeners implements ITestListener {

    public static Integer getAcceptedRate() {
        return acceptedRate;
    }

    public static void setAcceptedRate(Integer acceptedRate) {
        CustomListeners.acceptedRate = acceptedRate;
    }

    private static Integer acceptedRate =95;
//
//    @Override
//    public void onFinish(ITestContext context) {
//        // Get the total number of tests
//        int totalTests = context.getAllTestMethods().length;
//        System.out.println("totalTests "+totalTests);
//        // Get the number of passed tests
//        int passedTests = context.getPassedTests().size();
//        System.out.println("passedTests "+ passedTests);
//
//        // Calculate the pass rate
//        double passRate = (double) passedTests / totalTests;
//        passRate = passRate *100 ;
//        System.out.println("passRate " +passRate);
//
//        // Set the status code based on the pass rate
//        if (passRate < acceptedRate) {
//            System.out.println("acceptedRate 1 "+acceptedRate);
//            System.exit(1); // Failed
//        } else {
//            System.out.println("acceptedRate 2 "+acceptedRate);
//            System.exit(0); // Passed
//        }
//    }
}
