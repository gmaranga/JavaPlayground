package com.patterns.structural.proxy;

public class RealImage implements Image {
	
	//on System A 
	private String filename = null;
    
	/**
     * Constructor
     * @param FILENAME
     */
    public RealImage(final String FILENAME) { 
        filename = FILENAME;
        loadImageFromDisk();
    }
 
    /**
     * Loads the image from the disk
     */
    private void loadImageFromDisk() {
        System.out.println("Loading   " + filename);
    }
 
    /**
     * Displays the image
     */
    @Override
    public void displayImage() { 
        System.out.println("Displaying " + filename); 
    }

}
