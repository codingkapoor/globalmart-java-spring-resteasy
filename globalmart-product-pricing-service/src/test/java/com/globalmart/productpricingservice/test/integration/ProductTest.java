package com.globalmart.productpricingservice.test.integration;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.globalmart.productpricing.service.controller.ProductController;
import com.globalmart.productpricingservice.test.factory.DatabaseFactory;
import com.globalmart.productpricingservice.test.factory.DispatcherFactory;

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
	public void testGetProductPrice() throws URISyntaxException {
		MockHttpRequest request = MockHttpRequest.get("/products/1");
		MockHttpResponse response = new MockHttpResponse();

		dispatcher.invoke(request, response);
		
		Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());		
	}

}
