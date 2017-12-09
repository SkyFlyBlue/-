package cn.bdqn.sqlDao.program;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class BaseDao {
	private static String driver;//数据库驱动字符串
	private static String url;//连接URL字符串
	private static String user;//数据库用户名
	private static String password;//用户密码
	//PreparedStatement pstmt=null;
	Connection conn=null;//数据连接对象
	static {//静态代码块，在类加载的时候执行
		init();
	}
	/**
	 * 初始化连接参数，从配置文件里获得。
	 */
	public static void init(){
		Properties parms=new Properties();
		String configFile="database.properties";//配置文件路径
		//加载配置文件到输入流中
		InputStream is=BaseDao.class.getClassLoader().getResourceAsStream(configFile);
		try{
			//从输入流中读取属性列表
			parms.load(is);
		}catch(IOException e){
			e.printStackTrace();
		}
		//获取指定对象的值
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
	 * 关闭数据库连接
	 * @param pstmt PreparedStatement对象
	 * @param conn 数据库连接
	 */
	public void closeAll(Connection conn,PreparedStatement pstmt){
		if(pstmt!=null){
			try{pstmt.close();}catch(Exception e){e.printStackTrace();}
		}
		if(conn!=null){
			try{conn.close();}catch(Exception e){e.printStackTrace();}
		}
	}
	/**
	 * 增操作
	 * @param preSql 预编译sql语言
	 * @param param 参数的字符串数组
	 * @param num 影响的行数
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
	
}
