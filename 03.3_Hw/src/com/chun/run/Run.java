package com.chun.run;

import java.util.List;

import com.chun.model.dao.ChunDao;
import com.chun.model.vo2.intergrated.StudentOne;

public class Run {
	public static void main(String[] args) {
		ChunDao chunDao = new ChunDao();
		List<StudentOne> list = chunDao.findByDepartmentName("국어국문학과");
		
		System.out.println("--------------------------------------------------------");
		System.out.println("학번\t학과코드\t학과명\t\t학생이름\t휴학\t담당교수");
		System.out.println("--------------------------------------------------------");
		for(StudentOne s : list) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n",
					s.getStudentNo(), s.getDepartmentNo(), s.getDepartmentName(), s.getStudentName(),
					s.isAbsenceYN(), s.getCoachProfessorName());
		}
		
	}
}
