package controller;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import dao.MemberDAO;
import javabeans.MemberBean;

@WebServlet("/FindUserDetails")
public class FindUserDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;

    Connection conn;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");


        HttpSession session = request.getSession();
        String userAccount = (String) session.getAttribute("userAccount");

        MemberDAO memberDAO = null;
        
        try {
             memberDAO = new MemberDAO();
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
                request.getRequestDispatcher("/FirstPage/userUpdate.jsp").forward(request, response);
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

