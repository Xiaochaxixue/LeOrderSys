package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.dingdan;
import bean.dingshopping;
import bean.user;
import service.DingDanService;
import service.DingShoppingService;
import utils.RandomUtil;

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
		 * dingShoppingService.findAllSelectedDingShoppingByUid(uid)
		 * 该方法表示查询得到所有的不可选的商品信息，并将信息存入到dingShoppings中的List
		 * 集合中，便于前端展示
		 */
		
		if(action.equals("list")){
			/**
			 * 将拿到的信息list存入到请求头里面
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
			dingshopping dingShopping = new dingshopping();
			//得到picture参数,并将其存入dingShopping对象中
			dingShopping.setPicture(request.getParameter("picture"));
			//得到cnum参数，产品编号
			dingShopping.setCnum(request.getParameter("cnum"));
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
			dingShopping.setNumber(Integer.parseInt(request.getParameter("number")));
			
			dingShopping.setTotal(Float.parseFloat(request.getParameter("price"))*Integer.parseInt(request.getParameter("number")));
			
			dingShoppingService.addDingShoppingByObj(dingShopping);
		}
		else if(action.equals("cartshow")){
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
				dingDan.setState(0);
				dingDanService.addDingDanInfoByObj(dingDan);
				response.sendRedirect(getServletContext().getContextPath()+"/ShowShoppingInfoServlet?action=showDingdan");
			}
		}else if(action.equals("showDingdan")){
			List<dingdan> dingDans = new ArrayList<dingdan>();
			DingDanService dingDanService = new DingDanService();
			dingDans = dingDanService.findAllDingDanInfo();
			request.setAttribute("dingDans",dingDans);//将从数据库拿出来的的数据存入到请求头里面
			request.setAttribute("mainRight", "/WEB-INF/jsp/showDingdan.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}
		
	}

}
