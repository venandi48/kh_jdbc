package member.view;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import member.controller.MemberController;
import member.model.vo.Member;

/**
 * 
 * view단 클래스
 *  - 메뉴 노출, 사용자 입력값 처리, 결과값 출력
 *
 */
public class MemberMenu {
	
	private Scanner sc = new Scanner(System.in);
	private MemberController memberController = new MemberController();

	public void mainMenu() {
		String menu = "================= 회원관리 =================\n"
                + "1. 회원 전체 조회\n"
                + "2. 회원 아이디 조회\n"
                + "3. 회원 이름 검색\n"
                + "4. 회원 가입\n"
                + "5. 회원 정보 변경\n"
                + "6. 회원 탈퇴\n"
                + "0. 프로그램 종료\n"
                + "------------------------------------------\n"
                + "선택 : ";

		while (true) {
			System.out.print(menu);

			Member member = null;
			String name = null;
			String id = null;
			int result = 0;
			List<Member> list = null;

			String choice = sc.next();

			switch (choice) {
			case "1":
				list = memberController.selectAll();
				printMemberList(list);
				break;
			case "2":
				id = inputMemberId();
				member = memberController.findMemberById(id);
				printMember(member);
				break;
			case "3":
				name = inputMeberName();
				list = memberController.findMemberByName(name);
				printMemberList(list);
				break;
			case "4":
				member = inputMember();
				result = memberController.insertMember(member);
				printResultMsg(result, "회원가입 완료", "회원가입 실패");
				break;
			case "5":
				id = inputMemberId();
				member = memberController.findMemberById(id);
				printMember(member);
				if (member != null)
					subMenu(id);
				break;
			case "6":
				id = inputMemberId();
				result = memberController.deleteMember(id);
				printResultMsg(result, "회원탈퇴 완료", "회원탈퇴 실패");
				break;
			case "0":
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

	private void subMenu(String id) {
		String menu = "************* 회원 정보 변경 메뉴 *************\n"
					+ "1. 이름 변경\n"
					+ "2. 생일 변경\n"
					+ "3. 이메일 변경\n"
					+ "4. 주소 변경\n"
					+ "9. 메인메뉴 돌아가기\n"
					+ "------------------------------------------\n"
					+ "선택 : ";
		
		while (true) {
			System.out.print(menu);
			String choice = sc.next();
			int result = 0;
			String newString = null;
			Date newDate = null;

			switch (choice) {
			case "1":
				newString = inputNewMemberName();
				result = memberController.updateMemberName(id, newString);
				printUpdateMember(result, id);
				break;
			case "2":
				newDate = inputNewMemberBirthday();
				result = memberController.updateMemberBirthday(id, newDate);
				printUpdateMember(result, id);
				break;
			case "3":
				newString = inputNewMemberEmail();
				result = memberController.updateMemberEmail(id, newString);
				printUpdateMember(result, id);
				break;
			case "4":
				newString = inputNewMemberAddress();
				result = memberController.updateMemberAddress(id, newString);
				printUpdateMember(result, id);
				break;
			case "9":
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}

		}
	}

	private String inputNewMemberAddress() {
		sc.nextLine(); // 버퍼비우기
		System.out.println("> 새로운 주소를 입력하세요.");
		System.out.print("주소 : ");
		return sc.nextLine();
	}
	
	private String inputNewMemberEmail() {
		System.out.println("> 새로운 이메일을 입력하세요.");
		System.out.print("이메일 : ");
		return sc.next();
	}

	private Date inputNewMemberBirthday() {
		System.out.println("> 새로운 생일을 입력하세요.");
		System.out.print("생일(예시: 19990302) : ");
		String tmpBirthday = sc.next();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date birthday = null;
		try {
			long millis = sdf.parse(tmpBirthday).getTime();
			birthday = new Date(millis);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return birthday;
	}

	private String inputNewMemberName() {
		System.out.println("> 새로운 이름을 입력하세요.");
		System.out.print("이름 : ");
		return sc.next();
	}

	private void printUpdateMember(int result, String id) {
		if (result > 0) {
			Member member = memberController.findMemberById(id);
			printMember(member);
		} else
			System.out.println("> 회원정보변경에 실패하였습니다.\n");
	}

	private void printMember(Member member) {
		if(member == null)
			System.out.println("> 조회된 회원이 없습니다.\n");
		else {
			System.out.println("> 조회결과");
			System.out.println("==================================================================================================================================");
			System.out.printf("%15s%10s%10s%15s%20s%20s%30s%n",
					"id", "name", "gender","birthday", "email", "address", "regDate");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

			System.out.printf("%15s%10s%10s%15s%20s%20s%30s%n",
					member.getId(), member.getName(), member.getGender(), member.getBirthday(),
					member.getEmail(), member.getAddress(), member.getRegDate());
			
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
		}
	}

	private String inputMemberId() {
		System.out.println("> 대상의 아이디를 입력하세요.");
		System.out.print("아이디 : ");
		return sc.next();
	}

	private void printMemberList(List<Member> list) {
		if (list.isEmpty()) // null이 들어오진 않음
			System.out.println("> 조회된 회원이 없습니다.\n");
		else {
			System.out.println("> 조회결과");
			System.out.println("==================================================================================================================================");
			System.out.printf("%15s%10s%10s%15s%20s%20s%30s%n",
					"id", "name", "gender","birthday", "email", "address", "regDate");
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------");

			for (Member m : list) {
				System.out.printf("%15s%10s%10s%15s%20s%20s%30s%n",
						m.getId(), m.getName(), m.getGender(), m.getBirthday(),
						m.getEmail(), m.getAddress(), m.getRegDate());
			}
			System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
		}		
	}

	/**
	 * 조회할 회원명 입력 메소드
	 * @return
	 */
	private String inputMeberName() {
		System.out.println("> 조회할 이름을 입력하세요.");
		System.out.print("이름 : ");
		return sc.next();
	}

	/**
	 * 신규회원정보를 입력받는 메소드
	 * 
	 * @return
	 */
	private Member inputMember() {
		Member member = new Member();

		System.out.println("> 신규회원정보를 입력하세요.");
		System.out.print("아이디 : ");
		member.setId(sc.next());
		System.out.print("이름 : ");
		member.setName(sc.next());
		System.out.print("성별(M/F) : ");
		member.setGender(String.valueOf(sc.next().toUpperCase().charAt(0)));

		// 생일 입력
		System.out.print("생일(예시: 19990302) : ");
		String tmpBirthday = sc.next();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date birthday = null;
		try {
			long millis = sdf.parse(tmpBirthday).getTime();
			birthday = new Date(millis);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		member.setBirthday(birthday);

		System.out.print("이메일 : ");
		member.setEmail(sc.next());
		sc.nextLine(); // 버퍼 비우기
		System.out.print("주소 : ");
		member.setAddress(sc.nextLine());

		return member;
	}

	/**
	 * DML처리 결과를 출력하는 메소드
	 * 
	 * @param result
	 * @param successMsg
	 * @param failureMsg
	 */
	private void printResultMsg(int result, String successMsg, String failureMsg) {
		if (result > 0)
			System.out.println("> " + successMsg);
		else
			System.out.println("> " + failureMsg);
	}

}
