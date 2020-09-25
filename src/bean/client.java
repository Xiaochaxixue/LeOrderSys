package bean;

//import java.util.Date;

public class client {
	private String uid;//用户名
	private String paw;//密码
	private String corname;//公司名称
	private String coraddress;//公司地址
	private String contact;//联系人
	private String phone;//联系方式
	private String address0;//收货地址1（默认地址）
	private String address1;//收货地址2  需求发生改变，该字段暂时不用
	private String address2;//收货地址3  需求发生改变，该字段暂时不用
	//private Date date;
	private String picture;//用户的营业执照 新增字段，存储用户的营业执照扫描件。
	
	
	@Override
	public String toString() {
		return "client [uid=" + uid + ", paw=" + paw + ", corname=" + corname + ", coraddress=" + coraddress
				+ ", contact=" + contact + ", phone=" + phone + ", address0=" + address0 + ", address1=" + address1
				+ ", address2=" + address2 + ", picture=" + picture + "]";
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPaw() {
		return paw;
	}
	public void setPaw(String paw) {
		this.paw = paw;
	}
	public String getCorname() {
		return corname;
	}
	public void setCorname(String corname) {
		this.corname = corname;
	}
	public String getCoraddress() {
		return coraddress;
	}
	public void setCoraddress(String coraddress) {
		this.coraddress = coraddress;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress0() {
		return address0;
	}
	public void setAddress0(String address0) {
		this.address0 = address0;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	
}
