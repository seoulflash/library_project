package com.project.DTO;

import java.sql.Date;

public class UserDTO {
	private String id;
	private String pw;
	private String name;
	private String phone;
	private int borrowBookNum;
	private int overDueBookNum;
	private Date registdate;
	private Date limitDate;
	private String adminCheck;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public String setPw(String pw) {
		return this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getBorrowBookNum() {
		return borrowBookNum;
	}
	public void setBorrowBookNum(int borrowBookNum) {
		this.borrowBookNum = borrowBookNum;
	}
	public int getOverDueBookNum() {
		return overDueBookNum;
	}
	public void setOverDueBookNum(int overDueBookNum) {
		this.overDueBookNum = overDueBookNum;
	}
	public Date getRegistdate() {
		return registdate;
	}
	public void setRegistdate(Date registdate) {
		this.registdate = registdate;
	}
	public Date getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	public String getAdminCheck() {
		return adminCheck;
	}
	public String setAdminCheck(String adminCheck) {
		return this.adminCheck = adminCheck;
	}
		
}// end
