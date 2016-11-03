package com.codingcompetition.problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ImpStack {

	
	static BufferedReader newInput() throws IOException {
       return new BufferedReader(new InputStreamReader(new FileInputStream("stack.in")));
	}

	static PrintWriter newOutput() throws IOException {
			return new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("stack.out"))), false);
	}
	
	interface Stack<T>{
		T push(T item);
		T pop();
	}
	
	public class LinkedStack<T> implements Stack<T>{
		
		
		private class Node {
			
			public Node(T value, LinkedStack<T>.Node next) {
				super();
				this.value = value;
				this.next = next;
			}

			T value;
			Node next;
		}
		
		private Node top;

		@Override
		public T push(T item) {
			
			Node newTop = new Node(item, top);
			this.top = newTop;
			return item;
		}

		@Override
		public T pop() {
			
			T value = top.value;
			this.top = top.next;
			return value;
		}
	}
			
	public static void main(String[] args) throws IOException{
		
		try(BufferedReader in = newInput(); PrintWriter  out = newOutput()){
			
			Stack<Integer> stack = new ImpStack().new LinkedStack<>();
			in.readLine();
			String line;
			while((line = in.readLine()) != null){
				String[] command = line.split(" ");
				
				if("+".equals(command[0])){
					stack.push(Integer.valueOf(command[1]));
				}else{
					out.println(stack.pop());
				}
			}
				
		}
		
	}

}
