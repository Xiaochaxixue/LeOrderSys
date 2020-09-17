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
	
}
