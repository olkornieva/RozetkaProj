package rozetka.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import rozetka.model.DAOImpl.ProductDAOImpl;
import rozetka.model.bean.Product;

public class MailService {
	//https://www.google.com/settings/security/lesssecureapps
	//Enable less secure option to use your gmail account
	private static final String host = "smtp.gmail.com";
	private static final String to = "olyakorneva09@gmail.com";
    private static final String sender = "ironimy@gmail.com";
    private static final String senderPassword = "18Pro12Psix";
    
    private static final int intervalSeconds = 10;
    
    private static Session session;
    
    private ScheduledExecutorService scheduler;
    private ScheduledFuture<?> scheduledFuture;
    private boolean started = false;
    
    static {
    	Properties properties = new Properties();
    	properties.put("mail.smtp.auth", "true");
    	properties.put("mail.smtp.starttls.enable", "true");
    	properties.put("mail.smtp.host", host);
    	properties.put("mail.smtp.port", "587");
    	
    	session = Session.getInstance(properties,
    			  new Authenticator() {
    				@Override
    				protected PasswordAuthentication getPasswordAuthentication() {
    					return new PasswordAuthentication(sender, senderPassword);
    				}
    			  });
    }
    
    public void startMailService(ProductDAOImpl productDAO) {
    	if (started) return;
    	started = true;
    	
    	scheduler = Executors.newSingleThreadScheduledExecutor();
    	
    	final Runnable sendMail = new Runnable() {
    	       public void run() {
    	    	   try {
    	    		   sendEmail(productDAO.getAllProducts());
    	    	   } catch (MessagingException ex) {
    	               System.err.println("Error occured during sending email.");
    	           } catch (SQLException e) {
    	        	   System.err.println("Error occured during access MySQL RDBMS.");
    	           }
    	       }
    	     };
    	
    	scheduledFuture = scheduler.scheduleAtFixedRate(sendMail, 0, intervalSeconds, SECONDS);
    	
    	System.out.println("Mail service started...");
    }
    
    public void stopMailService() {
    	if (!started) return;
    	scheduledFuture.cancel(true);
    	scheduler.shutdown();
    	try {
			scheduler.awaitTermination(30, SECONDS);
		} catch (InterruptedException e) {}
    	started = false;
    	System.out.println("Mail service stopped...");
    }
    
    private void sendEmail(List<Product> products) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        
        message.setFrom(new InternetAddress(sender));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        
        message.setSubject("Rozetka best selling products");
        
        StringBuilder text = new StringBuilder();
        for (Product product : products) {
        	text.append(product.toString()).append('\n');
        }
        
        message.setText(text.toString());
        
        Transport.send(message);
        
        System.out.println("Email was sent successfully.");
    }

}
