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
	
	private int tiantype;//天线类型 ，需求更改该字段可能不用
	private String pt;//排针焊法，需求更改该字段可能不用（改为了工艺说明字段）
	private int pnum;//排针数量，需求更改该字段可能不用
	
	private float total;//该字段为订单商品表的总金额
	/**
	 * ①将固件信息也简单的存入
	 * ②将工艺信息也存入
	 * 以下新添加字段2020/10/07 11：49PM songlj
	 * 数据字段已经在数据库中进行更改2020/10/07 14：10 PM songlj
	 */
	private String guversion;//固件版本
	/**************分割线，上面是固件信息下面是工艺信息****************/
	private String pinNum;//排针数量
	private String pinSize;//排针大小
	private String pinShape;//排针形状
	private String pinWeld;//焊接方式
	/***********排针工艺*****************/
	private String antennaType;//天线类型
	private String antennaLength;//天线长度
	
	@Override
	public String toString() {
		return "dingshopping [ddanNum=" + ddanNum + ", uid=" + uid + ", gunum=" + gunum + ", cnum=" + cnum + ", cname="
				+ cname + ", sselect=" + sselect + ", sstate=" + sstate + ", ctype=" + ctype + ", danwei=" + danwei
				+ ", number=" + number + ", price=" + price + ", picture=" + picture + ", ruDate=" + ruDate
				+ ", tiantype=" + tiantype + ", pt=" + pt + ", pnum=" + pnum + ", total=" + total + ", guversion="
				+ guversion + ", pinNum=" + pinNum + ", pinSize=" + pinSize + ", pinShape=" + pinShape + ", pinWeld="
				+ pinWeld + ", antennaType=" + antennaType + ", antennaLength=" + antennaLength + "]";
	}
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

	public String getGuversion() {
		return guversion;
	}


	public void setGuversion(String guversion) {
		this.guversion = guversion;
	}

	public String getPinNum() {
		return pinNum;
	}


	public void setPinNum(String pinNum) {
		this.pinNum = pinNum;
	}


	public String getPinSize() {
		return pinSize;
	}


	public void setPinSize(String pinSize) {
		this.pinSize = pinSize;
	}


	public String getPinShape() {
		return pinShape;
	}

	public void setPinShape(String pinShape) {
		this.pinShape = pinShape;
	}

	public String getPinWeld() {
		return pinWeld;
	}

	public void setPinWeld(String pinWeld) {
		this.pinWeld = pinWeld;
	}

	public String getAntennaType() {
		return antennaType;
	}

	public void setAntennaType(String antennaType) {
		this.antennaType = antennaType;
	}

	public String getAntennaLength() {
		return antennaLength;
	}

	public void setAntennaLength(String antennaLength) {
		this.antennaLength = antennaLength;
	}
	
	
}
