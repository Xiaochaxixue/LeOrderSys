package bean;

public class dingdan {
	private String ddanNum;//订单编号
	private String uid;//用户名
	private String dealDate;//下单时间
	private float totalprice;//总金额
	private String ps;//备注
	private int state;//（订单） 状态 0表示正在处理，订单0：表示
	private String makedealDate;//交货日期
	private String opa;//操作人
	/**
	 * 订单表添加新的数据库字段，主要是两方面的信息
	 * ①收货信息
	 * ②发票信息
	 * 2020/10/08 9：27PM songlj
	 */
	/**
	 * 收货信息，2020/10/08 9：34AM songlj
	 * 以下是新添加的订单表字段
	 */
	private String acceptor;//收货人   2020/09/18 13PM songlj
	private String tel;//收货电话号码   2020/09/18 13PM songlj
	private String address;//收货地址   2020/09/18 13PM songlj
	/**
	 * 发票信息，2020/10/08 9：34AM songlj
	 * 以下是新添加的订单字段
	 */
	private String title;//公司抬头信息
	private String tax;//公司发票的税号
	private String bank;//开户银行
	private String banknumber;//银行账号
	private String registeadd;//注册地址
	private String registecall;//注册电话
	
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
	public String getAcceptor() {
		return acceptor;
	}
	public void setAcceptor(String acceptor) {
		this.acceptor = acceptor;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBanknumber() {
		return banknumber;
	}
	public void setBanknumber(String banknumber) {
		this.banknumber = banknumber;
	}
	public String getRegisteadd() {
		return registeadd;
	}
	public void setRegisteadd(String registeadd) {
		this.registeadd = registeadd;
	}
	public String getRegistecall() {
		return registecall;
	}
	public void setRegistecall(String registecall) {
		this.registecall = registecall;
	}
	
}
