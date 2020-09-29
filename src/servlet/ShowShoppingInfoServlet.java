 package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.client;
import bean.dingdan;
import bean.dingshopping;
import bean.gujian;
import bean.shoppinginfo;
import bean.user;
import service.ClientService;
import service.DingDanService;
import service.DingShoppingService;
import service.GujianService;
import service.ShoppingInfoService;
import utils.RandomUtil;
import utils.ShowCraftInfo;

/**
 * Servlet implementation class ShowShoppingInfoServlet
 */
@WebServlet("/ShowShoppingInfoServlet")
public class ShowShoppingInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowShoppingInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("=========商品信息显示servlet=========");
		
		
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//通过request.getParameter("id")方式获取的值都是String类型
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		System.out.println("id:"+id+" action:"+action);
		user  User = new user();
		User = (user) request.getSession().getAttribute("session_user");
		
		String uid = User.getUid();
		List<dingshopping> dingShoppings = new ArrayList<dingshopping>();
		DingShoppingService dingShoppingService = new DingShoppingService();
		/**
		 * 查看该用户是否上传该企业的营业
		 * 执照扫描件，如果未上传该公司的
		 * 企业营业执照扫描件，则跳转到相
		 * 应的页面提示用户上传该执照扫描
		 * 件
		 */
		System.out.println("=========查询是否上传了执照=========");
		ClientService clientService = new ClientService();
		client Client = new client();
		Client = clientService.findClientPictureIsExitByUid(uid);
		if(User.getType()==1&&(Client==null||Client.getPicture()==null||Client.getPicture().isEmpty())){
			/**
			 * 表明该用户未上传营业执照
			 * 则跳转到上传执照页面，提示上传
			 * 执照扫描件。
			 * 即跳转到企业信息管理界面进行提示
			 */
			request.getSession().setAttribute("tip", "亲，您还未上传相关具有法律效力的营业执照等相关信息，请先完善该信息。谢谢您的配合！");
			request.setAttribute("mainRight", "/WEB-INF/jsp/modifyInfoManage.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
			return;
		}
		/**
		 * dingShoppingService.findAllSelectedDingShoppingByUid(uid)
		 * 该方法表示查询得到所有的不可选的商品信息，并将信息存入到dingShoppings中的List
		 * 集合中，便于前端展示
		 */
		if(action.equals("list")){
			/**
			 * 2020/09/29 11：03PM songlj
			 * 将拿到的信息list存入到请求头里面
			 * 此中不仅仅是商品信息的list集合
			 * 还有相应的固件信息和相应的工艺说明。
			 */
			dingShoppings = dingShoppingService.findAllSelectedDingShoppingByUid(uid);
			/**
			 * dingshopping 的list遍历，找到固件信息以及工艺说明。
			 */
			Iterator<dingshopping> it = dingShoppings.iterator();
			Map<String,gujian> gujianMap = new HashMap<String,gujian>();
			Map<String,Map<String,String>> craftInfoLists = new HashMap<String,Map<String,String>>();//工艺信息,将工艺信息存储起来
			while(it.hasNext()){
				/**
				 * 将与商品信息相关的固件信息，与工艺信息取出来
				 * 并一起放入请求头里面，再在前端进行获取数据
				 * 然后进行展示数据
				 */
				dingshopping dingShopping =new dingshopping();
				dingShopping = it.next();
				String gunum = dingShopping.getGunum();//得到固件编号，然后根据固件的编号找出固件信息以及工艺信息
				gujian guJian = new gujian();
				GujianService gujianService = new GujianService();
				guJian  = gujianService.findGujianInfoByGunum(gunum);//将相应商品信息的固件信息存入到gujian类
				gujianMap.put(gunum, guJian);//将固件信息存入到固件map中
				
				/*********（分割线）上面为固件相关信息，下面为工艺说明信息**********/
				String pt = null;
					if(dingShopping.getCtype().contains("MK")||dingShopping.getCtype().contains("mk")){
						/**
						 * 判断是否是模块，如果是的话提交到ShowCraftInfo类
						 * 进行工艺说明细化，传入pt，然后进行解析，返回Map
						 * Map<String,String>该map存的数据就是工艺的详细说明
						 * 通过map将数据显示出来，通过键值对的形式获取该值
						 * 如果判断不为模块，则不把它丢给ShowCraftInfo类
						 * 而是将其赋值为null值，然后再前端判断是否为空值
						 * 若为空值则在前端不进行处理。不进行展示。
						 * 在展示之前就事先判断pt是否存在
						 * 若不存在则不进行后续工艺说明的展示
						 * 反之，亦然2020/09/29 16：10PM
						 */
						pt = dingShopping.getPt();//工艺说明字段
						Map<String,String> craftInfoList = new HashMap<String,String>();
						ShowCraftInfo ShowCraftInfo = new ShowCraftInfo();
						craftInfoList = ShowCraftInfo.getDetailCraftInfoByPt(pt);//getDetailCraftInfoByPt得到细节的工艺说明
						//将工艺说明信息存入到相关的的map中
						craftInfoLists.put(pt, craftInfoList);//将相应的工艺说明map存入到Map集合中
						//前端如何获取该值，通过el表达式嵌套获取该值
					}
			}
			request.setAttribute("gujianMap",gujianMap);//将固件信息存入到请求头里面。2020/09/29 11：52PM songlj
			request.setAttribute("craftInfoLists",craftInfoLists);//将工艺信息存入到请求头里面。2020/09/29 11：52PM songlj
			
			request.setAttribute("dingShoppings",dingShoppings);
			request.setAttribute("mainRight", "/WEB-INF/jsp/ShowShoppingInfo.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("sselect")){
			/**
			 * 将可选的商品信息拿出来，并将信息存放到dingShoppings中的List
			 * List集合中含有所有的可选的商品信息，并将dingShoppings对象
			 * 存放到request请求头里面，便于前端展示
			 * 2020/09/29 11：03PM
			 */
			String flag = "selected";
			dingShoppings = dingShoppingService.findAllNoSelectDingShopping();
			request.setAttribute("dingShoppings",dingShoppings);
			request.setAttribute("flag",flag);
			request.setAttribute("mainRight", "/WEB-INF/jsp/ShowShoppingInfo.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
			
		}else if(action.equals("add")){
			/**
			 * 加入购物车动作，处理该动作
			 * 添加到购物车,购物车信息入库
			 */
			/**
			 * 查看同一个商品是否多次购买，多次购买则叠加起来
			 */
			int number=0;
			float Total = 0;
			System.out.println("uid:"+uid);
			dingshopping dingShopping = new dingshopping();
			/**
			 * 查找相同的商品信息
			 */
			String cnum = request.getParameter("cnum");
			dingshopping flagDingshopping = dingShoppingService.findRepeatDingShoppingInfo(cnum,uid);
			//得到picture参数,并将其存入dingShopping对象中
			dingShopping.setPicture(request.getParameter("picture"));
			//得到cnum参数，产品编号
			dingShopping.setCnum(request.getParameter("cnum"));
			
			dingShopping.setCname(request.getParameter("cname"));//新添加字段，产品编号
			
			dingShopping.setCtype(request.getParameter("ctype"));
			dingShopping.setPt(request.getParameter("pt"));
			
			//得到gunum参数，固件编号
			dingShopping.setGunum(request.getParameter("gunum"));
			//得到ruDate参数，入库时间 
			dingShopping.setRuDate(request.getParameter("ruDate"));
			//得到danwei参数，单位
			dingShopping.setDanwei(request.getParameter("danwei"));
			//得到price参数，单价
			dingShopping.setPrice(Float.parseFloat(request.getParameter("price")));
			//得到sstate参数，商品状态
			dingShopping.setSstate(Integer.parseInt(request.getParameter("sstate")));
			//得到number参数，商品数量
			if(flagDingshopping !=null){
				number = Integer.parseInt(request.getParameter("number"))+flagDingshopping.getNumber();
				dingShopping.setNumber(Integer.parseInt(request.getParameter("number"))+flagDingshopping.getNumber());
			}else{
			dingShopping.setNumber(Integer.parseInt(request.getParameter("number")));
			}
			
			dingShopping.setUid(uid);//将客户信息存入，以方便查看订单
			if(Integer.parseInt(request.getParameter("number"))<1){
				request.setAttribute("error","购买的数量不合法!!!");
				//response.sendRedirect(getServletContext().getContextPath()+"/ShowShoppingInfoServlet?action=add");
				request.setAttribute("mainRight", "/WEB-INF/jsp/ShowShoppingInfo.jsp");
				request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
				return;
			}
			if(flagDingshopping !=null){
				//Float Total;
				dingShopping.setTotal(Float.parseFloat(request.getParameter("price"))*Integer.parseInt(request.getParameter("number"))
						+flagDingshopping.getTotal());
				Total=Float.parseFloat(request.getParameter("price"))*Integer.parseInt(request.getParameter("number"))+flagDingshopping.getTotal();
			}
			else{
				dingShopping.setTotal(Float.parseFloat(request.getParameter("price"))*Integer.parseInt(request.getParameter("number")));
			}
			
			
			if(flagDingshopping !=null){
				dingShoppingService.resetDingShoppingInfo(uid,cnum,number,Total);
			}else{
			dingShoppingService.addDingShoppingByObj(dingShopping);
			}
			
			response.sendRedirect(getServletContext().getContextPath()+"/ShowShoppingInfoServlet?action=cartshow");
			
		}else if(action.equals("cartshow")){
			/**
			 * 显示购物车中的信息
			 * 从数据库中拿出信息，存入到请求头里面
			 * 前端从request请求头里面拿出数据
			 * 并展示
			 */
			/**
			 * 跳转到购物车页面
			 */
			dingShoppings = dingShoppingService.findAllDingShoppingInfo();
			request.setAttribute("dingShoppings",dingShoppings);//将从数据库拿出来的的数据存入到请求头里面
			request.setAttribute("mainRight", "/WEB-INF/jsp/shoppingCart.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("buy")){
			/*System.out.println("购买该商品");*/
			/**
			 * 当前端传来购买的请求
			 * 购买商品处理
			 * 生成订单号，对订单表dingdan进行处理
			 */
			DingDanService dingDanService = new DingDanService();
			Date systemDate = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//当前时间年月日 时分秒
			String strDate = format.format(systemDate);//格式化过的当前时间，字符串类型
			String ddanNum = "LE"+RandomUtil.getOrderIdByUUId();
			float totalprice = 0;
			String []Ischeckeds = request.getParameterValues("Ischecked");//获取当前在购物车中勾选的商品信息
			if(Ischeckeds!=null&&Ischeckeds.length>0){
				for(int i=0;i<Ischeckeds.length;i++){
					/*System.out.println(Ischeckeds[i]);*/
					String cnum = Ischeckeds[i];
					/**
					 * 将选中的商品信息的总金额拿出来
					 * 将各个金额叠加，并存入订单表
					 */
					float Total = dingShoppingService.getTotalpriceByCnum(cnum);
					totalprice = totalprice + Total;
					System.out.println("Total"+i+":"+Total);
					System.out.println("totalprice"+i+":"+totalprice);
					dingShoppingService.setDdanNumByCnum(ddanNum,cnum);//将唯一的订单号存入到dingdan表里面，通过产品编号
				}
				dingdan dingDan = new dingdan();
				/**
				 * 将订单信息存为对象，dingDan对象
				 */
				dingDan.setDdanNum(ddanNum);
				dingDan.setUid(uid);
				dingDan.setDealDate(strDate);
				dingDan.setTotalprice(totalprice);
				dingDan.setState(0);//初始时，订单客户这边设置为0
				dingDanService.addDingDanInfoByObj(dingDan);
				response.sendRedirect(getServletContext().getContextPath()+"/ShowShoppingInfoServlet?action=showDingdan");
			}else {
				/**
				 * 当未勾选任何要购买的商品时，给出提示信息并且返回到开始页面状态
				 */
				request.getSession().setAttribute("error","请先勾选商品，再进行购买！");
				response.sendRedirect(getServletContext().getContextPath()+"/ShowShoppingInfoServlet?action=cartshow");
			}
		}else if(action.equals("showDingdan")){
			List<dingdan> dingDans = new ArrayList<dingdan>();
			DingDanService dingDanService = new DingDanService();
			if(User.getType()==1){
				
				/**
				 * 拿取当前用户的所有订单信息
				 */
				dingDans = dingDanService.findAllDingDanInfoByUid(uid);
			}else if(User.getType()==0||User.getType()>1){
				dingDans = dingDanService.findAllDingDanInfo();
			}
			request.setAttribute("dingDans",dingDans);//将从数据库拿出来的的数据存入到请求头里面
			request.setAttribute("mainRight", "/WEB-INF/jsp/showDingdan.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("comfirm")){
			/**
			 * 用户确认订单，设置订单状态为你
			 * 用户已经确认订单。
			 * 订单状态表示为0的时候，表示用户
			 * 初次提交订单。
			 */
			System.out.println("动作comfirm");
			String ddanNum = id;
			/**
			 * 用户设置订单状态，确认订单
			 * 数据库：dingdan
			 */
			DingDanService dingDanService = new DingDanService();
			dingDanService.comfirmDingDanState(ddanNum, 2);
			System.out.println("订单编号ddanNum："+ddanNum);
			response.sendRedirect(getServletContext().getContextPath()+"/ShowShoppingInfoServlet?action=showDingdan");
		}else if(action.equals("shoppingDetaileInfo")){
			/**
			 * 显示商品信息的详情，并将工艺给转换成文字形式
			 */
			/*dingShoppingService*/
			String cnum = id;
			shoppinginfo shoppingInfo = new shoppinginfo();
			ShoppingInfoService shoppingInfoService = new ShoppingInfoService();
			shoppingInfo = shoppingInfoService.findShoppingInfoByCnum(cnum);
			/**
			 * 向utils包下的ShowCraftInfo类中传入工艺的值
			 * 将工艺转换成可以理解的Map
			 * ps:pt字符串可能为空也可能为null，数据库中存储的时候
			 */
			ShowCraftInfo showCraftInfo = new ShowCraftInfo();
			
			Map<String,String> craftInfoMap = new HashMap<String,String>();//将工艺存入到Map中
			String pt;
			pt=shoppingInfo.getPt();
			if(pt!=null){
				craftInfoMap = showCraftInfo.getDetailCraftInfoByPt(pt);//将pt传入，获得map
			}else{
				craftInfoMap=null;
			}
			//craftInfoMap = showCraftInfo.getDetailCraftInfoByPt(pt);//将pt传入，获得map
			request.setAttribute("shoppingInfo", shoppingInfo);//将通过cnum得到的商品信息存入请求头里面
			request.setAttribute("craftInfoMap", craftInfoMap);//将转换后的Map存入请求头里面
			request.setAttribute("mainRight", "/WEB-INF/jsp/shoppingDetaileInfo.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}
	}
}
