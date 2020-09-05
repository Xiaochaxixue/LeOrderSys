package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.dingdan;
import bean.user;
import service.DingDanService;

/**
 * Servlet implementation class DingDanManageServlet
 */
@WebServlet("/DingDanManageServlet")
public class DingDanManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DingDanManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**
		 * 订单管理模块入口servlet，对订单进行管理
		 * 设置订单状态、交货日期、操作人
		 * 填写备注等信息
		 */
		System.out.println("=========订单管理信息显示servlet=========");
		
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//通过request.getParameter("id")方式获取的值都是String类型
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		System.out.println("id:"+id+" action:"+action);
		user  User = new user();
		User = (user) request.getSession().getAttribute("session_user");
		/**
		 * 从session里面拿出用户数据，得到user对象
		 */
		//String uid = User.getUid();
		if(action.equals("list")){
			List<dingdan> dingDans = new ArrayList<dingdan>();
			DingDanService dingDanService = new DingDanService();
			dingDans = dingDanService.findAllDingDanInfo();
			request.setAttribute("dingDans",dingDans);//将从数据库拿出来的的数据存入到请求头里面
			request.setAttribute("User",User);//将用户数据拿出来并存入到请求头里面
			/**
			 * 获取当前用户session，并将用户
			 * 的实体类拿出来，并存入到请求头里面
			 * 然后前端从请求头里面拿出用户数据
			 */
			request.setAttribute("mainRight", "/WEB-INF/jsp/dingDanManage.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("operation")){
			/**
			 * 对订单进行相关操作，设置订单相关信息
			 */
			/*System.out.println("request.getParameter(ps)");
			System.out.println(request.getParameter("ps"));*/
			request.getSession().removeAttribute("error");
			dingdan dingDan = new dingdan();
			/*System.out.println("request.getParameter(ddanNum):"+request.getParameter("ddanNum"));*/
			dingDan.setDdanNum(request.getParameter("ddanNum"));
			
			dingDan.setUid(request.getParameter("uid"));
			
			dingDan.setDealDate(request.getParameter("dealDate"));
			
			/*System.out.println("request.getParameter(totalprice):"+request.getParameter("totalprice"));*/
			dingDan.setTotalprice(Float.parseFloat(request.getParameter("totalprice")));
			
			
			dingDan.setPs(request.getParameter("ps"));
			//备注
			dingDan.setState(Integer.parseInt(request.getParameter("state"))+1);
			//状态加1
			String makedealDate=request.getParameter("makedealDate");
			dingDan.setMakedealDate(makedealDate);
			if(makedealDate==null||makedealDate==""){
				//request.setAttribute("error","未输入日期");
				request.getSession().setAttribute("error","未输入日期");
				response.sendRedirect(getServletContext().getContextPath()+"/DingDanManageServlet?action=list");
				return;
			}
			//需要进行入库处理
			dingDan.setOpa(request.getParameter("opa"));
			//需要进行入库处理
			DingDanService dingDanService = new DingDanService();
			dingDanService.setNextStepByObj(dingDan);
			response.sendRedirect(getServletContext().getContextPath()+"/DingDanManageServlet?action=list");
		}else{
			response.sendRedirect(getServletContext().getContextPath()+"/DingDanManageServlet?action=list");
		}
		
	}

}
