package tech.csm.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product implements Serializable {
	
	@Id
	@Column(name="product_id")
	private Integer productId;
	
	@Column (name="product_name")
	private String productName;
	
	private Integer price;
	
//	getters n setters
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getproductName() {
		return productName;
	}
	public void setproductName(String name) {
		this.productName = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Products [productId=" + productId + ", name=" + productName + ", price=" + price + "]";
	}
	
	

}
