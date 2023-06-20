package dbutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class ConnectionPool {
	private static ConnectionPool instance = null;
	private DataSource dataSource;

	private ConnectionPool() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	public Connection getConnection() {
		try {
			Connection conn=dataSource.getConnection();
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void releaseConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(PreparedStatement ps) {
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void close(Statement st) throws SQLException {
		if(st != null) {
			st.close();
		}
		
	}
	
	public void close(ResultSet rs) throws SQLException {
		if(rs != null) {
			rs.close();
		}
	}

	public void shutdown() {
		if (dataSource instanceof BasicDataSource) {
			try {
				((BasicDataSource) dataSource).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
