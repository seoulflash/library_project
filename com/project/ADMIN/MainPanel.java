package com.project.ADMIN;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.project.FRAME.LoginFrame;
import com.project.FRAME.MainFrame;

public class MainPanel extends JPanel implements ActionListener {
	MainFrame f;
	JPanel pCenter;
	String id;

	JButton btnHome, btnAdminInfo, btnMemberM, btnBookM, btnClaim, btnLogout, btnQuit;
	
	Font font1 = new Font("나눔바른고딕", Font.PLAIN, 16);
	Font font2 = new Font("나눔스퀘어", Font.BOLD, 16);
	Font font3 = new Font("Arial", Font.PLAIN, 16);
	Font font4 = new Font("나눔바른고딕", Font.PLAIN, 15);
	
	public MainPanel(MainFrame f, JPanel pCenter,String id) {
		this.f = f;
		this.id=id;
		this.pCenter = pCenter;
		setBackground(new Color(34, 45, 65));
		setLayout(null);
		
		
		// 버튼 생성
		
		ImageIcon home = new ImageIcon("img/home.png");
		Image homeImage = home.getImage();
		Image homeRe = homeImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon homeFix = new ImageIcon(homeRe);
		btnHome = new JButton(" H O M E ", homeFix);
		btnHome.setFont(font3);
		btnHome.setFocusPainted(false);
		btnHome.setForeground(new Color(254, 245, 253));
		btnHome.setBackground(new Color(34, 45, 65));
		btnHome.setBounds(10, 10, 132, 50);
		
		ImageIcon userinfo = new ImageIcon("img/userinfo.png");
		Image userinfoImage = userinfo.getImage();
		Image userinfoRe = userinfoImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon userinfoFix = new ImageIcon(userinfoRe);
		btnAdminInfo = new JButton(" 관리자 정보 ", userinfoFix);
		btnAdminInfo.setFont(font4);
		btnAdminInfo.setFocusPainted(false);
		btnAdminInfo.setForeground(new Color(254, 245, 253));
		btnAdminInfo.setBackground(new Color(34, 45, 65));
		btnAdminInfo.setBounds(10, 65, 132, 50);

		ImageIcon mbook = new ImageIcon("img/mbook.png");
		Image mbookImage = mbook.getImage();
		Image mbookRe = mbookImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon mbookFix = new ImageIcon(mbookRe);
		btnBookM = new JButton(" 도서관리 ", mbookFix);
		btnBookM.setFont(font1);
		btnBookM.setFocusPainted(false);
		btnBookM.setForeground(new Color(254, 245, 253));
		btnBookM.setBackground(new Color(34, 45, 65));
		btnBookM.setBounds(10, 120, 132, 50);
		
		ImageIcon usergroup = new ImageIcon("img/usergroup.png");
		Image usergroupImage = usergroup.getImage();
		Image usergroupRe = usergroupImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon usergroupFix = new ImageIcon(usergroupRe);
		btnMemberM = new JButton(" 회원관리 ", usergroupFix);
		btnMemberM.setFont(font1);
		btnMemberM.setFocusPainted(false);
		btnMemberM.setForeground(new Color(254, 245, 253));
		btnMemberM.setBackground(new Color(34, 45, 65));
		btnMemberM.setBounds(10, 175, 132, 50);
		
		ImageIcon customer = new ImageIcon("img/customer.png");
		Image customerImage = customer.getImage();
		Image customerRe = customerImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon customerFix = new ImageIcon(customerRe);
		btnClaim = new JButton(" 고객센터 ", customerFix);
		btnClaim.setFont(font1);
		btnClaim.setFocusPainted(false);
		btnClaim.setForeground(new Color(254, 245, 253));
		btnClaim.setBackground(new Color(34, 45, 65));
		btnClaim.setBounds(10, 230, 132, 50);
		
		ImageIcon logout = new ImageIcon("img/logout.png");
		Image logoutImage = logout.getImage();
		Image logoutRe = logoutImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon logoutFix = new ImageIcon(logoutRe);
		btnLogout = new JButton(" 로그아웃 ", logoutFix);
		btnLogout.setFont(font1);
		btnLogout.setFocusPainted(false);
		btnLogout.setForeground(new Color(254, 245, 253));
		btnLogout.setBackground(new Color(34, 45, 65));
		btnLogout.setBounds(10, 455, 132, 50);
		
		ImageIcon exit = new ImageIcon("img/exit.png");
		Image exitImage = exit.getImage();
		Image exitRe = exitImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon exitFix = new ImageIcon(exitRe);
		btnQuit = new JButton("<html>&nbsp종&nbsp&nbsp&nbsp&nbsp&nbsp 료</html>", exitFix);
		btnQuit.setFont(font1);
		btnQuit.setFocusPainted(false);
		btnQuit.setForeground(new Color(254, 245, 253));
		btnQuit.setBackground(new Color(34, 45, 65));
		btnQuit.setBounds(10, 510, 132, 50);
		
		add(btnHome);
		add(btnAdminInfo);
		add(btnBookM);
		add(btnMemberM);
		add(btnClaim);
		add(btnLogout);
		add(btnQuit); 
		
		// 이벤트처리
		btnHome.addActionListener(this);
		btnAdminInfo.addActionListener(this);
		btnBookM.addActionListener(this);
		btnMemberM.addActionListener(this);
		btnLogout.addActionListener(this);
		btnQuit.addActionListener(this);
	}// 생성자

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnHome) {
			pCenter.removeAll();
			f.init();
			f.invalidate();
			f.validate();
			f.repaint();
		} // 홈버튼 처리
		if (e.getSource() == btnAdminInfo) {
			pCenter.removeAll();
			InfoPanel i = new InfoPanel(f, id);
			pCenter.add(i);
			f.invalidate();
			f.validate();
			f.repaint();
		} // 개인정보버튼 처리
		if (e.getSource() == btnBookM) {
			pCenter.removeAll();
			SearchPanel s = new SearchPanel(f, id);
			pCenter.add(s);
			f.invalidate();
			f.validate();
			f.repaint();
		} // 도서검색버튼 처리
		if( e.getSource() == btnMemberM) {
			pCenter.removeAll();
			MemberPanel m = new MemberPanel(f, id);
			pCenter.add(m);
			f.invalidate();
			f.validate();
			f.repaint();
		}
		if (e.getSource() == btnLogout) {
			int returnvalue = JOptionPane.showConfirmDialog(f, "로그아웃 하시겠습니까?", "알림", JOptionPane.YES_NO_OPTION);
			if (returnvalue == 0) {
				f.dispose();
				LoginFrame l = new LoginFrame();
			} // 로그아웃버튼 처리
		}
		if (e.getSource() == btnQuit) {
//			int returnvalue = JOptionPane.showConfirmDialog(f, "프로그램을 종료하시겠습니까?");
//			if (returnvalue == 0) {
				System.exit(0);
			//} // 종료버튼 처리
		}
	}
}// end
