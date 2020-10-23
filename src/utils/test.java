package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import bean.user;
//import net.sf.json.JSON;

public class test {
    public static void main(String[] args) {
    	/**
		 * 将json返回给页面操作，就是使用response返回过去，
		 * 就response.out什么的
		 * PrintWriter out = cu.getWriterOut(response);
		 * out.print(object.toString());
		 * response.getWriter().write(jsonObject.toString());
		 */
        /**
         * ①将数据转成json
         * ②将json转成数据
         * JSON.parseObject(jsonStr,Obj.class);
         */
    	user User= new user();
    	User.setPaw("paw");
    	User.setType(5);
    	User.setUid("uid");
    	
    	user User3= new user();
    	User3.setPaw("paw3");
    	User3.setType(5);
    	User3.setUid("uid3");
		
    	user User4= new user();
    	User4.setPaw("paw4");
    	User4.setType(4);
    	User4.setUid("uid4");
    	
		String jsonString2 = JSON.toJSONString(User);
		System.out.println(jsonString2);
		
		user User2= new user();
		User2 = JSON.parseObject(jsonString2,user.class);//将json数据转换成obj对象
		System.out.println("User2.getPaw()"+User2.getPaw());
	    /*JSONArray : 相当于List
	    JSONObject: 相当于Map<String,Object>*/
		List<user> Users = new ArrayList<user>();
		Users.add(User);
		Users.add(User2);
		Users.add(User3);
		Users.add(User4);
		String userList = JSON.toJSONString(Users);
		System.out.println(userList);
		
		Map<String,List<user>> userMap = new HashMap<String,List<user>>();
		//userMap.put("usermap", "MAP");
		userMap.put("users", Users);
		String UserMap = JSON.toJSONString(userMap);
		System.out.println(UserMap);
		//JSONObject json = JSONObject.parseObject(JSON.toJSONString(map));//把map转为json数据
		JSONObject json = JSONObject.parseObject(UserMap);//把map转为json数据
		json.put("list", Users);
		json.put("description", "将list与map存入到json中");
		System.out.println(json.toString());
    }
}
