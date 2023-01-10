import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Post1000 {
	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost/shopdb?serverTimezone=UTC";
		String id = "root";	//ID
		String pw = "1234";		//PW
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
					
			conn =DriverManager.getConnection(url,id,pw);

			for (int i = 0; i < 1000; i++) {
				pstmt = conn.prepareStatement("insert into tbl_board values(null,?,?,?,now(),0,null,null,null)");
				pstmt.setString(1, "example" + i + "@example.com");
				pstmt.setString(2, "제목" + i);
				pstmt.setString(3, "내용" + i);
				pstmt.executeUpdate();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}

		}
	}
}
