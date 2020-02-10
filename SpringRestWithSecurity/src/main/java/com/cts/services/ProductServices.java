package com.cts.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.dao.ProductDao;
import com.cts.model.Product;

@Service
public class ProductServices {
	@Autowired
	ProductDao productdao;
	public int addProduct(Product product) {
		return productdao.addProduct(product);
	}
	
	public Product getById(int prodId) {
		return productdao.getById(prodId);
	}
	
	public int deleteProduct(int prodId) {
		return productdao.deleteByID(prodId);
	}
	
	public int updateProduct(Product product) {
		return productdao.updateProduct(product);
	}
}
