package com.project.DTO;

import java.util.Date;

public class ReturnBookDTO {
	
	private int returnNo;
	private int bookNo;
	private String id;
	private String bookName;
	private Date borrowDate;
	private Date returnDate;
	private int lastOverDueDay;
	
	public int getReturnNo() {
		return returnNo;
	}
	public void setReturnNo() {
		this.returnNo = returnNo;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public int getLastOverDueDay() {
		return lastOverDueDay;
	}
	public void setLastOverDueDay(int lastOverDueDay) {
		this.lastOverDueDay = lastOverDueDay;
	}
	
	
}
