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
	public List<String> getDetailCraftInfoByPt(String pt){
		List<String> craftInfoLists = new ArrayList<String>();
		
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
}
