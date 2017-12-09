package cn.bdqn.sqlDao.program;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		CarDao carDao=new CarDaoMySQLImpl();
		Scanner input=new Scanner(System.in);
		System.out.println("记录车辆购置税，请按提示录入相关信息：");
		System.out.print("请输入车主身份证号码（18位）：");
		String sfz=input.next();
		System.out.print("请输入车辆识别码（17位）：");
		String clsbm=input.next();
		System.out.print("请输入车辆排量：");
		double pl=input.nextDouble();
		System.out.print("请输入官方指导价：");
		int gPrice=input.nextInt();
		System.out.print("请输入发票价格：");
		int fPrice=input.nextInt();
		CarObj car=new CarObj(sfz, clsbm, pl, gPrice, fPrice);
		carDao.save(car);
		System.out.println("数据保存成功，车辆购置税为："+car.getGzs());
	}
}
