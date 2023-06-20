package controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.impl.ComImpls;
import javabeans.Commodity;

@WebServlet("/ComServlet")
@MultipartConfig
public class ComServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      ComImpls todo = new ComImpls();
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userState = request.getParameter("userState");		
		System.out.println("接收判斷");
		if(userState.equals("addNewItem")) {
			//新增方法
			todo.getaddCom(request, response);
			
		}else if (userState.equals("checkCom")) {
			//查詢全部商品方法
			todo.getInquireAllCom(request, response);
			
		}else if (userState.equals("checkOneCom")) {
			//修改按鈕後查詢該商品方法
			todo.getinquireOneCom(request, response);
			
		}else if (userState.equals("editCom")) {
			//修改商品方法
			todo.getUpdateCom(request, response);
			
		//以下為Aside欄位的方法
		}else if(userState.equals("searchByName")) {
			//關鍵字查詢方法
			todo.getSearchByName(request, response);
			
		}else if(userState.equals("sortByPrice")) {
			//用價格排序的方法
			todo.getSortByPrice(request, response);
		}else if(userState.equals("searchBType")) {
			todo.getSearchByType(request, response);
		}
		System.out.println(userState);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
