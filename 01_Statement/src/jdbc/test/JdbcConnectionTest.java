package jdbc.test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcConnectionTest {

	// 공통적으로 사용할 변수
	String driverClass = "oracle.jdbc.OracleDriver"; // 드라이버클래스명
	String url = "jdbc:oracle:thin:@localhost:1521:xe"; // 접속할 DB서버주소 (db접속프로토콜@ip:prot:sid)
	String user = "student";
	String password = "student";
	
	public static void main(String[] args) {
		JdbcConnectionTest instance = new JdbcConnectionTest();
//		instance.test1();
		instance.test2();
	}

	/**
	 * DQL
	 */
	private void test1() {
		// 닫기 처리가 필요하여 외부에 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			// 1. jdbc driver class 등록
			Class.forName(driverClass); // 예외처리
			System.out.println("> driver class 등록 완료");
			
			// 2. Connection객체 생성(url, user, password)
			conn = DriverManager.getConnection(url, user, password); // 예외처리
			System.out.println("> Connection객체 생성 완료");
			
			// 3. Statement객체(쿼리실행객체) 생성
			stmt = conn.createStatement();
			System.out.println("> Statement객체 생성 완료");
			
			// 4. Statement실행(executeQuery) - ResultSet객체 반환
			String sql = "select * from member";
			rset = stmt.executeQuery(sql);
			System.out.println("> Statement실행 및 ResultSet반환 성공");
			
			// 5. ResultSet을 1행씩 열람
			// 다음행이 존재하면 true 리턴
			System.out.println();
			while(rset.next()) {
				String id = rset.getString("id"); // 문자형인 "컬럼"
				String name = rset.getString("name");
				Date birthday = rset.getDate("birthday");
				System.out.printf("%s\t%s\t%s%n", id, name, birthday);
			}
			System.out.println();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			// 6. 자원반납
			// 객체생성 역순
			try {
				rset.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("> 자원반납 완료");
		}
	}

	/**
	 * DML
	 *  - insert
	 *  - update
	 *  - delete
	 */
	private void test2() {
		// 닫기 처리가 필요하여 외부에 선언
		Connection conn = null;
		Statement stmt = null;
		
		try {
			// 1. jdbc driver class 등록
			Class.forName(driverClass);
			
			// 2. Connection객체 생성(url, user, password)
			// setAutoCommit(false) : 코드로써 트랜잭션을 직접 관리하기위한 설정. 기본값 true
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
			
			// 3. Statement객체(쿼리실행객체) 생성
			stmt = conn.createStatement();
			
			// 4. Statement실행(executeUpdate) - int리턴(처리된 행 수)
			String sql = "update member set name = '고길동' where id = 'honggd'";
			int result = stmt.executeUpdate(sql); // 모든DML은 executeUpdate로 사용
			System.out.println("> " + result + "행이 수정되었습니다.");
			
			// 5.1 트랜잭션 처리
			if (result > 0)
				conn.commit();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// 5.2 트랜잭션 처리
			try {
				conn.rollback(); // 예외 발생 시 롤백
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// 6. 자원반납
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
