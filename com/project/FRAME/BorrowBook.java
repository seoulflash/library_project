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

public class BorrowBook extends JFrame implements ActionListener {
	String id;
	int bookNo;
	BookDTO dto = new BookDTO();
	TotalDAO totalDAO = new TotalDAO();
	JPanel pNorth, pCenter;
	JLabel lblPower, lblBorrow, lblTitle, lblNo, lblName, lblWriter, lblCompany, lblCheck, lblCopy;
	JTextField tfNo, tfName, tfWriter, tfCompany, tfCheck;
	JButton btnBorrow, btnCancel, btnClose;
	DefaultTableModel model;
	int row;

	Font font1 = new Font("나눔바른고딕", Font.PLAIN, 14);
	Font font2 = new Font("나눔스퀘어", Font.BOLD, 16);
	Font font3 = new Font("나눔바른고딕", Font.PLAIN, 30);
	Font font4 = new Font("나눔바른고딕", Font.PLAIN, 12);

	public BorrowBook(int bookNo, String id, DefaultTableModel model, int row) {
		this.id = id;
		this.bookNo = bookNo;
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

		// 라벨생성
		ImageIcon main = new ImageIcon("img/borrow.png");
		Image mainImage = main.getImage();
		Image mainRe = mainImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon mainFix = new ImageIcon(mainRe);
		lblBorrow = new JLabel(mainFix);
		lblBorrow.setBounds(105, 5, 50, 50);

		lblTitle = new JLabel(" 도 서 대 여");
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

		lblWriter = new JLabel("저자명");
		lblWriter.setFont(font1);
		lblWriter.setForeground(new Color(254, 245, 253));
		lblWriter.setBounds(120, 70, 60, 20);

		lblCompany = new JLabel("출판사명");
		lblCompany.setFont(font1);
		lblCompany.setForeground(new Color(254, 245, 253));
		lblCompany.setBounds(108, 100, 60, 20);

		lblCheck = new JLabel("대여여부");
		lblCheck.setFont(font1);
		lblCheck.setForeground(new Color(254, 245, 253));
		lblCheck.setBounds(108, 130, 60, 20);

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

		pNorth.add(lblBorrow);
		pNorth.add(lblTitle);
		pCenter.add(lblNo);
		pCenter.add(lblName);
		pCenter.add(lblWriter);
		pCenter.add(lblCompany);
		pCenter.add(lblCheck);
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

		tfWriter = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfWriter.setEditable(false);
		tfWriter.setBackground(new Color(145, 150, 160));
		tfWriter.setBounds(170, 70, 120, 20);

		tfCompany = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfCompany.setEditable(false);
		tfCompany.setBackground(new Color(145, 150, 160));
		tfCompany.setBounds(170, 100, 120, 20);

		tfCheck = new JTextField(14) {
			public void setBorder(Border border) {
			}
		};
		tfCheck.setEditable(false);
		tfCheck.setBackground(new Color(145, 150, 160));
		tfCheck.setBounds(170, 130, 120, 20);

		pCenter.add(tfNo);
		pCenter.add(tfName);
		pCenter.add(tfWriter);
		pCenter.add(tfCompany);
		pCenter.add(tfCheck);

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

		btnBorrow = new JButton("대여");
		btnBorrow.setFont(font1);
		btnBorrow.setFocusPainted(false);
		btnBorrow.setForeground(new Color(254, 245, 253));
		btnBorrow.setBackground(new Color(34, 45, 65));
		btnBorrow.setBounds(135, 160, 70, 30);

		btnCancel = new JButton("취소");
		btnCancel.setFont(font1);
		btnCancel.setFocusPainted(false);
		btnCancel.setForeground(new Color(254, 245, 253));
		btnCancel.setBackground(new Color(34, 45, 65));
		btnCancel.setBounds(215, 160, 70, 30);

		pNorth.add(btnClose);
		pCenter.add(btnBorrow);
		pCenter.add(btnCancel);

		// 이벤트처리
		btnClose.addActionListener(this);
		btnBorrow.addActionListener(this);
		btnCancel.addActionListener(this);

		// DB
		dto = totalDAO.searchBookInfo(bookNo);

		// 화면구현
		tfNo.setText("" + dto.getBookNo());
		tfName.setText(dto.getBookName());
		tfWriter.setText(dto.getWriter());
		tfCompany.setText(dto.getCompany());
		tfCheck.setText(dto.getBorrowCheck());

	}// 생성자

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBorrow) {
			if (dto.getBorrowCheck().equals("Y")) {
				JOptionPane.showMessageDialog(this, "이미 대여중인 책입니다");
			} else {
				int returnvalue = JOptionPane.showConfirmDialog(this, "대여하시겠습니까?", "알림", JOptionPane.YES_NO_OPTION);
				if (returnvalue == 0) {
					totalDAO.insertBorrowBook(dto, id);
					totalDAO.updateCheck(bookNo, false);
					model.setValueAt("Y", row, 4);
					model.fireTableDataChanged();
					totalDAO.updateBorrowNum(id, true);
					dispose();
				}
			}
		} else if (e.getSource() == btnClose) {
			this.dispose();
		} else if (e.getSource() == btnCancel) {
			this.dispose();
		} // X버튼, 취소버튼 처리
	}
} // end
