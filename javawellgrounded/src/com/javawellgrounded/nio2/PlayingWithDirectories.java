package com.javawellgrounded.nio2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class PlayingWithDirectories {

	public static void main(String[] args) {

//		listFiles(".exe");
		listFilesRecursively(".exe");
		
	}

	private static void listFilesRecursively(String patternMatch) {

		Path startingDir = Paths.get("C:\\Windows");
		try {
			/*
			 * The walkFileTree method doesn’t automatically follow symbolic
links, making operations like recursion safer. If you do need to follow symbolic
links, you’ll need to detect that attribute and act on it accordingly
			 */
			Files.walkFileTree(startingDir, new FindExeVisitor(patternMatch));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//Extends default implementatios SimpleFileVisitor that implements FileVisitor interface
	private static class FindExeVisitor extends SimpleFileVisitor<Path>{

		private String patternMatch;

		public FindExeVisitor (String patternMatch){
			this.patternMatch = patternMatch;
		}
		
		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			
			if(file.toString().endsWith(this.patternMatch)){
				System.out.println(file.getFileName());
			}
			
			return FileVisitResult.CONTINUE;
		}
		
		
	}
	
	private static void listFiles(String patternMatch) {
		//Listing all the .exe files
		Path dir = Paths.get("C:\\Windows");
	
		/*
		 * The pattern matching that’s used is called a glob pattern match, which is similar to,
			but has some differences from, the sorts of Perl-like regular expression pattern matching
			you’re used to.
		 */
		try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir, patternMatch)){
			
			for (Path path : stream) {
				System.out.println(path.getFileName());
			}
			
		}catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
