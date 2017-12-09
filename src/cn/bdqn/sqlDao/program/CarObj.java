package cn.bdqn.sqlDao.program;

public class CarObj {
	private String sfz; //身份证
	private String clsbm;//车辆识别码
	private double pl;//车辆排量
	private int gPrice; //官方指导价
	private int fPrice;//发票价格
	private double gzs;//车辆购置税
	public CarObj(){
	}
	public CarObj(String sfz, String clsbm, double pl, int gPrice, int fPrice) {
		super();
		this.sfz = sfz;
		this.clsbm = clsbm;
		this.pl = pl;
		this.gPrice = gPrice;
		this.fPrice = fPrice;
	}
	public String getSfz() {
		return sfz;
	}
	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	public String getClsbm() {
		return clsbm;
	}
	public void setClsbm(String clsbm) {
		this.clsbm = clsbm;
	}
	public double getPl() {
		return pl;
	}
	public void setPl(double pl) {
		this.pl = pl;
	}
	public int getgPrice() {
		return gPrice;
	}
	public void setgPrice(int gPrice) {
		this.gPrice = gPrice;
	}
	public int getfPrice() {
		return fPrice;
	}
	public void setfPrice(int fPrice) {
		this.fPrice = fPrice;
	}
	public int getGzs(){
		double result=fPrice/(1+0.17);
		if(pl<=1.6){
			gzs=result*0.075;
		}else{
			gzs=result*0.1;
		}
		return (int)gzs;
	}
}