package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.pai;
import service.PaiService;

/**
 * Servlet implementation class updatePaiServlet
 */
@WebServlet("/updatePaiServlet")
public class updatePaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updatePaiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("=========排针方式费用修改servlet=========");
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		pai Pai = new pai();
		PaiService paiService = new PaiService();
		Pai = paiService.findPaiInfo();//得到排针方式的信息。
		if(action.equals("preAdd")){
			
			request.setAttribute("Pai", Pai);//将排针方式信息存入请求头里面
			request.setAttribute("mainRight", "/WEB-INF/jsp/PaiUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
			
		}else if(action.equals("save")){
			String ppriceS = request.getParameter("id");
			System.out.println("ppriceS"+ppriceS);
			String pprice2 = request.getParameter("pprice2");
			System.out.println("pprice2"+pprice2);
			Pai = paiService.updatePaiInfoByPprice(Float.parseFloat(ppriceS), Float.parseFloat(pprice2));
			
			response.sendRedirect(getServletContext().getContextPath()+"/tianpriceManageServlet?action=list");
		}
		
	}

}
