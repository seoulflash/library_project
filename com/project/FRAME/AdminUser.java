package com.project.FRAME;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.project.DTO.BookDTO;
import com.project.DTO.TotalDAO;
import com.project.DTO.UserDTO;

public class AdminUser extends JFrame implements ActionListener {
	String id;
	int checkFix = 0;
	DefaultTableModel model;
	String userId, pw2;
	int row;

	UserDTO dto = new UserDTO();
	TotalDAO totalDAO = new TotalDAO();

	JPanel pNorth, pCenter;
	JLabel lblPower, lblChange, lblTitle, lblId, lblPw, lblName, lblPhone, lblCheck, lblPhone1, lblPhone2, lblCopy;
	JTextField tfId, tfPw, tfName, tfPhone1, tfPhone2, tfCheck;
	JButton btnFix, btnDel, btnCancel, btnClose;
	JComboBox cbPhone;

	Font font1 = new Font("나눔바른고딕", Font.PLAIN, 14);
	Font font2 = new Font("나눔스퀘어", Font.BOLD, 16);
	Font font3 = new Font("나눔바른고딕", Font.PLAIN, 30);
	Font font4 = new Font("나눔바른고딕", Font.PLAIN, 12);

	public AdminUser(String id, String userId, DefaultTableModel model, int row) {
		this.id = id;
		this.userId = userId;
		this.model = model;
		this.row = row;

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setUndecorated(true);
		setLayout(null);
		setSize(400, 278);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);

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
		ImageIcon main = new ImageIcon("img/borrow.png");
		Image mainImage = main.getImage();
		Image mainRe = mainImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon mainFix = new ImageIcon(mainRe);
		lblChange = new JLabel(mainFix);
		lblChange.setBounds(105, 5, 50, 50);

		lblTitle = new JLabel("회 원 정 보");
		lblTitle.setFont(font3);
		lblTitle.setForeground(new Color(34, 45, 65));
		lblTitle.setBounds(165, 15, 200, 30);

		lblId = new JLabel("회원 ID");
		lblId.setFont(font1);
		lblId.setForeground(new Color(254, 245, 253));
		lblId.setBounds(118, 10, 60, 20);

		lblPw = new JLabel("비밀번호");
		lblPw.setFont(font1);
		lblPw.setForeground(new Color(254, 245, 253));
		lblPw.setBounds(110, 40, 60, 20);

		lblName = new JLabel("회원이름");
		lblName.setFont(font1);
		lblName.setForeground(new Color(254, 245, 253));
		lblName.setBounds(110, 70, 60, 20);

		lblPhone = new JLabel("전화번호");
		lblPhone.setFont(font1);
		lblPhone.setForeground(new Color(254, 245, 253));
		lblPhone.setBounds(110, 100, 60, 20);

		lblPhone1 = new JLabel("-");
		lblPhone1.setFont(font2);
		lblPhone1.setForeground(new Color(254, 245, 253));
		lblPhone1.setBounds(225, 95, 30, 30);

		lblPhone2 = new JLabel("-");
		lblPhone2.setFont(font2);
		lblPhone2.setForeground(new Color(254, 245, 253));
		lblPhone2.setBounds(283, 95, 30, 30);

		lblCheck = new JLabel("관리자권한");
		lblCheck.setFont(font1);
		lblCheck.setForeground(new Color(254, 245, 253));
		lblCheck.setBounds(98, 130, 60, 20);

		ImageIcon power = new ImageIcon("img/power1.png");
		Image powerImage = power.getImage();
		Image powerRe = powerImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon powerFix = new ImageIcon(powerRe);
		lblPower = new JLabel(powerFix);
		lblPower.setBounds(145, 195, 20, 20);

		lblCopy = new JLabel("Powered by 6조");
		lblCopy.setFont(font4);
		lblCopy.setForeground(new Color(254, 245, 253));
		lblCopy.setBounds(170, 190, 120, 30);

		pNorth.add(lblChange);
		pNorth.add(lblTitle);
		pCenter.add(lblId);
		pCenter.add(lblPw);
		pCenter.add(lblName);
		pCenter.add(lblPhone);
		pCenter.add(lblPhone1);
		pCenter.add(lblPhone2);
		pCenter.add(lblCheck);
		pCenter.add(lblPower);
		pCenter.add(lblCopy);

		// 텍스트필드 생성
		tfId = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfId.setEditable(false);
		tfId.setBackground(new Color(145, 150, 160));
		tfId.setBounds(170, 10, 120, 20);

		tfPw = new JTextField(30) {
			public void setBorder(Border border) {
			}
		};
		tfPw.setBackground(new Color(255, 255, 240));
		tfPw.setBounds(170, 40, 120, 20);

