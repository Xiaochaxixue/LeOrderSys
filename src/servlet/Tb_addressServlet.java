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
import bean.tb_address;
import bean.user;
import service.ClientService;
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
		/**
		 * 新添修改、删除、设置为默认地址
		 * 等相关功能2020/09/24 songlj 16：53PM
		 */
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		String id = request.getParameter("id");
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
		Tb_addressService tb_addressService = new Tb_addressService();//获取地址管理的服务层服务
		if(action.equals("list")){
			/**
			 * 展示相关的所有地址的地址信息
			 * 将所有的地址信息存入到List集合中
			 * 然后在前端进行展示
			 */
			/**
			 * 新增默认地址一项2020/09/24 songlj 14：33PM
			 */
			List<tb_address> tb_addresss = new ArrayList<tb_address>();//存入到list中
			/**
			 * 在数据库中拿取所有信息，所有的地址信息
			 */
			tb_addresss = tb_addressService.findAllAddressInfoByUid(uid);
			ClientService clientService = new ClientService();
			client Client = clientService.findClientInfoByUid(uid);//方便展示默认地址
			request.setAttribute("tb_addresss", tb_addresss);
			request.getSession().setAttribute("Client", Client);
			request.setAttribute("mainRight", "/WEB-INF/jsp/addressInfoManage.jsp");//发票jsp静态文件
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);//在main.jsp页面展示发票的静态jsp页面
		}else if(action.equals("add")){
			/**
			 * 增加地址信息，对地址进行添加
			 * 添加地址信息后，返回到地址展示
			 * 信息页面
			 */
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
				request.getSession().setAttribute("tip", "该地址信息已经存在，请重新添加！");
				response.sendRedirect(getServletContext().getContextPath()+"/Tb_addressServlet?action=list");
				return;
			}/**
			 * 如果通过前面两重判断，
			 * 则对地址信息进行添加
			 * 操作，将相应的信息存入
			 */
			tb_addressService.addTb_addressServlet(Tb_address);
			request.getSession().setAttribute("tip", "添加信息成功");
			response.sendRedirect(getServletContext().getContextPath()+"/Tb_addressServlet?action=list");
		}else if(action.equals("proupdate")){
			/**
			 * 预修改地址信息
			 */
			//String uid;
			String acceptor;//收货人
			String tel;//收货电话号码
			String address;//收货地址
			acceptor = request.getParameter("id2");
			tel = request.getParameter("id3");
			address = request.getParameter("id4");
			tb_address Tb_address = new tb_address();
			Tb_address.setUid(uid);
			Tb_address.setAcceptor(acceptor);
			Tb_address.setTel(tel);
			Tb_address.setAddress(address);
			/**
			 * 如果是默认地址的话
			 * 则需要进行额外的处理
			 */
			request.getSession().setAttribute("Tb_address", Tb_address);
			System.out.println("acceptor:"+acceptor+" tel:"+tel+" address:"+address);
			if(id.equals("default")){
				request.setAttribute("id", id);
				request.setAttribute("mainRight", "/WEB-INF/jsp/addressUpdate.jsp");
				request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
				return;
			}
			request.setAttribute("mainRight", "/WEB-INF/jsp/addressUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("delete")){
			/**
			 * 删除地址信息
			 */
			String acceptor;//收货人
			String tel;//收货电话号码
			String address;//收货地址
			acceptor = request.getParameter("id2");
			tel = request.getParameter("id3");
			address = request.getParameter("id4");
			tb_address Tb_address = new tb_address();
			Tb_address.setUid(uid);
			Tb_address.setAcceptor(acceptor);
			Tb_address.setTel(tel);
			Tb_address.setAddress(address);
			tb_addressService.deleteTb_addressInfoByObj(Tb_address);
			request.getSession().setAttribute("tip", "删除信息成功");
			response.sendRedirect(getServletContext().getContextPath()+"/Tb_addressServlet?action=list");
		}else if(action.equals("reset")){
			/**
			 * 重置默认地址
			 */
			String acceptor;//收货人
			String tel;//收货电话号码
			String address;//收货地址
			acceptor = request.getParameter("id2");
			tel = request.getParameter("id3");
			address = request.getParameter("id4");
			tb_address Tb_address = new tb_address();
			Tb_address.setUid(uid);
			Tb_address.setAcceptor(acceptor);
			Tb_address.setTel(tel);
			Tb_address.setAddress(address);
			/**
			 * client里面修改默认地址
			 * 将client里面的默认地址存入
			 * 地址管理表中
			 * 1，将数据库中原本默认地址存入地址管理表
			 * 2，将要设置的地址更新到默认地址中
			 * 3，将要设置的地址删除
			 * 4，将更新的展示出来
			 */
			client Client = (client) request.getSession().getAttribute("Client");
			System.out.println(Client.toString());
			tb_address Tb_addressFromClient = new tb_address();
			Tb_addressFromClient.setUid(uid);
			Tb_addressFromClient.setAcceptor(Client.getContact());
			Tb_addressFromClient.setTel(Client.getPhone());
			Tb_addressFromClient.setAddress(Client.getAddress0());
			tb_addressService.addTb_addressServlet(Tb_addressFromClient);//原有默认地址存入地址表
			/**
			 * 默认地址修改到client里面
			 */
			Client.setContact(acceptor);
			Client.setPhone(tel);
			Client.setAddress0(address);
			ClientService clientService = new ClientService();
			clientService.updateClientInfoByObj(Client);
			//地址表删除Tb_address
			tb_addressService.deleteTb_addressInfoByObj(Tb_address);
			request.getSession().setAttribute("tip", "默认地址设置成功");
			response.sendRedirect(getServletContext().getContextPath()+"/Tb_addressServlet?action=list");
			
		}
		else if(action.equals("update")){
			/**
			 * 修改地址信息
			 */
			String acceptor;//收货人
			String tel;//收货电话号码
			String address;//收货地址
			acceptor = request.getParameter("acceptor");
			tel = request.getParameter("tel");
			address = request.getParameter("address");
			tb_address Tb_address = new tb_address();
			Tb_address.setUid(uid);
			Tb_address.setAcceptor(acceptor);
			Tb_address.setTel(tel);
			Tb_address.setAddress(address);
			tb_address Tb_addressFromSession = new tb_address();
			Tb_addressFromSession = (tb_address) request.getSession().getAttribute("Tb_address");
			
			if(id.equals("default")){
				/**
				 * 修改默认地址
				 */
				ClientService clientService = new ClientService();
				//client Client = clientService.findClientInfoByUid(uid);//方便展示默认地址
				client Client = (client) request.getSession().getAttribute("Client");
				Client.setContact(acceptor);
				Client.setPhone(tel);
				Client.setAddress0(address);
				clientService.updateClientInfoByObj(Client);
				request.getSession().setAttribute("tip", "修改信息成功");
				response.sendRedirect(getServletContext().getContextPath()+"/Tb_addressServlet?action=list");
				return;
			}
			
			tb_addressService.updateAddressByNewAndOldObj(Tb_addressFromSession,Tb_address);
			response.sendRedirect(getServletContext().getContextPath()+"/Tb_addressServlet?action=list");
		}else{
			/**
			 * 当没有其它action为出口时，则从这个入口
			 * 出去，返回到地址管理展示界面
			 */
			response.sendRedirect(getServletContext().getContextPath()+"/Tb_addressServlet?action=list");
		}
	}

}
