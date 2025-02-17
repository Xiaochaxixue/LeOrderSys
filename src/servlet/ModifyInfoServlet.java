package servlet;

import java.io.IOException;
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
 * Servlet implementation class ModifyInfoServlet
 */
@WebServlet("/ModifyInfoServlet")
public class ModifyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("=========修改个人信息servlet=========");
		
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		ClientService clientService=new ClientService();
		//通过request.getParameter("id")方式获取的值都是String类型
		/*String id = request.getParameter("id");
		request.setAttribute("id", id);
		System.out.println("id:"+id+" action:"+action);*/
		if(action.equals("list")){
			client Client = new client();
			user  User = new user();
			User = (user) request.getSession().getAttribute("session_user");
			String uid = User.getUid();
			Client = clientService.findClientInfoByUid(uid);
			request.setAttribute("client", Client);//将客户信息存入请求头里面
			request.setAttribute("mainRight", "/WEB-INF/jsp/modifyInfoManage.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("save")){
			client Client = new client();
			String psw;
			String paw = request.getParameter("paw");
			String newpaw = request.getParameter("newpaw");
			//保存Client里面的密码信息
			String uid = request.getParameter("uid");
			Client.setUid(uid);
			if(newpaw==""||newpaw==null){
				psw =paw;
			}else{
				psw = request.getParameter("newpaw");
			}
			Client.setPaw(psw);
			Client.setCorname(request.getParameter("corname"));
			Client.setCoraddress(request.getParameter("coraddress"));
			Client.setContact(request.getParameter("contact"));
			Client.setPhone(request.getParameter("phone"));
			Client.setAddress0(request.getParameter("address0"));
			Client.setAddress1(request.getParameter("address1"));
			Client.setAddress2(request.getParameter("address2"));
			//将客户信息更新
			ClientService.updateClientInfoByObj(Client);
			/**
			 * 将用户信息也随着客户信息的改变而改变，改变客户的用户登录信息
			 */
			if(newpaw==""||newpaw==null){
				response.sendRedirect(getServletContext().getContextPath()+"/ModifyInfoServlet?action=list");
				return;
			}else{
				user User = new user();
				User.setUid(uid);
				User.setPaw(psw);
				UserService.updateUserInfoByObj(User);
				response.sendRedirect(getServletContext().getContextPath()+"/LogOutServlet");
			}
		}else if(action.equals("resetPaw")){
			/**
			 * 重置密码
			 */
			String newpaw = request.getParameter("newpaw");
			String newpaw1 = request.getParameter("newpaw1");
			if(newpaw==null||newpaw.isEmpty()||newpaw1==null||newpaw1.isEmpty()){
				request.getSession().setAttribute("tip","输入信息不完整！请重新输入相关信息。");
				response.sendRedirect(getServletContext().getContextPath()+"/ModifyInfoServlet?action=list");
				return;
			}else if(!(newpaw.equals(newpaw1))){
				request.getSession().setAttribute("tip","两次输入的的信息不相同，请重新输入信息!");
				response.sendRedirect(getServletContext().getContextPath()+"/ModifyInfoServlet?action=list");
				return;
			}
			//更新密码
			user  User = new user();
			User = (user) request.getSession().getAttribute("session_user");
			String uid = User.getUid();
			client Client = clientService.findClientInfoByUid(uid);
			Client.setPaw(newpaw);
			//返回到展示信息页面，退出系统，要求重新登录
			User.setUid(uid);
			User.setPaw(newpaw);
			ClientService.updateClientInfoByObj(Client);
			UserService.updateUserInfoByObj(User);
			response.sendRedirect(getServletContext().getContextPath()+"/LogOutServlet");
		}else{
			request.getSession().setAttribute("tip","非法操作！已经记录，管理员将会处理，多次非法操作将冻结账号。");
			response.sendRedirect(getServletContext().getContextPath()+"/ModifyInfoServlet?action=list");
		}
	}

}
