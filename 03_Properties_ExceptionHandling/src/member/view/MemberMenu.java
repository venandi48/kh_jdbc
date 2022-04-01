package member.view;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import member.controller.MemberController;
import member.model.vo.Member;
import member.model.vo.MemberDel;

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
                + "7. 탈퇴회원 조회\n"
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
			List<MemberDel> delList = null;

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
				subMenu(id);
				break;
			case "6":
				id = inputMemberId();
				result = memberController.deleteMember(id);
				printResultMsg(result, "회원탈퇴 완료", "회원탈퇴 실패");
				break;
			case "7":
				delList = memberController.selectQuit();
				printQuitMemberList(delList);
				break;
			case "0":
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}


	private void printQuitMemberList(List<MemberDel> delList) {
		if (delList.isEmpty()) // null이 들어오진 않음
			System.out.println("> 조회된 회원이 없습니다.\n");
		else {
			System.out.println("> 조회결과");
			System.out.println("==========================================================================================================================================");
			System.out.printf("%15s%10s%10s%15s%20s%20s%30s%15s%n",
					"id", "name", "gender","birthday", "email", "address", "regDate", "delDate");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");

			for (MemberDel m : delList) {
				System.out.printf("%15s%10s%10s%15s%20s%20s%30s%15s%n",
						m.getId(), m.getName(), m.getGender(), m.getBirthday(),
						m.getEmail(), m.getAddress(), m.getRegDate(), m.getDelDate());
			}
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println();
		}
	}


	private void subMenu(String id) {
		String menu = "************* 회원 정보 변경 메뉴 *************\n"
					+ "1. 이름 변경\n"
					+ "2. 생일 변경\n"
					+ "3. 이메일 변경\n"
					+ "4. 주소 변경\n"
					+ "0. 메인메뉴 돌아가기\n"
					+ "------------------------------------------\n"
					+ "선택 : ";

		id = inputMemberId();
		while (true) {
			Member member = memberController.findMemberById(id);
			if (member == null) {
				System.out.println("> 조회된 회원이 없습니다.\n");
				return;
			}
			printMember(member);
			
			System.out.print(menu);
			String choice = sc.next();
			String colName = null;
			Object newValue = null;
			
			switch (choice) {
			case "1":
				System.out.print("변경할 이름 : ");
				colName = "name";
				newValue = sc.next();
				break;
			case "2":
				System.out.print("변경할 생일(예시: 19870302) : ");
				colName = "birthday";
				String tmpBirthday = sc.next();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				try {
					long millis = sdf.parse(tmpBirthday).getTime();
					newValue = new Date(millis);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				break;
			case "3":
				System.out.print("변경할 이메일 : ");
				colName = "email";
				newValue = sc.next();
				break;
			case "4":
				System.out.print("변경할 주소 : ");
				colName = "address";
				sc.nextLine(); // 버퍼 비우기
				newValue = sc.nextLine();
				break;
			case "0":
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
			
			int result = memberController.updateMember(id, colName, newValue);
			printResultMsg(result, "회원정보 수정 완료", "회원정보 수정 실패");
		}
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
		System.out.println("> 아이디를 입력하세요.");
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
		String id = null;

		// 중복아이디 검사
		Member existM = null;
		do {
			System.out.print("아이디 : ");
			id = sc.next();
			existM = memberController.findMemberById(id);
			if (existM != null)
				System.out.println("> 사용불가능한 아이디입니다. 다시 입력하세요.");
		} while (existM != null);

		System.out.println("> 사용가능한 아이디입니다😀");

		member.setId(id);
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
