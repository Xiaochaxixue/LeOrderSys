package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.tianprice;
import service.TianPriceService;


/**
 * Servlet implementation class tianpriceManageServlet
 */
@WebServlet("/tianpriceManageServlet")
public class tianpriceManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tianpriceManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("=========费用管理servlet=========");
		
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//通过request.getParameter("id")方式获取的值都是String类型
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		System.out.println("id:"+id+" action:"+action);
		//获取所有附加费用信息
		List<tianprice> tianPrices=new ArrayList<tianprice>();//生成存储所有附加费用信息的列表类
		TianPriceService tianPriceService=new TianPriceService();
		tianPrices=tianPriceService.findAllTianPriceInfo();//得到了所有附加费用信息
		if(action.equals("list")){
			
			request.setAttribute("tianPrices",tianPrices);
			request.setAttribute("mainRight", "/WEB-INF/jsp/tianpriceManageList.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("preAdd")){
			
			System.out.println("preAdd操作 "+"id:"+id+" action:"+action);
			request.setAttribute("mainRight", "/WEB-INF/jsp/tianpriceManageAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("preUpdate")){
			/*
			 * 根据tianType查找到特定的TianPrince信息，并将TianPrince信息存储
			 * 到请求头中，方便前端展示*/
			//request.setAttribute("id", id);
			tianprice tianPrice = new tianprice() ;
			int tianType = Integer.parseInt(id) ;
			tianPrice = TianPriceService.findTianPriceInfoByTiantype(tianType);
			System.out.println("preUpdate "+"id:"+id+" action:"+action);
			request.setAttribute("tianPrice", tianPrice);//将天线信息存入请求头里面
			request.setAttribute("mainRight", "/WEB-INF/jsp/tianpriceManageAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("save")){
			tianprice tianPrice = new tianprice() ;
			tianPrice.setTiantype(Integer.parseInt(request.getParameter("tiantype")));
			tianPrice.setPrice(Float.parseFloat(request.getParameter("price")));
			if(id==null){
				System.out.println("----------------------");
				System.out.println("id:"+id);
				System.out.println("save "+"id:"+id+" action:"+action);
				tianprice tianpriceMatch = new tianprice();
				tianpriceMatch = TianPriceService.findTianPriceInfoByTiantype(tianPrice.getTiantype());
				if (tianpriceMatch != null){
					request.setAttribute("error", "当前天线类型已存在，请重新输入！");
					request.setAttribute("mainRight", "/WEB-INF/jsp/tianpriceManageAddOrUpdate.jsp");
					request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
				}else {
					System.out.println("==========tianprice save==========");
					tianPriceService.addTianPriceInfoByObj(tianPrice);
					response.sendRedirect(getServletContext().getContextPath()+"/tianpriceManageServlet?action=list");
				}
			}else if(id!=null){
				System.out.println("==========tianprice save//修改==========");
				System.out.println("id:"+id+" action:"+action);
				//更新附加费用信息到数据库中
				tianPriceService.updateTianPriceInfoByObj(tianPrice);
				
				response.sendRedirect(getServletContext().getContextPath()+"/tianpriceManageServlet?action=list");
			}
		}
	}

}
