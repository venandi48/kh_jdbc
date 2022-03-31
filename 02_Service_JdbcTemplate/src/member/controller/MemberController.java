package member.controller;

import java.sql.Date;
import java.util.List;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * 
 * controller 클래스
 *  - service단에 업무요청
 *
 */
public class MemberController {

	private MemberService memberService = new MemberService();

	public int insertMember(Member member) {
		int result = memberService.insertMember(member);
		return result;
	}

	public List<Member> findMemberByName(String name) {
		List<Member> list = memberService.findMemberbyName(name);
		return list;
	}

	public Member findMemberById(String id) {
		Member member = memberService.findMemberById(id);
		return member;
	}

	public int deleteMember(String id) {
		int result = memberService.deleteMember(id);
		return result;
	}

	public List<Member> selectAll() {
		List<Member> list = memberService.selectAll();
		return list;
	}

	public int updateMemberName(String id, String newName) {
		int result = memberService.updateMemberName(id, newName);
		return result;
	}

	public int updateMemberBirthday(String id, Date newDate) {
		int result = memberService.updateMemberBirthday(id, newDate);
		return result;
	}

	public int updateMemberEmail(String id, String newEmail) {
		int result = memberService.updateMemberEmail(id, newEmail);
		return result;
	}

	public int updateMemberAddress(String id, String newAddress) {
		int result = memberService.updateMemberAddress(id, newAddress);
		return result;
	}

}
