
package com.mediaocean.mk;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * An item in an order
 */
@Entity
public class Sales {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long salesId;
	
	@ManyToOne
	private Product product;

	private Date dateOfSale;
	
	private double salesAmount;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getDateOfSale() {
		return dateOfSale;
	}

	public void setDateOfSale(Date dateOfSale) {
		this.dateOfSale = dateOfSale;
	}

	public double getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(double salesAmount) {
		this.salesAmount = salesAmount;
	}

	public Long getSalesId() {
		return salesId;
	}
	
	

}
