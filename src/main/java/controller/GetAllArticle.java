package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import javabeans.ArticleBean;
import dao.ArticleDao;

@WebServlet("/GetAllArticle")
public class GetAllArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
	
		try {
				ArticleDao ad= new ArticleDao();
				List<ArticleBean> articleListBean = ad.selectAllArticle();
//				request.setAttribute"key"自己取
				request.setAttribute("articleListBean", articleListBean);
				request.getRequestDispatcher("./jsp/GetAllArticle.jsp").forward(request, response);
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
