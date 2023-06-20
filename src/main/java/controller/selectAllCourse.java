package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.courseDAO;
import javabeans.CourseBean;


@WebServlet("/selectAllCourse")
public class selectAllCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public selectAllCourse() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		courseDAO cdao = null;
			try {
				cdao = new courseDAO();
				List<CourseBean> cbeans = cdao.selectAllCourse();
				request.setAttribute("cbeans", cbeans);
				request.getRequestDispatcher("/jsp/selectAllCourse.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			
			}finally {
				if(cdao != null && cdao.getConnection() !=null) {
					try {
						cdao.getConnection().close();
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
