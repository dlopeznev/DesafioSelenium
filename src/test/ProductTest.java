package test;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebElement;

import files.FileHandler;
import pageobject.HomePage;

@RunWith(value = Parameterized.class)
public class ProductTest 
{
    HomePage homePage;
    String product;
    
    public ProductTest (String product) 
    {
		this.product = product;
	}
    
    @Parameters(name = "Producto -> {0}")
	public static Iterable<Object[]> fileProducts() 
    {
		HomePage homePage = new HomePage();
		FileHandler fh = new FileHandler();
		
		//Obtengo todos los productos
		List<String> allProducts = homePage.getAllProductsTitles();
		
		homePage.quit();
		
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
    	this.homePage = new HomePage();
    }
    
    @Test
    public void Test() 
    {
    	try
    	{
	        this.homePage.open();
    		this.homePage.getSearchInput().sendKeys(product);
	        this.homePage.getSearchButton().click();
	        
	        List<WebElement> products = this.homePage.getProducts();
	        
	        if (products != null && products.size() == 1)
	        	Assert.assertEquals(products.get(0).getAttribute("title").trim().toLowerCase(), product.trim().toLowerCase());
	        
	        else
	        	Assert.assertNotNull(products);
        }
        catch (Exception e)
        {
        	System.out.println(e);
        	Assert.fail();
        }
    }
    
    @After
    public void tearDown() 
    {
        this.homePage.quit();
    }
}