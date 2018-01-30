package com.project.DTO;

public class BookDTO {

	private int bookNo;
	private String bookName;
	private String writer;
	private String company;
	private String borrowCheck;
	
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getBorrowCheck() {
		return borrowCheck;
	}
	public void setBorrowCheck(String borrowCheck) {
		this.borrowCheck = borrowCheck;
	}
	
}
