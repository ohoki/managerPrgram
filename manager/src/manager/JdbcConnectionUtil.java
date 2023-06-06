package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//싱글톤(Singeton) 적용 - 메모리 상에서 객체가 반드시 하나만 생성되도록 하기 위해서 사용됨
public class JdbcConnectionUtil {
	private static JdbcConnectionUtil instance;
	
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "youngho";
	private String password = "1234";
	
	private JdbcConnectionUtil() {}
	
	public static JdbcConnectionUtil getInstance() {
		//웹이나 멀티쓰레드 환경에선 여러개의 객체가 생길 가능성이 있어서 동기화(synchronized)를 통해 이를 예방한다.
		synchronized (JdbcConnectionUtil.class) {
			if(instance == null) {
				instance = new JdbcConnectionUtil();
			}
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(Connection con) {
		if(con != null) {
			try {
				con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
