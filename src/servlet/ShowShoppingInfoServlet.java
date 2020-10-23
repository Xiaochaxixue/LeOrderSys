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
import bean.receipt;
import bean.shoppinginfo;
import bean.tb_address;
import bean.user;
import service.ClientService;
import service.DingDanService;
import service.DingShoppingService;
import service.GujianService;
import service.ReceiptService;
import service.ShoppingInfoService;
import service.Tb_addressService;
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
		System.out.println("=========查询用户是否上传了执照=========");
		ClientService clientService = new ClientService();//客户的服务层
		client Client = new client();//客户对象
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
			String gunum = request.getParameter("gunum");//固件编号，由产品编号查找重复的商品信息，改为由固件编号查找重复的商品信息。
			dingshopping flagDingshopping = dingShoppingService.findRepeatDingShoppingInfo(gunum,uid);
			//得到picture参数,并将其存入dingShopping对象中
			dingShopping.setPicture(request.getParameter("picture"));
			//得到cnum参数，产品编号
			dingShopping.setCnum(request.getParameter("cnum"));//产品编号
			
			dingShopping.setCname(request.getParameter("cname"));//新添加字段，产品名称，显示产品的名称
			
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
			
			//将新增的数据字段从前端获取dingShopping(request.getParameter(""));
			dingShopping.setGuversion(request.getParameter("guversion"));
			//排针工艺
			dingShopping.setPinNum(request.getParameter("pinNum"));
			dingShopping.setPinSize(request.getParameter("pinSize"));
			dingShopping.setPinShape(request.getParameter("pinShape"));
			dingShopping.setPinWeld(request.getParameter("pinWeld"));
			//天线工艺
			dingShopping.setAntennaType(request.getParameter("antennaType"));
			dingShopping.setAntennaLength(request.getParameter("antennaLength"));
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
			/**
			 * 对其进行重新编写，不进行一次购买多个商品，而是一个商品
			 * 2020/10/08 17：18 PM songlj
			 * action为buy，id为该商品的固件编号。
			 * 固件编号是唯一的2020/10/08 17：33PM songlj
			 */
			/**
			 * 传入到订单意向清单购买页面的有，dingshopping信息，收货信息，发票信息
			 * 其中dingshoppin是g信息为单个类对象
			 * 收货信息包括，默认地址以及可供选择的地址。
			 * 发票信息是包括两部分，一部分是普通发票，另一部分是专业发票信息
			 * 此外，发票信息不是必选信息，可以在选取发票信息的选择时选择，默认是
			 * 不需要发票的。2020/10/09 15：04 PM
			 */
			/*DingDanService dingDanService = new DingDanService();
			Date systemDate = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//当前时间年月日 时分秒
			String strDate = format.format(systemDate);//格式化过的当前时间，字符串类型
			String ddanNum = "LE"+RandomUtil.getOrderIdByUUId();
			float totalprice = 0;*/
			String gunum;//固件编号
			gunum = id;
			
			dingshopping dingShopping = new dingshopping();
			dingShopping = dingShoppingService.findDingShoppingInfoByGunnumAndUid(gunum,uid);//根据固件编号拿取dingshopping信息
			/**
			 * 查找收货信息以及发票信息
			 * 然后将该两种信息存入到请求头里面
			 * 然后方便前端展示
			 * ①查找收货地址信息，一部分是默认地址，另一部分是非默认地址信息
			 * ②查找发票信息，发票信息分为两部分，一是专用发票，二是普通发票信息。
			 * 然后将两种信息存入到请求头里面。
			 */
			List<tb_address> Tb_addresses = new ArrayList<tb_address>();//存放非默认地址信息
			Client = clientService.findClientInfoByUid(uid);//获取包含默认地址的客户信息
			request.setAttribute("Client", Client);//将客户信息存入到请求头里面
			
			Tb_addressService tb_addressService = new Tb_addressService();
			Tb_addresses = tb_addressService.findAllAddressInfoByUid(uid);
			request.setAttribute("Tb_addresses", Tb_addresses);//将非默认地址存入到请求头里面
			
			/**
			 * 获取用户的发票信息，发票分为两种一种为专用发票，一种为普通发票。
			 */
			//receipt
			List<receipt> ReceiptP = new ArrayList<receipt>();//存放普通发票
			List<receipt> ReceiptQ = new ArrayList<receipt>();//存放专用发票。
			ReceiptService receiptService = new ReceiptService();//发票服务类
			ReceiptP = receiptService.findReceiptPInfoByUid(uid);//获得普通发票的信息list
			ReceiptQ = receiptService.findReceiptQInfoByUid(uid);//获得专用发票的信息List
			request.setAttribute("ReceiptP", ReceiptP);//将两种发票信息存放到请求头里面
			request.setAttribute("ReceiptQ", ReceiptQ);//将专用发票信息存入请求头里面
			
			request.setAttribute("dingShopping", dingShopping);
			request.setAttribute("mainRight", "/WEB-INF/jsp/proBuy.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
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
