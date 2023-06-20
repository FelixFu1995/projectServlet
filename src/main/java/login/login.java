package login;

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
import javax.servlet.jsp.tagext.TryCatchFinally;
import javax.sql.DataSource;

import dao.MemberDAO;
import javabeans.MemberBean;


@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public login() {
        super();
    }
    Connection conn;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 String userAccount = request.getParameter("userAccount");
		 String userPassword = request.getParameter("userPassword");
		 MemberDAO memberDAO = null;
		try {
		 
			 memberDAO = new MemberDAO();
            MemberBean member = memberDAO.validateUser(userAccount, userPassword);
		 
		 if (member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userAccount", member.getUserAccount());
			session.setAttribute("userNickName",  member.getUserNickName());
            session.setAttribute("userPermission",  member.getUserPermission());
            session.setAttribute("userId", member.getUserId());
			 // 登錄成功後
		    session.setAttribute("userLoggedIn", true);
			System.out.println();
			
			if (Integer.parseInt(member.getUserPermission()) == 0) {
                // 到管理者後台頁面
                response.sendRedirect("FirstPage/Admin.jsp");
            } else {
                // 到一般會員頁面
                response.sendRedirect("FirstPage/FirstPage.jsp");
            }
		}else {
			response.sendRedirect("Member/Login.jsp?error=1");
		}
		 
	} catch (Exception e) {
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
