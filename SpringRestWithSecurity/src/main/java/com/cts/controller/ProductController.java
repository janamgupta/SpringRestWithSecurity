package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.model.Product;
import com.cts.services.ProductServices;

@RestController
public class ProductController {

	@Autowired
	ProductServices prodservice;

	@RequestMapping(value = "/admin/product", method = RequestMethod.POST, produces = "application/json")
	public int addProduct(@RequestBody Product product) {
		return prodservice.addProduct(product);
	}

	//Get By ID
	@RequestMapping(value="/admin/getbyid/{prodId}",method=RequestMethod.GET)
	public ResponseEntity<Product> getById(@PathVariable("prodId") int prodId) {
		Product product = prodservice.getById(prodId);
		if(product == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@RequestMapping(value="/admin/delete/{prodId}", method=RequestMethod.DELETE)
	public ResponseEntity<Product>deleteProduct(@PathVariable("prodId")int prodId){
		HttpHeaders headers = new HttpHeaders();
		Product product = prodservice.getById(prodId);
		if(product == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		prodservice.deleteProduct(prodId);
		headers.add("Product Deleted: ", String.valueOf(prodId));
		return new ResponseEntity<Product>(product,headers,HttpStatus.OK);
	}
	
	//Update Product Details
	@RequestMapping(value="/admin/update/{prodId}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable("prodId") int prodId, @RequestBody Product product){
		HttpHeaders headers = new HttpHeaders();
		Product isProduct = prodservice.getById(prodId);
		if(isProduct == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		else if (product == null) {
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
		prodservice.updateProduct(product);
		headers.add("Product Updated - ", String.valueOf(prodId));
		return new ResponseEntity<Product>(product,headers,HttpStatus.OK);
	}
}
