package product.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import product.controller.ManagementController;
import product.model.vo.Product;
import product.model.vo.ProductIO;

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
			Product product = null;
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
				product = inputProduct();
				result = managementController.insertProduct(product);
				printResultMsg(result, "> 상품이 등록되었습니다.", "> 상품등록에 실패하였습니다.");
				break;
			case "4" :
				updateMenu(inputProductId());
				break;
			case "5":
				result = managementController.deleteProduct(inputProductId());
				printResultMsg(result, "> 상품이 삭제되었습니다.", "> 상품삭제에 실패하였습니다.");
				break;
			case "6":
				productIOMenu(); // 미완성
				break;
			case "0": return;
			default:
				System.out.println("> 잘못 입력하셨습니다.");
			}

		}
	}

	private void productIOMenu() {
		String menu = "\n******* 상품입출고 메뉴 🚚 *******\n"
				+ "1. 입출고내역 조회\n"
				+ "2. 상품 입고\n"
				+ "3. 상품 출고\n"
				+ "0. 메인메뉴로 돌아가기\n"
				+ "*****************************\n"
				+ "번호입력 : ";
		
		while(true) {
			System.out.print(menu);
			String choice = sc.next();
			List<ProductIO> list = null;
			
			switch(choice) {
			case "1":
				list = managementController.selectProductIO(inputProductId());
				printIOList(list);
				break;
			case "2": break;
			case "3": break;
			case "0": return;
			default:
				System.out.println("> 잘못 입력하셨습니다.");
			}
		}
	}

	private void printIOList(List<ProductIO> list) {
		
		
	}

	private void updateMenu(String id) {
		String menu = "\n****** 상품정보변경 메뉴 ✏ ******\n"
				+ "1. 상품명 변경\n"
				+ "2. 가격 변경\n"
				+ "3. 사양 변경\n"
				+ "0. 메인메뉴로 돌아가기\n"
				+ "*****************************\n"
				+ "번호입력 : ";
		
		Product product = managementController.selectOne(id);
		if(product == null) {
			System.out.println("> 입력하신 ID정보를 찾을 수 없습니다.\n");
			return;
		}
		
		while(true) {
			System.out.print(menu);
			String choice = sc.next();
			
			String col = null;
			Object newData = null;

			switch (choice) {
			case "1":
				col = "name";
				System.out.print("새로운 제품명 : ");
				newData = sc.next();
				break;
			case "2":
				col = "price";
				System.out.print("새로운 가격 : ");
				newData = sc.nextInt();
				break;
			case "3":
				col = selectSpec();
				switch (col) {
				case "monitor_size":
					System.out.print("새로운 모니터사이즈(inch) : ");
					newData = sc.nextInt();
					break;
				case "os":
					System.out.print("새로운 운영체제 : ");
					newData = sc.next();
					break;
				case "storage":
					System.out.print("새로운 저장용량(GB) : ");
					newData = sc.nextInt();
					break;
				default:
					continue;
				}
				break;
			case "0":
				return;
			default:
				System.out.println("> 잘못 입력하셨습니다.");
			}
			int result = managementController.updateProduct(id, col, newData);
			printResultMsg(result, "> 상품정보가 변경되었습니다.", "> 상품정보변경에 실패하였습니다.");
		}

	}

	private String selectSpec() {
		String choice = null;
		String menu = "\n-------- 변경할 사양 정보 --------\n"
				+ "1. 모니터 크기\n"
				+ "2. 운영체제\n"
				+ "3. 저쟝용량\n"
				+ "0. 이전 메뉴로 돌아가기\n"
				+ "-----------------------------\n"
				+ "번호입력 : ";
		System.out.print(menu);
		String col = null;
		
		while (true) {
			choice = sc.next();

			switch (choice) {
			case "1":
				col = "monitor_size";
				break;
			case "2":
				col = "os";
				break;
			case "3":
				col = "storage";
				break;
			case "0":
				return null;
			default:
				System.out.println("> 잘못 입력하셨습니다.");
			}
			return col;
		}

	}

	private Product inputProduct() {
		System.out.println("\n******* 💻 신규상품 등록 *******");
		Product product = new Product();

		while (true) {
			try {
				System.out.print("상품 ID : ");
				product.setId(sc.next());

				System.out.print("브랜드 : ");
				product.setBrand(sc.next());

				sc.nextLine(); // 버퍼비우기
				System.out.print("상품명 : ");
				product.setName(sc.nextLine());

				System.out.print("가격 : ");
				product.setPrice(sc.nextInt());

				System.out.print("모니터사이즈(inch) : ");
				product.setMonitorSize(sc.nextInt());

				System.out.print("운영체제 : ");
				product.setOs(sc.next());

				System.out.print("저장용량(GB) : ");
				product.setStorage(sc.nextInt());

				break;
			} catch (InputMismatchException e) {
				sc.nextLine(); // 버퍼비우기
				System.out.println("> 입력정보에 알맞은 형태로 입력해주세요.\n");
			}
		}

		return product;
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

			switch (choice) {
			case "1":
				col = "id";
				searchStr = inputProductId();
				break;
			case "2":
				col = "name";
				searchStr = inputProductName();
				break;
			case "0":
				return;
			default:
				System.out.println("> 잘못 입력하셨습니다.");
			}
			list = managementController.selectProductList(col, searchStr);
			printProductList(list);
		}

	}

	private String inputProductName() {
		System.out.println("\n🔍 상품명을 입력하세요.");
		System.out.print("상품명 : ");
		return sc.next();
	}

	private String inputProductId() {
		System.out.println("\n🔍 상품아이디를 입력하세요.");
		System.out.print("상품ID : ");
		return sc.next();
	}

	private void printProductList(List<Product> list) {
		if (list.isEmpty()) {
			System.out.println("> 등록된 상품이 없습니다.\n");
			return;
		}

		String line = "---------------------------------------------------------------------------------------------------------";

		System.out.println("\n> 조회결과");
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
	}
	
	private void printResultMsg(int result, String successMsg, String failureMsg) {
		if (result > 0)
			System.out.println(successMsg);
		else
			System.out.println(failureMsg);
	}

}
