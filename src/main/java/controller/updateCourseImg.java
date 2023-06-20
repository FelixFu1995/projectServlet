package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.courseDAO;
import javabeans.CourseBean;

@MultipartConfig
@WebServlet("/updateCourseImg")
public class updateCourseImg extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public updateCourseImg() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String courseId = request.getParameter("courseId");
		courseDAO cdao = null;
		try {
		
		// 获取上传的文件
        Part filePart = request.getPart("courseImg");
//        String fileName = filePart.getSubmittedFileName();
        InputStream fileContent = filePart.getInputStream();

        // 将文件内容读取为字节数组
        byte[] imageData = fileContent.readAllBytes();
        String mimeType = filePart.getContentType();
        
			
        cdao = new courseDAO();
		CourseBean cbean = new CourseBean();
		cbean.setCourseId(Integer.parseInt(courseId));
		cbean.setCourseImg(imageData);
				
				cdao.updateCourseImg(cbean);
				cbean.setCourseImgmimeType(mimeType);
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
