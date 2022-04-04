package product.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.commit;
import static common.JdbcTemplate.getConnection;
import static common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import product.model.dao.ManagementDao;
import product.model.vo.Product;
import product.model.vo.ProductIO;

public class ManagementService {
	/**
	 * application의 필요한 상수는 주로 업무로직 담당인 Service단에 작성한다.
	 */
	public static final String PRODUCT_INPUT = "I";		//입고
	public static final String PRODUCT_OUTPUT = "O";	//출고

	private ManagementDao managementDao = new ManagementDao();

	public List<Product> selectAll() {
		List<Product> list = null;
		Connection conn = null;
		conn = getConnection();
		list = managementDao.selectAll(conn);
		close(conn);

		return list;
	}

	public List<Product> selectProductList(String col, String searchData) {
		Connection conn = null;
		
		conn = getConnection();
		List<Product> list = managementDao.selectProductList(conn, col, searchData);
		close(conn);
		
		return list;
	}

	public int insertProduct(Product product) {
		Connection conn = null;
		int result = 0;

		try {
			conn = getConnection();
			result = managementDao.insertProduct(conn, product);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}

		return result;
	}

	public int deleteProduct(String id) {
		Connection conn = null;
		int result = 0;

		try {
			conn = getConnection();
			result = managementDao.deleteProduct(conn, id);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}

		return result;
	}

	public Product selectOne(String id) {
		Connection conn = null;
		Product product = null;
		
		conn = getConnection();
		product = managementDao.selectOne(conn, id);
		close(conn);
		
		return product;
	}

	public int updateProduct(String id, String col, Object newData) {
		Connection conn = null;
		int result = 0;
		try {
			conn = getConnection();
			result = managementDao.updateProduct(conn, id, col, newData);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public List<ProductIO> selectProductIO(String productId) {
		Connection conn = null;
		List<ProductIO> list = null;
		
		conn = getConnection();
		list = managementDao.selectProductIO(conn, productId);
		close(conn);
		
		return list;
	}

	public int changeProductStock(String productId, int count, String status) {
		Connection conn = null;
		int result = 0;

		try {
			conn = getConnection();
			result = managementDao.changeProductStock(conn, productId, count, status);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}

		return result;
	}


}
