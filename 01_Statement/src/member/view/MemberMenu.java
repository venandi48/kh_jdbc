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
 * view패키지 클래스
 *  - 사용자가 보게될 화면을 담당하는 클래스
 *  - 메뉴, 사용자입력값 처리, 요청한 데이터 출력
 *
 */
public class MemberMenu {
	
	private Scanner sc = new Scanner(System.in);
	private MemberController controller = new MemberController();

	public void mainMenu() {
		String menu = "========= 회원관리 =========\n"
					+ "1. 회원 전체 조회\n"
					+ "2. 회원 아이디 조회\n"
					+ "3. 회원 이름 검색\n"
					+ "4. 회원 가입\n"
					+ "5. 회원 정보 변경\n"
					+ "6. 회원 탈퇴\n"
					+ "0. 프로그램 종료\n"
					+ "--------------------------\n"
					+ "선택 : " ;
		
		while(true) {
			System.out.print(menu);
			String choice = sc.next();
			
			List<Member> list = null;
			Member newMember = null;
			Member member = null;
			int result = 0;
			String id = null;
			String name = null;
			
			switch(choice) {
			case "1" : 
				list = controller.selectAll();
				printMemberList(list);
				break;
			case "2" : 
				id = inputId();
				member = controller.selectOne(id);
				printMember(member);
				break;
			case "3" :
				break;
			case "4" : 
				newMember = inputMember();
				result = controller.insertMember(newMember);
				System.out.println(result > 0 ? "> 회원 가입 완료" : "> 회원 가입 실패");
				break;
			case "5" : break;
			case "6" : break;
			case "0" : return;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}

	

	/**
	 * 회원 1명 출력 메소드
	 *  - null
	 *  - member 1개
	 */
	private void printMember(Member member) {
		if(member == null)
			System.out.println("> 조회된 회원이 없습니다.");
		else {
			System.out.println("===============================");
			System.out.printf("id : %s%n", member.getId());
			System.out.printf("name : %s%n", member.getName());
			System.out.printf("gender : %s%n", member.getGender());
			System.out.printf("birthday : %s%n", member.getBirthday());
			System.out.printf("email : %s%n", member.getEmail());
			System.out.printf("address : %s%n", member.getAddress());
			System.out.printf("regDate : %s%n", member.getRegDate());
			System.out.println("------------------------------");
		}
	}

	/**
	 * 조회할 id를 입력하는 메소드
	 */
	private String inputId() {
		System.out.println("------ 조회할 id 입력 ------");
		System.out.print("id : ");
		return sc.next();
	}

	/**
	 * 여러행 조회결과 출력 메소드
	 * (list가 null인 경우 없음)
	 *  - 0행 조회
	 *  - n행 조회
	 */
	public void printMemberList(List<Member> list) {
		if (list.isEmpty())
			System.out.println("> 조회된 회원이 없습니다.");
		else {
			System.out.println("> 조회결과");
			System.out.println("===================================================");
			System.out.printf("%15s%15s%15s%15s%15s%15s%15s%n",
					"id", "name", "gender","birthday", "email", "address", "regDate");
			System.out.println("---------------------------------------------------");

			for (Member m : list) {
				System.out.printf("%15s%15s%15s%15s%15s%15s%15s%n",
						m.getId(), m.getName(), m.getGender(), m.getBirthday(),
						m.getEmail(), m.getAddress(), m.getRegDate());
			}
			System.out.println("---------------------------------------------------");
			System.out.println();
		}
	}

	/**
	 * 사용자 입력값으로 member객체 생성 메소드
	 */
	private Member inputMember() {
		System.out.println("----- 신규 회원 정보 입력 -----");
		
		System.out.print("아이디 : ");
		String id = sc.next();
		System.out.print("이름 : ");
		String name = sc.next();
		System.out.print("성별(M/F) : ");
		String gender = String.valueOf(sc.next().toUpperCase().charAt(0));
		
		System.out.print("생일(예: 19950401) : ");
		String tmp_birthday = sc.next();
		Date birthday = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
			birthday = new Date(sdf.parse(tmp_birthday).getTime()); // java.util.Date -> java.sql.Date
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.print("이메일 : ");
		String email = sc.next();
		
		sc.nextLine(); // 버퍼비우기
		System.out.print("주소 : ");
		String address = sc.nextLine();

		return new Member(id, name, gender, birthday, email, address, null);
	}

}
