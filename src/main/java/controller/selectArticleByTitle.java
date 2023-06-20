package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javabeans.ArticleBean;
import dao.ArticleDao;

@WebServlet("/selectArticleByTitle")
public class selectArticleByTitle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public selectArticleByTitle() {
		super();
	}

	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String articleTitle = request.getParameter("articleTitle");
		request.setCharacterEncoding("UTF-8"); 

		String articleId = request.getParameter("articleId");
		
			try {
				ArticleDao articleDao = new ArticleDao();
				ArticleBean articleBean = new ArticleBean();
				articleBean.setArticleId(Integer.parseInt(articleId));
				
				ArticleBean ArticleBean2 = articleDao.selectArticleById(Integer.parseInt(articleId));
				request.setAttribute("ArticleBean2", ArticleBean2);
				request.getRequestDispatcher("/jsp/GetOneArticle.jsp").forward(request, response);
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
