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

@WebServlet("/GetActivity")
@MultipartConfig
public class GetActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetActivity() {
		super();
	}

	Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      	request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		// 取得要查詢的activityId
		String activityId = request.getParameter("activityId");
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();
			// 呼叫find方法查詢單筆資料
			ActivityDao activityDao = new ActivityDao(conn);
			ActivityBean aBean = new ActivityBean();
//	        Part filePart = request.getPart("activityCover");
//	        InputStream fileContent = filePart.getInputStream();
//	        byte[] aa = fileContent.readAllBytes();
//	        aBean.setActivityCover(aa);
//	        aBean.setActivityName(request.getParameter("activityName"));
//	        aBean.setActivityContent(request.getParameter("activityContent"));
//	        aBean.setActivityDate(request.getParameter("activityDate"));
//	        System.out.println(request.getParameter("activityDate"));
//	        aBean.setActivityLocation(request.getParameter("activityLocation"));
//	        aBean.setActivityStatus(request.getParameter("activityStatus"));
	        aBean = activityDao.Find(activityId);
			// 將查詢到的ActivityBean物件傳給JSP做顯示
			request.setAttribute("aBean", aBean);
			request.getRequestDispatcher("/a00/GetActivity.jsp").forward(request, response);

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
