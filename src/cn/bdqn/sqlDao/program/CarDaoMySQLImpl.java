package cn.bdqn.sqlDao.program;

public class CarDaoMySQLImpl extends BaseDao implements CarDao{
	//±£´æÆû³µ
	public int save(CarObj car){
		String sql="insert into carmaster values(?,?,?,?,?,?)";
		Object[] param={car.getSfz(),car.getClsbm(),car.getPl(),car.getgPrice(),car.getfPrice(),car.getGzs()};
		int result=this.exceuteUpdate(sql, param);
		return result;
	}
}
