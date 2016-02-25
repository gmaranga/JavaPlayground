package com.javawellgrounded.nio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PlayingWithFileSystem {

	private static String tempDir = "C:\\jwgTemp";
	private static String fileName = "MyStuff.txt";
	private static MyWatchService watchService;
	
	public static void main(String[] args) {
		
		Path newFile = createFile(fileName, createDirectory(tempDir));
		createWatchService(tempDir);
		openFileForBufferedWirting("C:\\jwgTemp\\MyStuff.txt", "test_line_1");
		
		printBasicFileAttrs(newFile);
		
		openForBufferedReading("C:\\jwgTemp\\MyStuff.txt");
		
		simplifiedReadAllFile("C:\\jwgTemp\\MyStuff.txt");
		
		//		printFilePermisions(newFile);
		
		copyFile("C:\\jwgTemp\\MyStuff.txt", "C:\\jwgTemp\\MyStuff2.txt");
		
		moveFile(Paths.get("C:\\jwgTemp\\MyStuff.txt"), Paths.get("C:\\jwgTemp\\MyStuff3.txt"));
		
		//Housekeeping
		try {
			Files.delete(Paths.get("C:\\jwgTemp\\MyStuff3.txt"));
			Files.delete(Paths.get("C:\\jwgTemp\\MyStuff2.txt"));
			Files.delete(Paths.get(tempDir));
		} catch (IOException e) {
			e.printStackTrace();
		}
		watchService.terminate();
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
	
	public static Path createFile(String aFilename, Path targetDir){
		
		Path file = targetDir.resolve(aFilename);
		Path newFile = null;
		
		try {
			 newFile = Files.createFile(file);
			 System.out.println("New file created: " + newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFile;
	}
	
	public static void copyFile(String source, String target){
		Path sourcePath = Paths.get(source);
		Path targetPath = Paths.get(target);
		try {
			Files.copy(sourcePath, targetPath);
//			Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
			System.out.println(sourcePath + " copied to " + targetPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void moveFile(Path source, Path target){
		
		try {
			Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File: " + source + " moved to: " + target);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void printBasicFileAttrs(Path file){
		
		/*
		 * Although there aren’t many file attributes that are truly universal, there is a group that
			most filesystems support. The BasicFileAttributes interface defines this common
			set, but you actually use the Files utility class.
		 */
		System.out.println("File Attributes: ");
		System.out.println("-------------------------");
		try {

			System.out.println("Last modified: " + Files.getLastModifiedTime(file));
			System.out.println("Size: " + Files.size(file));
			System.out.println("Attrs: " + Files.readAttributes(file,"*"));//Read all attrs

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Is Simb. Link: " + Files.isSymbolicLink(file));
		System.out.println("Is Directory: " + Files.isDirectory(file));
	}
	
	public static void printFilePermisions(Path file){
		//Works only for OS that supports Posix (Linux, Unix, etc) not windows
		PosixFileAttributes attrs = null;
		try {
			attrs = Files.readAttributes(file, PosixFileAttributes.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Set<PosixFilePermission> posixPermissions = attrs.permissions();
		System.out.println("Owner: " + attrs.owner());
		System.out.println("Permissions: ");
		for (PosixFilePermission posixFilePermission : posixPermissions) {
			System.out.println("-" + posixFilePermission);
		}
	}
	
	public static void openFileForBufferedWirting(String file, String content){
		
		Path filePath = Paths.get(file);
		
		try(BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8, StandardOpenOption.WRITE)){//Other commonly used open options include READ and APPEND.
			writer.write(content);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void openForBufferedReading(String file){
		Path filePath = Paths.get(file);
		
		try(BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)){
			String line;
			
			while((line = reader.readLine()) != null){
				System.out.println(line);
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void simplifiedReadAllFile(String file){
		
		Path filePath = Paths.get(file);
		List<String> lines = new ArrayList<>();
		byte[] bytes = new byte[0];
		try {
			lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
			bytes = Files.readAllBytes(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Lines: ");
		for (String line : lines) {
			System.out.println(line);
		}
		
		System.out.println("Bytes:");
		for (int i = 0; i < bytes.length; i++) {
			System.out.println(bytes[i]);
		}
	}
	
	
	public static void createWatchService(String pathString){
		
		watchService = new MyWatchService(pathString);
		new Thread(watchService).start();
		
	}
	
	private static class MyWatchService implements Runnable {

		private volatile boolean shutdown = false;
		private String pathToWatch = null;
		
		public MyWatchService(String aPathToWatch){
			this.pathToWatch = aPathToWatch;
		}

		public void terminate() {
			this.shutdown = true;
		}

		@Override
		public void run() {
			try {

				WatchService watcher = FileSystems.getDefault().newWatchService();
				Path dir = FileSystems.getDefault().getPath(this.pathToWatch);
				WatchKey key = dir.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);// Watch
																							// modifications

				while (!shutdown) {
					key = watcher.take();
					for (WatchEvent<?> event : key.pollEvents()) {
						if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
							System.out.println(">>>>Dir has changed!");
							System.out.println(event.context());
						}
					}
					key.reset();
				}

			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}

		}

	}
}
