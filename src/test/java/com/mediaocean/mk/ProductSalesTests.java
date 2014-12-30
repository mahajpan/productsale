package com.mediaocean.mk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductSalesTests {

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void testSaveProductWithSales() throws Exception {
		Product product = new Product();
		product.getSalesEntries().add(new Sales());
		entityManager.persist(product);
		entityManager.flush();
		assertNotNull(product.getProductId());
	}

	@Test
	@Transactional
	public void testSaveAndGet() throws Exception {
		Product product = new Product();
		Sales sale = new Sales();
		sale.setProduct(product);
		product.getSalesEntries().add(sale);
		entityManager.persist(product);
		entityManager.flush();
		// Otherwise the query returns the existing order (and we didn't set the
		// parent in the item)...
		entityManager.clear();
		Product otherProduct = (Product) entityManager.find(Product.class, product.getProductId());
		assertEquals(1, otherProduct.getSalesEntries().size());
		assertEquals(otherProduct, otherProduct.getSalesEntries().iterator().next().getProduct());
	}

	@Test
	@Transactional
	public void testSaveAndFind() throws Exception {
		Product product = new Product();
		product.setProductName("foo");
		Sales sale = new Sales();
		sale.setProduct(product);
		product.getSalesEntries().add(sale);
		entityManager.persist(product);
		entityManager.flush();
		// Otherwise the query returns the existing order (and we didn't set the
		// parent in the item)...
		entityManager.clear();
		Product otherProduct = (Product) entityManager
				.createQuery(
						"select p from Product p join p.salesEntries s where p.productName=:productName")
				.setParameter("productName", "foo").getSingleResult();
		assertEquals(1, otherProduct.getSalesEntries().size());
		assertEquals(otherProduct, otherProduct.getSalesEntries().iterator().next().getProduct());
	}

}
