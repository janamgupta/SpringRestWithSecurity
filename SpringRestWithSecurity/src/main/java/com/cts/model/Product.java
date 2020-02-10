package com.cts.model;

public class Product {
	Integer prodId;
	String prodName;
	Integer prodquantity;
	Float prodprice;
	
	
	public Product(Integer prodId, String prodName, Integer prodquantity, Float prodprice) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodquantity = prodquantity;
		this.prodprice = prodprice;
	}
	
	
	public Product() {
		
	}


	public Integer getProdId() {
		return prodId;
	}
	public void setProdId(Integer prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Integer getProdquantity() {
		return prodquantity;
	}
	public void setProdquantity(Integer prodquantity) {
		this.prodquantity = prodquantity;
	}
	public Float getProdprice() {
		return prodprice;
	}
	public void setProdprice(Float prodprice) {
		this.prodprice = prodprice;
	}
}
