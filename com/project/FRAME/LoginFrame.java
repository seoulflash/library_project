package com.project.FRAME;

import java.awt.Color;

import javax.swing.JFrame;

import com.project.LOGIN.LoginPanel;

public class LoginFrame extends JFrame {
	LoginPanel p;
	
	public LoginFrame() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setUndecorated(true);
		p = new LoginPanel(this);
		add(p);
		setSize(400, 278);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
	} // 생성자
	public static void main(String[] args) {
		new LoginFrame();
	}// main
} // end
