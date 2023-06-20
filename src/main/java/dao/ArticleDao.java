package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.MultipartConfig;
import javax.sql.DataSource;

import javabeans.ArticleBean;

@MultipartConfig
public class ArticleDao {
		Connection conn;
		Context context;
		DataSource ds;
		
	public ArticleDao() throws NamingException, SQLException {
			context = new InitialContext();
			ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();
		}
		
	//單筆查詢
	public  ArticleBean selectArticleById(int articleId) throws NamingException, SQLException {
		String sql = "select * from article where articleId = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, articleId);
		ResultSet rs = stmt.executeQuery();
		ArticleBean articleBean =new ArticleBean();
		if(rs.next()) {
			articleBean.setArticleId(rs.getInt("articleId"));
			articleBean.setUserId(rs.getInt("userId"));
			articleBean.setArticleTitle(rs.getString("articleTitle"));
			articleBean.setArticleContent(rs.getString("articleContent"));
			articleBean.setArticleImg(rs.getBytes("articleImg"));
			articleBean.setArticleTime(rs.getString("articleTime"));
			articleBean.setArticleType(rs.getString("articleType"));
		}
		stmt.close();
//		conn.close();
		return articleBean;
	}
	
	//根據文章標題查詢
	public ArticleBean selectArticleByTitle(ArticleBean article) throws NamingException, SQLException {
		String sql = "select * from article where articleTitle = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, article.getArticleTitle());
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {

			
			article.setArticleId(rs.getInt("articleId"));
			article.setUserId(rs.getInt("userId"));
			article.setArticleTitle(rs.getString("articleTitle"));
			article.setArticleContent(rs.getString("articleContent"));
			article.setArticleImg(rs.getBytes("articleImg"));
//			article.setArticleTime(rs.getDate("articleTime"));
			
			article.setArticleTime(rs.getString("articleTime"));
			article.setArticleType(rs.getString("articleType"));
//			article2.setArticleLikes(rs.getInt("articleLikes"));
//			article2.setArticleFollowNum(rs.getInt("articleFollowNum"));
//			article2.setAriticleSaveNum(rs.getInt("ariticleSaveNum"));
		}
		stmt.close();
		return article;
	}
	
	//查詢全部資料
	public List<ArticleBean> selectAllArticle() throws NamingException, SQLException {
		String sql = "select * from article";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<ArticleBean> articleListBean = new ArrayList<>();
		while(rs.next()) {
			ArticleBean articleBean = new ArticleBean();
			articleBean.setArticleId(rs.getInt("articleId"));
			articleBean.setUserId(rs.getInt("userId"));
			articleBean.setArticleTitle(rs.getString("articleTitle"));
			articleBean.setArticleContent(rs.getString("articleContent"));
			articleBean.setArticleImg(rs.getBytes("articleImg"));
			articleBean.setArticleTime(rs.getString("articleTime"));
			articleBean.setArticleType(rs.getString("articleType"));
		//	article.setArticleLikes(rs.getInt("articleLikes"));
		//	article.setArticleFollowNum(rs.getInt("articleFollowNum"));
		//	article.setAriticleSaveNum(rs.getInt("ariticleSaveNum"));
			articleListBean.add(articleBean);
		}
//		System.out.println(articleListBean);
		stmt.close();
//		conn.close();
		return articleListBean;
	}
	
	
	
	
	public void insertArticle(ArticleBean article) throws NamingException, SQLException {
		String sql = "insert into article (articleTitle,articleContent,articleImg,articleTime,articleType) values(?,?,?,?,?);";
		PreparedStatement stmt = conn.prepareStatement(sql);
//		stmt.setInt(1, article.getArticleId());
//		stmt.setInt(2, article.getUserId());
		stmt.setString(1, article.getArticleTitle());
		stmt.setString(2, article.getArticleContent());
		stmt.setBytes(3, article.getArticleImg());
		stmt.setString(4, article.getArticleTime());	
		//stmt.setDate(4, java.sql.Date.valueOf(article.getArticleTime()));	
		stmt.setString(5, article.getArticleType());	
//		stmt.setInt(8, article.getArticleLikes());	
//		stmt.setInt(9, article.getArticleFollowNum());	
//		stmt.setInt(10, article.getAriticleSaveNum());	
		stmt.executeUpdate();
		stmt.close();
//		conn.close();
	}
	
	
	public void deleteArticleById(ArticleBean article) throws NamingException, SQLException {
		String sql = "delete from article where articleId = ?;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, article.getArticleId());
		stmt.executeUpdate();
		stmt.close();
//		conn.close();
	}
	
	
	public void updateArticleById(ArticleBean article) throws NamingException, SQLException {
		String sql = "update article set articleTitle=?,articleContent=?,articleImg=?,articleTime=?, articleType=? where articleTitle=?";
		
		PreparedStatement stmt = conn.prepareStatement(sql);
//		stmt.setInt(1, article.getUserId());
		stmt.setString(1, article.getArticleTitle());
		stmt.setString(2, article.getArticleContent());
		stmt.setBytes(3, article.getArticleImg());
		stmt.setString(4, article.getArticleTime());	
		stmt.setString(5, article.getArticleType());
//		stmt.setInt(7, article.getArticleLikes());
//		stmt.setInt(8, article.getArticleFollowNum());
//		stmt.setInt(9, article.getAriticleSaveNum());
		stmt.setInt(6, article.getArticleId());
		stmt.executeUpdate();
		stmt.close();
//		conn.close();
	}
	

	//修改多筆
	public  List<ArticleBean> updateArticle(ArticleBean articleBean, int articleId) throws NamingException, SQLException {
		String sql = "update article set articleTitle=?,articleContent=?,articleImg=?,articleTime=?, articleType=? where articleId=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
	//	stmt.setInt(1, article.getUserId());
		List<ArticleBean> articleListBean = new ArrayList<>();
		ArticleBean articleBean2 = new ArticleBean();
		stmt.setString(1, articleBean.getArticleTitle());
		stmt.setString(2, articleBean.getArticleContent());
		stmt.setBytes(3, articleBean.getArticleImg());
		stmt.setString(4, articleBean.getArticleTime());	
		stmt.setString(5, articleBean.getArticleType());
		stmt.setInt(6, articleBean.getArticleId());
		articleListBean.add(articleBean);
	//	stmt.setInt(7, article.getArticleLikes());
	//	stmt.setInt(8, article.getArticleFollowNum());
	//	stmt.setInt(9, article.getAriticleSaveNum());
		stmt.executeUpdate();
		
		stmt.close();
//		conn.close();
		return articleListBean;
	}
	
	
	//修改單筆
		public ArticleBean updateOneArticle(ArticleBean articleBean, int articleId) throws NamingException, SQLException {
			String sql = "update article set articleTitle=?,articleContent=?,articleImg=?,articleTime=?, articleType=? where articleId=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
		//	stmt.setInt(1, article.getUserId());
			ArticleBean articleBean2 = new ArticleBean();
			stmt.setString(1, articleBean.getArticleTitle());
			stmt.setString(2, articleBean.getArticleContent());
			stmt.setBytes(3, articleBean.getArticleImg());
			stmt.setString(4, articleBean.getArticleTime());	
			stmt.setString(5, articleBean.getArticleType());
			stmt.setInt(6, articleBean.getArticleId());
		//	stmt.setInt(7, article.getArticleLikes());
		//	stmt.setInt(8, article.getArticleFollowNum());
		//	stmt.setInt(9, article.getAriticleSaveNum());
			stmt.executeUpdate();
			
			stmt.close();
//			conn.close();
			return articleBean2;
		}
		
		
}



