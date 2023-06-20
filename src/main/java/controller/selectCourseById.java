package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.courseDAO;
import javabeans.CourseBean;

@WebServlet("/selectCourseById")
public class selectCourseById extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public selectCourseById() {
		super();
	}

	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String courseId = request.getParameter("courseId");
		System.out.print(courseId);
		
			try {
				courseDAO cdao = new courseDAO();
				CourseBean cbean = new CourseBean();
				cbean.setCourseId(Integer.parseInt(courseId));
				CourseBean ccbean = cdao.selectCourseById(cbean);
				request.setAttribute("cbean", ccbean);
				request.getRequestDispatcher("/jsp/updateCourse.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if(conn!=null) {
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
