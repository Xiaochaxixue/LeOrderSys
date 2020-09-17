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
import bean.receipt;
import bean.user;
import service.ClientService;
import service.ReceiptService;
import service.UserService;

/**
 * Servlet implementation class receiptInfoServlet
 */
@WebServlet("/receiptInfoServlet")
public class receiptInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public receiptInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("=========发票信息管理servlet=========");
		/**
		 * 新增发票管理模块，对用户的购买发票进行管理
		 * 发票进行展示
		 * 发票主要有抬头、税号
		 * 与发票关联的有用户、订单号。
		 */
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		user  User = new user();
		User = (user) request.getSession().getAttribute("session_user");
		String uid = User.getUid();
		/**
		 * 从session中获取user对象，方便后续的使用，查找等。
		 */
		
		if(action.equals("list")){
			/**
			 * 展示客户的所有的发票信息，
			 * 给客户展示相应的发票信息
			 */
			List<receipt> receipts = new ArrayList<receipt>();
			/**
			 * 从数据库中拿取所有当前用户的所有发票信息；
			 */
			ReceiptService receiptService = new ReceiptService();//获取发票的服务层的服务
			//然后通过该服务获取dao层的服务然后获取所有的发票的发票信息。
			receipts = receiptService.findAllReceiptsByUid(uid);
			/**
			 * 通过用户的uid获取用户曾经的发票信息，然后展示出来。
			 */
			request.setAttribute("receipts", receipts);//将所有的发票信息存入请求头里面
			
			request.setAttribute("mainRight", "/WEB-INF/jsp/receiptsInfoManage.jsp");//发票jsp静态文件
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);//在main.jsp页面展示发票的静态jsp页面
		}/*else if(action.equals("save")){
				System.out.println("User.setPaw(psw):"+"");
				UserService.updateUserInfoByObj(User);
				response.sendRedirect(getServletContext().getContextPath()+"/LogOutServlet");
		}*/else{
			/**
			 * 如果所有的action请求为非法请求（不为servlet，action里面 的请求）
			 * 则返回到发票展示页面。
			 * 防止多余的出口导致请求给出的响应为
			 * 浏览器的空白页面，给用户的体验不好。
			 */
			request.setAttribute("mainRight", "/WEB-INF/jsp/blank.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}
	}

}
