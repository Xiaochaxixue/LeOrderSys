package bean;



public class user {
	private String uid;//用户名
	private String paw;//密码
	private int type;//类型,0代表超级管理员，1代表用户，2代表销售部，3代表品质部
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
