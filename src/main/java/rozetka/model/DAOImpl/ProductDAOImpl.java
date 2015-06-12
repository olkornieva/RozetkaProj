package rozetka.model.DAOImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rozetka.model.bean.Product;

public class ProductDAOImpl {
	
	ProductDAOImpl(){}

	public List<Product> getAllProducts() throws SQLException {
		PreparedStatement stmt = Configuration.getSelectAllProductsStmt();
		ResultSet rs = stmt.executeQuery();
		
		List<Product> products = new ArrayList<Product>();
		while(rs.next()){
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getString("price"));
			product.setScanDate(rs.getTimestamp("scanDate"));
			
			products.add(product);
		}
		
		return products;
	}
	
	public void insertProduct(String name, String price) throws SQLException {
		PreparedStatement stmt = Configuration.getInsertProductStmt();
		stmt.setString(1, name);
		stmt.setString(2, price);
		stmt.executeUpdate();
	}
	
	public void clearProducts() throws SQLException {
		Configuration.getClearProductStmt().executeUpdate();
	}
}
