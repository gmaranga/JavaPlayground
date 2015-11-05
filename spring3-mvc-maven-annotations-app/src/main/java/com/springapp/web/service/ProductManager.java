package com.springapp.web.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springapp.web.domain.Product;
import com.springapp.web.repository.ProductDao;

@Service
public interface ProductManager {

	public void increasePrice(int percentage);
    
    public List<Product> getProducts();

//	public void setProducts(List<Product> products);
    
    public void setDao(ProductDao dao);
    
    public ProductDao getDao();
	
}
