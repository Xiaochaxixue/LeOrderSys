package servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import service.GujianService;
import service.ShoppingInfoService;
import utils.RandomUtil;

/**
 * Servlet implementation class ModifyFileServlet
 */
@WebServlet("/ModifyFileServlet")
public class ModifyFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("=========修改文件servlet=========");
		
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//通过request.getParameter("id")方式获取的值都是String类型
		String id = request.getParameter("id");
		//request.setAttribute("id", id);
		System.out.println("id:"+id+" action:"+action);
		if(action.equals("jump")){
			//String gunum = id;
			request.setAttribute("id", id);
			request.setAttribute("mainRight", "/WEB-INF/jsp/uploadGuFile.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("guFile")){
			String gunum = id;
			String updir = "C:\\upload";
			//判断提交过来的表单是否为文件上传表单。
			String fileNewName ="";
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart){
				//开始构造文件上传处理对象
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				Iterator items;
				try {
					//从表单中提取文件内容
					items = upload.parseRequest(request).iterator();
					while(items.hasNext()){
						FileItem item = (FileItem)items.next();
						if(!item.isFormField()){
							//取出上传文件的文件名称
							String name = item.getName();
							/*String fileName = name.substring(name.lastIndexOf('\\')+1, name.length());*/
							fileNewName =RandomUtil.getOrderIdByTime()+"_"+name;
							String path = updir+File.separatorChar+fileNewName;
							//将文件存储到服务器中
							File uploadFile=new File(path);
							item.write(uploadFile);
						}
					}
					/**
					 * 将新生成的文件名存入到数据库
					 * 根据固件编号存入到数据库2020/09/01am
					 * fileNewName, gunum
					 * 根据gunum，对数据库中的文件进行修改
					 * 将fileNewName存入
					 */
					GujianService gujianService = new GujianService();
					gujianService.updateGujianFileByGunum(fileNewName,gunum);
					System.out.println("fileNewName:"+fileNewName+" gunum:"+gunum);
					/**
					 * 存入数据库完毕后，进行页面跳转
					 * 跳转到gujianInfoManageServlet里面
					 * 进行固件信息数据展示
					 */
					response.sendRedirect(getServletContext().getContextPath()+"/gujianInfoManageServlet?action=list");
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else if(action.equals("jump2")){
			request.setAttribute("id", id);
			request.setAttribute("mainRight", "/WEB-INF/jsp/uploadShoppingFile.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action.equals("shoppingFile")){
			String cnum = id;//产品编号
			String updir = "C:\\upload";
			//判断提交过来的表单是否为文件上传表单。
			String fileNewName ="";
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if(isMultipart){
				//开始构造文件上传处理对象
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				Iterator items;
				try {
					//从表单中提取文件内容
					items = upload.parseRequest(request).iterator();
					while(items.hasNext()){
						FileItem item = (FileItem)items.next();
						if(!item.isFormField()){
							//取出上传文件的文件名称
							String name = item.getName();
							/*String fileName = name.substring(name.lastIndexOf('\\')+1, name.length());*/
							fileNewName =RandomUtil.getOrderIdByTime()+"_"+name;
							String path = updir+File.separatorChar+fileNewName;
							//将文件存储到服务器中
							File uploadFile=new File(path);
							item.write(uploadFile);
						}
					}
					/**
					 * 将新生成的文件名存入到数据库
					 * 根据产品编号存入到数据库2020/09/01pm14:32
					 * fileNewName, cnum
					 * 根据cnum，对数据库中的文件进行修改
					 * 将fileNewName存入
					 */
					ShoppingInfoService shoppingInfoService = new ShoppingInfoService();
					shoppingInfoService.updateShoppingFileByCnum(fileNewName,cnum);
					System.out.println("fileNewName:"+fileNewName+" cnum:"+cnum);
					/**
					 * 存入数据库完毕后，进行页面跳转
					 * 跳转到gujianInfoManageServlet里面
					 * 进行固件信息数据展示
					 */
					response.sendRedirect(getServletContext().getContextPath()+"/shoppingInfoManageServlet?action=list");
				} catch (FileUploadException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
