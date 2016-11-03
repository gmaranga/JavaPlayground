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

public class QueueMinImpl {


	public class QueueMinUsingStack {

		public class StackMinimum {
			private ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
			private ArrayDeque<Integer> minStack = new ArrayDeque<Integer>();

			public Integer push(Integer item) {
				if (stack.isEmpty()) {
					stack.push(item);
					minStack.push(item);
					return item;
				}
				Integer currentMin = minStack.peek();
				if (currentMin < item)
					minStack.push(currentMin);
				else
					minStack.push(item);
				stack.push(item);
				return item;
			}

			public Integer pop() {
				if (stack.size() == 0)
					return null;
				minStack.pop();
				return stack.pop();
			}

			public Integer getMinimum() {
				if (minStack.isEmpty())
					return null;
				return minStack.peek();
			}

			public int size() {
				return stack.size();
			}

		}
		
		StackMinimum stackMinimum1 = new StackMinimum();
		StackMinimum stackMinimum2 = new StackMinimum();

		public void enqueue(int a) {
			stackMinimum1.push(a);
		}

		public int dequeue() {
			if (stackMinimum2.size() == 0) {
				while (stackMinimum1.size() != 0)
					stackMinimum2.push(stackMinimum1.pop());
			}
			return stackMinimum2.pop();
		}

		public int getMinimum() {
			return Math.min(stackMinimum1.size() == 0 ? Integer.MAX_VALUE : stackMinimum1.getMinimum(),
					stackMinimum2.size() == 0 ? Integer.MAX_VALUE : stackMinimum2.getMinimum());
		}
	}

	static BufferedReader newInput() throws IOException {
//      return new BufferedReader(new InputStreamReader(new FileInputStream("queuemin.in")));
		return new BufferedReader(new InputStreamReader(QueueMinImpl.class.getResourceAsStream("queuemin.in")));
	}

	static PrintWriter newOutput() throws IOException {
			return new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream("queuemin.out"))), false);
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		
		try(BufferedReader in = newInput(); PrintWriter out = newOutput()){
			
			QueueMinUsingStack queque = new QueueMinImpl().new QueueMinUsingStack();
			in.readLine();
			String line;
			while((line = in.readLine()) != null){
				String[] command = line.split(" ");
				
				if("+".equals(command[0])){
					queque.enqueue(Integer.valueOf(command[1]));
				}else if("-".equals(command[0])){
					queque.dequeue();
				}else{
					out.println(queque.getMinimum());
				}
			}
		}

	}

}
