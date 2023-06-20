package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.impl.UserItemImpls;


@WebServlet("/UserItemServlet")
@MultipartConfig
public class UserItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserItemImpls todo = new UserItemImpls();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userState = request.getParameter("userState");
		System.out.println("接收判斷");
		if(userState.equals("checkItem")) {
			//進入商城查詢全部商品
			todo.getSearchAllItem(request, response);
		}else if(userState.equals("checkOneItem")) {
			//查詢單筆商品
			todo.getSearchOneItem(request, response);
		}else if(userState.equals("searchByName")) {
			//Aside的方法_關鍵字查詢
			todo.getSearchByName(request, response);
		}else if(userState.equals("sortByPrice")) {
			//Aside的方法_價格排序
			todo.getSortByPrice(request, response);
		}else if(userState.equals("searchBType")) {
			//Aside的方法_類別搜尋
			todo.getSearchByType(request, response);
		}else if(userState.equals("inputCart")) {
			//加入購物車方法
			todo.getAddCart(request, response);
		}else if(userState.equals("intoCart")) {
			request.getRequestDispatcher("./jsp/UserCart.jsp").forward(request, response);
		}else if(userState.equals("removeCartItem")) {
			todo.getRemoveCart(request, response);
		}else if(userState.equals("payMoney")) {
			//得到id陣列>>再進行查詢
			String[] comId = request.getParameterValues("comId");
			for (String i : comId) {
				System.out.println("id:" + i);
			  String itemNum = request.getParameter("itemNum-" + i);
			  System.out.println(i + " 的數量為：" + itemNum);
			}

			String totalPrice = request.getParameter("totalPrice");
			System.out.println("總價格為：" + totalPrice);
			HttpSession session = request.getSession();
			String aaaString= (String) session.getAttribute("userAccount");
			System.out.println(aaaString);
		}
		System.out.println(userState);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
