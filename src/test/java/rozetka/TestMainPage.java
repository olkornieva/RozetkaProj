package rozetka;

import rozetka_UI.MainPage;
import rozetka_UI.Navigation;

public class TestMainPage {
   
    public void testRozetka() {
        Navigation.visitMainPage();
        MainPage.goToSmatrphonesPage();
    }

}
