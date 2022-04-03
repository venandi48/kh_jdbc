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
import product.model.vo.ProductIO;

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
				Product product = new Product(rset.getString("id"), rset.getString("brand"), rset.getString("name"), rset.getInt("price"),
							rset.getInt("monitor_size"), rset.getString("os"), rset.getInt("storage"), rset.getDate("reg_date"), rset.getInt("stock"));
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
				Product product = new Product(rset.getString("id"), rset.getString("brand"), rset.getString("name"), rset.getInt("price"),
						rset.getInt("monitor_size"), rset.getString("os"), rset.getInt("storage"), rset.getDate("reg_date"), rset.getInt("stock"));
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

	public int insertProduct(Connection conn, Product product) {
		String sql = prop.getProperty("insertProduct");
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getId());
			pstmt.setString(2, product.getBrand());
			pstmt.setString(3, product.getName());
			pstmt.setInt(4, product.getPrice());
			pstmt.setInt(5, product.getMonitorSize());
			pstmt.setString(6, product.getOs());
			pstmt.setInt(7, product.getStorage());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductManagementException("insertProduct", e);
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int deleteProduct(Connection conn, String id) {
		String sql = prop.getProperty("deleteProduct");
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductManagementException("deleteProduct", e);
		} finally {
			close(pstmt);
		}

		return result;
	}

	public Product selectOne(Connection conn, String id) {
		String sql = prop.getProperty("selectOne");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Product product = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rset = pstmt.executeQuery();

			while (rset.next())
				product = new Product(rset.getString("id"), rset.getString("brand"), rset.getString("name"), rset.getInt("price"),
						rset.getInt("monitor_size"), rset.getString("os"), rset.getInt("storage"), rset.getDate("reg_date"), rset.getInt("stock"));

		} catch (SQLException e) {
			throw new ProductManagementException("selectOne", e);
		} finally {
			close(rset);
			close(pstmt);
		}

		return product;
	}

	public int updateProduct(Connection conn, String id, String col, Object newData) {
		String sql = prop.getProperty("updateProduct");
		sql = sql.replace("#", col);
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, newData);
			pstmt.setString(2, id);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductManagementException("updateProduct", e);
		} finally {
			close(pstmt);
		}

		return result;
	}

	public List<ProductIO> selectProductIO(Connection conn, String productId) {
		String sql = prop.getProperty("selectProductIO");
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<ProductIO> list = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				ProductIO productIO = new ProductIO(rset.getInt("no"), rset.getString("product_id"),
						rset.getInt("count"), rset.getString("status").charAt(0), rset.getTimestamp("io_datetime"));
				list.add(productIO);
			}
		} catch (SQLException e) {
			throw new ProductManagementException("selectProductIO", e);
		}
		return list;
	}

	public int changeProductStock(Connection conn, String productId, int count, String status) {
		String sql = prop.getProperty("addProductStock");
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productId);
			pstmt.setInt(2, count);
			pstmt.setString(3, status);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new ProductManagementException("addProductStock", e);
		} finally {
			close(pstmt);
		}

		return result;
	}

}
