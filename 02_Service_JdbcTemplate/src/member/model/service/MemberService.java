package member.model.service;

// 클래스이름 없이 메소드 사용 가능
import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import member.model.dao.MemberDao;
import member.model.vo.Member;

/**
 * 
 * service 클래스
 * 
 * -------------------- Service start --------------------
 * 1. 드라이버클래스 등록
 * 2. Connection객체 생성(setAutoCommit(false))
 * ---------------------- Doa start ----------------------
 * 		3. PreparedStatement객체 생성(미완성 sql 값대입)
 * 		4. 실행 및 ResultSet 처리
 * 		5. 자원반납(pstmt, rset) 
 * ----------------------- Doa end -----------------------
 * 6. 트랜잭션처리
 * 7. 자원반납(conn)
 * --------------------- Service end ---------------------
 *
 */
public class MemberService {



	private MemberDao memberDao = new MemberDao();
	
	public int insertMember(Member member) {
		int result = 0;
		
		// 1. Connection 생성
		Connection conn = getConnection();
		
		try {
			// 2. Dao요청
			result = memberDao.insertMember(conn, member);
			
			// 3. 트랜잭션처리
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			// 4. 자원반납
			close(conn);
		}
		return result;
	}

	public List<Member> findMemberbyName(String name) {
		// 1. connection 생성
		Connection conn = getConnection();
		
		// 2. dao요청
		List<Member> list = memberDao.findMemberByName(conn, name);
		
		// 3. 자원반납
		close(conn);
		
		return list;
	}

//	// jdbcTmplate 없을 때
//	public int insertMember(Member member) {
//		Connection conn = null;
//		int result = 0;
//
//		try {
//			// 1. driver class 등록
//			Class.forName(driverClass);
//
//			// 2. Connection 생성, setAutoCommit(false)
//			conn = DriverManager.getConnection(url, user, password);
//			conn.setAutoCommit(false);
//
//			// 3. Dao요청 및 리턴
//			result = memberDao.insertMember(conn, member); // conn도 같이 전달
//
//			// 4. 트랜잭션처리
//			conn.commit();
//
//		} catch (Exception e) {
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		} finally {
//			// 5. 자원반납
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return result;
//	}
	

}
