package com.codingcompetition.problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class BracketSequence {

	private static final String YES = "YES";
	private static final String NO = "NO";
	
	
	static BufferedReader newInput() throws IOException {
		return new BufferedReader(new InputStreamReader(new FileInputStream("brackets.in")));
//		 return new BufferedReader(new InputStreamReader(QueueMinImpl.class.getResourceAsStream("brackets.in")));
	}

	static PrintWriter newOutput() throws IOException {
		return new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("brackets.out"))), false);
	}

	public static void main(String[] args) throws IOException{
		
		try(BufferedReader in = newInput(); PrintWriter out = newOutput()){
			
			String bracketSeq;
			 while((bracketSeq = in.readLine()) != null){
				 if(isValidSeq(bracketSeq)){
					 out.println(YES);
				 }else{
					 out.println(NO);
				 }
			 }
		}
	}
	
	private static boolean isValidSeq(String bracketSeq){
		
		int bracketSeqLenght = bracketSeq.length();
		char openp = '(';
		char closep = ')';
		
		char openb = '[';
		char closeb = ']';
		
		if(bracketSeqLenght % 2 != 0){
			return false;
		}
		if(bracketSeq.charAt(0) == closep || bracketSeq.charAt(0) == closeb){
			return false;
		}
		if(bracketSeq.charAt(bracketSeqLenght -1) == openp || bracketSeq.charAt(bracketSeqLenght -1) == openb){
			return false;
		}
		
		
		ArrayDeque<Character> stack = new ArrayDeque<Character>();

		char c;
		for (int i = 0; i < bracketSeqLenght; i++) {
			c = bracketSeq.charAt(i);

			if (c == openp)
				stack.push(c);
			else if (c == openb)
				stack.push(c);
			else if (c == closep)
				if (stack.isEmpty())
					return false;
				else if (stack.peek() == openp)
					stack.pop();
				else
					return false;
			else if (c == closeb)
				if (stack.isEmpty())
					return false;
				else if (stack.peek() == openb)
					stack.pop();
				else
					return false;
		}
		return stack.isEmpty();
		
	}

}
