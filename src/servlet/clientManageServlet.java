package servlet;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.client;
import bean.user;
import service.ClientService;
import service.UserService;


/**
 * Servlet implementation class clientManageServlet
 */
@WebServlet("/clientManageServlet")
public class clientManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public clientManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("=========客户管理servlet=========");
		
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//通过request.getParameter("id")方式获取的值都是String类型
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		System.out.println("id:"+id+" action:"+action);
		//获取所有客户信息
		List<client> clients=new ArrayList<client>();//生成存储所有客户信息的列表类
		ClientService clientService=new ClientService();
		clients=clientService.findAllClientInfo();//得到了所有客户信息
		if(action.equals("list")){
			
			//request.setAttribute("id", id);
			//System.out.println("list "+"id:"+id+" action:"+action);
			request.setAttribute("clients", clients);//将客户信息存入请求头里面
			request.setAttribute("mainRight", "/WEB-INF/jsp/clientManageList.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("preAdd")){
			/*System.out.println("preAdd "+"id:"+id+" action:"+action);*/
			request.setAttribute("mainRight", "/WEB-INF/jsp/clientManageAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("preUpdate")){
			client Client = new client();
			String Uid=id;//将id的传给方法，根据Uid查找客户信息
			Client = ClientService.findClientInfoByUid(Uid);
			//request.setAttribute("id", id);
			System.out.println("preUpdate "+"id:"+id+" action:"+action);
			request.setAttribute("client", Client);//将客户信息存入请求头里面
			request.setAttribute("mainRight", "/WEB-INF/jsp/clientManageAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("save")){
			client Client = new client();
			//request.setAttribute("id", id);
			//保存Client里面的密码信息
			Client.setUid(request.getParameter("uid"));
			Client.setPaw(request.getParameter("paw"));
			Client.setCorname(request.getParameter("corname"));
			Client.setCoraddress(request.getParameter("coraddress"));
			Client.setContact(request.getParameter("contact"));
			Client.setPhone(request.getParameter("phone"));
			Client.setAddress0(request.getParameter("address0"));
			Client.setAddress1(request.getParameter("address1"));
			Client.setAddress2(request.getParameter("address2"));
			//System.out.println("uid:"+request.getParameter("uid")+" paw:"+request.getParameter("paw")+request.getParameter("paw").equals(""));
			//String Uid = request.getParameter("uid");
			//Client = ClientService.findClientInfoByUid(Uid);
			
			user User = new user();
			User.setUid(request.getParameter("uid"));//保存User里面Uid信息
			User.setPaw(request.getParameter("paw"));//保存User里面的密码信息
			User.setType(1);//保存User里面的用户类型信息
			
			if (id==null){
				 //添加新的客户信息，将数据存入数据库
				System.out.println("----------id为空，进行添加客户信息------------");
				System.out.println("id:"+id);
				System.out.println("save "+"id:"+id+" action:"+action);
				client ClientMatch = new client();//对数据库中的数据进行匹配新成一个对象
				ClientMatch = ClientService.findClientInfoByUid(Client.getUid());//查看客户是否已经存在在数据库中
				//System.out.println("ClientMatch.getUid():"+ClientMatch.getUid());
				if (ClientMatch!= null){
					request.setAttribute("error", "当前用户名已存在，请重新输入！");
					request.setAttribute("mainRight", "/WEB-INF/jsp/clientManageAddOrUpdate.jsp");
					request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
				}
				else{
					System.out.println("==========client save==========");
					ClientService.addClientInfoByObj(Client);
					System.out.println("==========user save==========");
					UserService.addUserInfoByObj(User);//对user表进行操作，添加user信息
					response.sendRedirect(getServletContext().getContextPath()+"/clientManageServlet?action=list");
				}
			}else if(id!=null){
				
				System.out.println("==========client save//修改==========");
				System.out.println("id:"+id+" action:"+action);
				
				ClientService.updateClientInfoByObj(Client);
				//对user表进行操作，修改user登录信息
				UserService.updateUserInfoByObj(User);
				
				//request.setAttribute("client", Client);//将客户信息存入请求头里面
				response.sendRedirect(getServletContext().getContextPath()+"/clientManageServlet?action=list");
			}
			//request.setAttribute("id", id);
			
		}
		
	}

}
