package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ArticleDao;
import javabeans.ArticleBean;


@WebServlet("/selectArticleById")
public class selectArticleById extends HttpServlet {
	private static final long serialVersionUID = 1L;


	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 

		String articleId = request.getParameter("articleId");
		
			try {
				
				ArticleDao articleDao = new ArticleDao();
				ArticleBean articleBean = new ArticleBean();
				articleBean = articleDao.selectArticleById(Integer.parseInt(articleId));
				request.setAttribute("articleBean", articleBean);
				request.getRequestDispatcher("/jsp/selectArticleId.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
		
			}finally {
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
