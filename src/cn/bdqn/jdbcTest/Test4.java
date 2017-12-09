package cn.bdqn.jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class Test4 {
	private static Logger logger=Logger.getLogger(Test4.class.getName());
	public static void main(String[] args) {
		Connection conn=null;
		Statement stat=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			logger.error(e);
		}
		try{
			conn= DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/library",
					"li","123");
			stat=conn.createStatement();
			rs=stat.executeQuery("select bid,bName,author,price from book limit 4");
			System.out.println("\t\t图书信息表");
			System.out.println("编号\t书名\t\t作者\t价格");
			while(rs.next()){
				System.out.print(rs.getInt(1)+"\t");
				System.out.print(rs.getString(2)+"\t\t");
				System.out.print(rs.getString(3)+"\t");
				System.out.print(rs.getFloat("price")+"\t");
				System.out.println();
			}
		}catch(SQLException e){
			logger.error(e);
		}finally{
			try{
				if(null !=stat){
					stat.close();
				}
				if(null !=conn){
					conn.close();
				}
			}catch(SQLException e){
				logger.error(e);
			}
		}
	}
}
