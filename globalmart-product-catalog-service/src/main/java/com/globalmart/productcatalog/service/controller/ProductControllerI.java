package com.globalmart.productcatalog.service.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.globalmart.product.common.entity.Product;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ProductControllerI {

	@GET
	@Path("/{productId}")
	public Response getProductById(@PathParam("productId") long id);

	@GET
	public Response getProductByType(@QueryParam("type") String type);

	@POST
	public Response addProduct(Product product);

	@DELETE
	@Path("/{productId}")
	public Response deleteProduct(@PathParam("productId") long id);
}
