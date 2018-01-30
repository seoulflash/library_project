package com.project.FRAME;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.project.NOMAL.MainPanel;

public class MainFrame extends JFrame {
	JPanel pCenter;
	JLabel lblPower, lblLib1, lblQuestion, lblBook, lblCall, lblNotice, welcome, welcome2, welcome3, welcome4, welcome5, welcome6, welcome7, lblCopy;
	static String id;
	static String adminCheck;
	// static String  = "1234";  = "Y";
	 
	Font font1 = new Font("나눔바른고딕", Font.BOLD, 45);
	Font font2 = new Font("나눔바른고딕", Font.PLAIN, 16);
	Font font3 = new Font("나눔바른고딕", Font.PLAIN, 18);
	Font font4 = new Font("나눔바른고딕", Font.PLAIN, 14);
	
	public MainFrame(String id, String adminCheck) {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.id = id;
		this.adminCheck = adminCheck;
		setLayout(null);
		setUndecorated(true);
		String check="Y";
		pCenter = new JPanel();
		pCenter.setBounds(150, 0, 850, 578);
		pCenter.setBackground(new Color(95, 210, 195));
		add(pCenter);
		init();
		if (check.equals(adminCheck)) {
			com.project.ADMIN.MainPanel a = new com.project.ADMIN.MainPanel(this, pCenter, id);
			a.setBounds(0, 0, 150, 578);
			add(a);
		} else{
		//메인 패널 - 일반유저
		MainPanel p = new MainPanel(this, pCenter, id);
		p.setBounds(0, 0, 150, 578);
		add(p);
		}
		
		setSize(1000, 578);
		setVisible(true);
		setResizable(false);
		//setResizable(false);
		setLocationRelativeTo(null);
	}// 생성자
	public void init() {
		pCenter.setLayout(null);
		
		// 라벨 생성
		ImageIcon lib1 = new ImageIcon("img/lib1.png");
		Image lib1Image = lib1.getImage();
		Image lib1Re = lib1Image.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
		ImageIcon lib1Fix = new ImageIcon(lib1Re);
		lblLib1 = new JLabel(lib1Fix);
		lblLib1.setBounds(360, 30, 130, 130);
		
		ImageIcon book = new ImageIcon("img/book1.png");
		Image bookImage = book.getImage();
		Image bookRe = bookImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon bookFix = new ImageIcon(bookRe);
		lblBook = new JLabel(bookFix);
		lblBook.setBounds(90, 172, 50, 50);
		
		ImageIcon q1 = new ImageIcon("img/q1.png");
		Image q1Image = q1.getImage();
		Image q1Re = q1Image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon q1Fix = new ImageIcon(q1Re);
		lblQuestion = new JLabel(q1Fix);
		lblQuestion.setBounds(240, 245, 25, 25);
		
		ImageIcon call = new ImageIcon("img/call1.png");
		Image callImage = call.getImage();
		Image callRe = callImage.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon callFix = new ImageIcon(callRe);
		lblCall = new JLabel(callFix);
		lblCall.setBackground(new Color(95, 210, 195));
		lblCall.setBounds(240, 285, 25, 25);
		
		ImageIcon n1 = new ImageIcon("img/n1.png");
		Image n1Image = n1.getImage();
		Image n1Re = n1Image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon n1Fix = new ImageIcon(n1Re);
		lblNotice = new JLabel(n1Fix);
		lblNotice.setBounds(250, 430, 50, 50);
		
		welcome = new JLabel("북서치에 오신 것을 환영합니다");
		welcome.setForeground(new Color(254, 245, 253));
		welcome.setFont(font1);
		welcome.setBounds(150, 150, 600, 100);
		
		welcome2 = new JLabel("안내 및 문의 :");
		welcome2.setForeground(new Color(254,67,101));
		welcome2.setFont(font3);
		welcome2.setBounds(270, 210, 500, 100);
		
		welcome3 = new JLabel("장애 및 오류 :");
		welcome3.setForeground(new Color(254,67,101));
		welcome3.setFont(font3);
		welcome3.setBounds(270, 250, 500, 100);
		
		welcome4 = new JLabel("안내데스크를 이용해주세요");
		welcome4.setForeground(new Color(254, 245, 253));
		welcome4.setFont(font3);
		welcome4.setBounds(380, 210, 500, 100);
		
		welcome5 = new JLabel("02 - 555 - 6666");
		welcome5.setForeground(new Color(254, 245, 253));
		welcome5.setFont(font2);
		welcome5.setBounds(380, 251, 500, 100);
		
		welcome6 = new JLabel("매월 넷째주 월요일은 휴관일 입니다");
		welcome6.setForeground(new Color(254,67,101));
		welcome6.setFont(font2);
		welcome6.setBounds(310, 390, 500, 100);
		
		welcome7 = new JLabel("이용에 불편없게 참고 부탁드립니다");
		welcome7.setForeground(new Color(254,67,101));
		welcome7.setFont(font2);
		welcome7.setBounds(312, 420, 500, 100);
		
		ImageIcon power = new ImageIcon("img/power1.png");
		Image powerImage = power.getImage();
		Image powerRe = powerImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon powerFix = new ImageIcon(powerRe);
		lblPower = new JLabel(powerFix);
		lblPower.setBounds(355, 545, 20, 20);
		
		lblCopy = new JLabel("Powered by 6조");
		lblCopy.setFont(font4);
		lblCopy.setForeground(new Color(34, 45, 65));
		lblCopy.setBounds(380, 540, 120, 30);
		
		pCenter.add(lblLib1);
		pCenter.add(lblBook);
		pCenter.add(lblQuestion);
		pCenter.add(lblCall);
		pCenter.add(lblNotice);
		pCenter.add(welcome);
		pCenter.add(welcome2);
		pCenter.add(welcome3);
		pCenter.add(welcome4);
		pCenter.add(welcome5);
		pCenter.add(welcome6);
		pCenter.add(welcome7);
		pCenter.add(lblPower);
		pCenter.add(lblCopy);
		
		
	}
	public static void main(String[] args) {
		new MainFrame(id, adminCheck);
	}// main
}// end
