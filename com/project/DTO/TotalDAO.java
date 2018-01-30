package com.project.DTO;

import java.awt.image.ReplicateScaleFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.project.ADMIN.MemberPanel;

import java.sql.Date;
import java.sql.DriverManager;

public class TotalDAO {

	// 회원가입 : 회원정보 저장
	public boolean createMember(UserDTO dto) {
		Connection conn = getConn();
		System.out.println(conn);
		PreparedStatement ps = null;
		int result = 0;
		boolean ok = false;

		try {
			String sql = "insert into user values(?,?,?,?,0,0,now(), null, 'N')";
			ps = conn.prepareStatement(sql);

			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getPhone());

			// 실행
			result = ps.executeUpdate();
			if (result == 1) {
				System.out.println("저장성공");
				ok = true;
			} else {
				System.out.println("저장실패");
				ok = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원해제 : rs -> ps -> conn
			release(conn, ps);
		} // finally // try~catch
		return ok;
	}

	public boolean loginCheck(UserDTO dto) {
		boolean ok = false;
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user where id=? and pw=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getId());
			ps.setString(2, dto.getPw());
			rs = ps.executeQuery();
			if (rs.next()) {
				ok = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		System.out.println("아이디와 비밀번호가 맞는가? = " + ok);
		return ok;
	} // 로그인 체크

	// 관리자 체크
	public String adminCheck(String id) {
		UserDTO dto = new UserDTO();
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String admin = "N";
		String sql = "select * from user where id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				admin = dto.setAdminCheck(rs.getString("check_admin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return admin;
	}

	// 개인정보 화면 출력
	public UserDTO searchUser(String id) {
		UserDTO dto = new UserDTO();
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user where id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setRegistdate(rs.getDate("regist_date"));
				dto.setLimitDate(rs.getDate("limit_use_date"));
				dto.setBorrowBookNum(rs.getInt("Borrow_book_num"));
				dto.setOverDueBookNum(rs.getInt("overdue_book"));
				dto.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return dto;
	}

	// 비밀번호변경창 화면구현
	public UserDTO searchInfo(String id) {
		UserDTO dto = new UserDTO();
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user where id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return dto;
	}

