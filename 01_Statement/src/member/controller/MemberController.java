package member.controller;

import java.util.List;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberController {
	
	private MemberDao memberDao = new MemberDao();

	/**
	 * 사용자의 회원가입 요청을 처리하는 메소드
	 *  1. dao에 회원가입 요청 후 int반환
	 *  2. menu에 int반환
	 */
	public int insertMember(Member newMember) {
		int result = memberDao.insertMember(newMember);
		System.out.println("result@MemberController = " + result); // 로깅용 출력
		return result;
	}

	public List<Member> selectAll() {
		List<Member> list = memberDao.selectAll();
		return list;
	}

	public Member selectOne(String id) {
		Member member = memberDao.selectOne(id);
		return member;
	}

	public List<Member> selectMulti(String name) {
		List<Member> list = memberDao.selectMulti(name);
		return list;
	}

	public int updateMember(Member member) {
		int result = memberDao.updateMember(member);
		return result;
	}

	public int deleteMember(String id) {
		int result = memberDao.deleteMember(id);
		return result;
	}

}
