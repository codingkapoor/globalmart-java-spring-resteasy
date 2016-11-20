package com.globalmart.productcatalog.service.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.globalmart.product.common.entity.Product;

@Repository
public class ProductDao implements ProductDaoI {

	@Autowired(required = true)
	private SessionFactory sessionFactory;
	
	@Override
	public Product getProductById(long id) {
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from com.globalmart.product.common.entity.Product p where p.id = :id");
		query.setParameter("id", id);

		List<?> products = query.list();
		session.close();

		return (Product) products.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getProductByType(String type) {
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("from com.globalmart.product.common.entity.Product p where p.type = :type");
		query.setParameter("type", type);

		List<Product> products = query.list();
		session.close();

		return products;
	}

	@Override
	public Product addProduct(Product product) {
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		session.merge(product);
		tx.commit();
		
		// The saved product doesn't outlive this session. Not really sure why? Needs thorough study on in-memory databases.
		// Hence had to make this query separately instead of using getProductById() method.
		Query query = session.createQuery("from com.globalmart.product.common.entity.Product p where p.id = :id");
		query.setParameter("id", product.getId());

		List<?> products = query.list();
		Product result = (Product) products.get(0);
		
		session.close();
		
		return result;
	}

	@Override
	public void deleteProduct(long id) {
		Session session = sessionFactory.openSession();
		
		Query query = session.createQuery("delete from com.globalmart.product.common.entity.Product p where p.id = :id");
		query.setParameter("id", id);
		
		query.executeUpdate();
	}

}
