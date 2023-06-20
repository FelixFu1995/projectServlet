package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/MemberQuery")
public class MemberQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection conn ;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		MemberDAO memberDAO = null;
		
		try {
	             memberDAO = new MemberDAO();
	            List<MemberBean> members = memberDAO.findAllMembers();
	            request.setAttribute("members", members);
	            request.getRequestDispatcher("/FirstPage/MemberQuery.jsp").forward(request, response);
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
