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

@WebServlet("/UpdateActivityView")
@MultipartConfig
public class UpdateActivityView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateActivityView() {
        super();
    }

    Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			// 取得 DataSource 並建立連線
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();
			ActivityDao activityDao = new ActivityDao(conn);
			ActivityBean aBean = new ActivityBean();
			
			// 取得表單資料
	        Part filePart = request.getPart("activityCover");
	        InputStream fileContent = filePart.getInputStream();
	        byte[] aa = fileContent.readAllBytes();
	        aBean.setActivityCover(aa);
	        String activityId =  request.getParameter("activityId");
	        aBean.setActivityId(activityId);
	        aBean.setActivityName(request.getParameter("activityName"));
	        aBean.setActivityContent(request.getParameter("activityContent"));
	        aBean.setActivityDate(request.getParameter("activityDate"));
	        aBean.setActivityLocation(request.getParameter("activityLocation"));
	        aBean.setActivityStatus(request.getParameter("activityStatus"));
			
			// 使用 DAO 更新資料庫
			activityDao.UpdateData(aBean);
			aBean = activityDao.Find(activityId);
			request.setAttribute("aBean", aBean);

			request.getRequestDispatcher("/a00/GetActivity.jsp").forward(request, response);


		} catch (SQLException e) {
			e.printStackTrace();
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
