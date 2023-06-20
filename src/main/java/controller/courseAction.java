package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
@WebServlet("/courseAction")
public class courseAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public courseAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String page = request.getParameter("page");
			// 新增頁面
		if (page != null && page.equals("add")) {
			request.getRequestDispatcher("/insertCourse").forward(request, response);
			// 修改頁面
		} else if (page != null && page.equals("update")) {
			request.getRequestDispatcher("/selectCourseById").forward(request, response);
			// 修改圖片
		} else if (page != null && page.equals("updateImg")) {
			request.getRequestDispatcher("/updateCourseImg").forward(request, response);
			// 刪除頁面
		} else if (page != null && page.equals("delete")) {
			request.getRequestDispatcher("/deleteCourse").forward(request, response);
		}
		
		;

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
