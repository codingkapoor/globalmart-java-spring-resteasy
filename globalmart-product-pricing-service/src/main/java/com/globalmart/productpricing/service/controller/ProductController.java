package com.globalmart.productpricing.service.controller;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.globalmart.productpricing.service.service.ProductServiceI;

@Controller
public class ProductController implements ProductControllerI {

	@Autowired(required = true)
	ProductServiceI productService;

	public Response getProductPrice(long id) {
		double productPrice = productService.getProductPrice(id);
		return Response.status(Status.OK).entity(String.valueOf(productPrice)).build();
	}

}
