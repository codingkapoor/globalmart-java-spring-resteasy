package com.globalmart.productpricing.service.dao;

import java.util.List;

import com.globalmart.product.common.entity.Product;

public interface ProductDaoI {

	public Product getProductById(long id);
	
	public List<Product> getProductByType(String type);
	
	public Product addProduct(Product product);
	
	public void deleteProduct(long id);
	
}
