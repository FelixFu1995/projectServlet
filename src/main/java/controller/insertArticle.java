package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.naming.NamingException;
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
@WebServlet("/insertArticle")
public class insertArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public insertArticle() {
		super();
	}
	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
			try {
				ArticleDao articleDao= new ArticleDao();
				ArticleBean articleBean= new ArticleBean();
				
				
//				String articleId=request.getParameter("articleId");
//				String userId=request.getParameter("userId");
				String articleTitle = request.getParameter("articleTitle");
				String articleContent = request.getParameter("articleContent");
//				String articleImg = request.getParameter("articleImg");
				String articleType = request.getParameter("articleType");
				
				// 获取上传的文件
		        Part filePart = request.getPart("articleImg");
//		        String fileName = filePart.getSubmittedFileName();
		        InputStream fileContent = filePart.getInputStream();
		        // 将文件内容读取为字节数组
		        byte[] imageData = fileContent.readAllBytes();
		        String mimeType = filePart.getContentType();
//					articleBean.setArticleId(Integer.parseInt(articleId));
//					articleBean.setUserId(Integer.parseInt(userId));
					articleBean.setArticleTitle(articleTitle);
					articleBean.setArticleContent(articleContent);
				
					LocalDateTime now = LocalDateTime.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					String articleTime = now.format(formatter);
					//把sql 語句的? 改成getDate()，就能直接在sql處理時間，而不用再java處理時間
//					String articleTime=request.getParameter("articleTime");
					articleBean.setArticleTime(articleTime);
					
					articleBean.setArticleType(articleType);
					
					//圖片
					articleBean.setArticleImg(imageData);
					articleBean.setArticleImgMimeType(mimeType);
					
					articleDao.insertArticle(articleBean);
					
					ArticleBean articleBean2 = articleDao.selectArticleByTitle(articleBean);
					
				request.setAttribute("articleBean2", articleBean2);
	
				request.getRequestDispatcher("/jsp/Insert.jsp").forward(request, response);
//				art.setArticleLikes(Integer.parseInt(request.getParameter("articleLikes")));
//				art.setArticleFollowNum(Integer.parseInt(request.getParameter("articleFollowNum")));
//				art.setAriticleSaveNum(Integer.parseInt(request.getParameter("ariticleSaveNum")));
				
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
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
