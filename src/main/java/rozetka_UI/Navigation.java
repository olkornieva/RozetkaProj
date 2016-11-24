package rozetka_UI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Navigation {
	
	   System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
	    public static WebDriver driver = new FirefoxDriver();

	    public static void visitMainPage() {
	        driver.get("http://www.rozetka.com.ua");
	        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	                return d.getPageSource().contains("</html>");
	            }
	        });
	    }

	    public static void visitPage(int page) {
	        WebElement element1 = Navigation.driver
	                .findElement(By.cssSelector("#page" + page + " a"));

	        element1.click();
	    }
	    
}


	

