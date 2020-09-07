package utils;

import java.util.ArrayList;
import java.util.List;

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
	String pinNum;//排针数量
	String pinSize;//排针大小
	String pinShape;//排针形状
	String pinWeld;//焊接方式
	
	String antennaType;//天线类型
	String antennaLength;//天线长度
	
	List<String> craftInfoLists = new ArrayList<String>();
	public List<String> getDetailCraftInfoByPt(String pt){
		//List<String> craftInfoLists = new ArrayList<String>();
		String Pin;//排针工艺
		String Antenna;//天线工艺
		/**
		 * 将工艺具体到哪个工艺
		 * 排针工艺还是天线工艺
		 */
		if(pt.contains("P")||pt.contains("B")||pt.contains("C")||pt.contains("I")){
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
				handlePin(Pin);
				handleAntenna(Antenna);
			}
		}else{
			return null;
		}
		return craftInfoLists;
	}
	/**
	 * 排针与天线说明
	 * 排针说明：eg：3P2.54WZ
	 * 						3P表示：排针数量为3
	 * 						2.54：排针间距为2.54
	 * 						WZ:排针形状为直排针，焊接方式为正焊
	 * 天线说明：eg：B3
	 * 						B表示：标准铁氟龙天线
	 * 						3表示：天线长度为3cm
	 */
	/**
	 * 方法说明：处理排针工艺方法
	 * @param pin
	 * @return
	 */
	public void handlePin(String pin){
		
		return;
	}
	/**
	 * 方法说明：处理天线工艺方法
	 * @param antenna
	 * @return
	 */
	public void handleAntenna(String antenna){
		
		return;
	}
}
