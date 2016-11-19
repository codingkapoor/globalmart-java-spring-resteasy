package com.globalmart.productcatalog.service.controller;

import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.globalmart.product.common.entity.Product;
import com.globalmart.productcatalog.service.service.ProductServiceI;

@Controller
public class ProductController implements ProductControllerI {

	@Autowired(required = true)
	ProductServiceI productService;

	public Response getProductById(long id) {
		Product product = productService.getProductById(id);
		return Response.status(Status.OK).entity(product).build();
	}

	public Response getProductByType(String type) {
		List<Product> products = productService.getProductByType(type);

		GenericEntity<List<Product>> genericEntity = new GenericEntity<List<Product>>(products) {};
		return Response.status(Status.OK).entity(genericEntity).build();
	}

	public Response addProduct(Product product) {
		Product newProduct = productService.addProduct(product);
		return Response.status(Status.CREATED).entity(newProduct).build();
	}

	public Response deleteProduct(long id) {
		productService.deleteProduct(id);
		return Response.status(Status.OK).build();
	}

}
