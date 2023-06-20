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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import dao.MemberDAO;
import javabeans.MemberBean;

@WebServlet("/MemberListServlet")
public class MemberListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MemberListServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/FirstPage/MemberQuery.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        String userAccount = request.getParameter("userAccount");
        String userPassword = request.getParameter("userPassword");
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userId = request.getParameter("userId");

        MemberDAO memberDAO = null;
        
        try {
             memberDAO = new MemberDAO();
            boolean result = memberDAO.updateMember(userAccount, userPassword, userName, userEmail, userId);

            request.setAttribute("result", result ? "更新成功" : "更新失敗");
        } catch (Exception e) {
            request.setAttribute("result", "更新失敗");
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

        response.sendRedirect("./FirstPage/MemberQuery.jsp");
    }
}


