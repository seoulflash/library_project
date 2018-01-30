package com.project.ADMIN;

import java.sql.Date;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.project.DTO.TotalDAO;
import com.project.DTO.UserDTO;
import com.project.FRAME.MainFrame;
import com.project.FRAME.PwdFrame;
import com.project.FRAME.ReturnDueBook;

public class InfoPanel extends JPanel implements ActionListener, MouseListener {
	MainFrame f;
	UserDTO dto = new UserDTO();
	TotalDAO totalDAO = new TotalDAO();

	JPanel pNorth, pSouth;
	JScrollPane sp;
	JTable tbInfoSearch;
	JLabel lblTitle, lblId, lblName, lblPhone, lblJdate, lblBlock, lblBorrow, lblDelay;
	JLabel lblPhone1, lblPhone2;
	JLabel lblId1;
	JTextField tfId, tfName, tfPhone1, tfPhone2, tfJdate, tfBlock, tfBorrow, tfDelay;
	JComboBox<String> cbPhone;
	JButton btnInfo, btnPwd, btnShowme, btnShowReturn;
	String id;

	Font font1 = new Font("나눔바른고딕", Font.PLAIN, 20);
	Font font2 = new Font("나눔바른고딕", Font.BOLD, 12);
	Font font3 = new Font("나눔바른고딕", Font.BOLD, 14);
	Font font4 = new Font("나눔바른고딕", Font.PLAIN, 14);

	DefaultTableModel model;
	int checkInfo = 0;

