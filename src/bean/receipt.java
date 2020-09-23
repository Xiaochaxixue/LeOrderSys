package bean;

public class receipt {
	/**
	 * 新增客户发发票管理
	 * 给用户管理他相关联的订单的
	 * 发票相关信息。
	 */
	//private String ddanNum;//订单编号
	private String uid;//用户名
	private String title;//公司抬头信息
	private String tax;//公司发票的税号
	/**
	 * 新增需求，增加专用增值发票
	 * 包括发票抬头，企业纳税人识别号，开户银行，银行账号，注册地址，注册电话信息。
	 * 2020/09/23 PM 17：02 songlj
	 */
	private String bank;//开户银行
	private String banknumber;//银行账号
	private String registeadd;//注册地址
	private String registecall;//注册电话
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
