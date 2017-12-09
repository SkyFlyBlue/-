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
		System.out.println("\t�������˵�½");
		System.out.print("������������");
		String name=input.next();
		System.out.print("���������룺");
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
				System.out.println("��ӭ����½�ɹ���");
			else
				System.out.println("��½ʧ�ܣ����������룡");
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
