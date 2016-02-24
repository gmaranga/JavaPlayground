package com.javawellgrounded.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlayingWithFileSystem {

	private static String tempDir = "C:\\jwgTemp";
	private static String fileName = "MyStuff.txt";
	
	public static void main(String[] args) {
		createFile(fileName, createDirectory(tempDir));
		copyFile("C:\\jwgTemp\\MyStuff.txt", "C:\\jwgTemp\\MyStuff2.txt");
		
		//Housekeeping
		try {
			Files.delete(Paths.get("C:\\jwgTemp\\MyStuff.txt"));
			Files.delete(Paths.get("C:\\jwgTemp\\MyStuff2.txt"));
			Files.delete(Paths.get(tempDir));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Path createDirectory(String target){
		
		Path dir = null;
		
		try {
			dir = Files.createDirectories(Paths.get(target));
			System.out.println("New directory created: " + dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dir;
	}
	
	public static void createFile(String aFilename, Path targetDir){
		
		Path file = targetDir.resolve(aFilename);
		try {
			 Path newFile = Files.createFile(file);
			 System.out.println("New file created: " + newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void copyFile(String source, String target){
		Path sourcePath = Paths.get(source);
		Path targetPath = Paths.get(target);
		try {
			Files.copy(sourcePath, targetPath);
			System.out.println(sourcePath + " coied to " + targetPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
