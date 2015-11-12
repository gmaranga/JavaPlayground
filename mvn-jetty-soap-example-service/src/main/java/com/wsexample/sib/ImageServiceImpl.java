package com.wsexample.sib;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;

import org.json.JSONArray;
import org.json.JSONObject;

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
			try{
				return googleLookup(fileName);
			}catch(Exception ex){
				throw new WebServiceException(ex);
			}
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
	
	private Image googleLookup(String query) throws Exception{
 
			URL url = new URL("https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q="+query);
            URLConnection connection = url.openConnection();
            Image image = null;
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while((line = reader.readLine()) != null) {
                builder.append(line);
            }

            JSONObject json = new JSONObject(builder.toString());
            
            JSONArray array = json.getJSONObject("responseData").getJSONArray("results");
            
            for(int i = 0; i < array.length(); i++){
            	String imageUrl = json.getJSONObject("responseData").getJSONArray("results").getJSONObject(i).getString("url");
            	try{
            		image = ImageIO.read(new URL(imageUrl));
            	}catch(IOException e){}
            }
            
            return image;
	}

}
