package bean;

public class tb_address {
	/**
	 * 用户的收货等相关信息
	 */
	private String uid;//用户的用户名   2020/09/18 13PM songlj
	private String acceptor;//收货人   2020/09/18 13PM songlj
	private String tel;//收货电话号码   2020/09/18 13PM songlj
	private String address;//收货地址   2020/09/18 13PM songlj
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	
	
}
