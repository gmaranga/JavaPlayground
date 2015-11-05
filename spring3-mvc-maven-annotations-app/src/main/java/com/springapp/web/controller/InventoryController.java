package com.springapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springapp.web.service.ProductManager;

@Controller
public class InventoryController {

	static final Logger LOG = LoggerFactory.getLogger(InventoryController.class);
	
	@Autowired
	private ProductManager productManager;
	
	@RequestMapping(value = "/showProductsList", method = RequestMethod.GET)
	public ModelAndView showProducts() {

		ModelAndView model = new ModelAndView();
		model.setViewName("showProducts");
		model.addObject("products", productManager.getProducts());
		LOG.info("Displaying a list of "+ productManager.getProducts().size() +" products");

		return model;

	}
	
}
