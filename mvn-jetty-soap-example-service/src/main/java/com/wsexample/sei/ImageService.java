package com.wsexample.sei;

import java.awt.Image;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ImageService {

	@WebMethod Image downloadImage(String name);
	
	@WebMethod String uploadImage(Image data);
	
}
