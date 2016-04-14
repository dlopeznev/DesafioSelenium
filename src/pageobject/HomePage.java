package pageobject;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage extends BasePageObject 
{
	private static final Integer PRODUCT_PAGES = 2;
	
	public HomePage ()
	{
		super("http://demo.opencart.com/index.php?route=common/home", 120); 
	}
	
	public WebElement getSearchInput()
	{
		return getWebElement(By.name("search"));
	}
	
	public WebElement getSearchButton() 
	{
		return getWebElement(By.xpath("//span[@class='input-group-btn']/button"));
	}
	
	public String getSearchUrl() 
	{
		return "http://demo.opencart.com/index.php?route=product/search&search=&page=";
	}
	
	public List<WebElement> getProducts ()
	{
		return getWebElements(By.xpath("//div[@class='product-thumb']/div[@class='image']/a/img"));
	}
	
	public List<String> getAllProductsTitles ()
	{
		List<String> products = new ArrayList<>();
		
		for (int i = 1 ; i <= PRODUCT_PAGES ; i++)
		{
			getWebDriver().get(getSearchUrl() + i);
			List<WebElement> webElements = getProducts();
			
			if (CollectionUtils.isNotEmpty(webElements))
	        	for (WebElement we : webElements)
	        		if (we != null && StringUtils.isNotEmpty(we.getAttribute("title")))
	        			products.add(we.getAttribute("title").trim().toLowerCase());
		}
		return products;
	}
}