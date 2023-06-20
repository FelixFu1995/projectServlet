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

@WebServlet("/DeleteActivity")
@MultipartConfig
public class DeleteActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DeleteActivity() {
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
//			Part filePart = request.getPart("activityCover");
//			InputStream fileContent = filePart.getInputStream();
//			byte[] aa = fileContent.readAllBytes();
			ActivityBean aBean = new ActivityBean();
//	        aBean.setActivityCover(aa);
//			aBean.setActivityId(activityId);
//			aBean.setActivityName(request.getParameter("activityName"));
//			aBean.setActivityContent(request.getParameter("activityContent"));
//			aBean.setActivityDate(request.getParameter("activityDate"));
//			aBean.setActivityLocation(request.getParameter("activityLocation"));
//			aBean.setActivityStatus(request.getParameter("activityStatus"));

			ActivityDao activityDao = new ActivityDao(conn);
			aBean.setActivityId(activityId); // 将 activityId 设置到 ActivityBean 对象
			aBean = activityDao.Find(activityId);
			request.setAttribute("activityId", activityId);
			request.setAttribute("aBean", aBean);
			request.setAttribute("message", "刪除成功");
			activityDao.Delete(activityId); // 删除该 ActivityBean 对象
			request.getRequestDispatcher("/a00/DeleteActivity.jsp").forward(request, response);

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
