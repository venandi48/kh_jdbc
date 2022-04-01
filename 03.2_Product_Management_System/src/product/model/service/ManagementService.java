package product.model.service;

import static common.JdbcTemplate.close;
import static common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import product.model.dao.ManagementDao;
import product.model.vo.Product;

public class ManagementService {

	private ManagementDao managementDao = new ManagementDao();

	public List<Product> selectAll() {
		List<Product> list = null;
		Connection conn = null;
		conn = getConnection();
		list = managementDao.selectAll(conn);
		close(conn);

		return list;
	}

	public int findStock(String id) {
		int count = 0;
		Connection conn = null;

		conn = getConnection();
		count = managementDao.findStock(conn, id);
		close(conn);

		return count;
	}

	public List<Product> selectProductList(String col, String searchData) {
		Connection conn = null;
		
		conn = getConnection();
		List<Product> list = managementDao.selectProductList(conn, col, searchData);
		close(conn);
		
		return list;
	}

}
