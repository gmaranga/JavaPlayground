package com.javawellgrounded.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinExample {

	public static void main(String[] args) {

		List<Update> lu = new ArrayList<>();
		String text = "";
		final Update.Builder ub = new Update.Builder();
		final Author a = new Author("Tallulah");
		
		for(int i = 0; i <256; i++){
			text = text + "X";
			long now = System.currentTimeMillis();
			lu.add(ub.author(a).updateText(text).createTime(now).build());
			try{
				Thread.sleep(1);
			}catch(InterruptedException e){}
		}
		Collections.shuffle(lu);
		Update[] updates = lu.toArray(new Update[0]);
		MicroBlogUpdateSorter sorter = new MicroBlogUpdateSorter(updates);
		ForkJoinPool pool = new ForkJoinPool(4);
		pool.invoke(sorter);
		
		for(Update u : sorter.getResult()){
			System.out.println(u);
		}
		
	}

}

