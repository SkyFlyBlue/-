package cn.bdqn.jdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;


import cn.bdqn.odbc.Test1;

public class Test2 {
	private static Logger logger=Logger.getLogger(Test1.class.getName());
	public static void main(String[] args) {
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.err.println("1");
			logger.error(e);
		}
		try{
			conn=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/myschool",
					"root","");
			System.out.println("建立连接成功！");
		}catch(SQLException e){
			System.err.println("2");
			logger.error(e);
		}finally{
			try{
				if(null !=conn){
					conn.close();
					System.out.println("关闭连接成功！");
				}
			}catch(SQLException e){
				System.err.println("3");
				logger.error(e);
			}
		}
	}
}
