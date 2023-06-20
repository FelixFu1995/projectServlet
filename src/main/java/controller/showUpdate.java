package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import javabeans.ArticleBean;
import dao.ArticleDao;
@MultipartConfig
@WebServlet("/showUpdate")
public class showUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public showUpdate() {
		super();
	}

	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 

		try {
				String articleId = request.getParameter("articleId");
				String articleTitle= request.getParameter("articleTitle");
				String articleContent= request.getParameter("articleContent");
				String articleType =request.getParameter("articleType");
				
				
				Part filePart = request.getPart("articleImg");
				InputStream fileContent = filePart.getInputStream();
				byte[] imageData = fileContent.readAllBytes();
				String mimeType = filePart.getContentType();
				
				String articleImg=request.getParameter("articleImg");
				LocalDateTime now = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String articleTime = now.format(formatter);
				
				

				ArticleDao articleDao = new ArticleDao();
				ArticleBean articleBean = new ArticleBean();
				
				articleBean.setArticleId(Integer.parseInt(articleId));
				articleBean.setArticleTitle(articleTitle);
				articleBean.setArticleContent(articleContent);
				
				articleBean.setArticleTime(articleTime);
				
				articleBean.setArticleType(articleType);
				
				//圖片
				articleBean.setArticleImg(imageData);
				articleBean.setArticleImgMimeType(mimeType);
				
				articleDao.updateOneArticle(articleBean, Integer.parseInt(articleId));
				articleBean = articleDao.selectArticleById(Integer.parseInt(articleId));
				
				request.setAttribute("articleBean", articleBean);
				request.getRequestDispatcher("/jsp/ShowResult.jsp").forward(request, response);
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
