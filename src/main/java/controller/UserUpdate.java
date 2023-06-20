package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import javax.sql.DataSource;

import dao.MemberDAO;
import javabeans.MemberBean;

@WebServlet("/UserUpdate")
public class UserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userAccount = request.getParameter("userAccount");
		 String userName = request.getParameter("userName");
		 String userGender = request.getParameter("userGender");
		 String userAddress = request.getParameter("userAddress");
		 String userBirthDay = request.getParameter("userBirthDay");
		 String userTel = request.getParameter("userTel");
		 String userEmail = request.getParameter("userEmail");
		 String userNickName = request.getParameter("userNickName");
		 
		 MemberDAO memberDAO = null;
		 
		 try {
	             memberDAO = new MemberDAO();
	            int ex = memberDAO.updateUserDetails(userId, userAccount, userName, userGender, userAddress, userBirthDay, userTel, userEmail, userNickName);
	            if (ex >= 0) {
	                MemberBean member = memberDAO.findUserDetails(userAccount);
	                if (member != null) {
	                	request.setAttribute("userId", member.getUserId());
	            	    request.setAttribute("userAccount", member.getUserAccount());
	            	    request.setAttribute("userName", member.getUserName());
	            	    request.setAttribute("userGender", member.getUserGender());
	            	    request.setAttribute("userBirthDay", member.getUserBirthDay());
	            	    request.setAttribute("userAddress", member.getUserAddress());
	            	    request.setAttribute("userTel", member.getUserTel());
	            	    request.setAttribute("userEmail", member.getUserEmail());
	            	    request.setAttribute("userNickName", member.getUserNickName());
	                    request.getRequestDispatcher("/FirstPage/userDetail.jsp").forward(request, response);
	                } else {
	                    System.out.println("User details not found");
	                    response.sendRedirect("login.html");
	                }
	            } else {
	                response.sendRedirect("login.html");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
