package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import config.Apputil;
import utilities.ExcelFileUtil;

public class DriverScript extends Apputil {
String inputpath="./FileInput/Login_Data.xlsx";
String outputpath="./FileOutput/Datadrivenresults.xlsx";
ExtentReports report;
ExtentTest logger;
@Test
public void startest() throws Throwable
{
	report = new ExtentReports("./Reports/LoginTest.html");
	ExcelFileUtil xl= new ExcelFileUtil(inputpath);
	int rc=xl.rowCount("Login");
	Reporter.log("no of rows"+rc,true);
	for (int i=1;i<=rc;i++)
	{
		logger=report.startTest("Login test");
		logger.assignAuthor("Faizan");
		String username= xl.getCellData("Login", i, 0);
		String password= xl.getCellData("Login", i, 1);
		boolean res=FunctionLibrary.adminLogin(username, password);
		if(res)
		{
			logger.log(LogStatus.PASS, "Login Succes");
			xl.setCellData("Login", i, 2, "Login succes", outputpath);
			xl.setCellData("Login", i, 3, "Pass", outputpath);
		}
		else
		{
		    File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    FileUtils.copyFile(screen, new File("./ScreenShot/Iteration/"+i+"Loginpage.png"));
			String Errormessage=driver.findElement(By.xpath(conpro.getProperty("Objerror"))).getText();
			xl.setCellData("Login", i, 2, Errormessage, outputpath);
			xl.setCellData("Login", i, 3, "Fail", outputpath);
			logger.log(LogStatus.FAIL, Errormessage);
			
			
		}
		report.endTest(logger);
		report.flush();
		
	}
	
}
}
