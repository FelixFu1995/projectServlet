package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.MultipartConfig;
import javax.sql.DataSource;

import javabeans.ReplyBean;
@MultipartConfig

public class ReplyDao {
	Connection conn;
	Context context;
	DataSource ds;
	
	public ReplyDao() throws NamingException, SQLException {
		context = new InitialContext();
		ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
		conn = ds.getConnection();
	}
	
	public ReplyBean selectReplyById(ReplyBean reply) throws NamingException, SQLException {
		String sql = "select * from reply where replyId = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reply.getReplyId());
		ResultSet rs = stmt.executeQuery();
		ReplyBean reply2 = new ReplyBean();
		if(rs.next()) {
			reply2.setReplyId(rs.getInt("replyId"));
			reply2.setArticleId(rs.getInt("articleId"));
			reply2.setUserId(rs.getInt("userId"));
			reply2.setReplyContent(rs.getString("replyContent"));
			reply2.setReplyImg(rs.getBytes("replyImg"));
			reply2.setReplyTime(rs.getString("replyTime"));
//			reply2.setReplyLike(rs.getInt("replyLike"));
		}
		stmt.close();
		conn.close();
		return reply2;
	}
	
	
	public List<ReplyBean> selectAllReply() throws NamingException, SQLException {
		String sql = "select * from reply";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<ReplyBean> replyList = new ArrayList<>();
		while(rs.next()) {
			ReplyBean reply = new ReplyBean();
			reply.setReplyId(rs.getInt("replyId"));
			reply.setArticleId(rs.getInt("articleId"));
			reply.setUserId(rs.getInt("userId"));
			reply.setReplyContent(rs.getString("replyContent"));
			reply.setReplyImg(rs.getBytes("replyImg"));
			reply.setReplyTime(rs.getString("replyTime"));
//			reply.setReplyLike(rs.getInt("replyLike"));
			replyList.add(reply);
		}
		stmt.close();
		conn.close();
		return replyList;
	}
	
	
	public void insertReply(ReplyBean reply) throws NamingException, SQLException {
		String sql = "insert into reply (replyId,articleId,userId,replyContent,replyPic,replyTime) values(?,?,?,?,?,?);";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reply.getReplyId());
		stmt.setInt(2, reply.getArticleId());
		stmt.setInt(3, reply.getUserId());
		stmt.setString(4, reply.getReplyContent());
		stmt.setBytes(5, reply.getReplyImg());
		stmt.setString(6, reply.getReplyTime());	
//		stmt.setInt(7, reply.getReplyLike());	
		stmt.executeUpdate();
		stmt.close();
		conn.close();
	}
	
	
	public void deleteReplyById(ReplyBean reply) throws NamingException, SQLException {
		String sql = "delete from reply where replyId = ?;";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reply.getReplyId());
		stmt.executeUpdate();
		stmt.close();
		conn.close();
	}
	
	public void update(ReplyBean reply) throws NamingException, SQLException {
		String sql = "update reply set articleId=?,userId=?,replyContent=?,replyImg=?,replyTime=?, replyLike=? where replyId=?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, reply.getArticleId());
		stmt.setInt(2, reply.getUserId());
		stmt.setString(3, reply.getReplyContent());
		stmt.setBytes(4, reply.getReplyImg());
		stmt.setString(5, reply.getReplyTime());	
//		stmt.setInt(6, reply.getReplyLike());
		stmt.setInt(6, reply.getReplyId());
		stmt.executeUpdate();
		stmt.close();
		conn.close();
	}
	
	
}
