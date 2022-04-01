package product.controller;

import java.util.List;

import product.model.service.ManagementService;
import product.model.vo.Product;
import product.model.vo.ProductIO;

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

	public int insertProduct(Product product) {
		int result = 0;
		try {
			result = managementService.insertProduct(product);
		} catch (Exception e) {
			System.err.println("[오류 : " + e.getMessage() + "] 관리자에게 문의하세요.");
		}
		return result;
	}

	public int deleteProduct(String id) {
		int result = 0;
		try {
			result = managementService.deleteProduct(id);
		} catch (Exception e) {
			System.err.println("[오류 : " + e.getMessage() + "] 관리자에게 문의하세요.");
		}
		return result;
	}

	public Product selectOne(String id) {
		Product product = null;
		try {
			product = managementService.selectOne(id);
		} catch (Exception e) {
			System.err.println("[오류 : " + e.getMessage() + "] 관리자에게 문의하세요.");
		}
		return product;
	}

	public int updateProduct(String id, String col, Object newData) {
		int result = 0;
		try {
			result = managementService.updateProduct(id, col, newData);
		} catch (Exception e) {
			System.err.println("[오류 : " + e.getMessage() + "] 관리자에게 문의하세요.");
		}
		return result;
	}

	public List<ProductIO> selectProductIO(String productId) {
		List<ProductIO> list = null;
		try {
			list = managementService.selectProductIO(productId);
		} catch (Exception e) {
			System.err.println("[오류 : " + e.getMessage() + "] 관리자에게 문의하세요.");
		}
		return list;
	}

	

}
