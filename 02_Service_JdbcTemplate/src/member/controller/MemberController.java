package member.controller;

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

}
