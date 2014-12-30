package com.mediaocean.mk;

import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="PRODUCT")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long productId;
	
	@Column(name="product_name")
	private String productName;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="PRODUCT_ID")
	private Collection<Sales> salesEntries = new LinkedHashSet<Sales>();

	public Long getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Collection<Sales> getSalesEntries() {
		return salesEntries;
	}

	public void setSalesEntries(Collection<Sales> salesEntries) {
		this.salesEntries = salesEntries;
	}
}
