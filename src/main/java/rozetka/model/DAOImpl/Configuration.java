package rozetka.model.DAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Configuration {
	
	private static Connection con;
	private static PreparedStatement selectAllProductsStmt;
	private static PreparedStatement insertProductStmt;
	private static PreparedStatement clearProductStmt;
	
	public static void openConnection() {
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		}
		catch(ClassNotFoundException ex) {
			System.err.println("No driver available to connect to the MySQL RDBS.");
			System.exit(-1);
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/rozetka?characterEncoding=UTF-8", "root", "password");
			selectAllProductsStmt = con.prepareStatement("select * from BestSellingProduct");
			insertProductStmt = con.prepareStatement("insert into BestSellingProduct (name, price, scanDate) values (?, ?, now())");
			clearProductStmt = con.prepareStatement("delete from BestSellingProduct");
		}
		catch(SQLException ex) {
			System.err.println("Can't read data from MySQL RDBS.");
			closeConnection();
			System.exit(-1);
		}
	}
	
	public static PreparedStatement getSelectAllProductsStmt() {
		return selectAllProductsStmt;
	}
	
	public static PreparedStatement getInsertProductStmt() {
		return insertProductStmt;
	}
	
	public static PreparedStatement getClearProductStmt() {
		return clearProductStmt;
	}
	
	public static void closeConnection() {
		if (con != null){
			try{
				con.close();
			}
			catch(SQLException ex) {}
		}
		if (selectAllProductsStmt != null){
			try{
				selectAllProductsStmt.close();
			}
			catch(SQLException ex) {}
		}
		if (insertProductStmt != null){
			try{
				insertProductStmt.close();
			}
			catch(SQLException ex) {}
		}
	}
	
	public static ProductDAOImpl getProductDAO() {
		return new ProductDAOImpl();
	}

}
