package com.springapp.web.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springapp.web.service.PriceIncrease;
import com.springapp.web.service.ProductManager;

@Controller
public class PriceIncreaseController {

	static final Logger LOG = LoggerFactory
			.getLogger(PriceIncreaseController.class);
	
	@Autowired
	private ProductManager productManager;

	@RequestMapping(value = "/increasePrice", method = RequestMethod.POST)
	public String increasePrice(@ModelAttribute("pi") @Valid PriceIncrease pi,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "priceIncrease";
		}
		
		int increase = pi.getPercentage();
		LOG.info("Increasing prices by " + increase + "%.");
		productManager.increasePrice(increase);
		
		return "redirect:/showProductsList";

	}

	@RequestMapping(value = "/increasePrice", method = RequestMethod.GET)
	public String increasePrice(ModelMap model) {
		PriceIncrease pi = new PriceIncrease();
		model.addAttribute("pi", pi);
		return "priceIncrease";

	}
}
