package product.view;

import java.util.List;
import java.util.Scanner;

import product.controller.ManagementController;
import product.model.vo.Product;

public class ManagementMenu {
	
	private Scanner sc = new Scanner(System.in);
	private ManagementController managementController = new ManagementController();

	public void mainMenu() {
		String menu = "\n******* 📦 상품재고관리 프로그램 📦 *******\n"
				+ "1. 전체상품조회\n"
				+ "2. 상품검색\n"
				+ "3. 상품등록\n"
				+ "4. 상품정보변경\n"
				+ "5. 상품삭제\n"
				+ "6. 상품입/출고 메뉴\n"
				+ "0. 프로그램종료\n"
				+ "************************************\n"
				+ "번호입력 : ";
		
		while(true) {
			System.out.print(menu);
			String choice = sc.next();
			List<Product> list = null;
			int result = 0;
			
			switch(choice) {
			case "1" :
				list = managementController.selectAll();
				printProductList(list);
				break;
			case "2" :
				serchMenu();
				break;
			case "3" :
				break;
			case "4" : break;
			case "5" : break;
			case "6" : break;
			case "0" : return;
			default : System.out.println("> 잘못 입력하셨습니다.");
			}
			
		}
	}

	private void serchMenu() {
		String menu = "\n******* 상품검색 메뉴 🔍 *******\n"
				+ "1. 아이디 검색\n"
				+ "2. 상품명 검색\n"
				+ "0. 메인메뉴로 돌아가기\n"
				+ "****************************\n"
				+ "번호입력 : ";
		
		while(true) {
			System.out.print(menu);
			String choice = sc.next();
			
			String col = null;
			String searchStr = null;
			List<Product> list = null;
			
			switch(choice) {
			case "1":
				col = "id";
				searchStr = inputProductId();
				break;
			case "2":
				col = "name";
				searchStr = inputProductName();
				break;
			case "0": return;
			default : System.out.println("> 잘못 입력하셨습니다.");
			}
			list = managementController.selectProductList(col, searchStr);
			printProductList(list);
		}
		
	}

	private String inputProductName() {
		System.out.println("\n🔍 검색어를 입력하세요.");
		System.out.print("상품명 : ");
		return sc.next();
	}

	private String inputProductId() {
		System.out.println("\n🔍 검색어를 입력하세요.");
		System.out.print("상품아이디 : ");
		return sc.next();
	}

	private void printProductList(List<Product> list) {
		if (list.isEmpty()) {
			System.out.println("> 등록된 상품이 없습니다.\n");
			return;
		}

		String line = "---------------------------------------------------------------------------------------------------------";

		System.out.println("> 조회결과");
		System.out.println(line);
		System.out.printf("%20s%10s%15s%15s%20s%20s%n", "id", "brand", "name", "price", "spec", "stock");
		System.out.println(line);

		for (Product p : list) {
			int count = managementController.findStock(p.getId());
			
			String spec = p.getMonitorSize() + "인치 / " + p.getOs() + " / " + p.getStorage() + "GB";
			System.out.printf("%20s%10s%15s%,15d원 %30s%10s%n",
					p.getId(), p.getBrand(), p.getName(), p.getPrice(),
					spec, count);
		}

		System.out.println(line);
		System.out.println();
	}

}
