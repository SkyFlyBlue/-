package cn.bdqn.jdbcTest;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;
public class TestMaxId {
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.out.println("11");
			e.printStackTrace();
		}
		try{
			conn=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myschool",
					"root","");
			String sqlStr="select max(StudentNo) as id From student";
			pstmt=conn.prepareStatement(sqlStr);
			rs=pstmt.executeQuery();
			System.out.println(rs);
			rs.next();
			System.out.println(rs.getInt(1));
		}catch(SQLException e){
			System.out.println("22");
			e.printStackTrace();
		}finally{
			try{
				if(null !=conn){
					conn.close();
				}
				if(null !=pstmt){
					pstmt.close();
				}
				if(null !=rs){
					rs.close();
				}
				
			}catch(SQLException e){
				System.out.println("33");
				e.printStackTrace();
			}
		}
	}
	
}
