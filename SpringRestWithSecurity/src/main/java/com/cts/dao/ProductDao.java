package com.cts.dao;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.cts.model.Product;

@Repository
public class ProductDao {
	@Autowired
	private DataSource ds;
	private JdbcTemplate jdbc;

	@Autowired
	public void setDs(DataSource ds) {
		this.ds=ds;
		jdbc=new JdbcTemplate(this.ds);
	}
	
	//Add Product
	public int addProduct(Product product){
		int storedStatus=jdbc.update("INSERT INTO productdata values(?,?,?,?)",new Object[]{product.getProdId(),product.getProdName(),product.getProdquantity(),product.getProdprice()});
		System.out.println(storedStatus);
		return product.getProdId();
	}
	
	//Get Product by ID
	public Product getById(int prodId) {
		jdbc = new JdbcTemplate(ds);
		String sql = "SELECT * FROM productdata WHERE prodId=?";
		Product product = (Product)jdbc.queryForObject(sql, new Object[] {prodId},

				new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

				Product product = new Product();

				product.setProdId(rs.getInt(1));
				product.setProdName(rs.getString(2));
				product.setProdquantity(rs.getInt(3));
				product.setProdprice(rs.getFloat(4));

				return product;
			}

		});
		return product;
	}
	
	//Delete Product
	public int deleteByID(int prodId) {
		jdbc = new JdbcTemplate();
		String sql = "DELETE FROM productdata WHERE prodId=?";
		int deleteStatus = jdbc.update(sql,new Object[] {prodId});
		return deleteStatus;
	}
	
	//Update Product
	public int updateProduct(Product product) {
		jdbc = new JdbcTemplate(ds);
		String sql = "UPDATE product SET ProdName=?, Prodquantity=?, Prodprice=? WHERE ProdId=?";
		int count = jdbc.update(sql,new Object[] 
				{product.getProdName(),product.getProdquantity(),product.getProdprice(),product.getProdId()});
		return count;
	}
}
