package cn.bdan.sqlJDBC.shiyan;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import cn.bdqn.sqlDao.program.BaseDao;

public class DiaoJDBC {
	private static String driver;//���ݿ������ַ���
	private static String url;//����URL�ַ���
	private static String user;//���ݿ��û���
	private static String password;//�û�����
//	PreparedStatement pstmt=null;
	ResultSet rs=null;
	Connection conn=null;//�������Ӷ���
	static {//��̬����飬������ص�ʱ��ִ��
		init();
	}
	/**
	 * ��ʼ�����Ӳ������������ļ����á�
	 */
	public static void init(){
		Properties parms=new Properties();
		String configFile="database1.properties";//�����ļ�·��
		//���������ļ�����������
		InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(configFile);
		try{
			//���������ж�ȡ�����б�
			parms.load(is);
		}catch(IOException e){
			e.printStackTrace();
		}
		//��ȡָ�������ֵ
		driver=parms.getProperty("driver");
		url=parms.getProperty("url");
		user=parms.getProperty("username");
		password=parms.getProperty("password");
	}
	public Connection getConnection(){
		if(conn==null){
			try{
				Class.forName(driver);
				conn=DriverManager.getConnection(url, user, password);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return conn;
	}
	/**
	 * �ر����ݿ�����
	 * @param pstmt PreparedStatement����
	 * @param conn ���ݿ�����
	 */
	public void closeAll(Connection conn,PreparedStatement pstmt){
		if(pstmt!=null){
			try{pstmt.close();}catch(Exception e){e.printStackTrace();}
		}
		if(rs!=null){
			try{rs.close();}catch(Exception e){e.printStackTrace();}			
		}
		if(conn!=null){
			try{conn.close();}catch(Exception e){e.printStackTrace();}
		}
	}
	/**
	 * ������
	 * @param preSql Ԥ����sql����
	 * @param param �������ַ�������
	 * @param num Ӱ�������
	 */
	public int exceuteUpdate(String preSql,Object[] param){
		PreparedStatement pstmt=null;
		int num=0;
		conn=getConnection();
		try{
			pstmt=conn.prepareStatement(preSql);
			if(param!=null){
				for(int i=0;i<param.length;i++){
					pstmt.setObject(i+1, param[i]);
				}
			}
			num=pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(conn, pstmt);
		}
		return num;
	}
	/**
	 * ����
	 * @param preSql
	 * @return �Ƿ�治��������û�
	 */
	public boolean resultQueryMain(String preSql,String[] param){
		PreparedStatement pstmt=null;
		boolean num=false;
		conn=getConnection();
		try{
			pstmt=conn.prepareStatement(preSql);
			if(param!=null){
				for(int i=0;i<param.length;i++){
					pstmt.setObject(i+1, param[i]);
				}
			}
			rs=pstmt.executeQuery();
			num=rs.next();
			if(num==true){
			System.out.println("�û�ID:"+rs.getInt("id")+"\n�û���ʵ������"+rs.getString("userName"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(conn, pstmt);
		}
		return num;
	}
	
}
