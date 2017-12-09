package cn.bdqn.sqlJDBC.program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Program3 {
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
					"jdbc:mysql://localhost:3306/shiwu",
					"li","123");
			String sqlStr="insert into student values(?,?)";
			pstmt=conn.prepareStatement(sqlStr);
			pstmt.setInt(1, 1);
			pstmt.setString(2, "张三");
			pstmt.executeUpdate();
			pstmt.setInt(1, 2);
			pstmt.setString(2, "李四");
			pstmt.executeUpdate();
			pstmt.setInt(1, 3);
			pstmt.setString(2, "王五");
			pstmt.executeUpdate();
			pstmt.setInt(1, 4);
			pstmt.setString(2, "赵六");
			pstmt.executeUpdate();
//			
			String sqlQuery="select count(*) from student";
			pstmt=conn.prepareStatement(sqlQuery);
			rs=pstmt.executeQuery();
			System.out.println("统计的数量");
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
