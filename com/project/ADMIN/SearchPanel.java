package com.project.ADMIN;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import com.project.FRAME.AddNewBook;
import com.project.FRAME.AdminBorrow;
import com.project.FRAME.BorrowBook;
import com.project.FRAME.MainFrame;

public class SearchPanel extends JPanel implements ActionListener, MouseListener {
	MainFrame f;
	String id;

	TotalDAO totalDAO = new TotalDAO();

	JPanel pNorth, pCenter, pSearch, pSearch2, pSearch3;
	JScrollPane sp;
	JTable tbBookSearch;
	JLabel lblBs, lblS1, lblS2, lblS3, lblSearch, lblBook, lblWriter, lblCompany;
	JComboBox<String> cbSearch;
	JTextField tfSearch, tfBook, tfWirter, tfCompany;
	JButton btnSearch, btnDetail, btnNew;

	Font font1 = new Font("나눔바른고딕", Font.PLAIN, 20);
	Font font2 = new Font("나눔바른고딕", Font.BOLD, 12);
	Font font3 = new Font("나눔바른고딕", Font.BOLD, 14);
	Font font4 = new Font("나눔바른고딕", Font.PLAIN, 14);
	Font font5 = new Font("나눔바른고딕", Font.PLAIN, 18);

	DefaultTableModel model;
	int checkDetail = 0;

	public SearchPanel(MainFrame f, String id) {
		this.f = f;
		this.id = id;
		setBackground(new Color(95, 210, 195));
		setBounds(0, 0, 850, 578);
		setLayout(null);

		nomal();
	} // 생성자

