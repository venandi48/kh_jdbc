package member.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement; // Statement의 자식클래스
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import member.model.vo.Member;

public class MemberDao {
	
	String driverClass = "oracle.jdbc.OracleDriver"; // 드라이버클래스명
	String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 접속할 DB서버주소 (db접속프로토콜@ip:prot:sid)
	String user = "student";
	String password = "student";

	/**
	 * DB접속, 쿼리실행하는 메소드
	 */
	public int insertMember(Member newMember) {
		// [레시피]
		// 1. driver class 등록
		// 2. Connection객체 생성(SetAutoCommit(false))
		// 3. PreparedStatement객체 생성(미완성 sql전달 & 값대입)
		// 4. Statement실행 및 int(처리행수) 반환
		// 5. 트랜잭션 처리
		// 6. 자원반납
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "insert into member values(?, ?, ?, ?, ?, ?, default)"; // 미완성 sql문
		
		try {
			// 1. driver class 등록
			Class.forName(driverClass);

			// 2. Connection객체 생성(SetAutoCommit(false))
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			
			// 3. PreparedStatement객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newMember.getId());
			pstmt.setString(2, newMember.getName());
			pstmt.setString(3, newMember.getGender());
			pstmt.setDate(4, newMember.getBirthday());
			pstmt.setString(5, newMember.getEmail());
			pstmt.setString(6, newMember.getAddress());
			
			
			// 4. Statement실행 및 int(처리행수) 반환
			result = pstmt.executeUpdate();
			
			// 5.1 트랜잭션 처리
			conn.commit();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			// 5.2 트랜잭션 처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		} finally {
			// 6. 자원반납
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	/**
	 * member테이블의 모든 행을 리턴하는 메소드
	 */
	public List<Member> selectAll() {
		// [레시피]
		// 1. driver class 등록
		// 2. Connection객체 생성
		// 3. PreparedStatement객체 생성(미완성 sql전달 & 값대입)
		// 4. PreparedStatement실행 및 ResultSet반환
		// 5. ResultSet 한 행씩 fetch -> Member객체 전환 후 list에 추가
		// 6. 자원반납
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from member order by reg_date desc";
		List<Member> list = new ArrayList<>();

		try {
			// 1. driver class 등록
			Class.forName(driverClass);

			// 2. Connection객체 생성
			conn = DriverManager.getConnection(url, user, password);

			// 3. PreparedStatement객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);

			// 4. PreparedStatement실행 및 ResultSet반환
			rset = pstmt.executeQuery();

			// 5. ResultSet 한 행씩 fetch -> Member객체 전환 후 list에 추가
			// 1행씩 접근
			while (rset.next()) {
				String id = rset.getString("id");
				String name = rset.getString("name");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				String address = rset.getString("address");
				Timestamp regDate = rset.getTimestamp("reg_date");

				Member member = new Member(id, name, gender, birthday, email, address, regDate);
				list.add(member);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6. 자원반납
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * member테이블의 모든 행을 리턴하는 메소드
	 */
	public Member selectOne(String id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		String sql = "select * from member where id = ?"; 
		
		// 아래의 케이스는 DBMS가 캐싱못하기때문에 효율 떨어짐
		// String sql = "select * from member where id = '" + id + "'"; 
		
		try {
			// 1. driver class 등록
			Class.forName(driverClass);

			// 2. Connection객체 생성
			conn = DriverManager.getConnection(url, user, password);

			// 3. PreparedStatement객체 생성(미완성 sql전달 & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			// 4. PreparedStatement실행 및 ResultSet반환
			rset = pstmt.executeQuery();

			// 5. ResultSet 한 행씩 fetch -> Member객체 전환
			while (rset.next()) {
				String name = rset.getString("name");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				String address = rset.getString("address");
				Timestamp regDate = rset.getTimestamp("reg_date");

				member = new Member(id, name, gender, birthday, email, address, regDate);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6. 자원반납
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return member;
	}

	/**
	 * 이름에 인자값을 포함하는 결과집합 모든 행 리턴
	 */
	public List<Member> selectMulti(String searchName) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "select * from member where name like ?";
		List<Member> list = new ArrayList<>();;

		try {
			// 1. driver class
			Class.forName(driverClass);

			// 2. Connection
			conn = DriverManager.getConnection(url, user, password);

			// 3. PreparedStatement생성 및 값대입
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchName + "%");

			// 4. PreparedStatement실행
			rset = pstmt.executeQuery();

			// 5. ResultSet 한 행씩 fetch -> Member객체 전환 후 list에 추가
			while (rset.next()) {
				String id = rset.getString("id");
				String name = rset.getString("name");
				String gender = rset.getString("gender");
				Date birthday = rset.getDate("birthday");
				String email = rset.getString("email");
				String address = rset.getString("address");
				Timestamp regDate = rset.getTimestamp("reg_date");

				Member member = new Member(id, name, gender, birthday, email, address, regDate);
				list.add(member);
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	public int updateMember(Member member) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update member set name = ?, birthday = ?, email = ?, address = ? where id = ?";
		
		try {
			Class.forName(driverClass);
			
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setDate(2, member.getBirthday());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getId());
			
			result = pstmt.executeUpdate();
			
			conn.commit();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int deleteMember(String id) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "delete from member where id = ?";
		
		try {
			Class.forName(driverClass);
			
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
			conn.commit();
			
		} catch (ClassNotFoundException | SQLException e) {

			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

}
