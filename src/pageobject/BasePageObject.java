package pageobject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BasePageObject 
{
	protected String url;
	protected WebDriver webDriver;

	public BasePageObject ()
	{
		super();
	}
	
	public BasePageObject(String url, Integer timeout) 
	{
		super();
		this.url = url;
		this.webDriver = new FirefoxDriver();
		this.webDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	
	public void open ()
	{
		if (getWebDriver() != null && StringUtils.isNotEmpty(getUrl()))
			getWebDriver().get(getUrl());
	}
	
	public String getTitle ()
	{
		if (getWebDriver() != null && StringUtils.isNotEmpty(getWebDriver().getTitle()))
			return getWebDriver().getTitle();
		else
			return "";
	}
	
	protected WebElement getWebElement (By by)
	{
		if (getWebDriver() != null && by != null)
			return getWebDriver().findElement(by);
		
		else
			return null;
	}
	
	protected List<WebElement> getWebElements (By by)
	{
		if (getWebDriver() != null && by != null)
			return getWebDriver().findElements(by);
		
		else
			return null;
	}
	
	protected boolean isElementPresent (By by)
	{
		if (getWebDriver() != null && by != null)
			return getWebDriver().findElement(by) != null ? true : false;
		
		return false; 
	}
	
	public void quit ()
	{
		getWebDriver().quit();
	}
	
	public String getUrl() 
	{
		return url;
	}

	public void setUrl(String url) 
	{
		this.url = url;
	}

	public WebDriver getWebDriver() 
	{
		return webDriver;
	}

	public void setWebDriver(WebDriver webDriver) 
	{
		this.webDriver = webDriver;
	}
}