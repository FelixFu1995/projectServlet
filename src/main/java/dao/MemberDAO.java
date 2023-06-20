package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import javabeans.MemberBean;

public class MemberDAO {
    private DataSource ds;
    private Connection conn;
    
    public MemberDAO() throws Exception {
        Context context = new InitialContext();
        ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
        conn = ds.getConnection();
    }
    
    
    
    //------------新增類----------
    
    PreparedStatement stmt = null;
    
    //  註冊
    public int Register(MemberBean member) throws SQLException {
        
            String sql = "insert into members (userAccount, userPassword, userGender, "
                    + "userBirthDay, userAddress, userTel, userEmail, userNickName, userRegisterDate,"
                    + " userTotalSpend, userRewardPoint, userPermission ,userName) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
            
            try {
				
            	 stmt = conn.prepareStatement(sql);
            	
            	stmt.setString(1, member.getUserAccount());
            	stmt.setString(2, member.getUserPassword());
            	stmt.setString(3, member.getUserGender());
            	stmt.setDate(4, java.sql.Date.valueOf(member.getUserBirthDay()));
            	stmt.setString(5, member.getUserAddress());
            	stmt.setString(6, member.getUserTel());
            	stmt.setString(7, member.getUserEmail());
            	stmt.setString(8, member.getUserNickName());
            	stmt.setString(9, member.getUserRegisterDate());
            	stmt.setInt(10, Integer.parseInt(member.getUserTotalSpend()));
            	stmt.setBigDecimal(11, new BigDecimal(member.getUserRewardPoint()));
            	stmt.setInt(12, Integer.parseInt(member.getUserPermission()));
            	stmt.setString(13, member.getUserName());
            	
            	return stmt.executeUpdate();
			} finally {
				if (stmt != null) {
		            stmt.close();
		        }
			}
            
    	}
    
    
    
    // -----------查詢類-----------
    
    // 登入判定
    public MemberBean validateUser(String userAccount, String userPassword) throws SQLException {
        String sql = "select * from members where userAccount = ? and userPassword = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userAccount);
            stmt.setString(2, userPassword);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    MemberBean member = new MemberBean();
                    member.setUserId(rs.getString("userId"));
                    member.setUserAccount(userAccount);
                    member.setUserPassword(userPassword);
                    member.setUserNickName(rs.getString("userNickName"));
                    member.setUserPermission(rs.getString("userPermission"));
                    return member;
                } else {
                    return null;
                }
            }
        }
    }
		
    // 管理員查詢所有會員
    public List<MemberBean> findAllMembers() throws SQLException {
        List<MemberBean> members = new ArrayList<>();
        String sql = "SELECT * FROM members";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            MemberBean member = new MemberBean();
            member.setUserId(rs.getString("userId"));
            member.setUserAccount(rs.getString("userAccount"));
            member.setUserPassword(rs.getString("userPassword"));
            member.setUserName(rs.getString("userName"));
            member.setUserGender(rs.getString("userGender"));
            member.setUserBirthDay(rs.getString("userBirthDay"));
            member.setUserAddress(rs.getString("userAddress"));
            member.setUserTel(rs.getString("userTel"));
            member.setUserEmail(rs.getString("userEmail"));
            member.setUserNickName(rs.getString("userNickName"));
            member.setUserPic(rs.getString("userPic"));
            member.setUserContent(rs.getString("userContent"));
            member.setUserTotalSpend(rs.getString("userTotalSpend"));
            member.setUserRewardPoint(rs.getString("userRewardPoint"));
            member.setUserPermission(rs.getString("userPermission"));
            members.add(member);
        }
        rs.close();
        stmt.close();
        return members;
    }

    
    //使用者查詢自己的資料
    public MemberBean findUserDetails(String userAccount) throws SQLException {
        MemberBean member = null;
        String sql = "SELECT * FROM members WHERE userAccount = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, userAccount);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            member = new MemberBean();
            member.setUserId(rs.getString("userId"));
            member.setUserAccount(rs.getString("userAccount"));
            member.setUserName(rs.getString("userName"));
            member.setUserGender(rs.getString("userGender"));
            member.setUserBirthDay(rs.getDate("userBirthDay").toLocalDate().toString());
            member.setUserAddress(rs.getString("userAddress"));
            member.setUserTel(rs.getString("userTel"));
            member.setUserEmail(rs.getString("userEmail"));
            member.setUserNickName(rs.getString("userNickName"));
            
        }

        rs.close();
        stmt.close();
        return member;
    }

    
    //-------------修改類--------------
    
    //  管理者更新會員資料
    public boolean updateMember(String userAccount, String userPassword, String userName, String userEmail, String userId) {
        try {
            String sql = "UPDATE members SET userAccount=?, userPassword=?, userName=?, userEmail=? WHERE userId=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userAccount);
            stmt.setString(2, userPassword);
            stmt.setString(3, userName);
            stmt.setString(4, userEmail);
            stmt.setString(5, userId);

            int rs = stmt.executeUpdate();
            stmt.close();
            return rs > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 使用者更新自己的資料
    public int updateUserDetails(String userId, String userAccount, String userName, String userGender, String userAddress, String userBirthDay, String userTel, String userEmail, String userNickName) throws SQLException {
        
            String sql = "update members set userAccount=? ,userName =? ,userGender=?, userBirthDay=? ,userAddress =? ,userTel=? ,userEmail=?, userNickName =? WHERE userId =?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userAccount);
            stmt.setString(2, userName);
            stmt.setString(3, userGender);
            stmt.setDate(4, java.sql.Date.valueOf(userBirthDay));
            stmt.setString(5, userAddress);
            stmt.setString(6, userTel);
            stmt.setString(7, userEmail);
            stmt.setString(8, userNickName);
            stmt.setString(9, userId);

            int ex = stmt.executeUpdate();
            stmt.close();
            return ex;
      
    }
    
    //-------------刪除類---------------
    
    // 刪除會員
    public int deleteMember(String userId) throws SQLException {
        String sql = "DELETE FROM members WHERE userId = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, userId);
        int rs = stmt.executeUpdate();
        stmt.close();
        return rs;
    }
    	
   


	public Connection getConnection() {
		// TODO Auto-generated method stub
		return conn;
	}



	
    
}