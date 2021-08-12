package com.listener;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import io.qameta.allure.Attachment;


public class TestListener extends TestListenerAdapter implements ISuiteListener {
	
	private static final Logger logger = LogManager.getLogger(TestListener.class.getName());
	private static String fileSeperator = System.getProperty("file.separator");
	private String imagesPath = System.getProperty("user.dir") + fileSeperator + "src"+fileSeperator+"test"+fileSeperator+"resources"+fileSeperator+"Screenshots";
	
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}
	
	public void onTestFailure(ITestResult result) {
		
		logger.error("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" );
		logger.error("ERROR ----------"+result.getName()+" has failed-----------------" );
		logger.error("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\n" );
		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver)context.getAttribute("driver");
		String screenshotName = saveScreenShot(driver, result.getName());
		
	}
	
	public String saveScreenShot(WebDriver driver, String name) {
		System.out.println("Inside saveScreenshot");
		try {
			System.out.println("Saving screenshot of current browser window");
	        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        String screenShotName = "Screenshot"+ name+ ".png";
	        System.out.println("screenShotName: " + screenShotName);
			File targetFile = new File(imagesPath, screenShotName);
			FileUtils.copyFile(screenshotFile,targetFile );
			System.out.println("Screenshot created and : "+ screenShotName);
			return screenShotName;		
	    }catch(Exception e) {
	    	logger.error("An exception occured while saving screenshot of current browser window.."+e.getCause());
	        return null;
	    }
	}
}
