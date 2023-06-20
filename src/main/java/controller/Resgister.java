package controller;

import java.io.IOException;
import java.lang.invoke.MethodHandles.Lookup;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.MemberDAO;
import javabeans.MemberBean;
@WebServlet("/Resgister")
public class Resgister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		 String userAccount = request.getParameter("userAccount");
		 String userPassword = request.getParameter("userPassword");
		 String userName = request.getParameter("userName");
		 String userGender = request.getParameter("userGender");
		 String userBirthDay = request.getParameter("userBirthDay");
		 String userAddress = request.getParameter("userAddress");
		 
		 String userAddressFirst = request.getParameter("userAddressFirst");
		 String userAddressDetail = request.getParameter("userAddressDetail");
		  		userAddress = userAddressFirst + userAddressDetail;
		 String userTel = request.getParameter("userTel");
		 String userEmail = request.getParameter("userEmail");
		 String userNickName = request.getParameter("userNickName");
		 String userRegisterDate = request.getParameter("userRegisterDate");
		 String userTotalSpend = request.getParameter("userTotalSpend");
		 String userRewardPoint = request.getParameter("userRewardPoint");
		 String userPermission = request.getParameter("userPermission");
		 
		 //註冊時，以當前時間做為註冊時間。
		 LocalDateTime now = LocalDateTime.now();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 userRegisterDate = now.format(formatter);
		 
		 MemberBean member = new MemberBean();
	        member.setUserAccount(userAccount);
	        member.setUserPassword(userPassword);
	        member.setUserName(userName);
	        member.setUserGender(userGender);
	        member.setUserBirthDay(userBirthDay);
	        member.setUserAddress(userAddress);
	        member.setUserTel(userTel);
	        member.setUserEmail(userEmail);
	        member.setUserNickName(userNickName);
	        member.setUserRegisterDate(userRegisterDate);
	        member.setUserTotalSpend("0");
	        member.setUserRewardPoint("0");
	        member.setUserPermission("1");
	        
		  
	        MemberDAO memberDAO = null;
	        try {
	            memberDAO = new MemberDAO();
	            int rs = memberDAO.Register(member);

	            System.out.println("註冊了 " + rs + " 筆資料");

	            request.getRequestDispatcher("Member/RegisterSuccess.html")
	                    .forward(request, response);

	        } catch (Exception e) {
	            // Handle exception 
	        } finally {
	            if (memberDAO != null && memberDAO.getConnection() != null) {
	                try {
	                    memberDAO.getConnection().close();
	                } catch (SQLException e) {
	                    // Handle exception
	                }
	            }
	        }
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
