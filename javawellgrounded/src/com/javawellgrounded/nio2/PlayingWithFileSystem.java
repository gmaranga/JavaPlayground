package com.javawellgrounded.nio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileChannel;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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

		// printFilePermisions(newFile);

		copyFile("C:\\jwgTemp\\MyStuff.txt", "C:\\jwgTemp\\MyStuff2.txt");

		readUsingFileChannel(newFile);
		asyncFileReadFutureStyle(newFile);

		moveFile(Paths.get("C:\\jwgTemp\\MyStuff.txt"), Paths.get("C:\\jwgTemp\\MyStuff3.txt"));

		// Housekeeping
		try {
			Files.delete(Paths.get("C:\\jwgTemp\\MyStuff3.txt"));
			Files.delete(Paths.get("C:\\jwgTemp\\MyStuff2.txt"));
			Files.delete(Paths.get(tempDir));
		} catch (IOException e) {
			e.printStackTrace();
		}
		watchService.terminate();
	}

	public static Path createDirectory(String target) {

		Path dir = null;

		try {
			dir = Files.createDirectories(Paths.get(target));
			System.out.println("New directory created: " + dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dir;
	}

	public static Path createFile(String aFilename, Path targetDir) {

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

	public static void copyFile(String source, String target) {
		Path sourcePath = Paths.get(source);
		Path targetPath = Paths.get(target);
		try {
			Files.copy(sourcePath, targetPath);
			// Files.copy(sourcePath, targetPath,
			// StandardCopyOption.REPLACE_EXISTING,
			// StandardCopyOption.COPY_ATTRIBUTES);
			System.out.println(sourcePath + " copied to " + targetPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void moveFile(Path source, Path target) {

		try {
			Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("File: " + source + " moved to: " + target);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void printBasicFileAttrs(Path file) {

		/*
		 * Although there aren’t many file attributes that are truly universal,
		 * there is a group that most filesystems support. The
		 * BasicFileAttributes interface defines this common set, but you
		 * actually use the Files utility class.
		 */
		System.out.println("File Attributes: ");
		System.out.println("-------------------------");
		try {

			System.out.println("Last modified: " + Files.getLastModifiedTime(file));
			System.out.println("Size: " + Files.size(file));
			System.out.println("Attrs: " + Files.readAttributes(file, "*"));// Read
																			// all
																			// attrs

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Is Simb. Link: " + Files.isSymbolicLink(file));
		System.out.println("Is Directory: " + Files.isDirectory(file));
	}

	public static void printFilePermisions(Path file) {
		// Works only for OS that supports Posix (Linux, Unix, etc) not windows
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

	public static void openFileForBufferedWirting(String file, String content) {

		Path filePath = Paths.get(file);

		try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8,
				StandardOpenOption.WRITE)) {// Other commonly used open options
											// include READ and APPEND.
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void openForBufferedReading(String file) {
		Path filePath = Paths.get(file);

		try (BufferedReader reader = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
			String line;

			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void simplifiedReadAllFile(String file) {

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

	public static void createWatchService(String pathString) {

		watchService = new MyWatchService(pathString);
		new Thread(watchService).start();

	}

	public static void readUsingFileChannel(Path file) {

		/*
		 * The java.nio.channels.SeekableByteChannel interface has one
		 * implementing class in the JDK, java.nio.channels.FileChannel. This
		 * class gives you the ability to hold the current position of where you
		 * are when reading from, or writing to, a file.
		 */
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		System.out.println("Reading last 3 characters of: " + file.getFileName());

		try (FileChannel channel = FileChannel.open(file, StandardOpenOption.READ)) {
			System.out.println("Number of bytes read: " + channel.read(buffer, channel.size() - 3));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void asyncFileReadFutureStyle(Path file) {

		/*
		 * Another major new feature of NIO.2 is asynchronous capabilities for
		 * both socketand file-based I/O. Asynchronous I/O is simply a type of
		 * I/O processing that allows other activity to take place before the
		 * reading and writing has finished. New Async channels:
		 * -AsynchronousFileChannel : I/O 
		 * -AsynchronousSocketChannel : socket
		 * I/O and supports timeouts 
		 * -AsynchronousServerSocketChannel : async
		 * sockets accepting connections
		 * 
		 * You’ll typically want a Future style of asynchronous processing if
		 * you want your main thread of control to initiate the I/O and then
		 * poll for the results of that I/O.
		 */

		try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(file)) {

			ByteBuffer buffer = ByteBuffer.allocate(100_000);
			Future<Integer> result = channel.read(buffer, 0);
			System.out.println("Async reading file");
			while (!result.isDone()) {
				System.out.println("Executing other logic while is reading");
			}

			Integer bytesRead = result.get();
			System.out.println("Bytes read [" + bytesRead + "]");

		} catch (IOException | ExecutionException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void asyncFileReadCallbackStyle(Path file) {

		/*
		 * This style is typically used when you want to immediately act upon
		 * the success or failure of an asynchronous event. For example, if you
		 * were reading financial data that was mandatory for a
		 * profit-calculating business process, and that read failed, you’d
		 * immediately want to execute rollback or exception handling.
		 */
		try (AsynchronousFileChannel channel = AsynchronousFileChannel.open(file)) {

			ByteBuffer buffer = ByteBuffer.allocate(100_000);
			channel.read(buffer, 0, null, new CompletionHandler<Integer, ByteBuffer>() {

				@Override
				public void completed(Integer result, ByteBuffer attachment) {
					System.out.println("Bytes read [" + result + "]");
				}

				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
					exc.printStackTrace();
				}
			});

		} catch (IOException e) {

		}

	}

	private static class MyWatchService implements Runnable {

		private volatile boolean shutdown = false;
		private String pathToWatch = null;

		public MyWatchService(String aPathToWatch) {
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
							// other kinds of events you can monitor, such as
							// ENTRY_CREATE, ENTRY_DELETE, and OVERFLOW
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
