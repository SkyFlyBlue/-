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
		System.out.println("��ӭ����**********�û�����ϵͳ*********");
		System.out.println("1.ע�᣺");
		System.out.println("2.��½��");
		System.out.println("3.�˳���");
		System.out.print("=====����ѡ��");
		int choose=input.nextInt();
		switch (choose) {
		case 1:
			djb.register();
			break;
		case 2:
			boolean rowNum= djb.login();
			if(rowNum){
				System.out.println("*************��½�ɹ�***********");
				showSecondMenu();
				}
			else{
				System.out.println("��½ʧ��");
			}
			
			break;
		case 3:
			System.out.println("��ӭ�´ι��٣�");
			break;

		default:
			break;
		}
		
	}
	public static void showSecondMenu() throws SQLException{
		BaseJDBC djb=new BaseJDBC();
		System.out.println("1.�޸��û�");
		System.out.println("2.ɾ���û�");
		System.out.println("3.�������˵�");
		System.out.print("=====����ѡ��");
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
		System.out.println("������ע���ǳƣ�");
		String loginName=input.next();
		System.out.println("�����û�����");
		String name=input.next();
		System.out.println("�������䣺");
		String email=input.next();
		System.out.println("�������룺");
		String password=input.next();
		System.out.println("�����Ա�");
		int sex=input.nextInt();
		String preSql="insert into easybuy_user(loginName,userName,password,sex,email)values(?,?,?,?,?)";
		Object[] param={loginName,name,password,sex,email};
		int rowNum=this.exceuteUpdate(preSql, param);
		if(rowNum>0)
			System.out.println("ע��ɹ�");
		else
			System.out.println("ע��ʧ��");
		showFirsMenu();
	}
	private boolean login() throws SQLException{
		System.out.println("�������½�û�����");
		String name=input.next();
		System.out.println("���������룺");
		String password=input.next();
		String preSql="select * from easybuy_user where loginName=? and password=?";
		String[] param={name,password};
		boolean rowNum=this.resultQueryMain(preSql, param);
		return rowNum;
	}
	private void update(){
		System.out.println("��������Ҫ���ĵ�id��");
		int id=input.nextInt();
		System.out.println("��������ĺ���ǳƣ�");
		String loginName=input.next();
		String preSql="UPDATE  easybuy_user SET loginName=?  WHERE id=?";
		Object[] param={loginName,id};
		int rowNum=this.exceuteUpdate(preSql, param);
		if(rowNum>0)
			System.out.println("�޸ĳɹ�");
		else
			System.out.println("�޸�ʧ��");
	}
	private void delete(){
		System.out.println("��������Ҫɾ����id��");
		int id=input.nextInt();
		String preSql="delete from easybuy_user where id=?";
		Object[] param={id};
		int rowNum=this.exceuteUpdate(preSql, param);
		if(rowNum>0)
			System.out.println("�޸ĳɹ�");
		else
			System.out.println("�޸�ʧ��");
	}
}
