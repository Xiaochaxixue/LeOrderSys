package utils;

import java.util.HashMap;
import java.util.Map;

public class ShowCraftInfo {
	/**
	 * 将标准的工艺命名字符串进行加工与生成
	 * 主要有排针工艺和天线工艺
	 * 2020/09/05 17：38PM
	 * @param pt
	 * @return craftInfoLists
	 */
	/**
	 * 传入工艺的编号，得到工艺的详细信息
	 * @param pt
	 * @return
	 */
	String pinNum="无";//排针数量
	String pinSize="无";//排针大小
	String pinShape="无";//排针形状
	String pinWeld="无";//焊接方式
	
	String antennaType="无";//天线类型
	String antennaLength="无";//天线长度
	
	Map<String,String> craftInfoLists = new HashMap<String,String>();
	public Map<String,String> getDetailCraftInfoByPt(String pt){
		//List<String> craftInfoLists = new ArrayList<String>();
		String Pin;//排针工艺
		String Antenna;//天线工艺
		/**
		 * 将工艺具体到哪个工艺
		 * 排针工艺还是天线工艺
		 */
		/*if(pt.equals("")||pt==null){
			
		}*/
		if(pt!=null&&pt.length()>0){
			/**
			 * 判断是否含有工艺技术
			 * 含有的话进行处理
			 * 没有的话则跳出来
			 */
			if(pt.contains("P")&&(pt.contains("B")||pt.contains("C")||pt.contains("I"))){
				/**
				 * 既有排针工艺又有天线工艺
				 */
				String[] pinAndantenna =pt.split("-");
				Pin = pinAndantenna[0];
				Antenna = pinAndantenna[1];
				System.out.println("经过split方法     Pin："+Pin+"      Antenna:"+Antenna);
				handlePin(Pin);
				handleAntenna(Antenna);
			}else if(pt.contains("P")&&!(pt.contains("B")||pt.contains("C")||pt.contains("I"))){
				/**
				 * 有排针工艺没有天线工艺
				 */
				handlePin(pt);
				craftInfoLists.put("antennaType", antennaType);
				craftInfoLists.put("antennaLength", antennaLength);
			}else if(!pt.contains("P")&&(pt.contains("B")||pt.contains("C")||pt.contains("I"))){
				/**
				 * 有天线工艺没有排针工艺
				 */
				handlePin(pt);
				craftInfoLists.put("pinNum", pinNum);
				craftInfoLists.put("pinSize", pinSize);
				craftInfoLists.put("pinShape", pinShape);
				craftInfoLists.put("pinWeld", pinWeld);
			}
		}else{
			craftInfoLists.put("pinNum", pinNum);
			craftInfoLists.put("pinSize", pinSize);
			craftInfoLists.put("pinShape", pinShape);
			craftInfoLists.put("pinWeld", pinWeld);
			craftInfoLists.put("antennaType", antennaType);
			craftInfoLists.put("antennaLength", antennaLength);
		}
		return craftInfoLists;
	}
	/**
	 * 排针与天线说明
	 * 排针说明：eg：3P2.54WZ
	 * 						3P表示：排针数量为3
	 * 						2.54：排针间距为2.54
	 * 						WZ:排针形状为弯排针，焊接方式为正焊
	 * 天线说明：eg：B3
	 * 						B表示：标准铁氟龙天线
	 * 						3表示：天线长度为3cm
	 */
	/**
	 * 方法说明：处理排针工艺方法
	 * @param pin
	 * @return
	 */
	private void handlePin(String pin){
		/**eg:3P2.54WZ
		 * 排针数量String pinNum
		 * 排针大小String pinSize
		 * 排针形状String pinShape
		 * 焊接方式String pinWeld
		 */
		int pLocate;
		pLocate = pin.indexOf("P");
		pinNum = pin.substring(0,pLocate);
		System.out.println("排针数量："+pinNum);
		
		craftInfoLists.put("pinNum",pinNum);
		
		int allLength;
		allLength = pin.length();
		pinSize = pin.substring(pLocate+1,allLength-2);
		System.out.println("排针大小："+pinSize);
		
		craftInfoLists.put("pinSize",pinSize);
		
		pinShape = ""+pin.charAt(allLength-2);
		System.out.println("排针形状："+pinShape);
		
		craftInfoLists.put("pinShape",pinShape);
		
		pinWeld = ""+pin.charAt(allLength-1);
		System.out.println("焊接方式："+pinWeld);
		
		craftInfoLists.put("pinWeld",pinWeld);
		
		return;
	}
	/**
	 * 方法说明：处理天线工艺方法
	 * @param antenna
	 * @return
	 */
	private void handleAntenna(String antenna){
		/**eg:C18
		 * 天线类型String antennaType
		 * 天线长度String antennaLength
		 */
		antennaType = ""+antenna.charAt(0);
		craftInfoLists.put("antennaType", antennaType);
		
		antennaLength = antenna.substring(1);
		craftInfoLists.put("antennaLength", antennaLength);
		return;
	}
}
