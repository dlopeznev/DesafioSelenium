package test;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import files.FileHandler;
import product.ProductHandler;

@RunWith(value = Parameterized.class)
public class ProductTest 
{
    FirefoxDriver wd;
    String product;
    
    public ProductTest (String product) 
    {
		this.product = product;
	}
    
    @Parameters(name = "Producto -> {0}")
	public static Iterable<Object[]> fileProducts() 
    {
		ProductHandler ph = new ProductHandler();
		FileHandler fh = new FileHandler();
		
		//Obtengo todos los productos
		List<String> allProducts = ph.getAllProducts(2);
		
		//Guardo los mismos
		fh.writeFile("/file/allProducts.txt", ",", allProducts);
		
		//Los levanto del archivo
		List<String> products = fh.readFile("/file/allProducts.txt", ",");
		
		Collection<String> c = products;
		
		Collection<Object[]> co = c.stream()
		                            .map(ele -> new Object[]{ele})
		                            .collect(Collectors.toList());
		
		return co;
	}
    
    @Before
    public void setUp() throws Exception 
    {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
    }
    
    @Test
    public void Test() 
    {
    	try
    	{
	        wd.get("http://demo.opencart.com/index.php?route=common/home");
	        wd.findElement(By.name("search")).click();
	        wd.findElement(By.name("search")).clear();
	        wd.findElement(By.name("search")).sendKeys(product);
	        wd.findElement(By.xpath("//span[@class='input-group-btn']/button")).click();
	        wd.findElement(By.id("grid-view")).click();
	        
	        WebElement productTag = wd.findElement(By.xpath("//div[@class='product-thumb']/div[@class='image']/a/img"));
	        
	        if (productTag != null)
	        	Assert.assertEquals(productTag.getAttribute("title").trim().toLowerCase(), product.trim().toLowerCase());
	        
	        else
	        	Assert.assertNotNull(productTag);
        }
        catch (Exception e)
        {
        	System.out.println(e);
        	Assert.fail();
        }
    }
    
    @After
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) 
    {
        try 
        {
            wd.switchTo().alert();
            return true;
        } 
        catch (NoAlertPresentException e) 
        {
            return false;
        }
    }
}
