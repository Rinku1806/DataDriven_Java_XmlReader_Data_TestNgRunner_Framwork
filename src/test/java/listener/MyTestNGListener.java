package listener;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyTestNGListener implements ITestListener{

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("***************************Test Failed "+result.getName());
		System.out.println(result.getThrowable().getMessage());
		ExtentTest test = (ExtentTest)result.getTestContext().getAttribute("test");
		//Reporter.getCurrentTestResult().getTestContext().setAttribute("criticalFailure", "Y");
		try {
		test.log(Status.FAIL, result.getThrowable().getMessage());
		}catch(Exception E) {
			System.out.println("test caught in exception");
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("***************************Test Passed "+result.getName());
		ExtentTest test = (ExtentTest)result.getTestContext().getAttribute("test");
		try {
		test.log(Status.PASS, result.getName()+" -  Test Passed");
		}catch(Exception E) {
			System.out.println("test caught in exception");
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("***************************Test Skipped "+result.getName());
		ExtentTest test = (ExtentTest)result.getTestContext().getAttribute("test");
		System.out.println("It reached here - test"+ test+"gg");
		try {
		test.log(Status.SKIP, result.getName()+" -  Test Skipped");
		}catch(Exception E) {
			System.out.println("test caught in exception");
		}

		System.out.println(result.getName()+" -  Test Skipped");
	}

}
