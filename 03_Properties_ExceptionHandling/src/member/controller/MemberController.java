package member.controller;

import java.util.List;

import member.model.service.MemberService;
import member.model.vo.Member;
import member.model.vo.MemberDel;

/**
 * 
 * controller 클래스
 *  - service단에 업무요청
 *
 */
public class MemberController {

	private MemberService memberService = new MemberService();

	public int insertMember(Member member) {
		int result = 0;
		try {
			result = memberService.insertMember(member);
		} catch (Exception e) {
			//e.printStackTrace();
			System.err.println("[" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의하세요.");
		}
		return result;
	}

	public List<Member> findMemberByName(String name) {
		List<Member> list = null;
		try {
			list = memberService.findMemberbyName(name);
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("[" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의하세요.");
		}
		return list;
	}

	public Member findMemberById(String id) {
		Member member = null;
		try {
			member = memberService.findMemberById(id);
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("[" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의하세요.");
		}
		return member;
	}

	public int deleteMember(String id) {
		int result = 0;
		
		try {
			result = memberService.deleteMember(id);
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println("[" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의하세요.");
		}
		return result;
	}

	public List<Member> selectAll() {
		List<Member> list = null;

		try {
			list = memberService.selectAll();
		} catch (Exception e) {
			// 1. 오류로그(개발자용)
			// e.printStackTrace();

			// 2. 사용자용 오류 알림
			System.err.println("[" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의하세요.");
		}
		return list;
	}

	public int updateMember(String id, String colName, Object newValue) {
		int result = 0;

		try {
			result = memberService.updateMember(id, colName, newValue);
		} catch (Exception e) {
			// 1. 오류 로그 출력
			// e.printStackTrace();

			// 2. 사용자 통보
			System.err.println("[" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의하세요.");
		}

		return result;
	}

	public List<MemberDel> selectQuit() {
		List<MemberDel> list = null;
		
		try {
			list = memberService.selectQuit();
		} catch (Exception e) {
			//e.printStackTrace();
			System.err.println("[" + e.getMessage() + "] 불편을 드려 죄송합니다. 관리자에게 문의하세요.");
		}
		return list;
	}

}
