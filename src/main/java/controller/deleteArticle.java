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

import javabeans.ArticleBean;
import dao.ArticleDao;

@WebServlet("/deleteArticle")
public class deleteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public deleteArticle() {
		super();
	}

	Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			String articleId = request.getParameter("articleId");
			ArticleDao articleDao = new ArticleDao();
			ArticleBean articleBean = new ArticleBean();
			articleBean.setArticleId(Integer.parseInt(articleId));

			articleDao.deleteArticleById(articleBean);

			articleBean = articleDao.selectArticleById(Integer.parseInt(articleId));
			request.setAttribute("articleBean", articleBean);
			request.getRequestDispatcher("/jsp/ShowDeleteResult.jsp").forward(request, response);
		} catch (SQLException | NamingException e) {
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
