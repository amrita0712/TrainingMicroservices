package com.example.SubscriptionService.entity;

public class Book {
	
	private String bookId;

	private String bookName;
	
	private String author;
	
	private Integer availableCopies;
	
	private Integer totalCopies;
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getAvailableCopies() {
		return availableCopies;
	}
	public void setAvailableCopies(Integer availableCopies) {
		this.availableCopies = availableCopies;
	}
	public Integer getTotalCopies() {
		return totalCopies;
	}
	public void setTotalCopies(Integer totalCopies) {
		this.totalCopies = totalCopies;
	}
	
	
}
