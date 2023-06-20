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

import javabeans.CourseBean;
@MultipartConfig
public class courseDAO {
	Connection conn;
	Context context;
	DataSource ds;
	
	public courseDAO() throws NamingException, SQLException {
		context = new InitialContext();
		ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
		conn = ds.getConnection();
	}

	
	//新增資料的方法
	public void insertCourse(CourseBean cbean) throws SQLException {
		String SQL = "insert into course(courseName,coachName,courseCost,courseIntroduce,courseImg) values(?,?,?,?,?)";
		PreparedStatement preState = conn.prepareStatement(SQL);
		preState.setString(1, cbean.getCourseName());
		preState.setString(2, cbean.getCoachName());
		preState.setInt(3, cbean.getCourseCost() );
		preState.setString(4, cbean.getCourseIntroduce());
		preState.setBytes(5, cbean.getCourseImg());
		preState.executeUpdate();
		
		preState.close();
	}
	
	//查詢全部資料
		public List<CourseBean> selectAllCourse() throws SQLException {
			String SQL = "select * from course";
			PreparedStatement preState = conn.prepareStatement(SQL);
			ResultSet rs = preState.executeQuery();
			
			List<CourseBean> cbeans = new ArrayList<>();
			CourseBean cbean = null;
			while(rs.next()) {
				cbean = new CourseBean();
				cbean.setCourseId(rs.getInt("courseId"));
				cbean.setCourseName(rs.getString("courseName"));
				cbean.setCoachName(rs.getString("coachName"));
				cbean.setCourseCost(rs.getInt("courseCost"));
				cbean.setCourseIntroduce(rs.getString("courseIntroduce"));
				cbean.setCourseImg(rs.getBytes("courseImg"));
				cbeans.add(cbean);
			}
			rs.close();
			preState.close();
			return cbeans;
			}
		
	//查詢資料
	public CourseBean selectCourseById(CourseBean cbean) throws SQLException {
		String SQL = "select * from course where courseId = ?";
		PreparedStatement preState = conn.prepareStatement(SQL);
		preState.setInt(1,cbean.getCourseId());
		ResultSet rs = preState.executeQuery();
			if(rs.next()) {
			cbean.setCourseId(rs.getInt("courseId"));
			cbean.setCourseName(rs.getString("courseName"));
			cbean.setCoachName(rs.getString("coachName"));
			cbean.setCourseCost(rs.getInt("courseCost"));
			cbean.setCourseIntroduce(rs.getString("courseIntroduce"));
			cbean.setCourseImg(rs.getBytes("courseImg"));
			}
			rs.close();
			preState.close();
			return cbean;
	
	}
	//刪除資料
	public void deleteCourse(CourseBean cbean) throws SQLException {
		String SQL = "delete from course where courseId = ?";
		PreparedStatement preState = conn.prepareStatement(SQL);
		preState.setInt(1, cbean.getCourseId());
		preState.executeUpdate();
		
		preState.close();
	}
	//修改資料
	public void updateCourse(CourseBean cbean) throws SQLException {
	String SQL = "update course set courseName = ?,coachName = ?,courseCost = ?,courseIntroduce = ? where courseId = ?";
	PreparedStatement preState = conn.prepareStatement(SQL);
	preState.setString(1, cbean.getCourseName());
	preState.setString(2, cbean.getCoachName());
	preState.setInt(3, cbean.getCourseCost());
	preState.setString(4, cbean.getCourseIntroduce());
//	preState.setBytes(5, cbean.getCourseImg());
	preState.setInt(5, cbean.getCourseId());
	preState.executeUpdate();
	preState.close();
	}
	
	//修改資料
		public void updateCourseImg(CourseBean cbean) throws SQLException {
		String SQL = "update course set courseImg = ? where courseId = ?";
		PreparedStatement preState = conn.prepareStatement(SQL);
		preState.setBytes(1, cbean.getCourseImg());
		preState.setInt(2, cbean.getCourseId());
		preState.executeUpdate();
		preState.close();
		}
		
		public Connection getConnection() {
			return conn;
		}
	
}
