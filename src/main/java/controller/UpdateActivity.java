package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;

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


@WebServlet("/UpdateActivity")
@MultipartConfig
public class UpdateActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateActivity() {
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
			
			// 取得表單資料
			String activityId = request.getParameter("activityId");
//			String activityName = request.getParameter("activityName");
//			String activityContent = request.getParameter("activityContent");
//			String activityDate = request.getParameter("activityDate");
//			String activityLocation = request.getParameter("activityLocation");
//			Part filePart = request.getPart("activityCover");
//			InputStream fileContent = filePart.getInputStream();
//			byte[] activityCover = fileContent.readAllBytes();
			
				// 將表單資料封裝成ActivityBean物件
			ActivityBean aBean = new ActivityBean();
			aBean.setActivityId(activityId);
//			aBean.setActivityName(activityName);
//			aBean.setActivityContent(activityContent);
//			aBean.setActivityDate(activityDate);
//			aBean.setActivityLocation(activityLocation);
//			aBean.setActivityCover(activityCover);
			// 使用 DAO 更新資料庫
			aBean = activityDao.Find(activityId);
			
//			boolean isUpdated = activityDao.UpdateData(aBean);
			request.setAttribute("aBean", aBean);

//			if (isUpdated) {
				// 更新成功，重新查詢資料庫並顯示
//				List<ActivityBean> aBeanList = activityDao.findAll();
//				request.setAttribute("aBeanList", aBeanList);
				request.getRequestDispatcher("/a00/GetActivity.jsp").forward(request, response);
//			} else {
				// 更新失敗，顯示錯誤訊息
//				response.getWriter().println("更新活動失敗！");
//			}

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
