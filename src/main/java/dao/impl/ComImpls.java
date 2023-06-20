package dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import dao.ComDao;
import dbutil.ConnectionPool;
import javabeans.Commodity;

public class ComImpls implements ComDao {
	private PreparedStatement ps = null; // 先宣告會將字串轉換為資料庫語法
	private ResultSet rs = null;
	private Connection conn = null;

	/**
	 * 新增商品判斷
	 */
	@Override
	public void getaddCom(HttpServletRequest request, HttpServletResponse response) {
		Commodity com = new Commodity();
		try {
			// 將前台輸入的資料傳送到Commodity(Java Bean)裡面
			com.setComName((request.getParameter("comName")));
			com.setComNumber(Integer.parseInt(request.getParameter("comNumber")));
			com.setComPrice(Integer.parseInt(request.getParameter("comPrice")));
			com.setComContent(request.getParameter("comContent"));
			com.setComType(request.getParameter("comType"));
			com.setComStatus(request.getParameter("comStatus"));
			// 從 multipart/form-data 請求中取得上傳的檔案
			// 呼叫上傳資料庫的方法，並把Commodity(java bean)傳進去
			Part filePart = request.getPart("comPic");
			InputStream fileContent = filePart.getInputStream(); // 取得檔案的 InputStream
			byte[] fileBytes = fileContent.readAllBytes(); // 讀取檔案內容到一個 byte[] 陣列中
			com.setComPic( fileBytes);

		} catch (IOException | ServletException e1) {
			e1.printStackTrace();
		}

		int addState = addCom(com);
		if(addState == 1) {
			request.setAttribute("msg", "成功新增商品");
		}else {
			request.setAttribute("msg", "新增失敗");
		}
		try {
			request.getRequestDispatcher("./jsp/AddNewItem.jsp").forward(request, response);
			return;
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * 新增商品
	 */
	@Override
	public int addCom(Commodity com) {
		// 建立資料庫連線
		 int executeUpdate = 0;
		//			Context context = new InitialContext();
//			DataSource dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
//			conn = dataSource.getConnection();
		conn = ConnectionPool.getInstance().getConnection();
		try {
			// 建立一個字串並轉換為SQL語法執行
			String addSQL = "INSERT INTO commodity(comName,comNumber,comPrice,comContent, comType, comPic, comStatus)values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(addSQL);
			ps.setString(1, com.getComName());
			ps.setInt(2, com.getComNumber());
			ps.setInt(3, com.getComPrice());
			ps.setString(4, com.getComContent());
			ps.setString(5, com.getComType());
			ps.setBytes(6, com.getComPic());
			ps.setString(7, com.getComStatus());

			// 上傳並更新 回傳更新行數
			 executeUpdate = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
			    ConnectionPool.getInstance().close(ps);
			}
			if (conn != null) {
			    ConnectionPool.getInstance().releaseConnection(conn);
			}
		}
		return executeUpdate;
	}

	/**
	 * 查詢該賣家全部商品
	 */
	@Override
	public void getInquireAllCom(HttpServletRequest request, HttpServletResponse response) {

		// 宣告一個陣列，以便後續取得的資料放入
		List<Commodity> comList = null;
		// 建立一個查詢商品的實體

		// 取得使用者的帳號

		// 呼叫取得全部商品的方法，並把取得的商品資料傳到一個List裡面
		comList = inquireAllCommodity();
		// 將取得的商品全部傳入一個標籤裡面
		request.setAttribute("comList", comList);
		try {
			// 將資料轉發到商品頁面
			request.getRequestDispatcher("./jsp/ItemList.jsp").forward(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}

		return;
	}

	/**
	 * 查詢該賣家的商品方法 回傳list型態
	 */
	@Override
	public List<Commodity> inquireAllCommodity() {
		// 宣告一個陣列(list 資料型態為Java Bean<Commodity>)變數，用來存放從SQL裡面取得的資料
		List<Commodity> comList = null;
		try {
			// 建立資料連線

//			Context context = new InitialContext();
//			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
//			conn = ds.getConnection();
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
	 * 接收前台 修改前顯示該ID物件之方法
	 */
	@Override
	public void getinquireOneCom(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("comId"));

		List<Commodity> comList = inquireOneCommodity(id);
		request.setAttribute("OneComList", comList);
		try {

			// 將資料轉發到商品頁面
			request.getRequestDispatcher("./jsp/OneItemList.jsp").forward(request, response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}

		return;

	}

	/**
	 * 修改前 先查詢該ID之方法
	 */
	@Override
	public List<Commodity> inquireOneCommodity(int id) {
		// 宣告一個陣列(list 資料型態為Java Bean<Commodity>)變數，用來存放從SQL裡面取得的資料
		List<Commodity> comList = null;
		try {
//			Context context = new InitialContext();
//			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
//			conn = ds.getConnection();
			conn = ConnectionPool.getInstance().getConnection();
			// 建立資料連線

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
	 * 修改商品之判斷
	 */
	@Override
	public void getUpdateCom(HttpServletRequest request, HttpServletResponse response) {
		Commodity com = new Commodity();
		String id = null;
		List<Commodity> comList = null;
		try {
			// 將前台輸入的資料傳送到Commodity(Java Bean)裡面
			id = request.getParameter("comId");
			com.setComName((request.getParameter("comName")));
			com.setComNumber(Integer.parseInt(request.getParameter("comNumber")));
			com.setComPrice(Integer.parseInt(request.getParameter("comPrice")));
			com.setComContent(request.getParameter("comContent"));
			com.setComType(request.getParameter("comType"));
			com.setComStatus(request.getParameter("comStatus"));
//			System.out.println(request.getParameter("comPicBase64"));
			// 從 multipart/form-data 請求中取得上傳的檔案
			// 呼叫上傳資料庫的方法，並把Commodity(java bean)傳進去
			Part filePart = request.getPart("comPic");
			if (filePart == null) {
				com.setComPic(null);
			} else {
				InputStream fileContent = filePart.getInputStream(); // 取得檔案的 InputStream
				byte[] fileBytes = fileContent.readAllBytes(); // 讀取檔案內容到一個 byte[] 陣列中
				com.setComPic(fileBytes);
			}

		} catch (IOException | ServletException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		updateCom(com, id);

		try {
			// 新增完資料後回傳訊息(成功)並轉跳到查詢全部商品(商品列表)的方法
			comList = inquireOneCommodity(Integer.parseInt(id));
			request.setAttribute("OneComList", comList);
			request.setAttribute("msg", "成功修改商品");
//			response.sendRedirect("./html/FitnessBack.jsp");
			request.getRequestDispatcher("./jsp/OneItemList.jsp").forward(request, response);
			return;
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		inquireCom(request, response);
		return;

	}

	/**
	 * 修改商品細項之方法
	 */
	@Override
	public void updateCom(Commodity com, String id) {
		try {
			// 建立資料庫連線
//			Context context = new InitialContext();
//			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
//			conn = ds.getConnection();
			conn = ConnectionPool.getInstance().getConnection();

			// 建立一個字串並轉換為SQL語法執行
			String editSQL = "UPDATE commodity SET comName=?, comNumber=?, comPrice=?, comContent=?, comType=?, comPic=?, comStatus=? WHERE comId=?";
				ps = conn.prepareStatement(editSQL);
				ps.setString(1, com.getComName());
				ps.setInt(2, com.getComNumber());
				ps.setInt(3, com.getComPrice());
				ps.setString(4, com.getComContent());
				ps.setString(5, com.getComType());
				ps.setBytes(6, com.getComPic());
				ps.setString(7, com.getComStatus());
				ps.setInt(8, Integer.parseInt(id));
				// 上傳並更新
				ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (ps != null) {
			    ConnectionPool.getInstance().close(ps);
			}
			if (conn != null) {
			    ConnectionPool.getInstance().releaseConnection(conn);
			}
		}
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
			request.getRequestDispatcher("./jsp/ItemList.jsp").forward(request, response);
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

//			Context context = new InitialContext();
//			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
//			conn = ds.getConnection();
			conn = ConnectionPool.getInstance().getConnection();

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
	 * 判斷價錢排序方法
	 */
	@Override
	public void getSortByPrice(HttpServletRequest request, HttpServletResponse response) {

		// 宣告一個陣列，以便後續取得的資料放入
		List<Commodity> comList = null;
		// 呼叫取得全部商品的方法，並把取得的商品資料傳到一個List裡面
		comList = inquireAllCommodity();

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
			request.getRequestDispatcher("./jsp/ItemList.jsp").forward(request, response);
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
			request.getRequestDispatcher("./jsp/ItemList.jsp").forward(request, response);
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

//			Context context = new InitialContext();
//			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
//			conn = ds.getConnection();
			conn = ConnectionPool.getInstance().getConnection();

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

}
