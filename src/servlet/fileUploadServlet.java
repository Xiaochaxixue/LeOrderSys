package servlet;

import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.shoppinginfo;
import service.ShoppingInfoService;



/**
 * Servlet implementation class fileUploadServlet
 */
@WebServlet("/fileUploadServlet")
public class fileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (!ServletFileUpload.isMultipartContent(request)) {
			return ;
		}
		
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//通过request.getParameter("id")方式获取的值都是String类型
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		System.out.println("id:"+id+" action:"+action);
		
		DiskFileItemFactory factoy=new DiskFileItemFactory();
        //创建解析器
        ServletFileUpload sfu=new ServletFileUpload(factoy);
        sfu.setHeaderEncoding("utf-8");//防止中文的文件名乱码
        //设置上传文件的大小
        sfu.setFileSizeMax(20*1024*1024);
        shoppinginfo shoppingInfo = new shoppinginfo();//将shoppingInfo信息收集到shoppinginfo中
        //解析request
        try {
            List<FileItem> list=sfu.parseRequest(request);

            for (FileItem fileItem : list) {
            	if (!fileItem.isFormField()) {
            		String fileName = fileItem.getName();
            		InputStream in = fileItem.getInputStream();
            		String path = "C:\\upload";
            		System.out.println("path:"+path);
            		File file= new File(path);
            		//String url = path + File.separator + fileName;
            		if (!file.exists() && !file.isDirectory()) {
            			System.out.println(path + "目录不存在，需要创建");
            			// 创建目录
            			file.mkdir();
            		}
            		/*String url = path + "/" + fileName;*/
            		//获取输出流，输出到本地文件中
            		OutputStream out = new FileOutputStream(new File(path,fileName));
            		int len = 0;
            		byte[] b= new byte[1024];
            		while((len = in.read(b))!=-1){
            			out.write(b, 0, len);
            		}
            		in.close();
            		out.close();
            		shoppingInfo.setPicture(fileName);
            	}
                
            }
          //Date date = null;
			Date systemDate = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = format.format(systemDate);//格式化过的当前时间，字符串类型
			System.out.println("当前时间为："+strDate);
            
            shoppingInfo.setCnum(list.get(0).getString("utf-8"));
            //System.out.println(list.get(0).getString("utf-8"));
			shoppingInfo.setCtype(list.get(1).getString("utf-8"));
			//System.out.println(Integer.parseInt(list.get(1).getString("utf-8")));
			shoppingInfo.setCname(list.get(2).getString("utf-8"));
			//System.out.println(list.get(2).getString("utf-8"));
			shoppingInfo.setGuige(list.get(3).getString("utf-8"));
			//System.out.println(list.get(3).getString("utf-8"));
			shoppingInfo.setDanwei(list.get(4).getString("utf-8"));
			//System.out.println(list.get(4).getString("utf-8"));
			shoppingInfo.setPrice(Float.parseFloat(list.get(5).getString("utf-8")));
			//System.out.println(Float.parseFloat(list.get(5).getString("utf-8")));
			shoppingInfo.setTiantype(Integer.parseInt(list.get(6).getString("utf-8")));
			//System.out.println(Integer.parseInt(list.get(6).getString("utf-8")));
			shoppingInfo.setPt(list.get(7).getString("utf-8"));
			//System.out.println(list.get(7).getString("utf-8"));
			shoppingInfo.setPnum(Integer.parseInt(list.get(8).getString("utf-8")));
			//System.out.println(Integer.parseInt(list.get(8).getString("utf-8")));
			shoppingInfo.setSstate(Integer.parseInt(list.get(9).getString("utf-8")));
			//System.out.println(Integer.parseInt(list.get(9).getString("utf-8")));
			shoppingInfo.setSselect(Integer.parseInt(list.get(10).getString("utf-8")));
			//System.out.println(Integer.parseInt(list.get(10).getString("utf-8")));
			/*shoppingInfo.setPicture(picture);*/
			
			shoppingInfo.setRuDate(strDate);
			System.out.println(strDate);
			/**
			 * 添加shoppingInfo信息到数据库
			 * shoppingInfo对象存储的信息已经存储到类里面去了
			 * 
			 */
            ShoppingInfoService shoppingInfoService = new ShoppingInfoService();
            shoppinginfo shoppingInfo2 = new shoppinginfo();
            shoppingInfo2 = shoppingInfoService.findShoppingInfoByCnum(shoppingInfo.getCnum());
            if(shoppingInfo2 != null && id == null){
            	request.setAttribute("error", "当前商品信息已存在，请重新输入！");
				request.setAttribute("mainRight", "/WEB-INF/jsp/shoppingInfoManageAddOrUpdate.jsp");
				request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
            }else{
            	System.out.println("==========shoppingInfo 添加商品信息==========");
            	shoppingInfoService.addShoppingInfoByObj(shoppingInfo);
            	response.sendRedirect(getServletContext().getContextPath()+"/shoppingInfoManageServlet?action=list");
            }
        } catch (FileUploadException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}

}
