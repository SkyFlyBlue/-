package cn.bdqn.sqlJDBC.program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import java.sql.PreparedStatement;

public class Program5 {
	private static Logger logger=Logger.getLogger(Program5.class.getName());
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Scanner input=new Scanner(System.in);
		System.out.println("\t宠物主人登陆");
		System.out.print("请输入姓名：");
		String name=input.next();
		System.out.print("请输入密码：");
		String password=input.next();
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			logger.error(e);
		}
		try{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/shiwu",
					"li","123");
			
			String sql="select * from student where `name`='"+name+"' and `password`='"+password+"'";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
				System.out.println("欢迎您登陆成功！");
			else
				System.out.println("登陆失败，请重新输入！");
		}catch(SQLException e){
			logger.error(e);
		}
		finally{
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
				logger.error(e);
			}
		}
	}
}
