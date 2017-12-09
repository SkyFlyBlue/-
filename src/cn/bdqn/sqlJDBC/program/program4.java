package cn.bdqn.sqlJDBC.program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class program4 {
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
					"root","");
			String sqlStr="select id '–Ú∫≈',name '–’√˚' From student";
			pstmt=conn.prepareStatement(sqlStr);
			rs=pstmt.executeQuery();
			System.out.println("–Ú∫≈\t–’√˚");
			while(rs.next()){
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t");
				System.out.println();
			}
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
