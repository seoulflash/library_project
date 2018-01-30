package com.project.FRAME;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import com.project.DTO.BookDTO;
import com.project.DTO.TotalDAO;

public class AddNewBook extends JFrame implements ActionListener {
	String id;
	int bookNo;
	BookDTO dto = new BookDTO();
	TotalDAO totalDAO = new TotalDAO();
	JPanel pNorth, pCenter;
	JLabel lblPower, lblNewBook, lblTitle, lblName, lblWriter, lblCompany, lblCopy;
	JTextField tfName, tfWriter, tfCompany;
	JButton btnAdd, btnCancel, btnClose;
	DefaultTableModel model;
	int row;

	Font font1 = new Font("나눔바른고딕", Font.PLAIN, 14);
	Font font2 = new Font("나눔스퀘어", Font.BOLD, 16);
	Font font3 = new Font("나눔바른고딕", Font.PLAIN, 30);
	Font font4 = new Font("나눔바른고딕", Font.PLAIN, 12);

	public AddNewBook() {
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
		ImageIcon p1 = new ImageIcon("img/library.png");
		Image p1Image = p1.getImage();
		Image p1Re = p1Image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon p1Fix = new ImageIcon(p1Re);
		lblNewBook = new JLabel(p1Fix);
		lblNewBook.setBounds(105, 5, 50, 50);

		lblTitle = new JLabel("도 서 등 록");
		lblTitle.setFont(font3);
		lblTitle.setForeground(new Color(34, 45, 65));
		lblTitle.setBounds(165, 15, 200, 30);

		lblName = new JLabel("도서명");
		lblName.setFont(font1);
		lblName.setForeground(new Color(254, 245, 253));
		lblName.setBounds(120, 30, 60, 20);

		lblWriter = new JLabel("저자명");
		lblWriter.setFont(font1);
		lblWriter.setForeground(new Color(254, 245, 253));
		lblWriter.setBounds(120, 70, 60, 20);

		lblCompany = new JLabel("출판사명");
		lblCompany.setFont(font1);
		lblCompany.setForeground(new Color(254, 245, 253));
		lblCompany.setBounds(108, 110, 60, 20);

		ImageIcon power = new ImageIcon("img/power1.png");
		Image powerImage = power.getImage();
		Image powerRe = powerImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		ImageIcon powerFix = new ImageIcon(powerRe);
		lblPower = new JLabel(powerFix);
		lblPower.setBounds(145, 195, 20, 20);
		
		lblCopy = new JLabel("copyright by 6조");
		lblCopy.setFont(font4);
		lblCopy.setForeground(new Color(254, 245, 253));
		lblCopy.setBounds(170, 190, 120, 30);

		pNorth.add(lblNewBook);
		pNorth.add(lblTitle);
		pCenter.add(lblName);
		pCenter.add(lblWriter);
		pCenter.add(lblCompany);
		pCenter.add(lblPower);
		pCenter.add(lblCopy);

		// 텍스트필드 생성
		tfName = new JTextField(14) {

			public void setBorder(Border border) {
			}
		};
		tfName.setBackground(new Color(255, 255, 240));
		tfName.setBounds(170, 30, 120, 20);

		tfWriter = new JTextField(30) {
			public void setBorder(Border border) {
			}
		};
		tfWriter.setBackground(new Color(255, 255, 240));
		tfWriter.setBounds(170, 70, 120, 20);

		tfCompany = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfCompany.setBackground(new Color(255, 255, 240));
		tfCompany.setBounds(170, 110, 120, 20);

		pCenter.add(tfName);
		pCenter.add(tfWriter);
		pCenter.add(tfCompany);

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

		btnAdd = new JButton("등록");
		btnAdd.setFont(font1);
		btnAdd.setFocusPainted(false);
		btnAdd.setForeground(new Color(254, 245, 253));
		btnAdd.setBackground(new Color(34, 45, 65));
		btnAdd.setBounds(130, 150, 70, 30);

		btnCancel = new JButton("취소");
		btnCancel.setFont(font1);
		btnCancel.setFocusPainted(false);
		btnCancel.setForeground(new Color(254, 245, 253));
		btnCancel.setBackground(new Color(34, 45, 65));
		btnCancel.setBounds(210, 150, 70, 30);

		pNorth.add(btnClose);
		pCenter.add(btnAdd);
		pCenter.add(btnCancel);

		// 이벤트처리
		btnClose.addActionListener(this);
		btnAdd.addActionListener(this);
		btnCancel.addActionListener(this);
	}// 생성자

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdd) {
			int returnvalue = JOptionPane.showConfirmDialog(this, "입력하신 정보로 도서를 추가하시겠습니까?", "알림",
					JOptionPane.YES_NO_OPTION);
			if (returnvalue == 0) {
				addBook();
				this.dispose();
			}
		} else if (e.getSource() == btnClose) {
			this.dispose();
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		} // X버튼, 취소버튼 처리
	}

	private void addBook() {
		System.out.println("등록버튼 클릭");
		String bookName = tfName.getText();
		System.out.println("bookName = " + bookName);
		String writer = tfWriter.getText();
		System.out.println("writer = " + writer);
		String company = tfCompany.getText();
		System.out.println("company = " + company);

		if (bookName.length() == 0) {
			JOptionPane.showMessageDialog(this, "도서명을 입력해주세요");
		} else if (writer.length() == 0) {
			JOptionPane.showMessageDialog(this, "저자명을 입력해주세요");
		} else if (company.length() == 0) {
			JOptionPane.showMessageDialog(this, "출판사명을 입력해주세요");
		}
		dto = new BookDTO();
		if (bookName.length() != 0) {
			dto.setBookName(bookName);
		}
		if (writer.length() != 0) {
			dto.setWriter(writer);
		}
		if (company.length() != 0) {
			dto.setCompany(company);
		}
		// 정보저장
		totalDAO = new TotalDAO();
		boolean ok = totalDAO.newBook(dto);

	}
}// end
