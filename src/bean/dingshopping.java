package bean;

//import java.util.Date;

public class dingshopping {
	
	
	private String ddanNum;//订单编号
	private String uid;//新添加的字段：用户id 2020/08/21 11：59 songlj
	private String gunum;//固件编号
	private String cnum;//产品编号
	
	private String cname;//产品名称 新添加字段：产品名称  2020/09/21 16：04PM songlj
	
	private int sselect;//新添加的字段：可选状态sselect 2020/08/21 11：59 songlj
	private int sstate;//商品状态  新添加的字段2020/08/24 13：41 songlj
	private String ctype;//商品类型，新添加的字段2020/09/07 PM 15：53 songlj
	private String danwei;//单位
	private int number;//数量
	private float price;//单价
	private String picture;//图片地址   新添加的字段2020/08/24 9：13 songlj
	private String ruDate;//入库时间   新添加的字段2020/08/24 9：13 songlj
	/**
	 * 给出的商品需求与自己理解的不符合
	 * 2020/09/04 10：59 songlj
	 */
	
	private int tiantype;//天线类型 ，需求更改该字段可能不用
	private String pt;//排针焊法，需求更改该字段可能不用
	private int pnum;//排针数量，需求更改该字段可能不用
	
	
	private float total;//该字段为订单商品表的总金额
	
	@Override
	public String toString() {
		return "dingshopping [ddanNum=" + ddanNum + ", uid=" + uid + ", gunum=" + gunum + ", cnum=" + cnum
				+ ", sselect=" + sselect + ", sstate=" + sstate + ", danwei=" + danwei + ", number=" + number
				+ ", price=" + price + ", picture=" + picture + ", ruDate=" + ruDate + ", tiantype=" + tiantype
				+ ", pt=" + pt + ", pnum=" + pnum + ", total=" + total + "]";
	}
	
	
	//private Date date; 
	public String getDdanNum() {
		return ddanNum;
	}
	public void setDdanNum(String ddanNum) {
		this.ddanNum = ddanNum;
	}
	public String getCnum() {
		return cnum;
	}
	public void setCnum(String cnum) {
		this.cnum = cnum;
	}
	public String getDanwei() {
		return danwei;
	}
	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getTiantype() {
		return tiantype;
	}
	public void setTiantype(int tiantype) {
		this.tiantype = tiantype;
	}
	public String getPt() {
		return pt;
	}
	public void setPt(String pt) {
		this.pt = pt;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
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
	public int getSselect() {
		return sselect;
	}
	public void setSselect(int sselect) {
		this.sselect = sselect;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getRuDate() {
		return ruDate;
	}
	public void setRuDate(String ruDate) {
		this.ruDate = ruDate;
	}
	public int getSstate() {
		return sstate;
	}
	public void setSstate(int sstate) {
		this.sstate = sstate;
	}


	public String getCtype() {
		return ctype;
	}


	public void setCtype(String ctype) {
		this.ctype = ctype;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}
	
}
