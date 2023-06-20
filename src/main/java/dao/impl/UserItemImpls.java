package dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserItemDao;
import dbutil.ConnectionPool;
import javabeans.Commodity;

public class UserItemImpls implements UserItemDao{
	private PreparedStatement ps = null; // 先宣告會將字串轉換為資料庫語法
	private ResultSet rs = null;
	private Connection conn = null;
	
	/**
	 * 查詢該賣家全部商品
	 */
	@Override
	public void getSearchAllItem(HttpServletRequest request, HttpServletResponse response) {
		// 宣告一個陣列，以便後續取得的資料放入
				List<Commodity> comList = null;
				// 建立一個查詢商品的實體

				// 取得使用者的帳號

				// 呼叫取得全部商品的方法，並把取得的商品資料傳到一個List裡面
				comList = searchAllItem();
				// 將取得的商品全部傳入一個標籤裡面
				request.setAttribute("comList", comList);
				try {
					// 將資料轉發到商品頁面
					request.getRequestDispatcher("./jsp/UserItemList.jsp").forward(request, response);
				} catch (IOException | ServletException e) {
					e.printStackTrace();
				}

				return;
	}

	/**
	 * 查詢該賣家的商品方法 回傳list型態
	 */
	@Override
	public List<Commodity> searchAllItem() {
		// 宣告一個陣列(list 資料型態為Java Bean<Commodity>)變數，用來存放從SQL裡面取得的資料
				List<Commodity> comList = null;
				try {
					// 建立資料連線

//					Context context = new InitialContext();
//					DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
//					conn = ds.getConnection();
					conn = ConnectionPool.getInstance().getConnection();					
					
					// 建立一個字串並轉換為SQL語法執行
					String addSQL = "SELECT * FROM commodity ";
					ps = conn.prepareStatement(addSQL);
					// 將SQL語法內的?，填入值(value)

					// 執行SQL語法，並將取得的商品內容傳入rs中
					rs = ps.executeQuery();
					// 宣告要使用的陣列
					comList = new ArrayList<Commodity>();
					// 用迴圈將取得的資料放入陣列(list)裡面

					while (rs.next()) {
						Commodity newCommodity = new Commodity(rs.getInt("comId"), rs.getString("comName"),
								rs.getInt("comNumber"), rs.getInt("comPrice"), rs.getString("comContent"),
								rs.getString("comType"), rs.getBytes("comPic"), rs.getString("comStatus"));

						if (newCommodity.getComPic() != null) {
							String base64String = Base64.getEncoder().encodeToString(newCommodity.getComPic());
							newCommodity.setComPicBase64(base64String);

						}

						comList.add(newCommodity);

					}

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					// 關閉建立的資料庫連接
					try {
						 if (rs != null) {
					            ConnectionPool.getInstance().close(rs);
					        }
					        if (ps != null) {
					            ConnectionPool.getInstance().close(ps);
					        }
					        if (conn != null) {
					            ConnectionPool.getInstance().releaseConnection(conn);
					        }
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return comList;
	}
	/**
	 * 接收前台 點擊圖片顯示該ID物件之方法
	 */
	@Override
	public void getSearchOneItem(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("comId"));

		List<Commodity> comList = searchOneItem(id);
		request.setAttribute("OneComList", comList);
		try {

			// 將資料轉發到商品頁面
			request.getRequestDispatcher("./jsp/UserOneItemList.jsp").forward(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}

		return;		
	}
	/**
	 * 進入下個頁面前 先查詢該ID之方法
	 */
	@Override
	public List<Commodity> searchOneItem(int id) {
		// 宣告一個陣列(list 資料型態為Java Bean<Commodity>)變數，用來存放從SQL裡面取得的資料
				List<Commodity> comList = null;
				try {
//					Context context = new InitialContext();
//					DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
//					conn = ds.getConnection();
					// 建立資料連線
					conn = ConnectionPool.getInstance().getConnection();

					// 建立一個字串並轉換為SQL語法執行
					String addSQL = "SELECT * FROM commodity where comId=?";
					ps = conn.prepareStatement(addSQL);
					// 將SQL語法內的?，填入值(value)
					ps.setInt(1, id);
					// 執行SQL語法，並將取得的商品內容傳入rs中
					rs = ps.executeQuery();
					// 宣告要使用的陣列
					comList = new ArrayList<Commodity>();
					// 用迴圈將取得的資料放入陣列(list)裡面

					while (rs.next()) {
						Commodity newCommodity = new Commodity(rs.getInt("comId"), rs.getString("comName"),
								rs.getInt("comNumber"), rs.getInt("comPrice"), rs.getString("comContent"),
								rs.getString("comType"), rs.getBytes("comPic"), rs.getString("comStatus"));

						if (newCommodity.getComPic() != null) {
							String base64String = Base64.getEncoder().encodeToString(newCommodity.getComPic());
							newCommodity.setComPicBase64(base64String);
							// 將圖片資料儲存成檔案，以進行檢查
						}
						comList.add(newCommodity);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					// 關閉建立的資料庫連接
					try {
						 if (rs != null) {
					            ConnectionPool.getInstance().close(rs);
					        }
					        if (ps != null) {
					            ConnectionPool.getInstance().close(ps);
					        }
					        if (conn != null) {
					            ConnectionPool.getInstance().releaseConnection(conn);
					        }
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return comList;
	}

	/**
	 * 判斷關鍵字查詢方法
	 */
	@Override
	public void getSearchByName(HttpServletRequest request, HttpServletResponse response) {
		// 宣告一個陣列，以便後續取得的資料放入
				List<Commodity> comList = null;
				// 建立一個查詢商品的實體
				String comName = request.getParameter("comName");

				// 呼叫查詢關鍵字之方法的方法，並把取得的商品資料傳到一個List裡面
				comList = SearchByName(comName);
				if (comList == null) {
					request.setAttribute("msg", "查無此商品");
				} else if (comList.isEmpty()) {
					request.setAttribute("msg", "查無此商品");
				} else {
					request.setAttribute("comList", comList);
				}

				try {
					// 將資料轉發到商品頁面
					request.getRequestDispatcher("./jsp/UserItemList.jsp").forward(request, response);
				} catch (IOException | ServletException e) {
					e.printStackTrace();
				}

				return;
		
	}

	/**
	 * 關鍵字 查詢方法
	 */
	@Override
	public List<Commodity> SearchByName(String comName) {
		// 宣告一個陣列(list 資料型態為Java Bean<Commodity>)變數，用來存放從SQL裡面取得的資料
				List<Commodity> comList = null;
				try {
					// 建立資料連線

//					Context context = new InitialContext();
//					DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
//					conn = ds.getConnection();
					conn=ConnectionPool.getInstance().getConnection();

					// 建立一個字串並轉換為SQL語法執行
					String addSQL = "SELECT * FROM commodity WHERE comName LIKE ?";
					ps = conn.prepareStatement(addSQL);
					// 將SQL語法內的?，填入值(value)
					ps.setString(1, "%" + comName + "%");

					// 執行SQL語法，並將取得的商品內容傳入rs中
					rs = ps.executeQuery();
					// 宣告要使用的陣列
					comList = new ArrayList<Commodity>();
					// 用迴圈將取得的資料放入陣列(list)裡面

					while (rs.next()) {
						Commodity newCommodity = new Commodity(rs.getInt("comId"), rs.getString("comName"),
								rs.getInt("comNumber"), rs.getInt("comPrice"), rs.getString("comContent"),
								rs.getString("comType"), rs.getBytes("comPic"), rs.getString("comStatus"));

						if (newCommodity.getComPic() != null) {
							String base64String = Base64.getEncoder().encodeToString(newCommodity.getComPic());
							newCommodity.setComPicBase64(base64String);
						}
						comList.add(newCommodity);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					// 關閉建立的資料庫連接
					try {
						 if (rs != null) {
					            ConnectionPool.getInstance().close(rs);
					        }
					        if (ps != null) {
					            ConnectionPool.getInstance().close(ps);
					        }
					        if (conn != null) {
					            ConnectionPool.getInstance().releaseConnection(conn);
					        }
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return comList;
	}

	/**
	 * 判斷價錢排序方法 呼叫查詢全部商品方法
	 */
	@Override
	public void getSortByPrice(HttpServletRequest request, HttpServletResponse response) {
		// 宣告一個陣列，以便後續取得的資料放入
				List<Commodity> comList = null;
				// 呼叫取得全部商品的方法，並把取得的商品資料傳到一個List裡面
				comList = searchAllItem();

				String sortByPrice = request.getParameter("comPrice");
				if (sortByPrice.equals("0")) {
					// 使用 Comparator 比較商品的價格
					Comparator<Commodity> comparator = new Comparator<Commodity>() {

						@Override
						public int compare(Commodity o1, Commodity o2) {

							return Integer.compare(o2.getComPrice(), o1.getComPrice());
							// 價格高的排前面，使用 o2 和 o1 互換位置
						}
					};
					// 使用 Collections.sort() 方法進行排序
					Collections.sort(comList, comparator);
					// 將取得的商品全部傳入一個標籤裡面
					request.setAttribute("comList", comList);
				} else if (sortByPrice.equals("1")) {
					// 使用 Comparator 比較商品的價格
					Comparator<Commodity> comparator = new Comparator<Commodity>() {

						@Override
						public int compare(Commodity o1, Commodity o2) {

							return Integer.compare(o1.getComPrice(), o2.getComPrice());
							// 價格低的排前面，使用 o1 和 o2 互換位置
						}
					};
					// 使用 Collections.sort() 方法進行排序
					Collections.sort(comList, comparator);
					// 將取得的商品全部傳入一個標籤裡面
					request.setAttribute("comList", comList);
				}

				try {
					// 將資料轉發到商品頁面
					request.getRequestDispatcher("./jsp/UserItemList.jsp").forward(request, response);
				} catch (IOException | ServletException e) {
					e.printStackTrace();
				}

				return;
		
	}

	/**
	 * 判斷搜尋類型方法
	 */
	@Override
	public void getSearchByType(HttpServletRequest request, HttpServletResponse response) {
		// 宣告一個陣列，以便後續取得的資料放入
				List<Commodity> comList = null;
				// 呼叫取得全部商品的方法，並把取得的商品資料傳到一個List裡面
				String comType = request.getParameter("comType");
				comList = SearchByType(comType);
				if (comList == null) {
					request.setAttribute("msg", "查無此商品");
				} else if (comList.isEmpty()) {
					request.setAttribute("msg", "查無此商品");
				} else {
					request.setAttribute("comList", comList);
				}
				try {
					// 將資料轉發到商品頁面
					request.getRequestDispatcher("./jsp/UserItemList.jsp").forward(request, response);
				} catch (IOException | ServletException e) {
					e.printStackTrace();
				}

				return;
		
	}
	
	/**
	 * 搜尋類型方法
	 */
	@Override
	public List<Commodity> SearchByType(String comType) {
		// 宣告一個陣列(list 資料型態為Java Bean<Commodity>)變數，用來存放從SQL裡面取得的資料
				List<Commodity> comList = null;
				try {
					// 建立資料連線

//					Context context = new InitialContext();
//					DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
//					conn = ds.getConnection();
					conn=ConnectionPool.getInstance().getConnection();

					// 建立一個字串並轉換為SQL語法執行
					String addSQL = "SELECT * FROM commodity WHERE comType LIKE ?";
					ps = conn.prepareStatement(addSQL);
					// 將SQL語法內的?，填入值(value)
					ps.setString(1, "%" + comType + "%");

					// 執行SQL語法，並將取得的商品內容傳入rs中
					rs = ps.executeQuery();
					// 宣告要使用的陣列
					comList = new ArrayList<Commodity>();
					// 用迴圈將取得的資料放入陣列(list)裡面

					while (rs.next()) {
						Commodity newCommodity = new Commodity(rs.getInt("comId"), rs.getString("comName"),
								rs.getInt("comNumber"), rs.getInt("comPrice"), rs.getString("comContent"),
								rs.getString("comType"), rs.getBytes("comPic"), rs.getString("comStatus"));

						if (newCommodity.getComPic() != null) {
							String base64String = Base64.getEncoder().encodeToString(newCommodity.getComPic());
							newCommodity.setComPicBase64(base64String);
						}
						comList.add(newCommodity);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					// 關閉建立的資料庫連接
					try {
						 if (rs != null) {
					            ConnectionPool.getInstance().close(rs);
					        }
					        if (ps != null) {
					            ConnectionPool.getInstance().close(ps);
					        }
					        if (conn != null) {
					            ConnectionPool.getInstance().releaseConnection(conn);
					        }
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				return comList;
	}
	/**
	 * 判斷加入購物車方法
	 */
	@Override
	public void getAddCart(HttpServletRequest request, HttpServletResponse response) {
		
		int id = Integer.parseInt(request.getParameter("comId"));
		int itemNum = Integer.parseInt(request.getParameter("itemNumber")) ;
		HttpSession session= request.getSession();
		
		 // 取得CartList
	    @SuppressWarnings("unchecked")
	    List<Commodity> cartListCheck = (List<Commodity>) session.getAttribute("CartList");

	    // 如果CartList不為空，判斷商品是否已經存在
	    if (cartListCheck != null) {
	        for (Commodity commodity : cartListCheck) {
	            if (commodity.getComId() == id) {
	                // 如果商品已經存在於CartList中，直接返回
	            	request.setAttribute("msg", "已有同類商品在購物車中");
	            	try {
						request.getRequestDispatcher("./jsp/UserOneItemList.jsp").forward(request, response);
						return;
					} catch (ServletException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	        }
	    }
		
		// 取得購物車商品資訊
		List<Commodity> cartList = addCart(id,itemNum);

	    // 取得舊的comList
	    @SuppressWarnings("unchecked")
		List<Commodity> oldComList = (List<Commodity>) session.getAttribute("CartList");

	    // 如果舊的comList為空，直接將新的購物車商品資訊存到session中
	    if (oldComList == null) {
	        session.setAttribute("CartList", cartList);
	        session.setAttribute("length", cartList.size());
	    } 
	    // 否則將舊的comList和新的購物車商品資訊合併成一個新的List，再存到session中
	    else {
	        List<Commodity> newComList = new ArrayList<>(oldComList);
	        newComList.addAll(cartList);
	        session.setAttribute("CartList", newComList);
	        session.setAttribute("length", newComList.size());
	    }
	    

		try {
			List<Commodity> comList = searchOneItem(id);
			request.setAttribute("OneComList", comList);
			
			// 將資料轉發到商品頁面
			request.getRequestDispatcher("./jsp/UserOneItemList.jsp").forward(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}

		return;		
		
	}

	/**
	 *加入購物車的方法 
	 */
	@Override
	public List<Commodity> addCart(int id,int itemNum) {
		
		// 宣告一個陣列(list 資料型態為Java Bean<Commodity>)變數，用來存放從SQL裡面取得的資料
		List<Commodity> comList = null;
		try {
//			Context context = new InitialContext();
//			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
//			conn = ds.getConnection();
			// 建立資料連線
			conn=ConnectionPool.getInstance().getConnection();
			
			// 建立一個字串並轉換為SQL語法執行
			String addSQL = "SELECT * FROM commodity where comId=?";
			ps = conn.prepareStatement(addSQL);
			// 將SQL語法內的?，填入值(value)
			ps.setInt(1, id);
			// 執行SQL語法，並將取得的商品內容傳入rs中
			rs = ps.executeQuery();
			// 宣告要使用的陣列
			comList = new ArrayList<Commodity>();
			// 用迴圈將取得的資料放入陣列(list)裡面

			while (rs.next()) {
				Commodity newCommodity = new Commodity(rs.getInt("comId"), rs.getString("comName"),
						rs.getInt("comNumber"), rs.getInt("comPrice"), rs.getString("comContent"),
						rs.getString("comType"), rs.getBytes("comPic"), rs.getString("comStatus"));

				if (newCommodity.getComPic() != null) {
					String base64String = Base64.getEncoder().encodeToString(newCommodity.getComPic());
					newCommodity.setComPicBase64(base64String);
					// 將圖片資料儲存成檔案，以進行檢查
				}
				newCommodity.setItemNum(itemNum);
				comList.add(newCommodity);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 關閉建立的資料庫連接
			try {
				 if (rs != null) {
			            ConnectionPool.getInstance().close(rs);
			        }
			        if (ps != null) {
			            ConnectionPool.getInstance().close(ps);
			        }
			        if (conn != null) {
			            ConnectionPool.getInstance().releaseConnection(conn);
			        }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return comList;
		
	}
	/**
	 * 刪除購物車方法
	 */
	@Override
	public void getRemoveCart(HttpServletRequest request, HttpServletResponse response) {
	    int id = Integer.parseInt(request.getParameter("comId"));
	    int itemNum = Integer.parseInt(request.getParameter("itemNumber")) ;
	    HttpSession session = request.getSession();

	    // 從session中取出購物車商品列表
	    @SuppressWarnings("unchecked")
		List<Commodity> cartList = (List<Commodity>) session.getAttribute("CartList");

	    // 遍歷購物車商品列表，找到要刪除的商品並刪除它
	    Iterator<Commodity> iter = cartList.iterator();
	    while (iter.hasNext()) {
	        Commodity commodity = iter.next();
	        if (commodity.getComId() == id) {
	        	if(commodity.getItemNum() == itemNum) {
	        		iter.remove();	        		
	        	}
	        }
	    }


	    // 將更新後的購物車商品列表存回session中
	    session.setAttribute("CartList", cartList);
	    session.setAttribute("length", cartList.size());

	    try {
	        // 將資料轉發到購物車頁面
	        request.getRequestDispatcher("./jsp/UserCart.jsp").forward(request, response);
	    } catch (IOException | ServletException e) {
	        e.printStackTrace();
	    }
	}



}
