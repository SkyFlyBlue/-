package cn.bdqn.jdbcTest;

import java.sql.DriverManager;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Test3 {
	private static Logger logger=Logger.getLogger(Test3.class.getName());
	public static void main(String[] args) {
		Connection conn=null;
		Statement stat=null;
		int bid=105;
		String bName="天才在左，疯子在右";
		String author="罗伊人";
		String pubComp="书店出版社";
		String pubDate="2017-10-01";
		int bCount=5;
		int price=45;
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
			StringBuffer sbsql=new StringBuffer("insert into book(bid,bName,author,pubComp,pubDate,bCount,price)" +
					"values(");
			sbsql.append(bid+",'").append(bName+"','").append(author+"','").append(pubComp+"','").append(pubDate+"',")
			.append(bCount+",").append(price+")");
			System.out.println(sbsql.toString());
			stat.execute(sbsql.toString());
			logger.info("插入书籍成功！");
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
