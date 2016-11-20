# globalmart-java-spring-resteasy
This repository demonstrates restful webservices using Java, Spring and Resteasy in it's simplest form.

# Build
```
$ cd globalmart-product-common-entity
$ mvn install

$ cd globalmart-product-catalog-service
$ mvn install
```
# Deploy
Deploy WARs on JBoss or Tomcat.

# Restful
```
GET	api/products/1
{
  "id": 1,
  "name": "A",
  "price": 1200,
  "type": "alpha"
}

POST api/products
{"product":{"id":3,"name":"D","price":1200,"type":"gamma"}}
```
