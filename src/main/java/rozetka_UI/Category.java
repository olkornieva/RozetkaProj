package rozetka_UI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

	public class Category {

	    public static void visitSmartPhones() {
	        WebElement element1 = Navigation.driver
	                .findElement(By.linkText("Смартфоны"));
	        element1.click();
	    }

	    public static List<NamePrice> findTopSellers() {
	        List<WebElement> elements = Navigation.driver
	                .findElements(By.cssSelector(".popularity"));

	        List<NamePrice> names = new ArrayList<NamePrice>();
	        for (WebElement element : elements) {
	            WebElement element2 = element
	                    .findElement(By.xpath(".."))
	                    .findElement(By.xpath(".."))
	                    .findElement(By.xpath(".."))
	                    .findElement(By.xpath(".."));
	            List<WebElement> nameElements = element2.findElements(By.cssSelector(".gtile-i-title > a"));
	            List<WebElement> priceElements = element2.findElements(By.cssSelector(".g-price-uah"));
	            NamePrice namePrice = new NamePrice();
	            namePrice.name = nameElements.get(0).getText();
	            namePrice.price = priceElements.get(0).getText();
	            names.add(namePrice);
	        }

	        return names;
	    }
	}


