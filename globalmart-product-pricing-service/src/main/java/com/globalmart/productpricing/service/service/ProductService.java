package com.globalmart.productpricing.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globalmart.productpricing.service.dao.ProductDaoI;

@Service
public class ProductService implements ProductServiceI {
	
	@Autowired(required = true)
	ProductDaoI productDao;

	@Override
	public double getProductPrice(long id) {
		return productDao.getProductById(id).getPrice();
	}

}
