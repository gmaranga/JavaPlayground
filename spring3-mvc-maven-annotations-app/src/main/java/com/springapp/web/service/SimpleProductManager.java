package com.springapp.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springapp.web.domain.Product;
import com.springapp.web.repository.ProductDao;
@Service ("SimpleProductManager")
public class SimpleProductManager implements ProductManager {

//	@Resource(name="productsTestData")
//	private List<Product> products;
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public void increasePrice(int percentage) {
		
		List<Product> products = productDao.getProductList();
		if (products != null) {
            for (Product product : products) {
                double newPrice = product.getPrice().doubleValue() * 
                                    (100 + percentage)/100;
                product.setPrice(newPrice);
                productDao.saveProduct(product);
            }
        }
	}

	@Override
	public List<Product> getProducts() {
		return productDao.getProductList();
	}

	@Override
	public void setDao(ProductDao dao) {
		this.productDao = dao;
	}

	@Override
	public ProductDao getDao() {
		return this.productDao;
	}

//	@Override
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}

}
