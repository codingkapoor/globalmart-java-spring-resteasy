package com.globalmart.productcatalog.service.service;

import java.util.List;

import com.globalmart.product.common.entity.Product;

public interface ProductServiceI {
	
	public Product getProductById(long id);
	
	public List<Product> getProductByType(String type);
	
	public Product addProduct(Product product);
	
	public void deleteProduct(long id);

}
