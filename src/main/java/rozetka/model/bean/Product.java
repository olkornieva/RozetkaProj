package rozetka.model.bean;

import java.util.Date;

public class Product {
	
	private int id;
	private String name;
	private String price;
	private Date scanDate;
	
	@Override
	public String toString() {
		return name + ": " + price;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Date getScanDate() {
		return scanDate;
	}
	public void setScanDate(Date scanDate) {
		this.scanDate = scanDate;
	}

}
