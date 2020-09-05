package bean;

public class dingdan {
	private String ddanNum;//订单编号
	private String uid;//用户名
	private String dealDate;//下单时间
	private float totalprice;//总金额
	private String ps;//备注
	private int state;//（订单） 状态 0表示正在处理
	private String makedealDate;//交货日期
	private String opa;//操作人
	public String getDdanNum() {
		return ddanNum;
	}
	public void setDdanNum(String ddanNum) {
		this.ddanNum = ddanNum;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDealDate() {
		return dealDate;
	}
	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}
	public float getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}
	public String getPs() {
		return ps;
	}
	public void setPs(String ps) {
		this.ps = ps;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMakedealDate() {
		return makedealDate;
	}
	public void setMakedealDate(String makedealDate) {
		this.makedealDate = makedealDate;
	}
	public String getOpa() {
		return opa;
	}
	public void setOpa(String opa) {
		this.opa = opa;
	}
	
	
}
