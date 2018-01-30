package com.project.ADMIN;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.project.DTO.TotalDAO;
import com.project.FRAME.AddNewUser;
import com.project.FRAME.AdminUser;
import com.project.FRAME.MainFrame;

public class MemberPanel extends JPanel implements ActionListener, MouseListener {
	MainFrame f;
	String id;

	TotalDAO totalDAO = new TotalDAO();
	DefaultTableModel model;

	JPanel pNorth, pCenter, pCheck1, pCheck2, pSearch, pSearch2, pSearch3;
	JScrollPane sp;
	JTable tbMemberSearch;
	JLabel lblBs, lblS1, lblS2, lblS3, lblSearch, lblId, lblName, lblNum, lblBlack, lblBorrow, lblAdmin;
	JComboBox<String> cbSearch;
	JTextField tfSearch, tfId, tfName, tfNum;
	JButton btnSearch, btnDetail, btnShort, btnNew;
	JCheckBox checkId, checkName, checkNum, checkIdAll, checkNameAll, checkNumAll;
	JRadioButton rbB1, rbB2, rbB3, rbBookl, rbBook2, rbBook3, rbA1, rbA2, rbA3;

	Font font1 = new Font("나눔바른고딕", Font.PLAIN, 20);
	Font font2 = new Font("나눔바른고딕", Font.BOLD, 12);
	Font font3 = new Font("나눔바른고딕", Font.BOLD, 14);
	Font font4 = new Font("나눔바른고딕", Font.PLAIN, 14);
	Font font5 = new Font("나눔바른고딕", Font.PLAIN, 15);

	int checkDetail = 0;

	public MemberPanel(MainFrame f, String id) {
		this.f = f;
		this.id = id;
		setBackground(new Color(95, 210, 195));
		setBounds(0, 0, 850, 578);
		setLayout(null);

		nomal();
	}// 생성자

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

		ImageIcon bs1 = new ImageIcon("img/usersearch.png");
		Image bs1Image = bs1.getImage();
		Image bs1Re = bs1Image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon bs1Fix = new ImageIcon(bs1Re);
		lblBs = new JLabel(bs1Fix);
		lblBs.setBounds(20, 10, 50, 50);
		
		lblSearch = new JLabel("회원 관리");
		lblSearch.setFont(font1);
		lblSearch.setBounds(80, 20, 120, 30);
		
		pSearch.add(lblS1);
		pNorth.add(lblBs);
		pNorth.add(lblSearch);

