package com.springapp.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-web-servlet.xml")
@WebAppConfiguration()
@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
public class InventoryControllerTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private InventoryController controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = standaloneSetup(this.controller).build();
	}
	
	@Test
    public void testHandleRequestView() throws Exception{

		this.mockMvc.perform(get("/showProductsList").accept(MediaType.TEXT_HTML))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("products"))
				.andExpect(view().name("showProducts")).andDo(print());
        
    }

}
