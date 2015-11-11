package com.wsexample.client;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.wsexample.sei.ImageService;

public class ImageClient {

	public static void main(String[] args) throws Exception{

		URL url = new URL("http://localhost:8080/image?wsdl");
		QName qname = new QName("http://sib.wsexample.com/", "ImageServiceImplService");
		Service service = Service.create(url, qname);
		ImageService imageService = service.getPort(ImageService.class);
		
		Image image = imageService.downloadImage("rss.png");
        
        //display it in frame
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);

        System.out.println("imageServer.downloadImage() : Download Successful!");
	}

}
