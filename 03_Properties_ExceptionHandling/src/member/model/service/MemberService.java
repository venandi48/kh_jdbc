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
import member.model.vo.MemberDel;

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
			throw e;
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

	public Member findMemberById(String id) {

		Connection conn = getConnection();
		Member member = memberDao.findMemberById(conn, id);
		close(conn);

		return member;
	}

	public int deleteMember(String id) {
		int result = 0;
		Connection conn = null;

		try {
			conn = getConnection();

			result = memberDao.deleteMember(conn, id);

			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}

		return result;
	}

	public List<Member> selectAll() {
		Connection conn = null;

		conn = getConnection();
		List<Member> list = memberDao.selectAll(conn);
		close(conn);

		return list;
	}

	public int updateMember(String id, String colName, Object newValue) {
		int result = 0;
		Connection conn = null;

		try {
			conn = getConnection();
			result = memberDao.updateMember(conn, id, colName, newValue);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e; // controller에서 분기처리 할 수 있도록 다시 던짐
		} finally {
			close(conn);
		}
		return result;
	}

	public List<MemberDel> selectQuit() {
		Connection conn = null;

		conn = getConnection();
		List<MemberDel> list = memberDao.selectQuit(conn);
		close(conn);
		
		return list;
	}

}
