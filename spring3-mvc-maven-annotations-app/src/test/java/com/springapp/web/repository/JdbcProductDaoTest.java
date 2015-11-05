package com.springapp.web.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springapp.web.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
@DirtiesContext
public class JdbcProductDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private ProductDao productDao;

	@Before
	public void setUp() {
		super.deleteFromTables(new String[] { "products" });
		super.executeSqlScript("classpath:db/sql/load_data.sql", true);
	}

	@Test
	public void testGetProductList() {

		List<Product> products = productDao.getProductList();

		assertEquals("wrong number of products?", 3, products.size());

	}

	@Test
	public void testSaveProduct() {

		List<Product> products = productDao.getProductList();

		for (Product p : products) {
			p.setPrice(200.12);
			productDao.saveProduct(p);
		}

		List<Product> updatedProducts = productDao.getProductList();
		for (Product p : updatedProducts) {
			assertEquals("wrong price of product?", 200.12, p.getPrice().doubleValue(),0);
		}

	}

}
