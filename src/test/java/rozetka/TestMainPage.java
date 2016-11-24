package rozetka;

import rozetka_UI.MainPage;
import rozetka_UI.Navigation;

public class TestMainPage {
   
    public void testRozetka()  throws Exception {
        Navigation.visitMainPage();
        MainPage.goToSmatrphonesPage();
    }

}
