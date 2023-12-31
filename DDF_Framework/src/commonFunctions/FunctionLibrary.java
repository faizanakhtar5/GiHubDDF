package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.Apputil;

public class FunctionLibrary extends Apputil{
public static boolean adminLogin(String username,String password)
{	
	driver.get(conpro.getProperty("Url"));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(By.xpath(conpro.getProperty("Objuser"))).sendKeys(username);
	driver.findElement(By.xpath(conpro.getProperty("Objpass"))).sendKeys(password);
	driver.findElement(By.xpath(conpro.getProperty("Objlogin"))).click();
	String Expected="dashboard";
	String Actual=driver.getCurrentUrl();
	if(Actual.contains(Expected))
	{
		Reporter.log("Login Pass"+Expected+"     "+Actual,true);
		return true;
	}
	else
	{
		Reporter.log("Login Fail"+Expected+"       "+Actual,true);
		return false;
	}
}
}
