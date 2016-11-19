package com.globalmart.product.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "product", catalog = "globalmart")
@XmlRootElement
public class Product {

	private long id;
	private String name;
	private String type;
	private double price;

	public Product() {
	}

	public Product(long id, String name, String type, double price) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "name")
	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type")
	@XmlElement
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "price")
	@XmlElement
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
