package com.javawellgrounded.concurrency;

public class Update {

	private final Author author;
	private final String updateText;
	
	private Update(Builder b_){
		author = b_.author;
		updateText = b_.updateText;
	}
	
	@Override
	public String toString() {
		return "Builder [author=" + author + ", updateText=" + updateText + "]";
	}
	
	public static class Builder implements ObjBuilder<Update>{

		private Author author;
		private String updateText;
		
		
		public Builder author(Author author_){
			this.author = author_;
			return this;
		}
		
		public Builder updateText(String updateText_){
			this.updateText = updateText_;
			return this;
		}
		
		@Override
		public Update build() {
			return new Update(this);
		}
		
	}
	
}
