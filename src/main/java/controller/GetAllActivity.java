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

@WebServlet("/GetAllActivity")  // 設定 Servlet 的路徑
@MultipartConfig
public class GetAllActivity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetAllActivity() {
        super();
    }
    Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	      	request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			// 建立JNDI Context 物件,用來取得DataSource
			Context context = new InitialContext();
			// 從 JNDI Context 取得 DataSource 物件，並透過 DataSource 取得 Connection 物件
			DataSource ds = (DataSource)context
					.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();

			// 建立ActivityDao物件,並呼叫 findAll 方法以查詢資料庫中所有活動
			ActivityDao activityDao = new ActivityDao(conn);
			List<ActivityBean> aBeanList = activityDao.findAll();	
			// 將查詢結果存入 request 的屬性中
			request.setAttribute("aBeanList", aBeanList);
			// 轉發到 JSP 頁面，以顯示查詢結果
			request.getRequestDispatcher("/a00/GetAllActivity.jsp")
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
