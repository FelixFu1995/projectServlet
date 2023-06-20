package dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabeans.Commodity;

public interface UserItemDao {

	// 商品列表
	/**
	 * 查詢該賣家全部商品
	 */
	public void getSearchAllItem(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 查詢該賣家的商品方法 回傳list型態
	 */
	public List<Commodity> searchAllItem();

	// 點擊圖片
	/**
	 * 接收前台 修改前顯示該ID物件之方法
	 */
	public void getSearchOneItem(HttpServletRequest request, HttpServletResponse response);

	/**
	 * 修改前 先查詢該ID之方法
	 */
	public List<Commodity> searchOneItem(int id);

	// Aside功能
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
	
	/**
	 *判斷加入購物車的方法 
	 */
	public void getAddCart(HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 加入購物車方法
	 */
	public List<Commodity> addCart(int id,int itemNum);
	
	/**
	 * 刪除購物車方法
	 */
	public void getRemoveCart(HttpServletRequest request, HttpServletResponse response);
}
