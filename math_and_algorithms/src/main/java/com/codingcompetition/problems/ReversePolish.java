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

public class ReversePolish {

	
	static BufferedReader newInput() throws IOException {
		return new BufferedReader(new InputStreamReader(new FileInputStream("postfix.in")));
//		 return new BufferedReader(new InputStreamReader(QueueMinImpl.class.getResourceAsStream("postfix.in")));
	}

	static PrintWriter newOutput() throws IOException {
		return new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("postfix.out"))), false);
	}
	
	public static void main(String[] args) throws IOException {

		try(BufferedReader in = newInput(); PrintWriter out = newOutput()){
			
			String expression = in.readLine();
			Integer result = evaluate(expression);
			out.println(result);
		}
	}

	private static Integer evaluate(String expression) {

		ArrayDeque<Integer> values = new ArrayDeque<>();
		Integer length = expression.length();
		
		for(int i = 0; i < length; i++){
			
			 char token = expression.charAt(i);
			
			if(isInteger(token)){
				values.push(Character.getNumericValue(token));
			}else{
				switch (token) {
				case '+':
					values.push(addition(values.pop(), values.pop()));
					break;
				case '-':
					values.push(substraction(values.pop(), values.pop()));
					break;
				case '*':
					values.push(multiplication(values.pop(), values.pop()));
				default:
					break;
				}
			}
		}
		
		return values.pop();
	}

	public static boolean isInteger( char input )
	{
	      return Character.isDigit(input);
	}
	
	private static Integer addition(Integer a, Integer b){
		return a + b;
	}
	
	private static Integer substraction(Integer a, Integer b){
		return b -a;
	}
	
	private static Integer multiplication(Integer a, Integer b){
		return a * b;
	}
}