	public InfoPanel(MainFrame f, String id) {
		this.f = f;
		this.id = id;
		setBackground(new Color(95, 210, 195));
		setBounds(0, 0, 850, 578);
		setLayout(null);

		// 패널 생성
		pNorth = new JPanel();
		pNorth.setBackground(new Color(143, 222, 217));
		pNorth.setBounds(10, 10, 825, 215);

		pSouth = new JPanel();
		pSouth.setBackground(new Color(143, 222, 217));
		pSouth.setBounds(10, 235, 825, 330);

		add(pNorth);
		add(pSouth);

		pNorth.setLayout(null);
		pSouth.setLayout(null);

		// 라벨생성
		ImageIcon id1 = new ImageIcon("img/id1.png");
		Image id1Image = id1.getImage();
		Image id1Re = id1Image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon id1Fix = new ImageIcon(id1Re);
		lblId1 = new JLabel(id1Fix);
		lblId1.setBounds(10, 10, 40, 40);

		lblTitle = new JLabel("관리자 정보");
		lblTitle.setFont(font1);
		lblTitle.setBounds(55, 15, 120, 30);

		pNorth.add(lblId1);
		pNorth.add(lblTitle);
		lblId = new JLabel("회원 ID");
		lblId.setFont(font4);
		lblId.setBounds(63, 50, 100, 30);

		lblJdate = new JLabel("가입일자");
		lblJdate.setFont(font4);
		lblJdate.setBounds(54, 90, 100, 30);

		lblBorrow = new JLabel("대여권수");
		lblBorrow.setFont(font4);
		lblBorrow.setBounds(54, 130, 100, 30);

		lblPhone = new JLabel("전화번호");
		lblPhone.setFont(font4);
		lblPhone.setBounds(54, 170, 100, 30);

		lblPhone1 = new JLabel("-");
		lblPhone1.setFont(font4);
		lblPhone1.setBounds(205, 170, 30, 30);

		lblPhone2 = new JLabel("-");
		lblPhone2.setFont(font4);
		lblPhone2.setBounds(290, 170, 30, 30);

		lblName = new JLabel("회원이름");
		lblName.setFont(font4);
		lblName.setBounds(400, 50, 100, 30);

		lblBlock = new JLabel("정지기간");
		lblBlock.setFont(font4);
		lblBlock.setBounds(400, 90, 100, 30);

		lblDelay = new JLabel("연체권수");
		lblDelay.setFont(font4);
		lblDelay.setBounds(400, 130, 100, 30);

		pNorth.add(lblId);
		pNorth.add(lblJdate);
		pNorth.add(lblBorrow);
		pNorth.add(lblPhone);
		pNorth.add(lblPhone1);
		pNorth.add(lblPhone2);
		pNorth.add(lblName);
		pNorth.add(lblBlock);
		pNorth.add(lblDelay);

		// 텍스트필드 생성
		tfId = new JTextField() {
			public void setBorder(Border border) {
			}
		};
		tfId.setEditable(false);
		tfId.setBackground(new Color(200, 235, 240));
		tfId.setBounds(130, 50, 240, 30);

		tfJdate = new JTextField() {
			public void setBorder(Border border) {
			}
		};
		tfJdate.setEditable(false);
		tfJdate.setBackground(new Color(200, 235, 240));
		tfJdate.setBounds(130, 90, 240, 30);

		tfBorrow = new JTextField() {
			public void setBorder(Border border) {
			}
		};
		tfBorrow.setEditable(false);
		tfBorrow.setBackground(new Color(200, 235, 240));
		tfBorrow.setBounds(130, 130, 240, 30);

		tfPhone1 = new JTextField() {
			public void setBorder(Border border) {
			}
		};
		tfPhone1.setEditable(false);
		tfPhone1.setBackground(new Color(200, 235, 240));
		tfPhone1.setBounds(215, 170, 70, 30);

		tfPhone2 = new JTextField() {
			public void setBorder(Border border) {
			}
		};
		tfPhone2.setEditable(false);
		tfPhone2.setBackground(new Color(200, 235, 240));
		tfPhone2.setBounds(300, 170, 70, 30);

		tfName = new JTextField() {
			public void setBorder(Border border) {
			}
		};
		tfName.setEditable(false);
		tfName.setBackground(new Color(200, 235, 240));
		tfName.setBounds(475, 50, 240, 30);

		tfBlock = new JTextField() {
			public void setBorder(Border border) {
			}
		};
		tfBlock.setEditable(false);
		tfBlock.setBackground(new Color(200, 235, 240));
		tfBlock.setBounds(475, 90, 240, 30);

		tfDelay = new JTextField() {
			public void setBorder(Border border) {
			}
		};
		tfDelay.setEditable(false);
		tfDelay.setBackground(new Color(200, 235, 240));
		tfDelay.setBounds(475, 130, 240, 30);

		pNorth.add(tfId);
		pNorth.add(tfJdate);
		pNorth.add(tfBorrow);
		pNorth.add(tfPhone1);
		pNorth.add(tfPhone2);
		pNorth.add(tfName);
		pNorth.add(tfBlock);
		pNorth.add(tfDelay);

		// 콤보박스 생성
		String[] str = { "010", "011", "016", "017", "018", "019" };
		cbPhone = new JComboBox<>(str);
		cbPhone.setFont(font3);
		cbPhone.setEditable(false);
		cbPhone.setBackground(new Color(200, 235, 240));
		cbPhone.setBounds(130, 170, 70, 30);

		pNorth.add(cbPhone);

		// 버튼 생성
		btnInfo = new JButton("정보수정");
		btnInfo.setFocusPainted(false);
		btnInfo.setFont(font2);
		btnInfo.setForeground(new Color(254, 245, 253));
		btnInfo.setBackground(new Color(34, 45, 65));
		btnInfo.setBounds(410, 170, 80, 35);

		btnPwd = new JButton("<html>비밀번호<br/>&nbsp&nbsp변&nbsp경</html>");
		btnPwd.setFocusPainted(false);
		btnPwd.setFont(font2);
		btnPwd.setForeground(new Color(254, 245, 253));
		btnPwd.setBackground(new Color(34, 45, 65));
		btnPwd.setBounds(500, 170, 80, 35);

		btnShowme = new JButton("대여정보");
		btnShowme.setFocusPainted(false);
		btnShowme.setFont(font2);
		btnShowme.setForeground(new Color(254, 245, 253));
		btnShowme.setBackground(new Color(255, 51, 119));
		btnShowme.setBounds(590, 170, 80, 35);

		btnShowReturn = new JButton("반납정보");
		btnShowReturn.setFocusPainted(false);
		btnShowReturn.setFont(font2);
		btnShowReturn.setForeground(new Color(254, 245, 253));
		btnShowReturn.setBackground(new Color(255, 51, 119));
		btnShowReturn.setBounds(680, 170, 80, 35);

		pNorth.add(btnInfo);
		pNorth.add(btnPwd);
		pNorth.add(btnShowme);
		pNorth.add(btnShowReturn);

		// DB
		totalDAO = new TotalDAO();
		dto = totalDAO.searchUser(id);

		// DTO -> 화면
		tfId.setText(dto.getId());
		tfName.setText(dto.getName());
		tfJdate.setText("" + dto.getRegistdate());
		tfBlock.setText("" + dto.getLimitDate());
		tfBorrow.setText("" + dto.getBorrowBookNum());
		tfDelay.setText("" + dto.getOverDueBookNum());
		String phone = dto.getPhone();
		String[] phones = phone.split("-");
		String phone1 = phones[0];
		String phone2 = phones[1];
		String phone3 = phones[2];
		cbPhone.setSelectedItem(phone1);
		tfPhone1.setText(phone2);
		tfPhone2.setText(phone3);

		// 이벤트처리
		btnInfo.addActionListener(this);
		btnPwd.addActionListener(this);
		btnShowme.addActionListener(this);
		btnShowReturn.addActionListener(this);
	}// 생성자

