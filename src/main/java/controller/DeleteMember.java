package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.MemberDAO;

@WebServlet("/DeleteMember")
public class DeleteMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	Connection conn;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     String userId = request.getParameter("userId");
	     System.out.println("User ID: " + userId);
	
	     MemberDAO memberDAO = null;
	     
	   try {
		 memberDAO = new MemberDAO();
		 int rs = memberDAO.deleteMember(userId);
		 response.sendRedirect("./FirstPage/MemberQuery.jsp");
		 
		 System.out.println("刪除了 "+rs+" 筆資料");
	} catch (Exception e) {
		// TODO: handle exception
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
}
