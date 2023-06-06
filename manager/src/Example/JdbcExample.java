package Example;

import java.sql.Connection;
import java.sql.SQLException;

import manager.JdbcConnectionUtil;

public class JdbcExample {

	public static void main(String[] args) {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			//oracle.jdbc.driver.OracleDriver
			System.out.println("클래스 로딩!");
			
			conn = JdbcConnectionUtil.getInstance().getConnection();
			System.out.println("접속 성공!");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcConnectionUtil.getInstance().close(conn);
		}
	}
}
