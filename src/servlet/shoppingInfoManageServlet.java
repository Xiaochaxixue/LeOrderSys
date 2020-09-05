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

import bean.shoppinginfo;
import bean.tianprice;
import service.ShoppingInfoService;
import service.TianPriceService;

/**
 * Servlet implementation class shoppingInfoManageServlet
 */
@WebServlet("/shoppingInfoManageServlet")
public class shoppingInfoManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public shoppingInfoManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("=========商品信息管理servlet=========");
		
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//通过request.getParameter("id")方式获取的值都是String类型
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		System.out.println("id:"+id+" action:"+action);
		List<shoppinginfo> shoppingInfos = new ArrayList<shoppinginfo>();//商品信息list表存放所有的商品信息内容
		ShoppingInfoService shoppingInfoService = new ShoppingInfoService();
		shoppingInfos = shoppingInfoService.findAllShoppingInfo();
		if(action.equals("list")){
			//System.out.println("商品信息list"+" id:"+id+" action:"+action);
			request.setAttribute("shoppingInfos",shoppingInfos);//将商品信息存入到请求头中
			request.setAttribute("mainRight", "/WEB-INF/jsp/shoppingInfoManageList.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("preAdd")){
			
			System.out.println("preAdd操作 "+"id:"+id+" action:"+action);
			//将天线类型存储到请求头里面去
			List<tianprice> tianPrices = new ArrayList<tianprice>();
			TianPriceService tianPriceService = new TianPriceService();
			tianPrices = tianPriceService.findAllTianPriceInfo();
			request.setAttribute("tianPrices",tianPrices);
			request.setAttribute("mainRight", "/WEB-INF/jsp/shoppingInfoManageAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("preUpdate")){
			shoppinginfo shoppingInfo = new shoppinginfo();
			String Cnum = id;
			shoppingInfo = shoppingInfoService.findShoppingInfoByCnum(Cnum);
			//将天线类型存储到请求头里面去
			List<tianprice> tianPrices = new ArrayList<tianprice>();
			TianPriceService tianPriceService = new TianPriceService();
			tianPrices = tianPriceService.findAllTianPriceInfo();
			request.setAttribute("tianPrices",tianPrices);
			//将所有的天线信息存入了请求头里面
			request.setAttribute("shoppingInfo", shoppingInfo);//将商品信息存入请求头里面
			request.setAttribute("mainRight", "/WEB-INF/jsp/shoppingInfoManageAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("save")){
			shoppinginfo shoppingInfo = new shoppinginfo();
			//将前端传来的数据封装成shoppinginfo类
			shoppingInfo.setCtype(request.getParameter("ctype"));
        	shoppingInfo.setCnum(request.getParameter("cnum"));
        	shoppingInfo.setCname(request.getParameter("cname"));
        	shoppingInfo.setGuige(request.getParameter("guige"));
        	shoppingInfo.setDanwei(request.getParameter("danwei"));
        	shoppingInfo.setPrice(Float.parseFloat(request.getParameter("price")));
        	shoppingInfo.setSstate(Integer.parseInt(request.getParameter("sstate")));
        	shoppingInfo.setSselect(Integer.parseInt(request.getParameter("sselect")));
        	
        	Date systemDate = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = format.format(systemDate);//格式化过的当前时间，字符串类型
			System.out.println("当前时间为："+strDate);
			shoppingInfo.setRuDate(strDate);
			//将信息添加到数据库中
        	if(id==null){
        		//添加商品信息，将数据存入数据库
        		System.out.println("----------id为空，进行添加商品信息------------");
        		shoppinginfo shoppingInfoMatch = new shoppinginfo();
        		shoppingInfoMatch = shoppingInfoService.findShoppingInfoByCnum(request.getParameter("cnum"));
        		if(shoppingInfoMatch!=null){
        			request.setAttribute("error", "当前用商品信息已存在，请重新输入！");
        			request.setAttribute("mainRight", "/WEB-INF/jsp/shoppingInfoManageAddOrUpdate.jsp");
        			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
        		}
        		else{
        			/**
        			 * 将pt（标准工艺）存入数据库
        			 */
        			shoppingInfo.setPt(request.getParameter("pt"));
        			shoppingInfoService.addShoppingInfoByObj(shoppingInfo);
        			//shoppingInfoService.updateShoppingInfoByObj(shoppingInfo);
        			//更新完重定向到当前servlet，进行list显示操作
        			response.sendRedirect(getServletContext().getContextPath()+"/shoppingInfoManageServlet?action=list");
        		}
        	}else if(id!=null){
        		System.out.println("==========商品信息//修改==========");
				System.out.println("id:"+id+" action:"+action);
				shoppingInfoService.updateShoppingInfoByObj(shoppingInfo);
				response.sendRedirect(getServletContext().getContextPath()+"/shoppingInfoManageServlet?action=list");
        	}
			
        }
	}
}
