package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import javabeans.ActivityBean;
import dao.ActivityDao;

//指定這個Servlet的URL路徑為/Insert，表示當使用者訪問/Insert時，就會執行這個Servlet。
@WebServlet("/InsertActivity")
@MultipartConfig
public class InsertActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
      	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			// 取得 DataSource 並建立連線
			Context context = new InitialContext();
			DataSource ds = (DataSource)context
					.lookup("java:/comp/env/jdbc/servdb");
            // 使用連接進行資料庫操作
			conn = ds.getConnection();
	        Part filePart = request.getPart("activityCover");
	        InputStream fileContent = filePart.getInputStream();
	        byte[] aa = fileContent.readAllBytes();
	        ActivityBean aBean = new ActivityBean();
	        aBean.setActivityCover(aa);
	        
	        aBean.setActivityName(request.getParameter("activityName"));
	        aBean.setActivityContent(request.getParameter("activityContent"));
	        aBean.setActivityDate(request.getParameter("activityDate"));
	        System.out.println(request.getParameter("activityDate"));
	        aBean.setActivityLocation(request.getParameter("activityLocation"));
	        aBean.setActivityStatus(request.getParameter("activityStatus"));
	        
//	        // Save file to server filesystem
//	        String fileName = filePart.getSubmittedFileName();
//	        String filePath = getServletContext().getRealPath("/") + "uploads/" + fileName;
//	        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
//	        byte[] buffer = new byte[1024];
//	        int bytesRead = -1;
//	        while ((bytesRead = fileContent.read(buffer)) != -1) {
//	            fileOutputStream.write(buffer, 0, bytesRead);
//	        }
//	        fileOutputStream.close();
//			aBean.setActivityCover(filePart.getInputStream().readAllBytes()); // 將上傳的檔案轉換成byte[]，並存入ActivityBean物件中
	        String activityId =  request.getParameter("activityId");
	        aBean.setActivityId(activityId);
			ActivityDao activityDao = new ActivityDao(conn);		
			activityDao.Insert(aBean);
			aBean = activityDao.Find(activityId);
			
			request.setAttribute("aBean", aBean);
			request.setAttribute("message", "新增成功");
			request.getRequestDispatcher("/a00/InsertActivity.jsp")
					.forward(request,response);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
