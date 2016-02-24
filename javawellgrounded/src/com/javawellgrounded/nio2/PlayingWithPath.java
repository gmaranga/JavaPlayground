package com.javawellgrounded.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlayingWithPath {

	public static void main(String[] args) {

		Path listing = Paths.get("C:\\Program Files (x86)\\Notepad++\\notepad++.exe");
		System.out.println("File Name [" + listing.getFileName() + "]");
		System.out.println("Number of Name Elements in the Path [" + listing.getNameCount() + "]");
		System.out.println("Parent Path [" + listing.getParent() + "]");
		System.out.println("Root of the Path [" + listing.getRoot() + "]");
		System.out.println("Subpath from Root 2 elements deep [" + listing.subpath(0, 2) + "]");
		
		Path normalizedPath = Paths.get("./PlayingWithPath.java").normalize();//Remove redundant information
		System.out.println("Normalized Path: [" + normalizedPath + "]");
		
		try {
			//toRealPath() method combines the functionality of the
			//toAbsolutePath() and normalize() methods and detects and follows symbolic links.
			Path realPath = Paths.get("./src/com/javawellgrounded/nio2/PlayingWithPath.java").toRealPath();
			System.out.println("Real Path [" + realPath + "]");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Joining two Paths
		Path prefix = Paths.get("C:\\Program Files (x86)");
		Path suffix = Paths.get("Notepad++\\notepad++.exe");
		Path completePath = prefix.resolve(suffix);
		System.out.println("Complete Path [" + completePath + "]");
		
		//Get the Path between to paths
		Path programFiles = Paths.get("C:\\Program Files (x86)");
		Path system32 = Paths.get("C:\\Windows\\System32");
		Path pathToSystem32 = programFiles.relativize(system32);
		System.out.println("Path to System 32 [" + pathToSystem32 + "]");
		
		//Check for equality
		System.out.println("Equal:[" + programFiles.equals(system32) + "]");
		//Starts with
		System.out.println("Starts with [" + completePath.startsWith(prefix) + "]");
		//End with 
		System.out.println("Ends with [" + completePath.endsWith(suffix) + "]");
		
		//Working with legacy java.io.File based code
		File file = new File("./PlayingWithPath.java");
		System.out.println(file);
		Path path = file.toPath();
		System.out.println(path.toAbsolutePath());
		file = path.toFile();
		System.out.println(file);
	}

}