		// 콤보박스 생성
		String[] str = { "회원 ID", "회원이름", "전화번호" };
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
			AddNewUser a = new AddNewUser();
		} else if (e.getSource() == btnSearch) {
			pCenter.removeAll();
			pCenter.invalidate();
			pCenter.validate();
			pCenter.repaint();

			if (checkDetail == 0) {
				System.out.println("panel1");
				String combo = (String) cbSearch.getSelectedItem();
				String search = tfSearch.getText();

				datas = totalDAO.searchUser(search, combo);

				if (search.length() == 0) {
					JOptionPane.showMessageDialog(this, "검색어를 입력해주세요");
					return;
				}
				// 테이블 생성
				Vector cols = new Vector();
				cols.add("회원 ID");
				cols.add("회원이름");
				cols.add("전화번호");
				cols.add("대여권수");
				cols.add("연체권수");
				cols.add("가입일");
				cols.add("정지기한");
				cols.add("관리자권한");

				model = new DefaultTableModel(datas, cols) {
					public boolean isCellEditable(int i, int c) {
						return false;
					}
				};
				model.fireTableDataChanged();
				tbMemberSearch = new JTable(model);
				tbMemberSearch.setRowHeight(20);
				tbMemberSearch.setRowMargin(2);
				tbMemberSearch.setFont(font4);
				JTableHeader tableheader = tbMemberSearch.getTableHeader();
				tableheader.setBackground(new Color(210, 231, 255));
				tableheader.setReorderingAllowed(false);
				tableheader.setResizingAllowed(false);
				tbMemberSearch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				// 셀내용 정렬위치
				DefaultTableCellRenderer Center = new DefaultTableCellRenderer();
				Center.setHorizontalAlignment(JLabel.CENTER);

				tbMemberSearch.getColumn("회원 ID").setPreferredWidth(10);
				tbMemberSearch.getColumn("회원 ID").setCellRenderer(Center);
				tbMemberSearch.getColumn("회원이름").setPreferredWidth(50);
				tbMemberSearch.getColumn("회원이름").setCellRenderer(Center);
				tbMemberSearch.getColumn("전화번호").setPreferredWidth(100);
				tbMemberSearch.getColumn("전화번호").setCellRenderer(Center);
				tbMemberSearch.getColumn("대여권수").setPreferredWidth(10);
				tbMemberSearch.getColumn("대여권수").setCellRenderer(Center);
				tbMemberSearch.getColumn("연체권수").setPreferredWidth(10);
				tbMemberSearch.getColumn("연체권수").setCellRenderer(Center);
				tbMemberSearch.getColumn("가입일").setPreferredWidth(100);
				tbMemberSearch.getColumn("가입일").setCellRenderer(Center);
				tbMemberSearch.getColumn("정지기한").setPreferredWidth(100);
				tbMemberSearch.getColumn("정지기한").setCellRenderer(Center);
				tbMemberSearch.getColumn("관리자권한").setPreferredWidth(10);
				tbMemberSearch.getColumn("관리자권한").setCellRenderer(Center);
				
				tbMemberSearch.addMouseListener(this);

				// 스크롤팬 생성
				sp = new JScrollPane(tbMemberSearch);
				sp.getViewport().setBackground(new Color(227, 240, 255));
				sp.setBounds(10, 10, 805, 415);

				pCenter.add(sp);
				
			} else {
				
				// 전체 검색
				String strId = (tfId.getText().length() == 0) ? "N" : tfId.getText();
				String strName = (tfName.getText().length() == 0) ? "N" : tfName.getText();
				String strPhone = (tfNum.getText().length() == 0) ? "N" : tfNum.getText();
				
				// ID, 이름, 전화번호 check 확인 후 query 생성
				String objId = (!checkId.isSelected()) ? "N" : "Y";
				String objName = (!checkName.isSelected()) ? "N" : "Y";
				String objPhone = (!checkNum.isSelected()) ? "N" : "Y";
				
				//연체자, 대여유무, 관리자유무 확인 후 쿼리 생성
				String rbOverDue = (rbB1.isSelected()) ? "N" : (rbB2.isSelected() ? "NY" : "Y");
				String rbBorrow = (rbBookl.isSelected()) ? "N" : (rbBook2.isSelected() ? "NY" : "Y");
				String rbAdmin = (rbA1.isSelected()) ? "N" : (rbA2.isSelected() ? "NY" : "Y");
				
				String[] str1 = new String[3];
				String[] str2 = new String[3];
				String[] str3 = new String[3];
				
				str1[0] = strId;
				str1[1] = strName;
				str1[2] = strPhone;
				str2[0] = objId;
				str2[1] = objName;
				str2[2] = objPhone;
				str3[0] = rbOverDue;
				str3[1] = rbBorrow;
				str3[2] = rbAdmin;
				
				
				datas = totalDAO.searchUserDetail(str1, str2, str3);
				String errorCatch = datas.get(0).toString();
				if(errorCatch.equals("thisIsNull")) {
					JOptionPane.showMessageDialog(this, "값이 없습니다");
					pCenter.removeAll();
					pCenter.invalidate();
					pCenter.validate();
					pCenter.repaint();
					return;
				}
			
			// 테이블 출력
			Vector cols = new Vector();
			cols.add("회원 ID");
			cols.add("회원이름");
			cols.add("전화번호");
			cols.add("대여권수");
			cols.add("연체권수");
			cols.add("가입일");
			cols.add("이용제한기간");
			cols.add("관리자유무");
			
			model = new DefaultTableModel(datas, cols) {
				public boolean isCellEditable(int i, int c) {
					return false;
				}
			};
			model.fireTableDataChanged();
			tbMemberSearch = new JTable(model);
			tbMemberSearch.setRowHeight(20);
			tbMemberSearch.setRowMargin(2);
			tbMemberSearch.setFont(font4);
			JTableHeader tableheader = tbMemberSearch.getTableHeader();
			tableheader.setBackground(new Color(210, 231, 255));
			tableheader.setReorderingAllowed(false);
			tableheader.setResizingAllowed(false);
			tbMemberSearch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			// 셀내용 정렬위치
			DefaultTableCellRenderer Center = new DefaultTableCellRenderer();
			Center.setHorizontalAlignment(JLabel.CENTER);

			tbMemberSearch.getColumn("회원 ID").setPreferredWidth(10);
			tbMemberSearch.getColumn("회원 ID").setCellRenderer(Center);
			tbMemberSearch.getColumn("회원이름").setPreferredWidth(50);
			tbMemberSearch.getColumn("회원이름").setCellRenderer(Center);
			tbMemberSearch.getColumn("전화번호").setPreferredWidth(100);
			tbMemberSearch.getColumn("전화번호").setCellRenderer(Center);
			tbMemberSearch.getColumn("대여권수").setPreferredWidth(10);
			tbMemberSearch.getColumn("대여권수").setCellRenderer(Center);
			tbMemberSearch.getColumn("연체권수").setPreferredWidth(10);
			tbMemberSearch.getColumn("연체권수").setCellRenderer(Center);
			tbMemberSearch.getColumn("가입일").setPreferredWidth(100);
			tbMemberSearch.getColumn("가입일").setCellRenderer(Center);
			tbMemberSearch.getColumn("이용제한기간").setPreferredWidth(100);
			tbMemberSearch.getColumn("이용제한기간").setCellRenderer(Center);
			tbMemberSearch.getColumn("관리자유무").setPreferredWidth(10);
			tbMemberSearch.getColumn("관리자유무").setCellRenderer(Center);
			
			tbMemberSearch.addMouseListener(this);

			// 스크롤팬 생성
			sp = new JScrollPane(tbMemberSearch);
			sp.getViewport().setBackground(new Color(227, 240, 255));
			sp.setBounds(10, 10, 805, 415);

			pCenter.add(sp);
			
			}	
		} else if (e.getSource() == btnDetail) {
			if (checkDetail == 0) {
				this.removeAll();
				this.invalidate();
				this.validate();
				this.repaint();
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

				pCheck1 = new JPanel();
				pCheck1.setBackground(new Color(192,230,215));
				pCheck1.setBounds(410, 40, 300, 140);
				pCheck1.setLayout(null);

				pCheck2 = new JPanel();
				pCheck2.setBackground(new Color(216,248,199));
				pCheck2.setBounds(20, 95, 260, 35);
				pCheck2.setLayout(null);
				
				pSearch = new JPanel();
				pSearch.setBackground(new Color(200, 235, 240));
				pSearch.setBounds(90, 70, 40, 30);
				pSearch.setLayout(null);
				
				pSearch2 = new JPanel();
				pSearch2.setBackground(new Color(200, 235, 240));
				pSearch2.setBounds(90, 110, 40, 30);
				pSearch2.setLayout(null);
				
				pSearch3 = new JPanel();
				pSearch3.setBackground(new Color(200, 235, 240));
				pSearch3.setBounds(90, 150, 40, 30);
				pSearch3.setLayout(null);

				add(pNorth);
				add(pCenter);
				pNorth.add(pSearch);
				pNorth.add(pSearch2);
				pNorth.add(pSearch3);
				pNorth.add(pCheck1);
				pCheck1.add(pCheck2);

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
				
				ImageIcon bs1 = new ImageIcon("img/usersearch.png");
				Image bs1Image = bs1.getImage();
				Image bs1Re = bs1Image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				ImageIcon bs1Fix = new ImageIcon(bs1Re);
				lblBs = new JLabel(bs1Fix);
				lblBs.setBounds(20, 10, 50, 50);
				
				lblSearch = new JLabel("회원 관리");
				lblSearch.setFont(font1);
				lblSearch.setBounds(80, 20, 120, 30);

				lblId = new JLabel("회원 ID");
				lblId.setFont(font4);
				lblId.setBounds(36, 70, 100, 30);

				lblName = new JLabel("회원이름");
				lblName.setFont(font4);
				lblName.setBounds(28, 110, 100, 30);

				lblNum = new JLabel("전화번호");
				lblNum.setFont(font4);
				lblNum.setBounds(28, 150, 100, 30);

				lblBlack = new JLabel("연체자 : ");
				lblBlack.setFont(font4);
				lblBlack.setBounds(34, 10, 50, 20);

				lblBorrow = new JLabel("대여유무 : ");
				lblBorrow.setFont(font4);
				lblBorrow.setBounds(22, 40, 60, 20);

				lblAdmin = new JLabel("관리자포함 : ");
				lblAdmin.setFont(font4);
				lblAdmin.setBounds(10, 70, 70, 20);

				pSearch.add(lblS1);
				pSearch2.add(lblS2);
				pSearch3.add(lblS3);
				
				pNorth.add(lblBs);
				pNorth.add(lblSearch);
				pNorth.add(lblId);
				pNorth.add(lblName);
				pNorth.add(lblNum);

				pCheck1.add(lblBlack);
				pCheck1.add(lblBorrow);
				pCheck1.add(lblAdmin);

				// 텍스트 필드생성
				tfId = new JTextField(20) {

					public void setBorder(Border border) {
					}
				};
				tfId.setBackground(new Color(200, 235, 240));
				tfId.setBounds(130, 70, 160, 30);

				tfName = new JTextField(20) {
					public void setBorder(Border border) {
					}
				};
				tfName.setBackground(new Color(200, 235, 240));
				tfName.setBounds(130, 110, 160, 30);

				tfNum = new JTextField(20) {
					public void setBorder(Border border) {
					}
				};
				tfNum.setBackground(new Color(200, 235, 240));
				tfNum.setBounds(130, 150, 160, 30);

				pNorth.add(tfId);
				pNorth.add(tfName);
				pNorth.add(tfNum);

				// 체크박스 생성
				checkId = new JCheckBox("ID포함");
				checkId.setFocusPainted(false);
				checkId.setBackground(new Color(143, 222, 217));
				checkId.setFont(font5);
				checkId.setBounds(295, 70, 70, 30);

				checkName = new JCheckBox("이름포함");
				checkName.setFocusPainted(false);
				checkName.setBackground(new Color(143, 222, 217));
				checkName.setFont(font5);
				checkName.setBounds(295, 110, 80, 30);

				checkNum = new JCheckBox("번호포함");
				checkNum.setFocusPainted(false);
				checkNum.setBackground(new Color(143, 222, 217));
				checkNum.setFont(font5);
				checkNum.setBounds(295, 150, 80, 30);

				checkIdAll = new JCheckBox("ID전체");
				checkIdAll.setFocusPainted(false);
				checkIdAll.setBackground(new Color(216,248,199));
				checkIdAll.setFont(font5);
				checkIdAll.setBounds(10, 7, 70, 20);

				checkNameAll = new JCheckBox("이름전체");
				checkNameAll.setFocusPainted(false);
				checkNameAll.setBackground(new Color(216,248,199));
				checkNameAll.setFont(font5);
				checkNameAll.setBounds(85, 7, 80, 20);

				checkNumAll = new JCheckBox("번호전체");
				checkNumAll.setFocusPainted(false);
				checkNumAll.setBackground(new Color(216,248,199));
				checkNumAll.setFont(font5);
				checkNumAll.setBounds(170, 7, 80, 20);
				
				objectActionListner object = new objectActionListner();
				checkIdAll.addActionListener(object);
				checkNameAll.addActionListener(object);
				checkNumAll.addActionListener(object);
				
				pNorth.add(checkId);
				pNorth.add(checkName);
				pNorth.add(checkNum);
				pCheck2.add(checkIdAll);
				pCheck2.add(checkNameAll);
				pCheck2.add(checkNumAll);

				// 라디오버튼 생성
				rbB1 = new JRadioButton("조건무");
				rbB1.setFocusPainted(false);
				rbB1.setBackground(new Color(192,230,215));
				rbB1.setFont(font5);
				rbB1.setBounds(80, 9, 70, 20);

				rbB2 = new JRadioButton("미포함");
				rbB2.setFocusPainted(false);
				rbB2.setBackground(new Color(192,230,215));
				rbB2.setFont(font5);
				rbB2.setBounds(150, 9, 70, 20);

				rbB3 = new JRadioButton("포함");
				rbB3.setFocusPainted(false);
				rbB3.setBackground(new Color(192,230,215));
				rbB3.setFont(font5);
				rbB3.setBounds(220, 9, 70, 20);

				rbBookl = new JRadioButton("조건무");
				rbBookl.setFocusPainted(false);
				rbBookl.setBackground(new Color(192,230,215));
				rbBookl.setFont(font5);
				rbBookl.setBounds(80, 39, 70, 20);

				rbBook2 = new JRadioButton("미포함");
				rbBook2.setFocusPainted(false);
				rbBook2.setBackground(new Color(192,230,215));
				rbBook2.setFont(font5);
				rbBook2.setBounds(150, 39, 70, 20);

				rbBook3 = new JRadioButton("포함");
				rbBook3.setFocusPainted(false);
				rbBook3.setBackground(new Color(192,230,215));
				rbBook3.setFont(font5);
				rbBook3.setBounds(220, 39, 70, 20);

				rbA1 = new JRadioButton("조건무");
				rbA1.setFocusPainted(false);
				rbA1.setBackground(new Color(192,230,215));
				rbA1.setFont(font5);
				rbA1.setBounds(80, 69, 70, 20);

				rbA2 = new JRadioButton("미포함");
				rbA2.setFocusPainted(false);
				rbA2.setBackground(new Color(192,230,215));
				rbA2.setFont(font5);
				rbA2.setBounds(150, 69, 70, 20);

				rbA3 = new JRadioButton("포함");
				rbA3.setFocusPainted(false);
				rbA3.setBackground(new Color(192,230,215));
				rbA3.setFont(font5);
				rbA3.setBounds(220, 69, 70, 20);

				pCheck1.add(rbB1);
				pCheck1.add(rbB2);
				pCheck1.add(rbB3);
				pCheck1.add(rbBookl);
				pCheck1.add(rbBook2);
				pCheck1.add(rbBook3);
				pCheck1.add(rbA1);
				pCheck1.add(rbA2);
				pCheck1.add(rbA3);

				// 버튼그룹 생성
				ButtonGroup group1 = new ButtonGroup();
				ButtonGroup group2 = new ButtonGroup();
				ButtonGroup group3 = new ButtonGroup();

				group1.add(rbB1);
				group1.add(rbB2);
				group1.add(rbB3);

				group2.add(rbBookl);
				group2.add(rbBook2);
				group2.add(rbBook3);

				group3.add(rbA1);
				group3.add(rbA2);
				group3.add(rbA3);

				// 버튼생성
				btnSearch = new JButton("<html>검&nbsp&nbsp&nbsp&nbsp색</html");
				btnSearch.setFocusPainted(false);
				btnSearch.setFont(font2);
				btnSearch.setForeground(new Color(254, 245, 253));
				btnSearch.setBackground(new Color(34, 45, 65));
				btnSearch.setBounds(730, 139, 80, 40);

				btnDetail = new JButton("간편검색");
				btnDetail.setFocusPainted(false);
				btnDetail.setFont(font2);
				btnDetail.setForeground(new Color(254, 245, 253));
				btnDetail.setBackground(new Color(34, 45, 65));
				btnDetail.setBounds(730, 90, 80, 40);

				btnNew = new JButton("신규등록");
				btnNew.setFocusPainted(false);
				btnNew.setFont(font2);
				btnNew.setForeground(new Color(254, 245, 253));
				btnNew.setBackground(new Color(34, 45, 65));
				btnNew.setBounds(730, 41, 80, 40);

				pNorth.add(btnSearch);
				pNorth.add(btnDetail);
				pNorth.add(btnNew);

				btnSearch.addActionListener(this);
				btnDetail.addActionListener(this);
				btnNew.addActionListener(this);

				checkDetail = 1;
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
			int row = tbMemberSearch.getSelectedRow();
			int column = tbMemberSearch.getSelectedColumn();

			String userId = (String) model.getValueAt(row, 0);
			System.out.println(row + " 행 " + column + " 열 " + userId + " 선택");
			AdminUser a = new AdminUser(id, userId, model, row);
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
	
	class objectActionListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if ( checkDetail == 1 ) {
				//ID 전체 선택 검색
				if ( checkIdAll.isSelected() == true ) {
					tfId.setText(null);
					checkId.setSelected(false);
					
					tfId.setEditable(false);
					checkId.setEnabled(false);
				} else {
					tfId.setEditable(true);
					checkId.setEnabled(true);
				}
				
				// 이름 전체 선택 검색
				if ( checkNameAll.isSelected() == true ) {
					tfName.setText(null);
					checkName.setSelected(false);
					
					tfName.setEditable(false);
					checkName.setEnabled(false);
				} else {
					tfName.setEditable(true);
					checkName.setEnabled(true);
				}
				
				// 전화번호 전체선택 검색
				if ( checkNumAll.isSelected() == true ) {
					tfNum.setText(null);
					checkNum.setSelected(false);
					
					tfNum.setEditable(false);
					checkNum.setEnabled(false);
				} else {
					tfNum.setEditable(true);
					checkNum.setEnabled(true);
				}
			}
		}
	}

	
}// end
