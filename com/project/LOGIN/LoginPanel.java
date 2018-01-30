package com.project.LOGIN;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.project.DTO.TotalDAO;
import com.project.DTO.UserDTO;
import com.project.FRAME.LoginFrame;
import com.project.FRAME.MainFrame;

public class LoginPanel extends JPanel implements ActionListener {
	LoginFrame l;

	JPanel pNorth, pCenter;
	JLabel lblPower, lblIcon, lblTitle, lblId, lblPwd, lblCopy;
	JTextField tfId;
	JPasswordField pfPwd;
	JButton btnLogin, btnJoin, btnClose;
	Font font1 = new Font("나눔바른고딕", Font.PLAIN, 14);
	Font font2 = new Font("나눔스퀘어", Font.BOLD, 16);
	Font font3 = new Font("나눔바른고딕", Font.PLAIN, 25);
	Font font4 = new Font("나눔바른고딕", Font.PLAIN, 12);

	public LoginPanel(LoginFrame l) {
		this.l = l;
		setLayout(null);

		// 패널 생성
		pNorth = new JPanel();
		pNorth.setBackground(new Color(95, 210, 195));
		pNorth.setBounds(0, 0, 400, 60);
		pNorth.setLayout(null);

		pCenter = new JPanel();
		pCenter.setBackground(new Color(34, 45, 65));
		pCenter.setBounds(0, 60, 400, 220);
		pCenter.setLayout(null);

		add(pNorth);
		add(pCenter);

		// 라벨 생성
		ImageIcon main = new ImageIcon("img/l1.png");
		Image mainImage = main.getImage();
		Image mainRe = mainImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon mainFix = new ImageIcon(mainRe);
		lblIcon = new JLabel(mainFix);
		lblIcon.setBounds(115, 5, 50, 50);
		
		lblTitle = new JLabel("들 어 가 기");
		lblTitle.setFont(font3);
		lblTitle.setForeground(new Color(34, 45, 65));
		lblTitle.setBounds(175, 15, 200, 30);

		lblId = new JLabel("아이디");
		lblId.setFont(font1);
		lblId.setForeground(new Color(254, 245, 253));
		lblId.setBounds(115, 30, 40, 20);

		lblPwd = new JLabel("비밀번호");
		lblPwd.setFont(font1);
		lblPwd.setForeground(new Color(254, 245, 253));
		lblPwd.setBounds(103, 70, 50, 20);

		ImageIcon power = new ImageIcon("img/power1.png");
		Image powerImage = power.getImage();
		Image powerRe = powerImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon powerFix = new ImageIcon(powerRe);
		lblPower = new JLabel(powerFix);
		lblPower.setBounds(145, 185, 20, 20);
		
		lblCopy = new JLabel("Powered by 6조");
		lblCopy.setFont(font4);
		lblCopy.setForeground(new Color(254, 245, 253));
		lblCopy.setBounds(170, 180, 120, 30);

		pNorth.add(lblIcon);
		pNorth.add(lblTitle);
		pCenter.add(lblId);
		pCenter.add(lblPwd);
		pCenter.add(lblPower);
		pCenter.add(lblCopy);

		// 텍스트필드 생성
		tfId = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfId.setBackground(new Color(145, 150, 160));
		tfId.setBounds(165, 30, 120, 20);

		pfPwd = new JPasswordField(14) {
			public void setBorder(Border border) {
			}
		};
		pfPwd.setBackground(new Color(145, 150, 160));
		pfPwd.setBounds(165, 70, 120, 20);

		pCenter.add(tfId);
		pCenter.add(pfPwd);

		// 버튼 생성
		btnLogin = new JButton("로그인");
		btnLogin.setFont(font1);
		btnLogin.setFocusPainted(false);
		btnLogin.setForeground(new Color(254, 245, 253));
		btnLogin.setBackground(new Color(34, 45, 65));
		btnLogin.setBounds(120, 120, 80, 30);

		btnJoin = new JButton("회원가입");
		btnJoin.setFont(font1);
		btnJoin.setFocusPainted(false);
		btnJoin.setForeground(new Color(254, 245, 253));
		btnJoin.setBackground(new Color(34, 45, 65));
		btnJoin.setBounds(210, 120, 85, 30);
		
		
		
		// 일반 icon
		ImageIcon nomalIcon = new ImageIcon("img/1.png");
		Image nomalIcon1 = nomalIcon.getImage();
		Image nomalIconRe = nomalIcon1.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon nomalFix = new ImageIcon(nomalIconRe);
		
		// 버튼 누를때 icon
		ImageIcon pIcon = new ImageIcon("img/2.png");
		Image pIcon1 = pIcon.getImage();
		Image pIconRe = pIcon1.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon pFix = new ImageIcon(pIconRe);
		
		btnClose = new JButton(nomalFix);
		btnClose.setPressedIcon(pFix);
		btnClose.setFocusPainted(false);
		btnClose.setBorderPainted(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setBackground(new Color(95, 210, 195));
		btnClose.setBounds(355, 5, 45, 20);

		pNorth.add(btnClose);
		pCenter.add(btnLogin);
		pCenter.add(btnJoin);

		// 이벤트처리
		btnLogin.addActionListener(this);
		btnJoin.addActionListener(this);
		btnClose.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			String id = tfId.getText();
			String pw = pfPwd.getText();
			UserDTO dto = new UserDTO();
			TotalDAO totalDAO = new TotalDAO();
			dto.setId(id);
			dto.setPw(pw);
			String adminCheck=totalDAO.adminCheck(id);
			if (totalDAO.loginCheck(dto)) {
				l.dispose();
				MainFrame f = new MainFrame(id, adminCheck);
			} else {
				JOptionPane.showMessageDialog(this, "회원ID 와 비밀번호를 확인해주세요");
			}
		} else if (e.getSource() == btnJoin) {
			l.remove(this);
			JoinPanel p = new JoinPanel(l);
			l.add(p);
			l.invalidate();
			l.validate();
			l.repaint();
		} // 회원가입버튼 처리
		if (e.getSource() == btnClose) {
			System.exit(0);
		}

	}

}// end
