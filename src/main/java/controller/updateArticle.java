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
import javabeans.ArticleBean;
import dao.ArticleDao;

@MultipartConfig
@WebServlet("/updateArticle")
public class updateArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public updateArticle() {
		super();
	}

	Connection conn;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		try {
			String articleId = request.getParameter("articleId");
			String articleTitle = request.getParameter("articleTitle");
			String articleContent = request.getParameter("articleContent");
			String articleTime = request.getParameter("articleTime");
			String articleType = request.getParameter("articleType");
			// 获取上传的文件
			Part filePart = request.getPart("articleImg");
//        String fileName = filePart.getSubmittedFileName();
			InputStream fileContent = filePart.getInputStream();

			// 将文件内容读取为字节数组
			byte[] imageData = fileContent.readAllBytes();
			String mimeType = filePart.getContentType();

			ArticleDao articleDao = new ArticleDao();
			ArticleBean articleBean = new ArticleBean();
			articleBean.setArticleId(Integer.parseInt(articleId));
			articleBean.setArticleTitle(articleTitle);
			articleBean.setArticleContent(articleContent);
			articleBean.setArticleTime(articleTime);
			articleBean.setArticleType(articleType);
			articleBean.setArticleImg(imageData);
			articleBean.setArticleImgMimeType(mimeType);

			articleDao.updateOneArticle(articleBean, Integer.parseInt(articleId));

//		articleDao.updateArticleById(articleBean);
			ArticleBean beanArticleId = articleDao.selectArticleById(Integer.parseInt(articleId));

			// List<ArticleBean> ArticleBeanList = articleDao.selectAllArticle();

			request.setAttribute("beanArticleId", beanArticleId);
			request.getRequestDispatcher("/jsp/updateArticle.jsp").forward(request, response);
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
