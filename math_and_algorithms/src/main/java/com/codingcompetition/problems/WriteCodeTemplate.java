package com.codingcompetition.problems;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WriteCodeTemplate {

	
	static Scanner newInput() throws IOException {
		if (System.getProperty("JUDGE") != null) {
            return new Scanner(new File("template.in"));
        } else {
            return new Scanner(System.in);
        }
	}

	static PrintWriter newOutput() throws IOException {
		if (System.getProperty("JUDGE") != null) {
			return new PrintWriter("template.out");
		} else {
			return new PrintWriter(System.out);
		}
	}
	
	public static void main(String[] args) throws IOException{

		try(Scanner in = newInput(); PrintWriter out = newOutput()){

			String [] dim = in.nextLine().split(" ");
			
			char[][] finalKeyboard = createKeyboard(in, out, dim);
			
			in.nextLine();
			String language1 = in.nextLine();
			StringBuilder template1 = getNextTemplate(in);
			
			long time1 = calculateTime(finalKeyboard, template1);
			
			String language2 = in.nextLine();
			StringBuilder template2 = getNextTemplate(in);
			
			long time2 = calculateTime(finalKeyboard, template2);
			
			String language3 = in.nextLine();
			StringBuilder template3 = getNextTemplate(in);
			
			long time3 = calculateTime(finalKeyboard, template3);
			
			long minTime = Math.min(Math.min(time1, time2), time3);
			
			if(time1 == minTime){
				out.println(language1);
				out.println(time1);
			}else if(time2 == minTime){
				out.println(language2);
				out.println(time2);
			}else{
				out.println(language3);
				out.println(time3);
			}
			
		}
		
	}

	private static long calculateTime(char[][] finalKeyboard, StringBuilder template1) {
		char[] t1 = template1.toString().toCharArray();
		long time = 0;
		for(int i = 0, j = 1; j < t1.length;i++,j++){
			time += calculateDistance(t1[i], t1[j], finalKeyboard);
		}
		return time;
	}

	private static char[][] createKeyboard(Scanner in, PrintWriter out, String[] dim) {
		List<char[]> keyboard = new ArrayList<>();
		
		for (int i = 0; i < Integer.valueOf(dim[1]); i++){
			char[] keyboardLine = in.nextLine().toCharArray();
			keyboard.add(keyboardLine);
		}
		
		Collections.reverse(keyboard);
		char[][] finalKeyboard = keyboard.toArray(new char[0][0]);
		return finalKeyboard;
	}

	private static StringBuilder getNextTemplate(Scanner in) {
		String line = null;
		StringBuilder template = new StringBuilder();
		
		try{
			while(!(line = in.nextLine()).equals("")){
				template.append(line);
			}
		}catch(NoSuchElementException e){}
		return template;
	}

	private static int calculateDistance(char ch1, char ch2, char[][] keyboard){
		
		
		int[] key1 = findKeyFor(ch1, keyboard);
		int[] key2 = findKeyFor(ch2, keyboard);
		
		return Math.max(Math.abs(key1[0] - key2[0]), Math.abs(key1[1] - key2[1]));
	}
	
	private static int[] findKeyFor(char ch, char[][] keyboard){
		
		for(int i = 0; i < keyboard.length; i++){
			for(int j = 0; j < keyboard[i].length; j++){
				if(keyboard[i][j] == ch){
					return new int[]{i+1,j+1};
				}
			}
		}
			
		return new int[2];
	}
}
