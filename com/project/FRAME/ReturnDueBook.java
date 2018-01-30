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
import com.project.DTO.BorrowBookDTO;
import com.project.DTO.TotalDAO;

public class ReturnDueBook extends JFrame implements ActionListener {
	String dtoId, id;
	int bookNo;
	BorrowBookDTO dto = new BorrowBookDTO();
	TotalDAO totalDAO = new TotalDAO();
	JPanel pNorth, pCenter;
	JLabel lblPower, lblReturn, lblTitle, lblNo, lblName, lblBday, lblRday, lblOverdue, lblCopy;
	JTextField tfNo, tfName, tfBday, tfRday, tfOverdue;
	JButton btnReturn, btnCancel, btnClose;
	DefaultTableModel model;
	int row;

	JTextField tfBorrow;

	Font font1 = new Font("나눔바른고딕", Font.PLAIN, 14);
	Font font2 = new Font("나눔스퀘어", Font.BOLD, 16);
	Font font3 = new Font("나눔바른고딕", Font.PLAIN, 30);
	Font font4 = new Font("나눔바른고딕", Font.PLAIN, 12);

	public ReturnDueBook(int bookNo, String id, DefaultTableModel model, int row, JTextField tfBorrow) {
		this.id = id;
		this.bookNo = bookNo;
		this.model = model;
		this.row = row;
		this.tfBorrow = tfBorrow;
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
		ImageIcon p1 = new ImageIcon("img/return.png");
		Image p1Image = p1.getImage();
		Image p1Re = p1Image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon p1Fix = new ImageIcon(p1Re);
		lblReturn = new JLabel(p1Fix);
		lblReturn.setBounds(105, 5, 50, 50);

		lblTitle = new JLabel("도 서 반 납");
		lblTitle.setFont(font3);
		lblTitle.setForeground(new Color(34, 45, 65));
		lblTitle.setBounds(165, 15, 200, 30);

		lblNo = new JLabel("도서번호");
		lblNo.setFont(font1);
		lblNo.setForeground(new Color(254, 245, 253));
		lblNo.setBounds(108, 10, 60, 20);

		lblName = new JLabel("도서명");
		lblName.setFont(font1);
		lblName.setForeground(new Color(254, 245, 253));
		lblName.setBounds(120, 40, 60, 20);

		lblBday = new JLabel("대여날짜");
		lblBday.setFont(font1);
		lblBday.setForeground(new Color(254, 245, 253));
		lblBday.setBounds(108, 70, 60, 20);

		lblRday = new JLabel("반납기한");
		lblRday.setFont(font1);
		lblRday.setForeground(new Color(254, 245, 253));
		lblRday.setBounds(108, 100, 60, 20);

		lblOverdue = new JLabel("연체일");
		lblOverdue.setFont(font1);
		lblOverdue.setForeground(new Color(254, 245, 253));
		lblOverdue.setBounds(120, 130, 60, 20);

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

		pNorth.add(lblReturn);
		pNorth.add(lblTitle);
		pCenter.add(lblNo);
		pCenter.add(lblName);
		pCenter.add(lblBday);
		pCenter.add(lblRday);
		pCenter.add(lblOverdue);
		pCenter.add(lblPower);
		pCenter.add(lblCopy);

		// 텍스트필드 생성
		tfNo = new JTextField(14) {

			public void setBorder(Border border) {
			}
		};
		tfNo.setEditable(false);
		tfNo.setBackground(new Color(145, 150, 160));
		tfNo.setBounds(170, 10, 120, 20);

		tfName = new JTextField(30) {
			public void setBorder(Border border) {
			}
		};
		tfName.setEditable(false);
		tfName.setBackground(new Color(145, 150, 160));
		tfName.setBounds(170, 40, 120, 20);

		tfBday = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfBday.setEditable(false);
		tfBday.setBackground(new Color(145, 150, 160));
		tfBday.setBounds(170, 70, 120, 20);

		tfRday = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfRday.setEditable(false);
		tfRday.setBackground(new Color(145, 150, 160));
		tfRday.setBounds(170, 100, 120, 20);

		tfOverdue = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfOverdue.setEditable(false);
		tfOverdue.setBackground(new Color(145, 150, 160));
		tfOverdue.setBounds(170, 130, 120, 20);

		pCenter.add(tfNo);
		pCenter.add(tfName);
		pCenter.add(tfBday);
		pCenter.add(tfRday);
		pCenter.add(tfOverdue);

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

		btnReturn = new JButton("반납");
		btnReturn.setFont(font1);
		btnReturn.setFocusPainted(false);
		btnReturn.setForeground(new Color(254, 245, 253));
		btnReturn.setBackground(new Color(34, 45, 65));
		btnReturn.setBounds(130, 160, 70, 30);

		btnCancel = new JButton("취소");
		btnCancel.setFont(font1);
		btnCancel.setFocusPainted(false);
		btnCancel.setForeground(new Color(254, 245, 253));
		btnCancel.setBackground(new Color(34, 45, 65));
		btnCancel.setBounds(210, 160, 70, 30);

		pNorth.add(btnClose);
		pCenter.add(btnReturn);
		pCenter.add(btnCancel);

		// 이벤트처리
		btnClose.addActionListener(this);
		btnReturn.addActionListener(this);
		btnCancel.addActionListener(this);

		// DB
		dto = totalDAO.borrowBookInfo(bookNo);
		System.out.println("dto = " + dto);

		// 화면구현
		tfNo.setText("" + dto.getBookNo());
		tfName.setText(dto.getBookName());
		tfBday.setText("" + dto.getBorrowDate());
		tfRday.setText("" + dto.getReturnDueDate());
		tfOverdue.setText("" + dto.getOverDueDay());

	}// 생성자

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReturn) {
			int returnvalue = JOptionPane.showConfirmDialog(this, "반납하시겠습니까?", "알림", JOptionPane.YES_NO_OPTION);
			if (returnvalue == 0) {
				totalDAO.insertReturnBook(dto, id);
				totalDAO.updateCheck(bookNo, true);
				totalDAO.returnBook(bookNo);

				int result = totalDAO.updateBorrowNum(id, false);
				if (result == 1) {
					System.out.println("성공");
					model.removeRow(row);
					int num = Integer.parseInt(tfBorrow.getText());
					num -= 1;
					tfBorrow.setText("" + num);
					dispose();
				} else {
					System.out.println("실패");
				}
			}

		} else if (e.getSource() == btnClose) {
			this.dispose();
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		} // X버튼, 취소버튼 처리
	}
}// end
