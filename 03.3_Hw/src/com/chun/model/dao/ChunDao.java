package com.chun.model.dao;

import static com.chun.common.JdbcTemplate.close;
import static com.chun.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chun.model.vo2.intergrated.StudentOne;

public class ChunDao {

	public List<StudentOne> findByDepartmentName(String deptName){
		String sql = "select student_no, d.department_no department_no, department_name, student_name, student_ssn, student_address, "
				+ "entrance_date, absence_yn, coach_professor_no, professor_name "
				+ "from "
				+ "tb_student s join tb_department d "
				+ "on s.department_no = d.department_no "
				+ "left join tb_professor p "
				+ "on s.coach_professor_no = p.professor_no "
				+ "where department_name = ?";
		List<StudentOne> list = new ArrayList<>();
		
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptName);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				StudentOne s = new StudentOne(rset.getString("student_no"), rset.getString("department_no"), rset.getString("department_name"),
						rset.getString("student_name"), rset.getString("student_ssn"), rset.getString("student_address"), 
						rset.getDate("entrance_date"), (rset.getString("absence_yn")=="Y" ? true : false),
						rset.getString("coach_professor_no"), rset.getString("professor_name"));
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(conn);
		}
		
		return list;
	}
}
