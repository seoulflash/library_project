package com.project.DTO;

import java.util.Date;

public class BorrowBookDTO {

	private int bookNo;
	private String id;
	private String bookName;
	private Date borrowDate;
	private Date returnDueDate;
	private int overDueDay;
	
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
	public Date getReturnDueDate() {
		return returnDueDate;
	}
	public void setReturnDueDate(Date returnDueDate) {
		this.returnDueDate = returnDueDate;
	}
	public int getOverDueDay() {
		return overDueDay;
	}
	public void setOverDueDay(int overDueDay) {
		this.overDueDay = overDueDay;
	}
	
	
}
