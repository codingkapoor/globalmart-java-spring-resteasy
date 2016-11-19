package com.globalmart.productcatalog.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalmart.product.common.entity.Product;
import com.globalmart.productcatalog.service.dao.ProductDaoI;

@Service
public class ProductService implements ProductServiceI {
	
	@Autowired(required = true)
	ProductDaoI productDao;

	@Override
	public Product getProductById(long id) {
		return productDao.getProductById(id);
	}
	
	@Override
	public List<Product> getProductByType(String type) {
		return productDao.getProductByType(type);
	}

	@Override
	public Product addProduct(Product product) {
		return productDao.addProduct(product);
	}

	@Override
	public void deleteProduct(long id) {
		productDao.deleteProduct(id);
	}

}