	private void nomal() {
		// 패널 생성
		pNorth = new JPanel();
		pNorth.setBackground(new Color(143, 222, 217));
		pNorth.setBounds(10, 10, 825, 110);
		pNorth.setLayout(null);

		pCenter = new JPanel();
		pCenter.setBackground(new Color(143, 222, 217));
		pCenter.setBounds(10, 130, 825, 435);
		pCenter.setLayout(null);

		pSearch = new JPanel();
		pSearch.setBackground(new Color(200, 235, 240));
		pSearch.setBounds(130, 70, 40, 30);
		pSearch.setLayout(null);

		add(pNorth);
		add(pCenter);
		pNorth.add(pSearch);

		// 라벨생성
		ImageIcon s1 = new ImageIcon("img/search3.png");
		Image s1Image = s1.getImage();
		Image s1Re = s1Image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon s1Fix = new ImageIcon(s1Re);
		lblS1 = new JLabel(s1Fix);
		lblS1.setBounds(9, 3, 25, 25);

		ImageIcon bs1 = new ImageIcon("img/search1.png");
		Image bs1Image = bs1.getImage();
		Image bs1Re = bs1Image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon bs1Fix = new ImageIcon(bs1Re);
		lblBs = new JLabel(bs1Fix);
		lblBs.setBounds(20, 10, 50, 50);

		lblSearch = new JLabel("도서 검색");
		lblSearch.setFont(font1);
		lblSearch.setBounds(80, 20, 120, 30);
		
		pSearch.add(lblS1);
		pNorth.add(lblBs);
		pNorth.add(lblSearch);

		// 콤보박스 생성
		String[] str = { "도서명", "저자명", "회사명" };
		cbSearch = new JComboBox<>(str);
		cbSearch.setFont(font3);
		cbSearch.setBackground(new Color(200, 235, 240));
		cbSearch.setBounds(20, 70, 100, 30);
		pNorth.add(cbSearch);

		// 텍스트필드 생성
		tfSearch = new JTextField(20) {
			public void setBorder(Border border) {
			}
		};
		tfSearch.setBackground(new Color(200, 235, 240));
		tfSearch.setBounds(170, 70, 390, 30);
		pNorth.add(tfSearch);

		// 버튼생성
		btnSearch = new JButton("<html>검&nbsp&nbsp&nbsp&nbsp색</html");
		btnSearch.setFocusPainted(false);
		btnSearch.setFont(font2);
		btnSearch.setForeground(new Color(254, 245, 253));
		btnSearch.setBackground(new Color(34, 45, 65));
		btnSearch.setBounds(570, 50, 70, 50);

		btnDetail = new JButton("상세검색");
		btnDetail.setFocusPainted(false);
		btnDetail.setFont(font2);
		btnDetail.setForeground(new Color(254, 245, 253));
		btnDetail.setBackground(new Color(34, 45, 65));
		btnDetail.setBounds(645, 50, 80, 50);

		btnNew = new JButton("신규등록");
		btnNew.setFocusPainted(false);
		btnNew.setFont(font2);
		btnNew.setForeground(new Color(254, 245, 253));
		btnNew.setBackground(new Color(34, 45, 65));
		btnNew.setBounds(730, 50, 80, 50);

		pNorth.add(btnSearch);
		pNorth.add(btnDetail);
		pNorth.add(btnNew);

		// 이벤트처리
		btnSearch.addActionListener(this);
		btnDetail.addActionListener(this);
		btnNew.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Vector datas = new Vector();

		if (e.getSource() == btnNew) {
			AddNewBook a = new AddNewBook();
		}
		// 간편 검색 이벤트결과 출력
		else if (e.getSource() == btnSearch) {
			pCenter.removeAll();
			pCenter.invalidate();
			pCenter.validate();
			pCenter.repaint();

			if (checkDetail == 0) {
				String combo = (String) cbSearch.getSelectedItem();
				String search = tfSearch.getText();

				datas = totalDAO.searchBook(search, combo);
				if (search.length() == 0) {
					JOptionPane.showMessageDialog(this, "검색어를 입력해주세요");
					return;
				}
				// 테이블 생성
				Vector cols = new Vector();
				cols.add("도서번호");
				cols.add("도서명");
				cols.add("저자명");
				cols.add("출판사명");
				cols.add("대여여부");
				model = new DefaultTableModel(datas, cols) {
					public boolean isCellEditable(int i, int c) {
						return false;
					}
				};
				model.fireTableDataChanged();
				tbBookSearch = new JTable(model);
				tbBookSearch.setRowHeight(20);
				tbBookSearch.setRowMargin(2);
				tbBookSearch.setFont(font4);
				JTableHeader tableHeader = tbBookSearch.getTableHeader();
				tableHeader.setBackground(new Color(210, 231, 255));
				tableHeader.setReorderingAllowed(false);
				tableHeader.setResizingAllowed(false);
				tbBookSearch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				// 셀내용 정렬위치
				DefaultTableCellRenderer Center = new DefaultTableCellRenderer();
				Center.setHorizontalAlignment(JLabel.CENTER);

				tbBookSearch.getColumn("도서번호").setPreferredWidth(10);
				tbBookSearch.getColumn("도서번호").setCellRenderer(Center);
				tbBookSearch.getColumn("도서명").setPreferredWidth(200);
				tbBookSearch.getColumn("도서명").setCellRenderer(Center);
				tbBookSearch.getColumn("저자명").setPreferredWidth(50);
				tbBookSearch.getColumn("저자명").setCellRenderer(Center);
				tbBookSearch.getColumn("출판사명").setPreferredWidth(100);
				tbBookSearch.getColumn("출판사명").setCellRenderer(Center);
				tbBookSearch.getColumn("대여여부").setPreferredWidth(10);
				tbBookSearch.getColumn("대여여부").setCellRenderer(Center);

				tbBookSearch.addMouseListener(this);

				// 스크롤팬 생성
				sp = new JScrollPane(tbBookSearch);
				sp.getViewport().setBackground(new Color(227, 240, 255));
				sp.setBounds(10, 10, 805, 415);

				pCenter.add(sp);

				// 상세 검색 이벤트결과 출력
			} else if (checkDetail == 1) {
				String search1 = tfBook.getText();
				String search2 = tfWirter.getText();
				String search3 = tfCompany.getText();
				TotalDAO totalDAO = new TotalDAO();
				datas = totalDAO.searchBookDetail(search1, search2, search3);
				if (search1.length() == 0 && search2.length() == 0 && search3.length() == 0) {
					JOptionPane.showMessageDialog(this, "검색어를 입력해주세요");
					return;
				}
				// 테이블 생성
				Vector cols = new Vector();
				cols.add("도서번호");
				cols.add("도서명");
				cols.add("저자명");
				cols.add("출판사명");
				cols.add("대여여부");
				model = new DefaultTableModel(datas, cols) {
					public boolean isCellEditable(int i, int c) {
						return false;
					}
				};
				tbBookSearch = new JTable(model);
				tbBookSearch.setRowHeight(20);
				tbBookSearch.setRowMargin(2);
				tbBookSearch.setFont(font4);
				JTableHeader tableHeader = tbBookSearch.getTableHeader();
				tableHeader.setBackground(new Color(210, 231, 255));
				tbBookSearch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				tbBookSearch.addMouseListener(this);

				// 셀내용 정렬위치
				DefaultTableCellRenderer Center = new DefaultTableCellRenderer();
				Center.setHorizontalAlignment(JLabel.CENTER);

				tbBookSearch.getColumn("도서번호").setPreferredWidth(10);
				tbBookSearch.getColumn("도서번호").setCellRenderer(Center);
				tbBookSearch.getColumn("도서명").setPreferredWidth(200);
				tbBookSearch.getColumn("도서명").setCellRenderer(Center);
				tbBookSearch.getColumn("저자명").setPreferredWidth(50);
				tbBookSearch.getColumn("저자명").setCellRenderer(Center);
				tbBookSearch.getColumn("출판사명").setPreferredWidth(100);
				tbBookSearch.getColumn("출판사명").setCellRenderer(Center);
				tbBookSearch.getColumn("대여여부").setPreferredWidth(10);
				tbBookSearch.getColumn("대여여부").setCellRenderer(Center);

				// 스크롤팬 생성
				sp = new JScrollPane(tbBookSearch);
				sp.getViewport().setBackground(new Color(227, 240, 255));
				sp.setBounds(10, 10, 805, 335);

				pCenter.add(sp);
			}
		}
		// 상세검색 버튼 화면 출력
		else if (e.getSource() == btnDetail) {
			if (checkDetail == 0) {
				this.removeAll();
				setBackground(new Color(95, 210, 195));
				setBounds(0, 0, 850, 578);
				setLayout(null);

				// 패널 생성
				pNorth = new JPanel();
				pNorth.setBackground(new Color(143, 222, 217));
				pNorth.setBounds(10, 10, 825, 190);
				pNorth.setLayout(null);

				pCenter = new JPanel();
				pCenter.setBackground(new Color(143, 222, 217));
				pCenter.setBounds(10, 210, 825, 355);
				pCenter.setLayout(null);

				pSearch = new JPanel();
				pSearch.setBackground(new Color(200, 235, 240));
				pSearch.setBounds(130, 70, 40, 30);
				pSearch.setLayout(null);
				
				pSearch2 = new JPanel();
				pSearch2.setBackground(new Color(200, 235, 240));
				pSearch2.setBounds(130, 110, 40, 30);
				pSearch2.setLayout(null);
				
				pSearch3 = new JPanel();
				pSearch3.setBackground(new Color(200, 235, 240));
				pSearch3.setBounds(130, 150, 40, 30);
				pSearch3.setLayout(null);
				
				add(pNorth);
				add(pCenter);
				pNorth.add(pSearch);
				pNorth.add(pSearch2);
				pNorth.add(pSearch3);
				
				// 라벨생성
				ImageIcon s1 = new ImageIcon("img/search3.png");
				Image s1Image = s1.getImage();
				Image s1Re = s1Image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
				ImageIcon s1Fix = new ImageIcon(s1Re);
				lblS1 = new JLabel(s1Fix);
				lblS1.setBounds(9, 3, 25, 25);
				
				ImageIcon s2 = new ImageIcon("img/search3.png");
				Image s2Image = s2.getImage();
				Image s2Re = s2Image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
				ImageIcon s2Fix = new ImageIcon(s2Re);
				lblS2 = new JLabel(s2Fix);
				lblS2.setBounds(9, 3, 25, 25);
				
				ImageIcon s3 = new ImageIcon("img/search3.png");
				Image s3Image = s3.getImage();
				Image s3Re = s3Image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
				ImageIcon s3Fix = new ImageIcon(s3Re);
				lblS3 = new JLabel(s3Fix);
				lblS3.setBounds(9, 3, 25, 25);
				
				ImageIcon bs1 = new ImageIcon("img/search1.png");
				Image bs1Image = bs1.getImage();
				Image bs1Re = bs1Image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				ImageIcon bs1Fix = new ImageIcon(bs1Re);
				lblBs = new JLabel(bs1Fix);
				lblBs.setBounds(20, 10, 50, 50);

				lblSearch = new JLabel("도서 검색");
				lblSearch.setFont(font1);
				lblSearch.setBounds(80, 20, 120, 30);

				lblBook = new JLabel("도서명");
				lblBook.setFont(font4);
				lblBook.setBounds(68, 70, 100, 30);

				lblWriter = new JLabel("저자명");
				lblWriter.setFont(font4);
				lblWriter.setBounds(68, 110, 100, 30);

				lblCompany = new JLabel("회사명");
				lblCompany.setFont(font4);
				lblCompany.setBounds(68, 150, 100, 30);

				pSearch.add(lblS1);
				pSearch2.add(lblS2);
				pSearch3.add(lblS3);
				pNorth.add(lblBs);
				pNorth.add(lblSearch);
				pNorth.add(lblBook);
				pNorth.add(lblWriter);
				pNorth.add(lblCompany);

				// 텍스트 필드생성
				tfBook = new JTextField(20) {
					public void setBorder(Border border) {
					}
				};
				tfBook.setBackground(new Color(200, 235, 240));
				tfBook.setBounds(170, 70, 390, 30);

				tfWirter = new JTextField(20) {
					public void setBorder(Border border) {
					}
				};
				tfWirter.setBackground(new Color(200, 235, 240));
				tfWirter.setBounds(170, 110, 390, 30);

				tfCompany = new JTextField(20) {
					public void setBorder(Border border) {
					}
				};
				tfCompany.setBackground(new Color(200, 235, 240));
				tfCompany.setBounds(170, 150, 390, 30);

				pNorth.add(tfBook);
				pNorth.add(tfWirter);
				pNorth.add(tfCompany);

				// 버튼 생성
				btnSearch = new JButton("<html>검&nbsp&nbsp&nbsp&nbsp색</html");
				btnSearch.setFocusPainted(false);
				btnSearch.setFont(font2);
				btnSearch.setForeground(new Color(254, 245, 253));
				btnSearch.setBackground(new Color(34, 45, 65));
				btnSearch.setBounds(570, 50, 70, 50);

				btnDetail = new JButton("간편검색");
				btnDetail.setFocusPainted(false);
				btnDetail.setFont(font2);
				btnDetail.setForeground(new Color(254, 245, 253));
				btnDetail.setBackground(new Color(34, 45, 65));
				btnDetail.setBounds(645, 50, 80, 50);

				btnNew = new JButton("신규등록");
				btnNew.setFocusPainted(false);
				btnNew.setFont(font2);
				btnNew.setForeground(new Color(254, 245, 253));
				btnNew.setBackground(new Color(34, 45, 65));
				btnNew.setBounds(730, 50, 80, 50);

				pNorth.add(btnSearch);
				pNorth.add(btnDetail);
				pNorth.add(btnNew);

				// 이벤트처리
				btnSearch.addActionListener(this);
				btnDetail.addActionListener(this);
				btnNew.addActionListener(this);

				this.invalidate();
				this.validate();
				this.repaint();

				checkDetail = 1;
				// 간편검색 화면출력
			} else if (checkDetail == 1) {
				this.removeAll();
				nomal();
				this.invalidate();
				this.validate();
				this.repaint();
				checkDetail = 0;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int row = tbBookSearch.getSelectedRow();
			int column = tbBookSearch.getSelectedColumn();

			int bookNo = (int) model.getValueAt(row, 0);
			System.out.println(row + "행 " + column + "열 " + bookNo + " 선택했음");
			AdminBorrow b = new AdminBorrow(bookNo, id, model, row);
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
