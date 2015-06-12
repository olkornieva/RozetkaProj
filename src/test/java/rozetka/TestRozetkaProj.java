package rozetka;

import org.junit.Test;

import rozetka.model.DAOImpl.Configuration;
import rozetka.model.DAOImpl.ProductDAOImpl;
import rozetka_UI.Category;
import rozetka_UI.MainPage;
import rozetka_UI.NamePrice;
import rozetka_UI.Navigation;

import java.sql.SQLException;
import java.util.List;

public class TestRozetkaProj {

    @Test
    public void testRozetka() throws InterruptedException {
        Navigation.visitMainPage();
        MainPage.goToSmatrphonesPage();
        Category.visitSmartPhones();

        List<NamePrice> sellers = Category.findTopSellers();
        Thread.sleep(500);
        Navigation.visitPage(2);
        sellers.addAll(Category.findTopSellers());
        Thread.sleep(500);
        Navigation.visitPage(3);
        sellers.addAll(Category.findTopSellers());

        Configuration.openConnection();
    	
    	ProductDAOImpl productDAO = Configuration.getProductDAO();
    	try {
    		productDAO.clearProducts();
    	} catch (SQLException e) {}
    	
        for (NamePrice seller : sellers) {
        	try {
        		productDAO.insertProduct(seller.name, seller.price);
        	} catch (SQLException e) {}
        }
        
        Configuration.closeConnection();

        Thread.sleep(3000);
        Navigation.driver.close();
    }

}