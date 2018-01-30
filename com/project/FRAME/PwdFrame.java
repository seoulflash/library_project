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

public class PwdFrame extends JFrame implements ActionListener {
	String id, pw2;
	JPanel pNorth, pCenter;
	JLabel lblPower, lblP1, lblTitle, lblId, lblPwd, lblName, lblPhone, lblCopy;
	JTextField tfId, tfPwd, tfName, tfPhone1, tfPhone2;
	JComboBox<String> cbPhone;
	JButton btnChange, btnCancel, btnClose;

	Font font1 = new Font("나눔바른고딕", Font.PLAIN, 14);
	Font font2 = new Font("나눔스퀘어", Font.BOLD, 16);
	Font font3 = new Font("나눔바른고딕", Font.PLAIN, 30);
	Font font4 = new Font("나눔바른고딕", Font.PLAIN, 12);

	public PwdFrame(String id) {
		this.id = id;
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
		ImageIcon p1 = new ImageIcon("img/p1.png");
		Image p1Image = p1.getImage();
		Image p1Re = p1Image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon p1Fix = new ImageIcon(p1Re);
		lblP1 = new JLabel(p1Fix);
		lblP1.setBounds(80, 5, 50, 50);

		lblTitle = new JLabel("비밀번호 변경");
		lblTitle.setFont(font3);
		lblTitle.setForeground(new Color(34, 45, 65));
		lblTitle.setBounds(140, 15, 200, 30);

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
		
		lblCopy = new JLabel("copyright by 6조");
		lblCopy.setFont(font4);
		lblCopy.setForeground(new Color(254, 245, 253));
		lblCopy.setBounds(170, 180, 120, 30);

		pNorth.add(lblP1);
		pNorth.add(lblTitle);
		pCenter.add(lblId);
		pCenter.add(lblPwd);
		pCenter.add(lblName);
		pCenter.add(lblPhone);
		pCenter.add(lblPower);
		pCenter.add(lblCopy);

		// 텍스트필드 생성
		tfId = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfId.setEditable(false);
		tfId.setBackground(new Color(145, 150, 160));
		tfId.setBounds(165, 10, 120, 20);

		tfPwd = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfPwd.setEditable(true);
		tfPwd.setBackground(new Color(255, 255, 240));
		tfPwd.setBounds(165, 40, 120, 20);

		tfName = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfName.setEditable(false);
		tfName.setBackground(new Color(145, 150, 160));
		tfName.setBounds(165, 70, 120, 20);

		tfPhone1 = new JTextField(4) {
			public void setBorder(Border border) {
			}
		};
		tfPhone1.setEditable(false);
		tfPhone1.setBackground(new Color(145, 150, 160));
		tfPhone1.setBounds(226, 100, 40, 20);

		tfPhone2 = new JTextField(4) {
			public void setBorder(Border border) {
			}
		};
		tfPhone2.setEditable(false);
		tfPhone2.setBackground(new Color(145, 150, 160));
		tfPhone2.setBounds(276, 100, 40, 20);

		String[] strPhones = { "010", "011", "016", "018" };
		cbPhone = new JComboBox(strPhones);
		cbPhone.setEditable(false);
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

		btnChange = new JButton("수정");
		btnChange.setFont(font1);
		btnChange.setFocusPainted(false);
		btnChange.setForeground(new Color(254, 245, 253));
		btnChange.setBackground(new Color(34, 45, 65));
		btnChange.setBounds(130, 140, 70, 30);

		btnCancel = new JButton("취소");
		btnCancel.setFont(font1);
		btnCancel.setFocusPainted(false);
		btnCancel.setForeground(new Color(254, 245, 253));
		btnCancel.setBackground(new Color(34, 45, 65));
		btnCancel.setBounds(210, 140, 70, 30);

		pNorth.add(btnClose);
		pCenter.add(btnChange);
		pCenter.add(btnCancel);

		// 이벤트처리
		btnClose.addActionListener(this);
		btnChange.addActionListener(this);
		btnCancel.addActionListener(this);

		// DB
		TotalDAO totalDAO = new TotalDAO();
		UserDTO dto = totalDAO.searchInfo(id);
		System.out.println("dto = " + dto);

		// DTO -> 화면
		tfId.setText(dto.getId());
		tfPwd.setText(dto.getPw());
		pw2 = dto.getPw();
		tfName.setText(dto.getName());
		String phone = dto.getPhone();
		String[] phones = phone.split("-");
		String phone1 = phones[0];
		String phone2 = phones[1];
		String phone3 = phones[2];
		cbPhone.setSelectedItem(phone1);
		tfPhone1.setText(phone2);
		tfPhone2.setText(phone3);

	}// 생성자

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnChange) {
			UserDTO dto = new UserDTO();
			String pw = tfPwd.getText();
			System.out.println(pw);
			System.out.println(pw2);
			if (pw.equals(pw2)) {
				JOptionPane.showMessageDialog(this, "기존과 다른 비밀번호를 입력하세요");
				System.out.println("같은 비밀번호");
			} else {
				dto.setId(id);
				dto.setPw(pw);
				TotalDAO totalDAO = new TotalDAO();
				boolean ok = totalDAO.updatePw(dto);
				if (ok) {
					JOptionPane.showMessageDialog(this, "비밀번호가 변경되었습니다");
					this.dispose();
				} else {
					System.out.println(pw);
				}
			}
		} else if (e.getSource() == btnClose) {
			this.dispose();
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		} // X버튼, 취소버튼 처리
	}

}// end
