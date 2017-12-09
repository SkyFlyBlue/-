package cn.bdqn.odbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class Test1 {
	private static Logger logger=Logger.getLogger(Test1.class.getName());
	public static void main(String[] args) {
		Connection conn=null;
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		}catch(ClassNotFoundException e){
			System.err.println("11");
		}
		try{
			conn=DriverManager.getConnection("jdbc:odbc:mychool","root"," ");
			System.out.println("1111");
			System.out.println("建立连接成功");
		}catch(SQLException e){
			System.err.println("22");
		}finally{
			try{
				if(null !=conn){
					conn.close();
					System.out.println("关闭连接成功！");
				}
			}catch(SQLException e){
				System.err.println("33");
			}
		}
	}
}
