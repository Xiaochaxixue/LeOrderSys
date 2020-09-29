package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.client;
import bean.gujian;
import bean.shoppinginfo;
import service.ClientService;
import service.GujianService;
import service.ShoppingInfoService;
import utils.RandomUtil;

/**
 * Servlet implementation class gujianInfoManageServlet
 */
@WebServlet("/gujianInfoManageServlet")
public class gujianInfoManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gujianInfoManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("=========商品确认书管理servlet=========");
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//通过request.getParameter("id")方式获取的值都是String类型
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		System.out.println("id:"+id+" action:"+action);
		if(action.equals("list")){
			//获取所有固件信息
			/**
			 * 展示所有的固件信息，跳转到展示页面
			 */
			List<gujian> guJians = new ArrayList<gujian>();
			GujianService gujianService = new GujianService();
			guJians = gujianService.findAllguJianInfo();
			request.setAttribute("guJians", guJians);
			
			request.setAttribute("mainRight", "/WEB-INF/jsp/GujianInfoManageList.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
			
		}else if(action.equals("preAdd")){
			/**
			 * 进行预添加，跳转到修改/添加页面
			 */
			
			/**
			 * 将固件信息存下来
			 */
			
			String gunum = RandomUtil.getOrderIdByTime();//给固件编号生成唯一值
			/**
			 * 将所有的商品信息拿出来，存放到list列表里面
			 */
			List<shoppinginfo> shoppingInfos = new ArrayList<shoppinginfo>();//商品信息list表存放所有的商品信息内容
			ShoppingInfoService shoppingInfoService = new ShoppingInfoService();
			shoppingInfos = shoppingInfoService.findAllShoppingInfo();
			/**
			 * 将所有的客户信息拿出来，存放到list列表里面
			 */
			List<client> clients=new ArrayList<client>();//生成存储所有客户信息的列表类
			ClientService clientService=new ClientService();
			clients = clientService.findAllClientInfo();//得到了所有客户信息
			/**
			 * 将从数据库中拿到的数据（固件信息、商品信息、客户信息）存到请求头里面
			 */
			/*request.setAttribute("guJian", guJian);//将固件信息存入请求头里面*/
			request.setAttribute("gunum", gunum);//将固件编号信息存入请求头里面
			request.setAttribute("shoppingInfos", shoppingInfos);//将商品信息存入请求头里面
			request.setAttribute("clients", clients);//将客户信息存入请求头里面
			request.setAttribute("mainRight","/WEB-INF/jsp/GujianInfoManageAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("preUpdate")){
			/**
			 * 进行预更新，跳转到修改/添加页面
			 */
			/**
			 * 将固件信息存下来
			 */
			gujian guJian = new gujian();
			String gunum=id;
			GujianService gujianService = new GujianService();
			guJian = gujianService.findGujianInfoByGunum(gunum);
			/**
			 * 将所有的商品信息拿出来，存放到list列表里面
			 */
			List<shoppinginfo> shoppingInfos = new ArrayList<shoppinginfo>();//商品信息list表存放所有的商品信息内容
			ShoppingInfoService shoppingInfoService = new ShoppingInfoService();
			shoppingInfos = shoppingInfoService.findAllShoppingInfo();
			/**
			 * 将所有的客户信息拿出来，存放到list列表里面
			 */
			List<client> clients=new ArrayList<client>();//生成存储所有客户信息的列表类
			ClientService clientService=new ClientService();
			clients = clientService.findAllClientInfo();//得到了所有客户信息
			/**
			 * 将从数据库中拿到的数据（固件信息、商品信息、客户信息）存到请求头里面
			 */
			//request.setAttribute("id", id);//将固件编号存入请求头里面
			request.setAttribute("guJian", guJian);//将固件信息存入请求头里面
			request.setAttribute("shoppingInfos", shoppingInfos);//将商品信息存入请求头里面
			request.setAttribute("clients", clients);//将客户信息存入请求头里面
			request.setAttribute("mainRight","/WEB-INF/jsp/GujianInfoManageAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("save")){
			/**
			 * 存储非文件表单的其它信息
			 */
			gujian guJian = new gujian();
			guJian.setGunum(request.getParameter("gunum"));
			System.out.println("request.getParameter(gunum):"+request.getParameter("gunum"));
			guJian.setUid(request.getParameter("uid"));
			guJian.setCnum(request.getParameter("cnum"));
			guJian.setGuversion(request.getParameter("guversion"));
			guJian.setGuname(request.getParameter("guname"));
			guJian.setGustate(Integer.parseInt(request.getParameter("gustate")));
			guJian.setGups(request.getParameter("gups"));
			
			
			GujianService gujianService = new GujianService();
			gujianService.updateGujianInfoByObjNoneFile(guJian);
			response.sendRedirect(getServletContext().getContextPath()+"/gujianInfoManageServlet?action=list");
		}else{
			response.sendRedirect(getServletContext().getContextPath()+"/gujianInfoManageServlet?action=list");
		}
	}

}
