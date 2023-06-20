package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/logOut")
public class logOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session = request.getSession(false);

	        if (session != null) {
	            // 刪除 session 中的使用者資訊或其他相關的屬性
	            session.removeAttribute("username");
	            session.removeAttribute("userAccount");
	            // 其他清除動作...

	            // 結束 session
	            session.invalidate();
	        }

	        // 導向登出後的頁面或首頁
	        response.sendRedirect("FirstPage/FirstPage.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
}
}

