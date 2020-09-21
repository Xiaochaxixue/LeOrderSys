package utils;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bean.user;
import service.UserService;


/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(filterName="/LoginFilter",urlPatterns={"/clientManageServlet",
													"/DingDanManageServlet",
													"/fileUploadServlet",
													"/gujianFileUploadServlet",
													"/gujianInfoManageServlet",
													"/indexServlet",
													"/LoginServlet",
													"/LogOutServlet",
													"/ModifyFileServlet",
													"/ModifyInfoServlet",
													"/shoppingInfoManageServlet",
													"/ShowShoppingInfoServlet",
													"/tianpriceManageServlet",
													"/receiptInfoServlet",
													"/Tb_addressServlet",
													"/updatePaiServlet"})
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("------------拦截器,拦截操作，过滤------------");
		
		HttpServletRequest  httpServletRequest = (HttpServletRequest) request;
		HttpSession  session = httpServletRequest.getSession();
		user User = (user)session.getAttribute("session_user");
		UserService userService = new UserService();
		// pass the request along the filter chain
		//chain.doFilter(request, response);
		if(httpServletRequest.getRequestURI().contains("LoginServlet")){
			chain.doFilter(httpServletRequest,response);
		}else if(User!=null){
			/*登入，就放行，走处理该请求的方法
			 * 通过user判断登录的用户角色是否
			 * 拥有对该请求的权限
			 * 通过request中的httpServletRequest.getRequestURI()来
			 * 获取用户发送的请求
			 * */
			
			roleJudgment(User,httpServletRequest,response,chain);
			//chain.doFilter(request, response);
		}else{
			/**
			 * ② 判断cookie中是否有用户信息
			 * 然后去遍历所有的cookie，看是否
			 * 有保存当前的用户信息的cookie
			 */
			Cookie cookie = CookieUtil.getCookieByName(httpServletRequest, "cookie_name_pass");
			
			if(cookie != null){
				/**
				 * 表示当前的cookie中含有用户信息
				 */
				String strNameAndPaw = URLDecoder.decode(cookie.getValue(),"UTF-8");
				System.out.println("strNameAndPaw:"+strNameAndPaw);
				String[] strNameAndPaw2 = strNameAndPaw.split("#");
				user User2 = userService.findByuserNameAndPass(strNameAndPaw2[0], strNameAndPaw2[1]);
				
				System.out.println("strNameAndPaw2[0]:"+strNameAndPaw2[0]+"  strNameAndPaw2[1]:"+strNameAndPaw2[1]);
				if(User2 != null){
					/*记住在浏览器的用户信息有效，放行，自动登录成功将用户信息保存在session中
					通过user 判断登录的用户角色是否有对该请求的访问权限
					通过request中的httpServletRequest.getRequestURI(); 来获取用*/
					
					roleJudgment(User2,httpServletRequest,response,chain);
					/*httpServletRequest.getSession().setAttribute("session_user", user2);
					chain.doFilter(request, response);*/
				}else{
					/**
					 * 记住在浏览器中的用户信息无效
					 * 需跳转到登录页面
					 */
					request.setAttribute("error", "请先登录！");
					//跳转到登录页面
					request.getRequestDispatcher("/index.jsp").forward(httpServletRequest, response);
				}
			}else{
				request.setAttribute("error", "请先登录！");
				//跳转到登录页面
				request.getRequestDispatcher("/index.jsp").forward(httpServletRequest, response);
			}
		}
	}

	private void roleJudgment(user user, HttpServletRequest httpServletRequest, ServletResponse response,
			FilterChain chain) {
		// TODO Auto-generated method stub
		/**
		 * 获取 用户类型
		 * 获取请求地址
		 */
		Integer roleId = user.getType();
		String  requestUrI = httpServletRequest.getRequestURI();
		System.out.println("requestUrI:"+requestUrI);
		/**
		 * 动态获取项目名
		 */
		String path = httpServletRequest.getContextPath();
		//System.out.println("path:"+path);
		if((roleId.equals(0))&&(
				requestUrI.startsWith(path+"/indexServlet")||
				requestUrI.startsWith(path+"/LogOutServlet")||
				requestUrI.startsWith(path+"/clientManageServlet")||
				requestUrI.startsWith(path+"/DingDanManageServlet")||
				requestUrI.startsWith(path+"/fileUploadServlet")||
				requestUrI.startsWith(path+"/gujianFileUpload")||
				requestUrI.startsWith(path+"/gujianInfo")||
				requestUrI.startsWith(path+"/ModifyFileServlet")||
				requestUrI.startsWith(path+"/shoppingInfo")||
				requestUrI.startsWith(path+"/tianprice")||
				requestUrI.startsWith(path+"/updatePai")||
				requestUrI.startsWith(path+"/ShowShoppingInfoServlet")
				)){
			/**
			 * 表示当前用户为管理员用户，放行
			 */
			httpServletRequest.getSession().setAttribute("session_user", user);
			try {
				chain.doFilter(httpServletRequest, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if((roleId.equals(1))&&(//receiptInfoServlet,Tb_addressServlet
				requestUrI.startsWith(path+"/indexServlet")||
				requestUrI.startsWith(path+"/ShowShoppingInfoServlet")||
				requestUrI.startsWith(path+"/ModifyInfoServlet")||
				requestUrI.startsWith(path+"/ModifyFileServlet")||//添加用户上传企业执照扫描件，然后给用户开启权限
				requestUrI.startsWith(path+"/receiptInfoServlet")||
				requestUrI.startsWith(path+"/Tb_addressServlet")||
				requestUrI.startsWith(path+"/LogOutServlet")
				)){
			httpServletRequest.getSession().setAttribute("session_user", user);
			try {
				chain.doFilter(httpServletRequest, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if((roleId.equals(2))&&(
				requestUrI.startsWith(path+"/indexServlet")||
				requestUrI.startsWith(path+"/LogOutServlet")||
				requestUrI.startsWith(path+"/clientManageServlet")||
				requestUrI.startsWith(path+"/DingDanManageServlet")||
				requestUrI.startsWith(path+"/fileUploadServlet")||
				requestUrI.startsWith(path+"/ModifyFileServlet")||
				requestUrI.startsWith(path+"/shoppingInfo")||
				requestUrI.startsWith(path+"/tianprice")||
				requestUrI.startsWith(path+"/updatePai")||
				requestUrI.startsWith(path+"/ShowShoppingInfoServlet")
				)){
			/**
			 * 表示当前用户为管理员用户，放行
			 */
			httpServletRequest.getSession().setAttribute("session_user", user);
			try {
				chain.doFilter(httpServletRequest, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if((roleId.equals(3))&&(
				requestUrI.startsWith(path+"/indexServlet")||
				requestUrI.startsWith(path+"/LogOutServlet")||
				requestUrI.startsWith(path+"/fileUploadServlet")||
				requestUrI.startsWith(path+"/gujianFileUpload")||
				requestUrI.startsWith(path+"/gujianInfo")||
				requestUrI.startsWith(path+"/ModifyFileServlet")|
				requestUrI.startsWith(path+"/tianprice")||
				requestUrI.startsWith(path+"/updatePai")
				)){
			/**
			 * 表示当前用户为管理员用户，放行
			 */
			httpServletRequest.getSession().setAttribute("session_user", user);
			try {
				chain.doFilter(httpServletRequest, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				httpServletRequest.getRequestDispatcher("/index.jsp").forward(httpServletRequest, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
