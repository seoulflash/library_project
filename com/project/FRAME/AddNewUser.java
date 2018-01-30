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

import com.project.DTO.TotalDAO;
import com.project.DTO.UserDTO;
import com.project.LOGIN.LoginPanel;

public class AddNewUser extends JFrame implements ActionListener {
	LoginFrame l;
	String id2;

	JPanel pNorth, pCenter;
	JLabel lblPower, lblNewUser, lblTitle, lblId, lblPwd, lblName, lblPhone, lblCopy;
	JTextField tfId, tfPwd, tfName, tfPhone1, tfPhone2;
	JComboBox<String> cbPhone;
	JButton btnOk, btnCancel, btnClose, btnCheck;

	Font font1 = new Font("나눔바른고딕", Font.PLAIN, 14);
	Font font2 = new Font("나눔스퀘어", Font.BOLD, 16);
	Font font3 = new Font("나눔바른고딕", Font.PLAIN, 30);
	Font font4 = new Font("나눔바른고딕", Font.PLAIN, 12);

	public AddNewUser() {
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

		// 라벨생성
		ImageIcon p1 = new ImageIcon("img/adduser.png");
		Image p1Image = p1.getImage();
		Image p1Re = p1Image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon p1Fix = new ImageIcon(p1Re);
		lblNewUser = new JLabel(p1Fix);
		lblNewUser.setBounds(105, 5, 50, 50);

		lblTitle = new JLabel("회 원 등 록");
		lblTitle.setFont(font3);
		lblTitle.setForeground(new Color(34, 45, 65));
		lblTitle.setBounds(165, 15, 200, 30);

		lblId = new JLabel("아이디");
		lblId.setFont(font1);
		lblId.setForeground(new Color(254, 245, 253));
		lblId.setBounds(115, 10, 40, 20);

		lblPwd = new JLabel("비밀번호");
		lblPwd.setFont(font1);
		lblPwd.setForeground(new Color(254, 245, 253));
		lblPwd.setBounds(103, 40, 50, 20);

		lblName = new JLabel("이름");
		lblName.setFont(font1);
		lblName.setForeground(new Color(254, 245, 253));
		lblName.setBounds(127, 70, 50, 20);

		lblPhone = new JLabel("전화번호");
		lblPhone.setFont(font1);
		lblPhone.setForeground(new Color(254, 245, 253));
		lblPhone.setBounds(103, 100, 50, 20);

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

		pNorth.add(lblNewUser);
		pNorth.add(lblTitle);
		pCenter.add(lblId);
		pCenter.add(lblPwd);
		pCenter.add(lblName);
		pCenter.add(lblPhone);
		pCenter.add(lblPower);
		pCenter.add(lblCopy);

		// 텍스트필드 생빔성
		tfId = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfId.setBackground(new Color(145, 150, 160));
		tfId.setBounds(165, 10, 120, 20);

		tfPwd = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfPwd.setBackground(new Color(145, 150, 160));
		tfPwd.setBounds(165, 40, 120, 20);

		tfName = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfName.setBackground(new Color(145, 150, 160));
		tfName.setBounds(165, 70, 120, 20);

		tfPhone1 = new JTextField(4) {
			public void setBorder(Border border) {
			}
		};
		tfPhone1.setBackground(new Color(145, 150, 160));
		tfPhone1.setBounds(226, 100, 40, 20);

		tfPhone2 = new JTextField(4) {
			public void setBorder(Border border) {
			}
		};
		tfPhone2.setBackground(new Color(145, 150, 160));
		tfPhone2.setBounds(276, 100, 40, 20);

		String[] strPhones = { "010", "011", "016", "018" };
		cbPhone = new JComboBox(strPhones);
		cbPhone.setBackground(new Color(145, 150, 160));
		cbPhone.setBounds(165, 100, 50, 20);

		JLabel lbl1 = new JLabel("-");
		lbl1.setFont(font2);
		lbl1.setForeground(new Color(254, 245, 253));
		lbl1.setBounds(217, 105, 10, 10);
		pCenter.add(lbl1);

		JLabel lbl2 = new JLabel("-");
		lbl2.setFont(font2);
		lbl2.setForeground(new Color(254, 245, 253));
		lbl2.setBounds(267, 105, 10, 10);
		pCenter.add(lbl2);

		pCenter.add(tfId);
		pCenter.add(tfPwd);
		pCenter.add(tfName);
		pCenter.add(cbPhone);
		pCenter.add(tfPhone1);
		pCenter.add(tfPhone2);

		// 버튼 생성
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

		btnCheck = new JButton("중복체크");
		btnCheck.setFont(font4);
		btnCheck.setFocusPainted(false);
		btnCheck.setForeground(new Color(254, 245, 253));
		btnCheck.setBackground(new Color(34, 45, 65));
		btnCheck.setBounds(300, 10, 80, 20);

		btnOk = new JButton("등록");
		btnOk.setFont(font1);
		btnOk.setForeground(new Color(254, 245, 253));
		btnOk.setBackground(new Color(34, 45, 65));
		btnOk.setBounds(130, 140, 70, 30);

		btnCancel = new JButton("취소");
		btnCancel.setFont(font1);
		btnCancel.setForeground(new Color(254, 245, 253));
		btnCancel.setBackground(new Color(34, 45, 65));
		btnCancel.setBounds(210, 140, 70, 30);

		pNorth.add(btnClose);
		pCenter.add(btnCheck);
		pCenter.add(btnOk);
		pCenter.add(btnCancel);

		// 버튼 이벤트처리
		btnCheck.addActionListener(this);
		btnOk.addActionListener(this);
		btnCancel.addActionListener(this);
		btnClose.addActionListener(this);
	}// 생성자

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCheck) {
			TotalDAO totalDAO = new TotalDAO();
			String id = tfId.getText();
			UserDTO dto = totalDAO.searchInfo(id);
			id2 = dto.getId();
			System.out.println(id);
			System.out.println(id2);
			if (id.equals(id2)) {
				JOptionPane.showMessageDialog(this, "입력하신 ID가 이미 존재합니다");
				System.out.println("아이디 중복");
			} else {
				JOptionPane.showMessageDialog(this, "사용 가능한 ID 입니다");
			}
		} else if (e.getSource() == btnClose) {
			dispose();
		} // 창끄기 이벤트처리
		else if (e.getSource() == btnOk) {
			joinUser();
		} // 확인버튼 이벤트처리
		else if (e.getSource() == btnCancel) {
			dispose();
		} // 취소버튼 이벤트처리
	}// actionPerformed

	private void joinUser() {
		System.out.println("확인버튼을 클릭");
		String id = tfId.getText();
		System.out.println("id = " + id);
		String pwd = tfPwd.getText();
		System.out.println("pwd = " + pwd);
		String name = tfName.getText();
		System.out.println("name = " + name);
		String phone1 = (String) cbPhone.getSelectedItem();
		System.out.println("phone1 = " + phone1);
		String phone2 = tfPhone1.getText();
		System.out.println("phone2 = " + phone2);
		String phone3 = tfPhone2.getText();
		System.out.println("phone3 = " + phone3);
		String phone = phone1 + "-" + phone2 + "-" + phone3;
		System.out.println("phone = " + phone);
		if (id.length() == 0) {
			JOptionPane.showMessageDialog(this, "아이디를 입력해주세요");
		} else if (pwd.length() < 4) {
			JOptionPane.showMessageDialog(this, "비밀번호를 4자리 이상으로 만들어주세요");
		} else if (name.length() == 0) {
			JOptionPane.showMessageDialog(this, "이름을 입력해주세요");
		} else if (phone.length() < 13 || 13 < phone.length()) {
			JOptionPane.showMessageDialog(this, "전화번호를 정확히 입력해주세요");
		}

		UserDTO dto = new UserDTO();
		if (id.length() != 0) {
			dto.setId(id);
		}
		if (pwd.length() >= 4) {
			dto.setPw(pwd);
		}
		if (name.length() != 0) {
			dto.setName(name);
		}
		if (phone.length() == 13) {
			dto.setPhone(phone);
		}
		// 정보저장
		TotalDAO totalDAO = new TotalDAO();
		boolean ok = totalDAO.createMember(dto);
		if (ok) {
			dispose();
		}
	}
}// end
