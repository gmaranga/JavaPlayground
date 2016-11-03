package com.codingcompetition.problems;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;

public class ImpQueue {

	
	public class LinkedQueue<T>{
		
		
		private LinkedList<T> queueSupport = new LinkedList<>();
		
		public void push(T element){
			
			queueSupport.add(element);
		}
		
		public T pop(){
			return queueSupport.pop();
		}
		
	}
	
	static BufferedReader newInput() throws IOException {
      return new BufferedReader(new InputStreamReader(new FileInputStream("queue.in")));
//		return new BufferedReader(new InputStreamReader(ImpStack.class.getResourceAsStream("queue.in")));
	}

	static PrintWriter newOutput() throws IOException {
			return new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("queue.out"))), false);
	}

	public static void main(String[] args) throws IOException{
		
		try(BufferedReader in = newInput(); PrintWriter out = newOutput()){
			
			LinkedQueue<Integer> queque = new ImpQueue().new LinkedQueue<>();
			in.readLine();
			String line;
			while((line = in.readLine()) != null){
				String[] command = line.split(" ");
				
				if("+".equals(command[0])){
					queque.push(Integer.valueOf(command[1]));
				}else{
					out.println(queque.pop());
				}
			}
		}

	}

}
