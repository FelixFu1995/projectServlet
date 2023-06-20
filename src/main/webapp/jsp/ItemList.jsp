<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
        integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
    <link rel="icon"
        href="https://cdn1.iconfinder.com/data/icons/diet-and-nutrition-7/64/weights-gym-fitness-exercise-workout-256.png"
        type="image/x-icon" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ItemList.css?ver=123" />
    
    <!-- href加上 . 表示當前目錄(HTML文件所在的目錄),是讓排版從vscode或直接點html都不會跑掉 -->
    <!-- ./ 或都不要寫直接 plugins/style.css , 但建議習慣寫./-->

    <title>商品列表</title>
</head>

<body>
    <!-- NAV BAR -->
    <header>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/FirstPage/Admin.jsp"><i class="fa fas fa-solid fa-dumbbell"></i> Oh GymGym</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false">
                                課程
                            </a>
                            <ul class="dropdown-menu">
								   <li><a class="dropdown-item" href="${pageContext.request.contextPath}/jsp/insertCourse.jsp">新增課程</a></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/selectAllCourse">全部課程</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false">
                                線上購物
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/ComServlet?userState=checkCom">商品列表</a></li>
                                <li><a class="dropdown-item" href="#">訂單</a></li>

                            </ul>
                        </li>
                        <li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">
									活動 </a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="../GetAllActivity">近期賽事</a>
									</li>
								</ul></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                aria-expanded="false">
                                論壇
                            </a>
                            <ul class="dropdown-menu">

                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                    aria-expanded="false">
                                    管理員
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="../MemberQuery">會員查詢列表</a></li>
                                    <li><a class="dropdown-item" href="../logOut">登出</a></li>
                                    
                                </ul>
                            </li>
                    </ul>
                    
                </div>
            </div>
        </nav>
    </header>

    <div class="add-new-item">
        <a href="${pageContext.request.contextPath}/jsp/AddNewItem.jsp"><span>新增商品</span></a>
    </div>
    <!-- 包起整體頁面的div -->
    <div class="wholePageContent">
        <main class="main-content">
        	<font color="red">${msg}<br><br></font>
            <c:forEach items="${comList}" var="com">
				<form action="${pageContext.request.contextPath}/ComServlet" method="post"
					enctype="multipart/form-data" class="item-form">
					<div class="card com-list" style="">
						<input type="hidden" name="comId" value="${com.comId}"> 
						<img
							src="data:image/png;base64,${com.comPicBase64}" name="comPic"
							height="100px" width="100px"  class="comPic"/>
						<div class="card-body cardContent">
							<div>
								商品:<input type="text" value="${com.comName}" name="comName"
									readonly>
							</div>
							<div>
								數量:<input type="text" value="${com.comNumber}" name="comNumber"
									readonly>
							</div>
							<div>
								價格:<input type="text" value="${com.comPrice}" name="comPrice"
									readonly>
							</div>
							<div class="add-input">
				                    <label for="comType">商品類型: </label>
				                    <select name="comType" id="" disabled>
				                        <option value="上衣" ${com.comType == '上衣' ? 'selected' : ''}>上衣</option>
									    <option value="褲子" ${com.comType == '褲子' ? 'selected' : ''}>褲子</option>
									    <option value="護具" ${com.comType == '護具' ? 'selected' : ''}>護具</option>
									    <option value="配件" ${com.comType == '配件' ? 'selected' : ''}>配件</option>				                    </select>
				            </div>
							<div class="add-input">
				                    <label for="comStatus">商品狀態: </label>
				                    <select name="comStatus" id="" disabled>
				                       <option value="全新上架" ${com.comStatus == '全新上架' ? 'selected' : ''}>全新上架</option>
									    <option value="熱賣" ${com.comStatus == '熱賣' ? 'selected' : ''}>熱賣</option>
									    <option value="已完售" ${com.comStatus == '已完售' ? 'selected' : ''}>已完售</option>
									    <option value="缺貨" ${com.comStatus == '缺貨' ? 'selected' : ''}>缺貨</option>
				                    </select>
				            </div>			
							
							<div>
								描述:<textarea name="comContent" id="" cols="20" rows="5"
									placeholder="${com.comContent}" readonly resized="none"></textarea>
							</div>
							<input type="hidden" name="userState" value="checkOneCom"> <input
								type="submit" value="修改">
						</div>
					</div>
				</form>
		</c:forEach>
        </main>
    </div>

    <!--Aside-->
    <div class="div-aside">
        <aside class="aside">
            <!-- Aside content here -->

            <div>
            <p>搜尋條件</p>
                <div class="aside-search">
                    <form action="${pageContext.request.contextPath}/ComServlet" method="post">
                    	<input type="hidden" name="userState" value="searchByName">
                        <input type="search" name="comName" id="" placeholder="請輸入查詢名稱" style="width: 10vw;">
                        <input type="submit" value="查詢">
                    </form>
                </div>
                <div class="aside-search">
                    <form action="${pageContext.request.contextPath}/ComServlet" method="post">
                    	<input type="hidden" name="userState" value="sortByPrice">
                        <select name="comPrice" id="">
	                        <option value="0">價格高至低</option>
	                        <option value="1">價格低至高</option>
                         </select>
                        <input type="submit" value="查詢">
                    </form>
                </div>
 
                <div class="aside-search">
                    <form action="${pageContext.request.contextPath}/ComServlet" method="post">
                    	<input type="hidden" name="userState" value="searchBType">
                        <select name="comType" id="">
	                        <option value="上衣">上衣</option>
	                        <option value="褲子">褲子</option>
	                        <option value="護具">護具</option>
	                        <option value="配件">配件</option>
                         </select>
                        <input type="submit" value="查詢">
                    </form>
                </div>
               <!--  <div class="aside-a">
                    <a href="#"><span>依類別排序</span></a>
                </div>
                 -->
            </div>
        </aside>
    </div>




    <!--回到上方按鈕-->
    <button id="back-to-top-btn" title="回到頁面頂端">
        <i class="fas fa-arrow-up">TOP</i>
    </button>

    <script>
        // 當網頁滾動超過 20px 時，按鈕才會顯示
        window.onscroll = function () {
            scrollFunction();
        };

        function scrollFunction() {
            if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                document.getElementById("back-to-top-btn").classList.remove("hide");
            } else {
                document.getElementById("back-to-top-btn").classList.add("hide");
            }
        }

        // 按下按鈕後回到頁面頂端
        document.getElementById("back-to-top-btn").onclick = function () {
            document.body.scrollTop = 0;
            document.documentElement.scrollTop = 0;
        };



    </script>


    
    <!-- Bootstrap JS JQ -->

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
        integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
        integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
        crossorigin="anonymous"></script>
</body>

</html>