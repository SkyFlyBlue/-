package cn.bdqn.sqlDao.program;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		CarDao carDao=new CarDaoMySQLImpl();
		Scanner input=new Scanner(System.in);
		System.out.println("��¼��������˰���밴��ʾ¼�������Ϣ��");
		System.out.print("�����복�����֤���루18λ����");
		String sfz=input.next();
		System.out.print("�����복��ʶ���루17λ����");
		String clsbm=input.next();
		System.out.print("�����복��������");
		double pl=input.nextDouble();
		System.out.print("������ٷ�ָ���ۣ�");
		int gPrice=input.nextInt();
		System.out.print("�����뷢Ʊ�۸�");
		int fPrice=input.nextInt();
		CarObj car=new CarObj(sfz, clsbm, pl, gPrice, fPrice);
		carDao.save(car);
		System.out.println("���ݱ���ɹ�����������˰Ϊ��"+car.getGzs());
	}
}
