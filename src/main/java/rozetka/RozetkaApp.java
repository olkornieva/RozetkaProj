package rozetka;

import rozetka.model.DAOImpl.Configuration;
import rozetka.model.DAOImpl.ProductDAOImpl;
import rozetka.service.MailService;

import java.util.Scanner;

public class RozetkaApp 
{
    public static void main( String[] args ) {
    	
    	Configuration.openConnection();
    	
    	ProductDAOImpl productDAO = Configuration.getProductDAO();
    	MailService mailService = new MailService();
    	mailService.startMailService(productDAO);
    	
    	System.out.println("Type 'exit' to quit...");
    	
    	Scanner scanner = new Scanner(System.in);
    	String command = null;
    	while (command == null || !command.trim().equals("exit")) {
    		command = scanner.nextLine();
    	}
    	scanner.close();
    	
    	mailService.stopMailService();
    	
    	Configuration.closeConnection();
    }

}