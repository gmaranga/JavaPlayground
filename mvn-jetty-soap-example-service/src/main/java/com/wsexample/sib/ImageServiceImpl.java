package com.wsexample.sib;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;

import com.wsexample.sei.ImageService;

@MTOM
@WebService(endpointInterface="com.wsexample.sei.ImageService")
public class ImageServiceImpl implements ImageService {

	private static final String PICTURES_PATH = "C:\\Users\\Gmaranga\\Pictures\\";

	@Override
	public Image downloadImage(String fileName) {
		
		Image img = null;
		
		File file = new File(PICTURES_PATH+fileName);
		
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			throw new WebServiceException("Failed reading file", e);
		}
		return img;
	}

	@Override
	public String uploadImage(Image data) {
		
		if(data!=null){
			//store somewhere
			return "Upload Successful";
		}
		
		throw new WebServiceException("Upload Failed!");
	}

}
