package dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabeans.Commodity;

public interface ComDao {

	//新增商品按鈕
	/**
	 * 新增商品判斷
	 */
	public void getaddCom(HttpServletRequest request, HttpServletResponse response);
	/**
	 * 新增商品
	 */
	public int addCom(Commodity com);
	//商品列表
	/**
	 * 查詢該賣家全部商品
	 */
	public void getInquireAllCom(HttpServletRequest request, HttpServletResponse response);	
	/**
	 * 查詢該賣家的商品方法
	 * 回傳list型態
	 */
	public List<Commodity> inquireAllCommodity();
	
	//修改按鈕
	/**
	 * 接收前台 修改前顯示該ID物件之方法
	 */
	public void getinquireOneCom(HttpServletRequest request, HttpServletResponse response);
	/**
	 * 修改前 先查詢該ID之方法
	 */
	public List<Commodity> inquireOneCommodity(int id);
	
	//確認修改按鈕
	/**
	 * 修改商品之判斷
	 */
	public void getUpdateCom(HttpServletRequest request, HttpServletResponse response);
	/**
	 * 修改商品細項之方法
	 */
	public void updateCom(Commodity com, String id);
	
	//Aside功能
	/**
	 * 判斷關鍵字查詢方法 
	 */
	public void getSearchByName(HttpServletRequest request, HttpServletResponse response);
	/**
	 * 關鍵字 查詢方法 
	 */
	public List<Commodity> SearchByName(String comName);
	
	/**
	 * 判斷價錢排序方法
	 */
	public void getSortByPrice(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 判斷搜尋類型方法
	 */
	public void getSearchByType(HttpServletRequest request, HttpServletResponse response);
	/**
	 * 搜尋類型方法
	 */
	public List<Commodity> SearchByType(String comType);
}
