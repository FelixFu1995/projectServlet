package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.courseDAO;
import javabeans.CourseBean;

@WebServlet("/deleteCourse")
public class deleteCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public deleteCourse() {
		super();
	}

	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		courseDAO cdao = null;
			try {
				String courseId = request.getParameter("courseId");
				cdao = new courseDAO();
				CourseBean cbean = new CourseBean();
				cbean.setCourseId(Integer.parseInt(courseId));
				cdao.deleteCourse(cbean);
				List<CourseBean> cbeans = cdao.selectAllCourse();
				request.setAttribute("cbeans", cbeans);
				request.getRequestDispatcher("/jsp/selectAllCourse.jsp").forward(request, response);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
			}	finally {
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
