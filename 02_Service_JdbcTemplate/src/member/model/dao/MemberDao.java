package member.model.dao;

import static common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import member.model.vo.Member;

public class MemberDao {

	public int insertMember(Connection conn, Member member) {
		String sql = "insert into member "
				   + "values(?, ?, ?, ?, ?, ?, default)";
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			// 1. PreparedStatment생성(미완성 sql & 값대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getGender());
			pstmt.setDate(4, member.getBirthday());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getAddress());

			// 2. 실행
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// checked예외를 un-checked로 전환하여 던지기
			throw new RuntimeException(e); // 기존 예외를 전환하여 던짐 -> service 트랜잭션처리용
		} finally {
			// 3. 자원반납(pstmt) - 트랜잭션 처리 전이므로 conn반환X
			close(pstmt);
		}

		return result;
	}

	public List<Member> findMemberByName(Connection conn, String name) {
		String sql = "select * from member where name like ?";
		List<Member> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 1. PreparedStatement생성 (미완성sql 전달 및 값대입)
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");

			// 2. 실행
			rset = pstmt.executeQuery();

			// 3. ResultSet처리 -> Member객체 반환
			while (rset.next()) {
				Member member = new Member();
				member.setId(rset.getString("id"));
				member.setName(rset.getString("name"));
				member.setGender(rset.getString("gender"));
				member.setBirthday(rset.getDate("birthday"));
				member.setEmail(rset.getString("email"));
				member.setAddress(rset.getString("address"));
				member.setRegDate(rset.getTimestamp("reg_date"));

				list.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4. 자원반납(pstmt, rset)
			close(rset);
			close(pstmt);
		}

		return list;
	}

}
