package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.tb_address;
import bean.user;
import service.Tb_addressService;

/**
 * Servlet implementation class Tb_addressServlet
 */
@WebServlet("/Tb_addressServlet")
public class Tb_addressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tb_addressServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("=========地址信息管理servlet=========");
		/**
		 * 新增地址信息管理模块，对用户的收货地址进行管理
		 * 地址进行展示
		 * 地址管理类主要有收货人、收货电话号码、收货地址等信息
		 * 此外与地址相关的还有用户名
		 */
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		user  User = new user();
		User = (user) request.getSession().getAttribute("session_user");
		String uid = User.getUid();
		/**
		 * 从session中获取user对象，方便后续的使用，查找等。
		 */
		Tb_addressService tb_addressService = new Tb_addressService();//获取地址管理的服务层服务
		if(action.equals("list")){
			/**
			 * 展示相关的所有地址的地址信息
			 * 将所有的地址信息存入到List集合中
			 * 然后在前端进行展示
			 */
			List<tb_address> tb_addresss = new ArrayList<tb_address>();//存入到list中
			/**
			 * 在数据库中拿取所有信息，所有的地址信息
			 */
			tb_addresss = tb_addressService.findAllAddressInfoByUid(uid);
			request.setAttribute("tb_addresss", tb_addresss);
			request.setAttribute("mainRight", "/WEB-INF/jsp/addressInfoManage.jsp");//发票jsp静态文件
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);//在main.jsp页面展示发票的静态jsp页面
		}else if(action.equals("add")){
			/**
			 * 增加地址信息，对地址进行添加
			 * 添加地址信息后，返回到地址展示
			 * 信息页面
			 */
			//request.getParameter("");
			tb_address Tb_address = new tb_address();
			String acceptor = request.getParameter("acceptor");
			String tel = request.getParameter("tel");
			String address = request.getParameter("address");
			System.out.println("acceptor:"+acceptor+" tel:"+tel+" adress:"+address);
			/**
			 * 将数据封装成类
			 * Tb_addressMatch对象中设置地址信息
			 */
			Tb_address.setUid(uid);
			Tb_address.setAcceptor(acceptor);
			Tb_address.setTel(tel);
			Tb_address.setAddress(address);
			
			tb_address Tb_addressMatch = new tb_address();
			Tb_addressMatch = tb_addressService.findTb_addressIsExitByObj(Tb_address);
			/**
			 * 检查添加的信息是重复
			 * 如果重复则对其给出提示信息
			 * 同时检查输入的信息是否合法或者不输入
			 */
			if(acceptor==null||acceptor.isEmpty()||tel==null||tel.isEmpty()||address==null||address.isEmpty()){
				/**
				 * 如果输入的数据为空给出提示信息
				 */
				request.getSession().setAttribute("tip","输入的信息不完整，请重新添加信息！");
				response.sendRedirect(getServletContext().getContextPath()+"/Tb_addressServlet?action=list");
				return;
			}else if(Tb_addressMatch!=null){
				/**
				 * 判断是否数据库是否已经存在
				 * 若已经存在则返回到地址信息
				 * 管理页面
				 */
				
			}
		}
	}

}