	@Override
	public void actionPerformed(ActionEvent e) {
		Vector datas = new Vector();

		// 정보수정
		if (e.getSource() == btnInfo) {
			if (checkInfo == 0) {
				tfName.setEditable(true);
				cbPhone.setEditable(true);
				tfPhone1.setEditable(true);
				tfPhone2.setEditable(true);
				tfName.setBackground(new Color(255, 255, 245));
				cbPhone.setBackground(new Color(255, 255, 245));
				tfPhone1.setBackground(new Color(255, 255, 245));
				tfPhone2.setBackground(new Color(255, 255, 245));

				checkInfo = 1;
			} else if (checkInfo == 1) {
				repaint();
				String name = tfName.getText();
				String phone1 = (String) cbPhone.getSelectedItem();
				String phone2 = tfPhone1.getText();
				String phone3 = tfPhone2.getText();
				String phone = phone1 + "-" + phone2 + "-" + phone3;
				System.out.println(name);
				System.out.println(phone);

				dto.setId(id);
				dto.setName(name);
				dto.setPhone(phone);

				totalDAO = new TotalDAO();
				boolean ok = totalDAO.updateInfo(dto);

				tfName.setEditable(false);
				cbPhone.setEditable(false);
				tfPhone1.setEditable(false);
				tfPhone2.setEditable(false);
				tfName.setBackground(new Color(234, 244, 255, 150));
				cbPhone.setBackground(new Color(234, 244, 255, 100));
				tfPhone1.setBackground(new Color(234, 244, 255, 100));
				tfPhone2.setBackground(new Color(234, 244, 255, 100));

				checkInfo = 0;
			}

		} else if (e.getSource() == btnPwd) {
			PwdFrame f = new PwdFrame(id);
		} else if (e.getSource() == btnShowme) {
			pSouth.removeAll();
			pSouth.invalidate();
			pSouth.validate();
			pSouth.repaint();

			datas = totalDAO.myBorrowInfo(id);

			// 테이블 생성
			Vector cols = new Vector();
			cols.add("도서번호");
			cols.add("도서명");
			cols.add("대여 날짜");
			cols.add("반납 기한");
			cols.add("연체 기간");
			model = new DefaultTableModel(datas, cols) {
				public boolean isCellEditable(int i, int c) {
					return false;
				}
			};
			tbInfoSearch = new JTable(model);
			tbInfoSearch.setRowHeight(20);
			tbInfoSearch.setRowMargin(2);
			tbInfoSearch.setFont(font4);
			JTableHeader tableHeader = tbInfoSearch.getTableHeader();
			tableHeader.setBackground(new Color(210, 231, 255));
			tableHeader.setReorderingAllowed(false);
			tableHeader.setResizingAllowed(false);
			tbInfoSearch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			// 셀내용 정렬위치
			DefaultTableCellRenderer Center = new DefaultTableCellRenderer();
			Center.setHorizontalAlignment(JLabel.CENTER);

			tbInfoSearch.getColumn("도서번호").setPreferredWidth(10);
			tbInfoSearch.getColumn("도서번호").setCellRenderer(Center);
			tbInfoSearch.getColumn("도서명").setPreferredWidth(100);
			tbInfoSearch.getColumn("도서명").setCellRenderer(Center);
			tbInfoSearch.getColumn("대여 날짜").setPreferredWidth(100);
			tbInfoSearch.getColumn("대여 날짜").setCellRenderer(Center);
			tbInfoSearch.getColumn("반납 기한").setPreferredWidth(100);
			tbInfoSearch.getColumn("반납 기한").setCellRenderer(Center);
			tbInfoSearch.getColumn("연체 기간").setPreferredWidth(10);
			tbInfoSearch.getColumn("연체 기간").setCellRenderer(Center);

			// 테이블 이벤트처리
			tbInfoSearch.addMouseListener(this);

			// 스크롤패널 생성
			sp = new JScrollPane(tbInfoSearch);
			sp.getViewport().setBackground(new Color(227, 240, 255));
			sp.setBounds(10, 10, 805, 310);

			pSouth.add(sp);
		} else if (e.getSource() == btnShowReturn) {
			pSouth.removeAll();
			pSouth.invalidate();
			pSouth.validate();
			pSouth.repaint();

			datas = totalDAO.myReturnInfo(id);

			// 테이블 생성
			Vector cols = new Vector();
			cols.add("도서번호");
			cols.add("도서명");
			cols.add("대여 날짜");
			cols.add("반납 기한");
			cols.add("최종연체일");
			model = new DefaultTableModel(datas, cols) {
				public boolean isCellEditable(int i, int c) {
					return false;
				}
			};
			tbInfoSearch = new JTable(model);
			tbInfoSearch.setRowHeight(20);
			tbInfoSearch.setRowMargin(2);
			tbInfoSearch.setFont(font4);
			JTableHeader tableHeader = tbInfoSearch.getTableHeader();
			tableHeader.setBackground(new Color(210, 231, 255));
			tableHeader.setReorderingAllowed(false);
			tableHeader.setResizingAllowed(false);
			tbInfoSearch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			// 셀내용 정렬위치
			DefaultTableCellRenderer Center = new DefaultTableCellRenderer();
			Center.setHorizontalAlignment(JLabel.CENTER);

			tbInfoSearch.getColumn("도서번호").setPreferredWidth(10);
			tbInfoSearch.getColumn("도서번호").setCellRenderer(Center);
			tbInfoSearch.getColumn("도서명").setPreferredWidth(100);
			tbInfoSearch.getColumn("도서명").setCellRenderer(Center);
			tbInfoSearch.getColumn("대여 날짜").setPreferredWidth(100);
			tbInfoSearch.getColumn("대여 날짜").setCellRenderer(Center);
			tbInfoSearch.getColumn("반납 기한").setPreferredWidth(100);
			tbInfoSearch.getColumn("반납 기한").setCellRenderer(Center);
			tbInfoSearch.getColumn("최종연체일").setPreferredWidth(10);
			tbInfoSearch.getColumn("최종연체일").setCellRenderer(Center);

			// 스크롤패널 생성
			sp = new JScrollPane(tbInfoSearch);
			sp.getViewport().setBackground(new Color(227, 240, 255));
			sp.setBounds(10, 10, 805, 310);

			pSouth.add(sp);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int row = tbInfoSearch.getSelectedRow();
			int column = tbInfoSearch.getSelectedColumn();

			int bookNo = (int) model.getValueAt(row, 0);
			ReturnDueBook r = new ReturnDueBook(bookNo, id, model, row, tfBorrow);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}// end