		tfName = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfName.setBackground(new Color(255, 255, 240));
		tfName.setBounds(170, 70, 120, 20);

		tfPhone1 = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfPhone1.setBackground(new Color(255, 255, 240));
		tfPhone1.setBounds(238, 100, 40, 20);

		tfPhone2 = new JTextField() {
			public void setBorder(Border border) {
			}
		};
		tfPhone2.setBackground(new Color(255, 255, 240));
		tfPhone2.setBounds(296, 100, 40, 20);

		tfCheck = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfCheck.setBackground(new Color(255, 255, 240));
		tfCheck.setBounds(170, 130, 120, 20);

		pCenter.add(tfId);
		pCenter.add(tfPw);
		pCenter.add(tfName);
		pCenter.add(tfPhone1);
		pCenter.add(tfPhone2);
		pCenter.add(tfCheck);

		// 콤보박스 생성
		String[] str = { "010", "011", "016", "017", "018", "019" };
		cbPhone = new JComboBox<>(str);
		cbPhone.setFont(font4);
		cbPhone.setBackground(new Color(255, 255, 240));
		cbPhone.setBounds(170, 100, 50, 20);

		pCenter.add(cbPhone);

		// 버튼생성
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

		btnFix = new JButton("수정");
		btnFix.setFont(font1);
		btnFix.setFocusPainted(false);
		btnFix.setForeground(new Color(254, 245, 253));
		btnFix.setBackground(new Color(34, 45, 65));
		btnFix.setBounds(95, 160, 70, 30);

		btnDel = new JButton("삭제");
		btnDel.setFont(font1);
		btnDel.setFocusPainted(false);
		btnDel.setForeground(new Color(254, 245, 253));
		btnDel.setBackground(new Color(34, 45, 65));
		btnDel.setBounds(170, 160, 70, 30);

		btnCancel = new JButton("취소");
		btnCancel.setFont(font1);
		btnCancel.setFocusPainted(false);
		btnCancel.setForeground(new Color(254, 245, 253));
		btnCancel.setBackground(new Color(34, 45, 65));
		btnCancel.setBounds(245, 160, 70, 30);

		pNorth.add(btnClose);
		pCenter.add(btnFix);
		pCenter.add(btnDel);
		pCenter.add(btnCancel);

		// 이벤트처리
		btnClose.addActionListener(this);
		btnFix.addActionListener(this);
		btnDel.addActionListener(this);
		btnCancel.addActionListener(this);

		// DB
		dto = totalDAO.searchUserInfo(userId);
		System.out.println("dto = " + dto);

		// 화면구현
		tfId.setText("" + dto.getId());
		tfPw.setText(dto.getPw());
		tfName.setText(dto.getName());
		String phone = dto.getPhone();
		String[] phones = phone.split("-");
		String phone1 = phones[0];
		String phone2 = phones[1];
		String phone3 = phones[2];
		cbPhone.setSelectedItem(phone1);
		tfPhone1.setText(phone2);
		tfPhone2.setText(phone3);
		tfCheck.setText(dto.getAdminCheck());
	}// 생성자

	@Override
	public void actionPerformed(ActionEvent e) {
		// 회원정보삭제
		if (e.getSource() == btnDel) {
			System.out.println("삭제버튼을 누름");
			int returnvalue = JOptionPane.showConfirmDialog(this, "선택하신 회원정보를 삭제하시겠습니까?", "알림",
					JOptionPane.YES_NO_OPTION);
			if (returnvalue == 0) {
				String id = dto.getId();
				totalDAO.userInfoDelete(id);
				dispose();
			}
		}
		// 회원정보수정
		if (e.getSource() == btnFix) {
			System.out.println("수정버튼을 누름");
			int returnvalue = JOptionPane.showConfirmDialog(this, "입력하신 정보로 수정하시겠습니까?", "알림",
					JOptionPane.YES_NO_OPTION);
			if (returnvalue == 0) {
				dto = new UserDTO();
				String id = tfId.getText();
				String pw = tfPw.getText();
				String name = tfName.getText();
				String phone1 = (String) cbPhone.getSelectedItem();
				String phone2 = tfPhone1.getText();
				String phone3 = tfPhone2.getText();
				String phone = phone1 + "-" + phone2 + "-" + phone3;
				String check = tfCheck.getText();

				dto.setId(id);
				dto.setPw(pw);
				dto.setName(name);
				dto.setPhone(phone);
				dto.setAdminCheck(check);
				boolean ok = totalDAO.updateUserInfo(dto);
				dispose();
			}
		} else if (e.getSource() == btnClose) {
			this.dispose();
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		} // X버튼, 취소버튼 처리
	}
}// end
