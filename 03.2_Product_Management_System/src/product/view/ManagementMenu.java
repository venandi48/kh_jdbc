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
		String menu = "\n******* π¦ μνμ¬κ³ κ΄λ¦¬ νλ‘κ·Έλ¨ π¦ *******\n"
				+ "1. μ μ²΄μνμ‘°ν\n"
				+ "2. μνκ²μ\n"
				+ "3. μνλ±λ‘\n"
				+ "4. μνμ λ³΄λ³κ²½\n"
				+ "5. μνμ­μ \n"
				+ "6. μνμ/μΆκ³  λ©λ΄\n"
				+ "0. νλ‘κ·Έλ¨μ’λ£\n"
				+ "************************************\n"
				+ "λ²νΈμλ ₯ : ";

		while (true) {
			System.out.print(menu);
			String choice = sc.next();
			List<Product> list = null;
			Product product = null;
			int result = 0;

			switch (choice) {
			case "1":
				list = managementController.selectAll();
				printProductList(list);
				break;
			case "2":
				serchMenu();
				break;
			case "3":
				product = inputProduct();
				result = managementController.insertProduct(product);
				printResultMsg(result, "> μνμ΄ λ±λ‘λμμ΅λλ€.", "> μνλ±λ‘μ μ€ν¨νμμ΅λλ€.");
				break;
			case "4":
				updateMenu(inputProductId());
				break;
			case "5":
				result = managementController.deleteProduct(inputProductId());
				printResultMsg(result, "> μνμ΄ μ­μ λμμ΅λλ€.", "> μνμ­μ μ μ€ν¨νμμ΅λλ€.");
				break;
			case "6":
				productIOMenu();
				break;
			case "0":
				return;
			default:
				System.out.println("> μλͺ» μλ ₯νμ¨μ΅λλ€.");
			}

		}
	}

	private void productIOMenu() {
		String menu = "\n******* μνμμΆκ³  λ©λ΄ π *******\n"
				+ "1. μμΆκ³ λ΄μ­ μ‘°ν\n"
				+ "2. μν μκ³ \n"
				+ "3. μν μΆκ³ \n"
				+ "0. λ©μΈλ©λ΄λ‘ λμκ°κΈ°\n"
				+ "*****************************\n"
				+ "λ²νΈμλ ₯ : ";

		while (true) {
			System.out.print(menu);
			String choice = sc.next();
			List<ProductIO> list = null;

			switch (choice) {
			case "1":
				list = managementController.selectProductIO(inputProductId());
				printIOList(list);
				break;
			case "2":
				addProductStock();
				break;
			case "3":
				dropProductStock();
				break;
			case "0":
				return;
			default:
				System.out.println("> μλͺ» μλ ₯νμ¨μ΅λλ€.");
			}
		}
	}

	private void dropProductStock() {
		int result = 0;

		String id = inputProductId();

		// μλ ₯ν μνμμ΄λκ° μ‘΄μ¬νμ§ μλκ²½μ°
		Product product = managementController.selectOne(id);
		if (product == null) {
			printResultMsg(result, "", "> μλ ₯νμ  IDμ μνμ μ°Ύμ μ μμ΅λλ€.");
			return;
		}

		// μλ ₯ν μνμμ΄λκ° μ‘΄μ¬νλ κ²½μ°
		System.out.println("\nπ μΆκ³ μλμ μλ ₯νμΈμ.");
		System.out.print("μΆκ³ μλ : ");
		int count = sc.nextInt();
		// μΆκ³ λ μ ν¨μ± κ²μ¬λ service λ¨μμ νλκ²μ΄ λ μ’μ
		if (count > product.getStock()) {
			System.err.println("> μλ ₯νμ  μλμ΄ νμ¬ μ¬κ³ λλ³΄λ€ λ§μ΅λλ€.");
			return;
		}

		result = managementController.changeProductStock(id, count, "O");
		printResultMsg(result, "> μΆκ³ μ²λ¦¬ μλ£", "> μΆκ³ μ²λ¦¬ μ€ν¨");

		return;
	}

	private void addProductStock() {
		int result = 0;

		String id = inputProductId();

		// μλ ₯ν μνμμ΄λκ° μ‘΄μ¬νμ§ μλκ²½μ°
		Product product = managementController.selectOne(id);
		if (product == null) {
			printResultMsg(result, "", "> μλ ₯νμ  IDμ μνμ μ°Ύμ μ μμ΅λλ€.");
			return;
		}

		// μλ ₯ν μνμμ΄λκ° μ‘΄μ¬νλ κ²½μ°
		System.out.println("\nπ μκ³ μλμ μλ ₯νμΈμ.");
		System.out.print("μκ³ μλ : ");
		int count = sc.nextInt();

		result = managementController.changeProductStock(id, count, "I");
		printResultMsg(result, "> μκ³ μ²λ¦¬ μλ£", "> μκ³ μ²λ¦¬ μ€ν¨");

		return;
	}

	private void printIOList(List<ProductIO> list) {
		if (list.isEmpty()) {
			System.err.println("> ν΄λΉ IDμ μμΆκ³ λ΄μ­μ μ°Ύμ μ μμ΅λλ€.\n");
			return;
		}

		String line = "------------------------------------------------------------------------------";

		System.out.println("\n> μ‘°νκ²°κ³Ό");
		System.out.println(line);
		System.out.printf("%5s%18s%8s%10s%30s%n", "no", "product", "count", "status", "io_datetime");
		System.out.println(line);

		for (ProductIO io : list) {
			System.out.printf("%5d%18s%8d%10c%30s%n", 
					io.getNo(), io.getProductId(), io.getCount(), io.getStatus(), io.getIoDatetme());
		}

		System.out.println(line);
	}

	private void updateMenu(String id) {
		String menu = "\n****** μνμ λ³΄λ³κ²½ λ©λ΄ β ******\n"
				+ "1. μνλͺ λ³κ²½\n"
				+ "2. κ°κ²© λ³κ²½\n"
				+ "3. μ¬μ λ³κ²½\n"
				+ "0. λ©μΈλ©λ΄λ‘ λμκ°κΈ°\n"
				+ "*****************************\n"
				+ "λ²νΈμλ ₯ : ";

		Product product = managementController.selectOne(id);
		if (product == null) {
			System.err.println("> μλ ₯νμ  IDμ λ³΄λ₯Ό μ°Ύμ μ μμ΅λλ€.\n");
			return;
		}

		while (true) {
			System.out.print(menu);
			String choice = sc.next();

			String col = null;
			Object newData = null;

			switch (choice) {
			case "1":
				col = "name";
				System.out.print("μλ‘μ΄ μ νλͺ : ");
				newData = sc.next();
				break;
			case "2":
				col = "price";
				System.out.print("μλ‘μ΄ κ°κ²© : ");
				newData = sc.nextInt();
				break;
			case "3":
				col = selectSpec();
				switch (col) {
				case "monitor_size":
					System.out.print("μλ‘μ΄ λͺ¨λν°μ¬μ΄μ¦(inch) : ");
					newData = sc.nextInt();
					break;
				case "os":
					System.out.print("μλ‘μ΄ μ΄μμ²΄μ  : ");
					newData = sc.next();
					break;
				case "storage":
					System.out.print("μλ‘μ΄ μ μ₯μ©λ(GB) : ");
					newData = sc.nextInt();
					break;
				default:
					continue;
				}
				break;
			case "0":
				return;
			default:
				System.out.println("> μλͺ» μλ ₯νμ¨μ΅λλ€.");
			}
			int result = managementController.updateProduct(id, col, newData);
			printResultMsg(result, "> μνμ λ³΄κ° λ³κ²½λμμ΅λλ€.", "> μνμ λ³΄λ³κ²½μ μ€ν¨νμμ΅λλ€.");
		}

	}

	private String selectSpec() {
		String choice = null;
		String menu = "\n-------- λ³κ²½ν  μ¬μ μ λ³΄ --------\n"
				+ "1. λͺ¨λν° ν¬κΈ°\n"
				+ "2. μ΄μμ²΄μ \n"
				+ "3. μ μμ©λ\n"
				+ "0. μ΄μ  λ©λ΄λ‘ λμκ°κΈ°\n"
				+ "-----------------------------\n"
				+ "λ²νΈμλ ₯ : ";
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
				System.out.println("> μλͺ» μλ ₯νμ¨μ΅λλ€.");
			}
			return col;
		}

	}

	private Product inputProduct() {
		System.out.println("\n******* π» μ κ·μν λ±λ‘ *******");
		Product product = new Product();

		while (true) {
			try {
				System.out.print("μν ID : ");
				product.setId(sc.next());

				System.out.print("λΈλλ : ");
				product.setBrand(sc.next());

				sc.nextLine(); // λ²νΌλΉμ°κΈ°
				System.out.print("μνλͺ : ");
				product.setName(sc.nextLine());

				System.out.print("κ°κ²© : ");
				product.setPrice(sc.nextInt());

				System.out.print("λͺ¨λν°μ¬μ΄μ¦(inch) : ");
				product.setMonitorSize(sc.nextInt());

				System.out.print("μ΄μμ²΄μ  : ");
				product.setOs(sc.next());

				System.out.print("μ μ₯μ©λ(GB) : ");
				product.setStorage(sc.nextInt());

				break;
			} catch (InputMismatchException e) {
				sc.nextLine(); // λ²νΌλΉμ°κΈ°
				System.err.println("> μλ ₯μ λ³΄μ μλ§μ ννλ‘ μλ ₯ν΄μ£ΌμΈμ.\n");
			}
		}

		return product;
	}

	private void serchMenu() {
		String menu = "\n******* μνκ²μ λ©λ΄ π *******\n"
				+ "1. μμ΄λ κ²μ\n"
				+ "2. μνλͺ κ²μ\n"
				+ "0. λ©μΈλ©λ΄λ‘ λμκ°κΈ°\n"
				+ "****************************\n"
				+ "λ²νΈμλ ₯ : ";

		while (true) {
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
				System.out.println("> μλͺ» μλ ₯νμ¨μ΅λλ€.");
			}
			list = managementController.selectProductList(col, searchStr);
			printProductList(list);
		}

	}

	private String inputProductName() {
		System.out.println("\nπ μνλͺμ μλ ₯νμΈμ.");
		System.out.print("μνλͺ : ");
		return sc.next();
	}

	private String inputProductId() {
		System.out.println("\nπ μνμμ΄λλ₯Ό μλ ₯νμΈμ.");
		System.out.print("μνID : ");
		return sc.next();
	}

	private void printProductList(List<Product> list) {
		if (list.isEmpty()) {
			System.out.println("> λ±λ‘λ μνμ΄ μμ΅λλ€.\n");
			return;
		}

		String line = "---------------------------------------------------------------------------------------------------------";

		System.out.println("\n> μ‘°νκ²°κ³Ό");
		System.out.println(line);
		System.out.printf("%20s%10s%15s%15s%25s%20s%n", "id", "brand", "name", "price", "spec", "stock");
		System.out.println(line);

		for (Product p : list) {
			
			String spec = p.getMonitorSize() + "μΈμΉ / " + p.getOs() + " / " + p.getStorage() + "GB";
			System.out.printf("%20s%10s%15s%,15dμ %30s%10s%n",
					p.getId(), p.getBrand(), p.getName(), p.getPrice(), spec, p.getStock());
		}

		System.out.println(line);
	}

	private void printResultMsg(int result, String successMsg, String failureMsg) {
		if (result > 0)
			System.out.println(successMsg);
		else
			System.err.println(failureMsg);
	}

}
