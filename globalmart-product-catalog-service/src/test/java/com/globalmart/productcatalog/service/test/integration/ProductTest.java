package com.globalmart.productcatalog.service.test.integration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.core.messagebody.WriterUtility;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.globalmart.product.common.entity.Product;
import com.globalmart.productcatalog.service.controller.ProductController;
import com.globalmart.productcatalog.service.test.factory.DatabaseFactory;
import com.globalmart.productcatalog.service.test.factory.DispatcherFactory;

public class ProductTest {
	
	private static final String springContextFileName = "testApplicationContext.xml";

	private static Dispatcher dispatcher;
	
	@BeforeClass
	public static void mockServer() {
		dispatcher = DispatcherFactory.createMockDispatcher(springContextFileName, ProductController.class);
	}
	
	@BeforeClass
	public static void mockDatabase() throws SQLException, FileNotFoundException {
		DatabaseFactory.createMockDatabase(ProductTest.class, springContextFileName, "test_globalmart_schema.sql");
	}	
	
	@Test
	public void testGetProductById() throws URISyntaxException {
		MockHttpRequest request = MockHttpRequest.get("/products/1");
		MockHttpResponse response = new MockHttpResponse();

		dispatcher.invoke(request, response);

		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());		
	}
	
	@Test
	public void testGetProductByType() throws URISyntaxException {
		MockHttpRequest request = MockHttpRequest.get("/products?type=alpha");
		MockHttpResponse response = new MockHttpResponse();

		dispatcher.invoke(request, response);

		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());		
	}
	
	@Test
	public void testAddProduct() throws URISyntaxException, IOException {		
		MockHttpRequest request = MockHttpRequest.post("/products");
		MockHttpResponse response = new MockHttpResponse();
		
		Product product = new Product(3, "C", "beta", 15);
		String productAsJson = WriterUtility.asString(product, MediaType.APPLICATION_JSON);
		
		request.contentType(MediaType.APPLICATION_JSON);
		request.content(productAsJson.getBytes());
		
		dispatcher.invoke(request, response);

		Assert.assertEquals(HttpServletResponse.SC_CREATED, response.getStatus());
	
	}
	
	@Test
	public void testDeleteProduct() throws URISyntaxException {
		MockHttpRequest request = MockHttpRequest.delete("/products/1");
		MockHttpResponse response = new MockHttpResponse();

		dispatcher.invoke(request, response);

		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
	}
	

}