	// 개인정보패널 정보수정
	public boolean updateInfo(UserDTO dto) {
		boolean ok = false;
		Connection conn = getConn();
		PreparedStatement ps = null;
		String sql = "update user set name=?, phone=? where id=?";

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, dto.getName());
			ps.setString(2, dto.getPhone());
			ps.setString(3, dto.getId());
			int count = ps.executeUpdate();
			if (count == 1) {
				ok = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}
		return ok;
	}

	// 비밀번호변경 저장
	public boolean updatePw(UserDTO dto) {
		boolean ok = false;
		Connection conn = getConn();
		PreparedStatement ps = null;
		String sql = "update user set pw=? where id=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getPw());
			ps.setString(2, dto.getId());
			int count = ps.executeUpdate();
			if (count == 1) {
				ok = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}
		return ok;
	}

	// 도서 간편검색 화면출력
	public Vector searchBook(String search, String combo) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector datas = new Vector();

		String sql = "select * from book where ";

		try {
			if (combo.equals("도서명")) {
				sql += "bookname like '%" + search + "%'";
				ps = conn.prepareStatement(sql);
			} else if (combo.equals("저자명")) {
				sql += "writer like '%" + search + "%'";
				ps = conn.prepareStatement(sql);
			} else if (combo.equals("회사명")) {
				sql += "company like '%" + search + "%'";
				ps = conn.prepareStatement(sql);
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("bookNo"));
				v.add(rs.getString("bookName"));
				v.add(rs.getString("writer"));
				v.add(rs.getString("company"));
				v.add(rs.getString("check_borrow"));
				datas.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return datas;
	}

	// 도서상세검색 화면 구현
	public Vector searchBookDetail(String search1, String search2, String search3) {
		Vector datas = new Vector();
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select * from book where";

		try {
			search1 = search1.length() == 0 ? "" : " bookname like '%" + search1 + "%' and";
			search2 = search2.length() == 0 ? "" : " writer like '%" + search2 + "%' and";
			search3 = search3.length() == 0 ? "" : " company like '%" + search3 + "%'";

			sql += search1 + search2 + search3;
			if (search3.length() == 0) {
				sql = sql.substring(0, sql.length() - 4);
			}

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("bookNo"));
				v.add(rs.getString("bookName"));
				v.add(rs.getString("writer"));
				v.add(rs.getString("company"));
				v.add(rs.getString("check_borrow"));
				datas.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}

		return datas;
	}

	// 도서검색 : 선택한 도서정보 화면구현
	public BookDTO searchBookInfo(int bookNo) {
		BookDTO dto = new BookDTO();
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from book where bookno=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookNo);
			rs = ps.executeQuery();
			if (rs.next()) {
				dto.setBookNo(rs.getInt("bookno"));
				dto.setBookName(rs.getString("bookname"));
				dto.setWriter(rs.getString("writer"));
				dto.setCompany(rs.getString("company"));
				dto.setBorrowCheck(rs.getString("check_borrow"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return dto;
	}

	// 대여정보 대여DB에 저장
	public boolean insertBorrowBook(BookDTO dto, String id) {
		boolean ok = false;
		Connection conn = getConn();
		PreparedStatement ps = null;
		int result = 0;

		String sql = "insert into borrowbook values(?,?,?,now(),(select date_add(now(), INTERVAL 14 DAY)), ?)";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, dto.getBookNo());
			ps.setString(2, id);
			ps.setString(3, dto.getBookName());
			ps.setInt(4, 0);

			// 실행
			result = ps.executeUpdate();
			if (result == 1) {
				System.out.println("저장성공");
				ok = true;
			} else {
				System.out.println("저장실패");
				ok = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원해제 : rs -> ps -> conn
			release(conn, ps);
		} // finally // try~catch
		return ok;
	}

	// 대여한 도서 : 대여여부 변경 후 저장
	public void updateCheck(int bookNo, boolean isBorrowed) {
		Connection conn = getConn();
		PreparedStatement ps = null;

		String sql = "update book set check_borrow=? where bookno=?";

		try {
			if (isBorrowed == false) {
				ps = conn.prepareStatement(sql);
				ps.setString(1, "Y");
				ps.setInt(2, bookNo);
			} else {
				ps = conn.prepareStatement(sql);
				ps.setString(1, "N");
				ps.setInt(2, bookNo);
			}

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}
	}

	// 나의 대여정보버튼 화면구현
	public Vector myBorrowInfo(String id) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector datas = new Vector();

		String sql = "select * from borrowbook where id=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("bookno"));
				v.add(rs.getString("bookname"));
				v.add(rs.getString("borrow_date"));
				v.add(rs.getString("return_due_date"));
				v.add(rs.getString("overdue_day"));

				datas.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return datas;
	}

	// 나의 반납정보버튼 화면구현
	public Vector myReturnInfo(String id) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector datas = new Vector();

		String sql = "select * from returnbook where id=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getInt("bookno"));
				v.add(rs.getString("bookname"));
				v.add(rs.getString("borrow_date"));
				v.add(rs.getString("return_date"));
				v.add(rs.getString("last_overdue_day"));

				datas.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return datas;
	}

	// 책 대여 성공시 UserDB에 대여권수 증가!
	public int updateBorrowNum(String id, boolean check) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		int result = 0;

		String sql1 = "update user set borrow_book_num = borrow_book_num + 1 where id=?";
		String sql2 = "update user set borrow_book_num = borrow_book_num - 1 where id=?";
		try {
			if (check == true) {
				ps = conn.prepareStatement(sql1);
				ps.setString(1, id);
				result = ps.executeUpdate();
			} else {
				ps = conn.prepareStatement(sql2);
				ps.setString(1, id);
				result = ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}
		return result;
	}

	// 도서검색 : 선택한 도서정보 화면구현
	public BorrowBookDTO borrowBookInfo(int bookNo) {
		BorrowBookDTO dto = new BorrowBookDTO();
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from borrowbook where bookno=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				dto.setBookNo(rs.getInt("bookno"));
				dto.setBookName(rs.getString("bookname"));
				dto.setBorrowDate(rs.getDate("borrow_date"));
				dto.setReturnDueDate(rs.getDate("return_due_date"));
				dto.setOverDueDay(rs.getInt("overdue_day"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return dto;
	}

	// 도서반납 : DB삭제
	public void returnBook(int bookNo) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "delete from borrowbook where bookno=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookNo);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
	}

	// 반납정보 : 반납DB에 저장
	public boolean insertReturnBook(BorrowBookDTO dto, String id) {
		boolean ok = false;
		Connection conn = getConn();
		PreparedStatement ps = null;
		int result = 0;

		String sql = "insert into returnbook values(?,?,?,?,now(), now(), ?)";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, 0);
			ps.setInt(2, dto.getBookNo());
			ps.setString(3, id);
			ps.setString(4, dto.getBookName());
			ps.setInt(5, 0);

			// 실행
			result = ps.executeUpdate();
			if (result == 1) {
				System.out.println("저장성공");
				ok = true;
			} else {
				System.out.println("저장실패");
				ok = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 자원해제 : rs -> ps -> conn
			release(conn, ps);
		} // finally // try~catch
		return ok;
	}

	// 관리자권한 : 신규 도서 등록
	public boolean newBook(BookDTO dto) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		int result = 0;
		boolean ok = false;

		String sql = "insert into book values(?, ?, ?, ?, 'N')";

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, 0);
			ps.setString(2, dto.getBookName());
			ps.setString(3, dto.getWriter());
			ps.setString(4, dto.getCompany());

			result = ps.executeUpdate();
			if (result == 1) {
				System.out.println("저장성공");
			} else {
				System.out.println("저장실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ok;
	}

	// 관리자권한 : 도서 데이터 삭제
	public void bookDelete(int bookNo) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		String sql = "delete from returnbook where bookno=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, bookNo);
			ps.executeQuery();

			int result = 1;
			if (result == 1) {

				String sql2 = "delete from book where bookno=?";

				ps = conn.prepareStatement(sql2);
				ps.setInt(1, bookNo);
				ps.executeQuery();

				result = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}
	}

	// 관리자 권한 : 도서 정보 수정 (DB설계문제로 불가능)
	public void updateBookInfo(BookDTO dto, int bookNo) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		int result = 0;

		String sql = "update from returnbook set bookname=?, writer=?, company =? where bookno=?";
		String sql2 = "update from borrowbook set bookname=?, writer=?, company =? where bookno=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getBookName());
			ps.setString(2, dto.getWriter());
			ps.setString(3, dto.getCompany());
			ps.setInt(4, bookNo);
			System.out.println(bookNo);
			if (result == 1) {
				System.out.println("저장성공");
			} else if (result == 0) {
				System.out.println("수정하려는 책의 데이터가 없음");

				ps = conn.prepareStatement(sql2);
				ps.setString(1, dto.getBookName());
				ps.setString(2, dto.getWriter());
				ps.setString(3, dto.getCompany());
				ps.setInt(4, bookNo);

				if (result == 1) {
					System.out.println("저장성공");
				} else {
					System.out.println("저장실패");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 회원관리 : 회원 검색
	public Vector searchUser(String search, String combo) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector datas = new Vector();

		String sql = "select * from user where ";

		try {
			if (combo.equals("회원 ID")) {
				sql += "id like '%" + search + "%'";
				ps = conn.prepareStatement(sql);
			} else if (combo.equals("회원이름")) {
				sql += "name like '%" + search + "%'";
				ps = conn.prepareStatement(sql);
			} else if (combo.equals("전화번호")) {
				sql += "phone like '%" + search + "%'";
				ps = conn.prepareStatement(sql);
			}

			rs = ps.executeQuery();
			while (rs.next()) {
				Vector v = new Vector();
				v.add(rs.getString("id"));
				v.add(rs.getString("name"));
				v.add(rs.getString("phone"));
				v.add(rs.getInt("borrow_book_num"));
				v.add(rs.getInt("overdue_book"));
				v.add(rs.getDate("regist_date"));
				v.add(rs.getDate("limit_use_date"));
				v.add(rs.getString("check_admin"));
				datas.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return datas;
	}

	// 회원 상세 검색
	public Vector searchUserDetail(String[] v1, String[] v2, String[] v3) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int checkI = 0;
		boolean checkd = false;
		Vector datas = new Vector();
		Vector check = new Vector();

		String sql = "select * from user ";

		try {

			for (int i = 0; i < v1.length; i++) {
				if (!v1[i].equals("N") || v3[i].equals("NY") || v3[i].equals("Y")) {
					sql += " where ";

					checkd = true;
					break;
				}
			}
			if (checkd) {
				String[] lblSearch = { "id ", "name ", "phone " };
				String[] lblCheck = { "limit_use_date ", "borrow_book_num ", "check_admin " };

				// tf로 받아온 것을 검색
				for (int i = 0; i < v1.length; i++) {
					if (!v1[i].equals("N")) {
						sql += lblSearch[i] + " like '%" + v1[i] + "%' and ";
					}
				}
				// rb에서 맞는 것을 검색
				if (!v3[0].equals("N")) {
					sql += v3[0].equals("NY") ? lblCheck[0] + "is null and " : lblCheck[0] + " is not null and ";
				}
				if (!v3[1].equals("N")) {
					sql += v3[1].equals("NY") ? lblCheck[1] + "=0 and " : lblCheck[1] + "!=0 and ";
				}
				if (!v3[2].equals("N")) {
					sql += v3[2].equals("NY") ? lblCheck[2] + " NOT LIKE 'Y' and " : lblCheck[2] + " LIKE 'Y' and ";
				}
				sql = sql.substring(0, sql.length() - 4);
			}

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (!rs.next()) {
				check.add("thisIsNull");
				return check;
			} else {
				do {
					Vector cols = new Vector();
					cols.add(rs.getString("id"));
					cols.add(rs.getString("name"));
					cols.add(rs.getString("phone"));
					cols.add(rs.getInt("borrow_book_num"));
					cols.add(rs.getInt("overdue_book"));
					cols.add(rs.getDate("regist_date"));
					cols.add(rs.getDate("limit_use_date"));
					cols.add(rs.getString("check_admin"));
					datas.add(cols);
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		if (checkI == 1) {
			return check;
		}

		return datas;

	}

	// 회원관리 : 선택한 회원 화면구현
	public UserDTO searchUserInfo(String userId) {
		UserDTO dto = new UserDTO();
		Connection conn = getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from user where id=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();

			if (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setAdminCheck(rs.getString("check_admin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps, rs);
		}
		return dto;
	}

	// 관리자권한 : 회원정보 변경
	public boolean updateUserInfo(UserDTO dto) {
		Connection conn = getConn();
		PreparedStatement ps = null;
		int result = 0;
		boolean ok = false;

		String sql = "update user set pw=?, name=?, phone=?, check_admin=? where id=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getPw());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getPhone());
			ps.setString(4, dto.getAdminCheck());
			ps.setString(5, dto.getId());

			result = ps.executeUpdate();
			if (result == 1) {
				System.out.println("저장성공");
			} else {
				System.out.println("저장실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			release(conn, ps);
		}
		return ok;
	}

	// 관리자권한 : 회원정보삭제
	public void userInfoDelete(String id) {
		Connection conn = getConn();
		PreparedStatement ps = null;

		String sql = "delete from user where id=?";

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.executeQuery();

			int result = 1;
			if (result == 1) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void release(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		release(conn, ps);
	} // ps, conn 해제

	private void release(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private Connection getConn() {
		Connection conn = null;
		String dbUrl = "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8";

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, "root", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	} // getConn

}// end
