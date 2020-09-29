package bean;

public class gujian {
	private String gunum;//固件编号
	private String uid;//用户名
	private String cnum;//产品编号
	private String guversion;//固件版本
	private String guname;//固件名
	private int gustate;//固件状态
	private String gups;//固件备注
	private String qfile;//产品确认书(样品确认书)
	private String ruDate;//入库时间
	public String getGunum() {
		return gunum;
	}
	public void setGunum(String gunum) {
		this.gunum = gunum;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCnum() {
		return cnum;
	}
	public void setCnum(String cnum) {
		this.cnum = cnum;
	}
	public String getGuversion() {
		return guversion;
	}
	public void setGuversion(String guversion) {
		this.guversion = guversion;
	}
	public String getGuname() {
		return guname;
	}
	public void setGuname(String guname) {
		this.guname = guname;
	}
	public int getGustate() {
		return gustate;
	}
	public void setGustate(int gustate) {
		this.gustate = gustate;
	}
	public String getGups() {
		return gups;
	}
	public void setGups(String gups) {
		this.gups = gups;
	}
	public String getQfile() {
		return qfile;
	}
	public void setQfile(String qfile) {
		this.qfile = qfile;
	}
	public String getRuDate() {
		return ruDate;
	}
	public void setRuDate(String ruDate) {
		this.ruDate = ruDate;
	}
	
}
