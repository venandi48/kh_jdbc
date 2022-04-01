package product.controller;

import java.util.List;

import product.model.service.ManagementService;
import product.model.vo.Product;

public class ManagementController {

	private ManagementService managementService = new ManagementService();

	public List<Product> selectAll() {
		List<Product> list = null;

		try {
			list = managementService.selectAll();
		} catch (Exception e) {
			System.err.println("[오류 : " + e.getMessage() + "] 관리자에게 문의하세요.");
		}

		return list;
	}

	public int findStock(String id) {
		int count = 0;

		try {
			count = managementService.findStock(id);
		} catch (Exception e) {
			System.err.println("[오류 : " + e.getMessage() + "] 관리자에게 문의하세요.");
		}

		return count;
	}

	public List<Product> selectProductList(String col, String searchData) {
		List<Product> list = null;

		try {
			list = managementService.selectProductList(col, searchData);
		} catch (Exception e) {
			System.err.println("[오류 : " + e.getMessage() + "] 관리자에게 문의하세요.");
		}

		return list;
	}

}
