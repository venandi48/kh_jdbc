package product.model.dao;

import static common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import product.model.exception.ProductManagementException;
import product.model.vo.Product;

public class ManagementDao {

	Properties prop = new Properties();

	public ManagementDao() {
		try {
			prop.load(new FileReader("resources/management-query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Product> selectAll(Connection conn) {
		String sql = prop.getProperty("selectAll");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery();
			while (rset.next()) {
				Product product = new Product(rset.getString("id"), rset.getString("brand"), rset.getString("name"),
						rset.getInt("price"), rset.getInt("monitor_size"), rset.getString("os"), rset.getInt("storage"), rset.getDate("reg_date"));
				list.add(product);
			}
		} catch (SQLException e) {
			throw new ProductManagementException("selectAll", e);
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}

	public int findStock(Connection conn, String id) {
		String sql = prop.getProperty("findStock");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rset = pstmt.executeQuery();

			while (rset.next())
				count = rset.getInt("count");
		} catch (SQLException e) {
			throw new ProductManagementException("findStock", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return count;
	}

	public List<Product> selectProductList(Connection conn, String col, String searchData) {
		String sql = prop.getProperty("selectProductList");
		sql = sql.replace("#", col);
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Product> list = new ArrayList<>();

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchData + "%");

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product product = new Product(rset.getString("id"), rset.getString("brand"), rset.getString("name"),
						rset.getInt("price"), rset.getInt("monitor_size"), rset.getString("os"), rset.getInt("storage"), rset.getDate("reg_date"));
				list.add(product);
			}

		} catch (SQLException e) {
			throw new ProductManagementException("selectOne", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return list;
	}

}
