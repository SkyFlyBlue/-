package cn.bdan.sqlJDBC.shiyan;

import java.sql.SQLException;
import java.util.Scanner;

public class BaseJDBC extends DiaoJDBC{
	static Scanner input=new Scanner(System.in);
	public static void main(String[] args) throws SQLException {
		showFirsMenu();
	}
	public static void showFirsMenu() throws SQLException{
		BaseJDBC djb=new BaseJDBC();
		System.out.println("欢迎进入**********用户管理系统*********");
		System.out.println("1.注册：");
		System.out.println("2.登陆：");
		System.out.println("3.退出：");
		System.out.print("=====》请选择：");
		int choose=input.nextInt();
		switch (choose) {
		case 1:
			djb.register();
			break;
		case 2:
			boolean rowNum= djb.login();
			if(rowNum){
				System.out.println("*************登陆成功***********");
				showSecondMenu();
				}
			else{
				System.out.println("登陆失败");
			}
			
			break;
		case 3:
			System.out.println("欢迎下次光临！");
			break;

		default:
			break;
		}
		
	}
	public static void showSecondMenu() throws SQLException{
		BaseJDBC djb=new BaseJDBC();
		System.out.println("1.修改用户");
		System.out.println("2.删除用户");
		System.out.println("3.返回主菜单");
		System.out.print("=====》请选择：");
		int chooseSecond=input.nextInt();
		switch (chooseSecond) {
		case 1:
			djb.update();
			break;
		case 2:
			djb.delete();
			break;
		case 3:
			showFirsMenu();
			break;

		default:
			break;
		}
		
	}
	private void register() throws SQLException{
		System.out.println("请输入注册昵称：");
		String loginName=input.next();
		System.out.println("输入用户名：");
		String name=input.next();
		System.out.println("输入邮箱：");
		String email=input.next();
		System.out.println("输入密码：");
		String password=input.next();
		System.out.println("输入性别：");
		int sex=input.nextInt();
		String preSql="insert into easybuy_user(loginName,userName,password,sex,email)values(?,?,?,?,?)";
		Object[] param={loginName,name,password,sex,email};
		int rowNum=this.exceuteUpdate(preSql, param);
		if(rowNum>0)
			System.out.println("注册成功");
		else
			System.out.println("注册失败");
		showFirsMenu();
	}
	private boolean login() throws SQLException{
		System.out.println("请输入登陆用户名：");
		String name=input.next();
		System.out.println("请输入密码：");
		String password=input.next();
		String preSql="select * from easybuy_user where loginName=? and password=?";
		String[] param={name,password};
		boolean rowNum=this.resultQueryMain(preSql, param);
		return rowNum;
	}
	private void update(){
		System.out.println("请输入需要更改的id：");
		int id=input.nextInt();
		System.out.println("请输入更改后的昵称：");
		String loginName=input.next();
		String preSql="UPDATE  easybuy_user SET loginName=?  WHERE id=?";
		Object[] param={loginName,id};
		int rowNum=this.exceuteUpdate(preSql, param);
		if(rowNum>0)
			System.out.println("修改成功");
		else
			System.out.println("修改失败");
	}
	private void delete(){
		System.out.println("请输入需要删除的id：");
		int id=input.nextInt();
		String preSql="delete from easybuy_user where id=?";
		Object[] param={id};
		int rowNum=this.exceuteUpdate(preSql, param);
		if(rowNum>0)
			System.out.println("修改成功");
		else
			System.out.println("修改失败");
	}
}
