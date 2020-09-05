package bean;

//import java.util.Date;

/**
 * @author LeMeshCloudDev
 *
 */
public class shoppinginfo {
	
	private String cnum;//产品编号
	private String ctype;//产品类型 1：芯片（IC）、2：模组（MK/MIMK/JDMK）、3：PCBA、4：成品   2020/09/04 修改songlj
						/**
						 * 修改ctype int 类型为String类型
						 * 2020/09/04  11：14修改songlj
						 */
	private String cname;//产品名称
	private String guige;//规格
	private String danwei;//单位
	private float price;//单价
	private int tiantype;//天线类型
	private String pt;//排针焊法         
					/**
					 * 将pt字段改成8道标准工艺
					 */
	private int pnum;//排针数量
	private int sstate;//（商品）状态  1:在售 2：正在生产
	private int sselect;//可选状态  1：可选  2：不可选
	private String picture;//产品图片
	private String ruDate;//入库时间
	
	public String getRuDate() {
		return ruDate;
	}
	public void setRuDate(String ruDate) {
		this.ruDate = ruDate;
	}
	//private Date date ;
	public String getCnum() {
		return cnum;
	}
	public void setCnum(String cnum) {
		this.cnum = cnum;
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
	public String getGuige() {
		return guige;
	}
	public void setGuige(String guige) {
		this.guige = guige;
	}
	public String getDanwei() {
		return danwei;
	}
	public void setDanwei(String danwei) {
		this.danwei = danwei;
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
	public int getSstate() {
		return sstate;
	}
	public void setSstate(int sstate) {
		this.sstate = sstate;
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
	
	
	
}
