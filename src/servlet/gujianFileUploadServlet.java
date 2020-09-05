package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.gujian;
import service.GujianService;

/**
 * Servlet implementation class gujianFileUploadServlet
 */
@WebServlet("/gujianFileUploadServlet")
public class gujianFileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gujianFileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//通过request.getParameter("id")方式获取的值都是String类型
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		System.out.println("id:"+id+" action:"+action);
		
		gujian guJian = new gujian();//生成固件信息类存储固件信息
		
		DiskFileItemFactory factoy=new DiskFileItemFactory();
        //创建解析器
        ServletFileUpload sfu=new ServletFileUpload(factoy);
        sfu.setHeaderEncoding("utf-8");//防止中文的文件名乱码
        //设置上传文件的大小
        sfu.setFileSizeMax(20*1024*1024);
        
        
      //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = "C:\\upload";
        File file = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
        System.out.println(savePath+"目录不存在，需要创建");
        //创建目录
        file.mkdir();
        }
        
        Map<String,String> map = new HashMap<>();
        if (!ServletFileUpload.isMultipartContent(request)) {
			return ;
		}
        try {
			List<FileItem> list=sfu.parseRequest(request);
			int flag = 0;
			 for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					
	        		System.out.println(fileItem.getFieldName()+":"+fileItem.getString("UTF-8"));
                    map.put(fileItem.getFieldName(),fileItem.getString("UTF-8"));
	        	}else{
	        		
	        		String fileName = fileItem.getName();
	        		if(fileName==null||fileName==""){
	        			break;
	        		}
	        		flag = flag+1;
	        		
	        		
	        		InputStream in = fileItem.getInputStream();
	        		String path = "C:\\upload";
	        		System.out.println("path:"+path);
	        		OutputStream out = new FileOutputStream(new File(path,fileName));
	        		int len = 0;
	        		byte[] b= new byte[1024];
	        		while((len = in.read(b))!=-1){
	        			out.write(b, 0, len);}
	        		out.flush();
	        		in.close();
	        		out.close();
	        		guJian.setQfile(fileName);
	        		System.out.println("文件名："+fileName);
	        		
	        		/*String value = fileItem.getString("UTF-8");
	        		pList.add(value);*/
	        	}
	            
	        }
			 	Date systemDate = new Date();
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String strDate = format.format(systemDate);//格式化过的当前时间，字符串类型
				System.out.println("当前时间为："+strDate);
				//将表单内的数据存入到固件类中
				
				guJian.setGunum(map.get("gunum"));
				
				guJian.setUid(map.get("uid"));
				
				guJian.setCnum(map.get("cnum"));
				
				guJian.setGuversion(map.get("guversion"));
				
				guJian.setGuname(map.get("guname"));
				
				guJian.setGustate(Integer.parseInt(map.get("gustate")));
				
				guJian.setGups(map.get("gups"));
				
				guJian.setRuDate(strDate); 
				
				GujianService gujianService = new GujianService();
				
				/**
				 * 将固件信息添加到数据库，guJian对象存入到数据库中
				 * guJian对象存储的信息已经存储到了类里面了
				 */
				/**
				 * 进行数据的修改和添加，将对于对象数据进行修改或增加操作
				 */
				/**
				 * id值不为空的话，表示进行修改操作，id值为要修改的的数据项的固件编号
				 */
				if(flag == 0){
					/**
					 * 如果flag为1的话表明表单中没有传入文件，则需要进行额外的处理
					 */
					gujianService.updateGujianInfoByObjNoneFile(guJian);/*
					response.sendRedirect(getServletContext().getContextPath()+"/gujianInfoManageServlet?action=list");
					return;*/
				}
				if(id!=null ||id !=""){
					/**
					 * 进行数据库修改操作
					 */
					gujianService.updateGujianInfoByObj(guJian);
				}
				if(id == null){
					gujianService.addGujianInfoByObj(guJian);
				}
				response.sendRedirect(getServletContext().getContextPath()+"/gujianInfoManageServlet?action=list");
				/**
				 * 如果id值存在，则进行数据库的增加操作
				 */
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
