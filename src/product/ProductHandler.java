package product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ProductHandler 
{
	public List<String> getAllProducts (Integer pageNumbers)
	{
		List<String> products = new ArrayList<>();
		
		try
		{
			FirefoxDriver wd = new FirefoxDriver();
	        wd.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	        
	        for (int i = 1 ; i <= pageNumbers ; i++)
	        {
	        	wd.get("http://demo.opencart.com/index.php?route=product/search&search=&page=" + i);
		        List<WebElement> webElements = wd.findElements(By.xpath("//div[@class='product-thumb']/div[@class='image']/a/img"));
		        
		        if (CollectionUtils.isNotEmpty(webElements))
		        	for (WebElement we : webElements)
		        		if (we != null && StringUtils.isNotEmpty(we.getAttribute("title")))
		        			products.add(we.getAttribute("title").trim().toLowerCase());
	        }
	        
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		return products;
	}
}