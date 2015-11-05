package com.springapp.web.repository;

import java.util.List;

import com.springapp.web.domain.Product;

public interface ProductDao {
	
	public List<Product> getProductList();

    public void saveProduct(Product prod);

}
