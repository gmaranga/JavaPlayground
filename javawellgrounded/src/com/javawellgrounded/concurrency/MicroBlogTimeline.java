package com.javawellgrounded.concurrency;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class MicroBlogTimeline {

	private final List<Update> updates;
	private final ReentrantLock lock;
	private final String name;
	private Iterator<Update> it;

	public MicroBlogTimeline(String name, List<Update> updates, ReentrantLock lock) {
		super();
		this.updates = updates;
		this.lock = lock;
		this.name = name;
	}

	public void addUpdate(Update update_) {
		this.updates.add(update_);
	}

	public void prep() {
		this.it = updates.iterator();
	}

	public void printTimeline() {
		lock.lock();
		try {
			if (it != null) {
				System.out.println(name + ": ");
				while (it.hasNext()) {
					Update s = it.next();
					System.out.println(s + ", ");
				}
				System.out.println();
			}
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {

		final CountDownLatch firstLatch = new CountDownLatch(1);
		final CountDownLatch secondLatch = new CountDownLatch(1);
		final Update.Builder ub = new Update.Builder();

		final List<Update> l = new CopyOnWriteArrayList<>();
		l.add(ub.author(new Author("Ben")).updateText("I like pie").build());
		l.add(ub.author(new Author("Charles")).updateText("I like ham on rye").build());

		ReentrantLock lock = new ReentrantLock();
		final MicroBlogTimeline tl1 = new MicroBlogTimeline("TL1", l, lock);
		final MicroBlogTimeline tl2 = new MicroBlogTimeline("TL2", l, lock);

		Thread t1 = new Thread() {

			public void run() {
				l.add(ub.author(new Author("Jeffrey")).updateText("I like a lot of things").build());
				tl1.prep();
				firstLatch.countDown();
				try {
					secondLatch.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				tl1.printTimeline();
			}
		};

		Thread t2 = new Thread() {

			@Override
			public void run() {
				try {
					firstLatch.await();
					l.add(ub.author(new Author("Gavin")).updateText("I like otters").build());// This value wont be visible when executing tl1.printTimeline() because
																								// the iterator was copied on writing
					tl2.prep();
					secondLatch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				tl2.printTimeline();

			}

		};
		
		t1.start();
		t2.start();
	}
}
