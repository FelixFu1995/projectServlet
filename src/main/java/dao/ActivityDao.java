package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabeans.ActivityBean;

public class ActivityDao {
	private Connection conn;

	public ActivityDao(Connection conn) {
		this.conn = conn;
	}
	
	// 查詢單筆
	public ActivityBean Find(String activityId) throws SQLException {
		String SQL = "select * from activity where activityId = ?;";
		PreparedStatement stmt = conn.prepareStatement(SQL);
		stmt.setString(1, activityId);
		ResultSet rs = stmt.executeQuery();
		ActivityBean aBean = new ActivityBean();
		if (rs.next()) {
			aBean.setActivityId(rs.getString("activityId"));
			aBean.setActivityName(rs.getString("activityName"));
			aBean.setActivityContent(rs.getString("activityContent"));
			aBean.setActivityCover(rs.getBytes("activityCover"));
			aBean.setActivityDate(rs.getString("activityDate"));
			aBean.setActivityLocation(rs.getString("activityLocation"));
			aBean.setActivityStatus(rs.getString("activityStatus"));
		}
		rs.close();
		stmt.close();
		return aBean;
	}

	// 查詢全部
	public List<ActivityBean> findAll() throws SQLException {
	    String SQL = "select * from activity;";
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    List<ActivityBean> aBeanList = new ArrayList<ActivityBean>();
	    try {
	        stmt = conn.prepareStatement(SQL);
	        rs = stmt.executeQuery();
	        ActivityBean aBean = null;
	        while (rs.next()) {
	            aBean = new ActivityBean();
	            aBean.setActivityId(rs.getString("activityId"));
	            aBean.setActivityName(rs.getString("activityName"));
	            aBean.setActivityContent(rs.getString("activityContent"));
	            aBean.setActivityCover(rs.getBytes("activityCover"));
	            aBean.setActivityDate(rs.getString("activityDate"));
	            aBean.setActivityLocation(rs.getString("activityLocation"));
	            aBean.setActivityStatus(rs.getString("activityStatus"));
	            aBeanList.add(aBean);
	        }
	    } finally {
	        if (rs != null) {
	            rs.close();
	        }
	        if (stmt != null) {
	            stmt.close();
	        }
	    }
	    return aBeanList;
	}
	 //新增資料
	 public void Insert(ActivityBean aBean) throws SQLException {
		 String SQL = "insert into activity(activityName, activityContent, activityCover, activityDate, activityLocation, activityStatus) values (?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(SQL);
		    stmt.setString(1, aBean.getActivityName());
		    stmt.setString(2, aBean.getActivityContent());
		    stmt.setBytes(3, aBean.getActivityCover());
		    stmt.setString(4, aBean.getActivityDate());
		    stmt.setString(5, aBean.getActivityLocation());
		    stmt.setString(6, aBean.getActivityStatus());
			
			stmt.executeUpdate();
			stmt.close();
	 }
	 
	 //修改資料
	 public boolean UpdateData(ActivityBean aBean)throws SQLException {
		 String SQL = "UPDATE activity SET activityName = ?,  activityContent = ?, activityCover = ? , activityDate = ? , activityLocation = ?, activityStatus = ? WHERE activityId = ?";
			PreparedStatement stmt = conn.prepareStatement(SQL);
		    stmt.setString(1, aBean.getActivityName());
		    stmt.setString(2, aBean.getActivityContent());
		    stmt.setBytes(3, aBean.getActivityCover());
		    stmt.setString(4, aBean.getActivityDate());
		    stmt.setString(5, aBean.getActivityLocation());
		    stmt.setString(6, aBean.getActivityStatus());
		    stmt.setString(7, aBean.getActivityId());
			
			stmt.executeUpdate();
			stmt.close();
			return true;
	 }
	// 刪除資料
	 public void Delete(String activityId) throws SQLException {
	     String SQL = "DELETE FROM activity WHERE activityId = ?";
	     PreparedStatement stmt = conn.prepareStatement(SQL);
	     stmt.setString(1, activityId);
	     stmt.executeUpdate();
	     stmt.close();
	     // 假設刪除成功，則回傳要刪除的 ActivityBean 物件
	 }
	

}
