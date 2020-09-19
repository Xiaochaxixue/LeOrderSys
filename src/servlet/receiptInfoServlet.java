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
		 * 与发票关联的有用户
		 */
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		user  User = new user();
		User = (user) request.getSession().getAttribute("session_user");
		if(User==null){
			response.sendRedirect("index.jsp");
			return;
		}
		String uid = User.getUid();
		/**
		 * 从session中获取user对象，方便后续的使用，查找等。
		 */
		ReceiptService receiptService = new ReceiptService();//获取发票的服务层的服务
		
		if(action.equals("list")){
			/**
			 * 展示客户的所有的发票信息，
			 * 给客户展示相应的发票信息
			 */
			List<receipt> receipts = new ArrayList<receipt>();
			/**
			 * 从数据库中拿取所有当前用户的所有发票信息；
			 */
			//然后通过该服务获取dao层的服务然后获取所有的发票的发票信息。
			receipts = receiptService.findAllReceiptsByUid(uid);
			/**
			 * 通过用户的uid获取用户曾经的发票信息，然后展示出来。
			 */
			request.setAttribute("receipts", receipts);//将所有的发票信息存入请求头里面
			request.setAttribute("mainRight", "/WEB-INF/jsp/receiptsInfoManage.jsp");//发票jsp静态文件
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);//在main.jsp页面展示发票的静态jsp页面
		}else if(action.equals("add")){
			/**
			 * 新增发票信息，对发票进行添加
			 * 添加发票信息后，仍然返回到发票信息界面
			 * 展示发票信息。
			 */
			String title = request.getParameter("title");
			String tax = request.getParameter("tax");
			System.out.println("title:"+title+" tax:"+tax);
			/**
			 * 检查是否对其进行了重复添加
			 * 是重复添加，则返回并给出提示信息。
			 */
			receipt ReceiptMatch = new receipt();//匹配订单类，匹配数据库是否已经存在该数据
			ReceiptMatch = receiptService.findReceiptIsExit(uid,title);
			if(title==null||title.isEmpty()||tax==null||tax.isEmpty()){
				/*request.setAttribute("error", "请输入数据再进行提交");*/
				request.getSession().setAttribute("tip","输入信息不完整，请重新添加数据");
				response.sendRedirect(getServletContext().getContextPath()+"/receiptInfoServlet?action=list");
				return;
			}else if(ReceiptMatch!=null){
				/**
				 * 当数据库中存在该数据，则给出提示信息
				 */
				request.getSession().setAttribute("tip","该发票信息已经存在，请重新添加！");
				response.sendRedirect(getServletContext().getContextPath()+"/receiptInfoServlet?action=list");
				return;
			}/**
			 * 如果数据库中含有相同的数据，则给出
			 * 提示信息，告知客户不要重复添加
			 * 然后返回到开始的页面
			 * 当前面两种情况都不存在则对该发票信息进行添加。
			 */
			/**
			 * 将信息封装成类，并通过该类对
			 * 数据库信息进行添加。
			 */
			receipt Receipt = new receipt();
			Receipt.setUid(uid);
			Receipt.setTitle(title);
			Receipt.setTax(tax);
			receiptService.addReceiptInfoByObj(Receipt);
			response.sendRedirect(getServletContext().getContextPath()+"/receiptInfoServlet?action=list");
		}else{
			/**
			 * 如果所有的action请求为非法请求（不为servlet，action里面 的请求）
			 * 则返回到发票展示页面。
			 * 防止多余的出口导致请求给出的响应为
			 * 浏览器的空白页面，给用户的体验不好。
			 */
			response.sendRedirect(getServletContext().getContextPath()+"/receiptInfoServlet?action=list");
		}
	}

}
