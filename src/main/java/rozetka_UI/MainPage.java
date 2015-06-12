package rozetka_UI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage {

    public static void goToSmatrphonesPage() {
        WebElement element1 = Navigation.driver
                .findElement(By.linkText("Телефоны, MP3, GPS"));
        element1.click();
    }
    
}