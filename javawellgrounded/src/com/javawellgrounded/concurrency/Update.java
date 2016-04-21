package com.javawellgrounded.concurrency;

public class Update implements Comparable<Update>{

	private final Author author;
	private final String updateText;
	private final long createTime; 
	
	private Update(Builder b_){
		author = b_.author;
		updateText = b_.updateText;
		createTime = b_.createTime;
	}
	
	@Override
	public String toString() {
		return "Builder [author=" + author + ", updateText=" + updateText + ", createTime=" + createTime +"]";
	}
	
	public static class Builder implements ObjBuilder<Update>{

		private Author author;
		private String updateText;
		private long createTime;
		
		public Builder author(Author author_){
			this.author = author_;
			return this;
		}
		
		public Builder updateText(String updateText_){
			this.updateText = updateText_;
			return this;
		}
		
		public Builder createTime(long now){
			this.createTime = now;
			return this;
		}
		
		@Override
		public Update build() {
			return new Update(this);
		}
		
	}

	@Override
	public int compareTo(Update o) {
		
		return (this.createTime < o.createTime) ? -1 : ((this.createTime == o.createTime) ? 0 : 1);
	}
	
}
