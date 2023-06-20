<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, javabeans.ArticleBean"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<%!@SuppressWarnings("unchecked")%>
<html>
<head>
	<meta charset="UTF-8">
	<title>所有文章</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
    <link rel="icon"
        href="https://cdn1.iconfinder.com/data/icons/diet-and-nutrition-7/64/weights-gym-fitness-exercise-workout-256.png"
        type="image/x-icon" />
    <link rel="stylesheet" href="../plugins/style.css" />
</head>
<body style="background-color: #fdf5e6">
 <div class="container-fluid">
        <header>
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#"><i class="fa fas fa-solid fa-dumbbell"></i> Oh GymGym</a>
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
                                    <li><a class="dropdown-item" href="#">1</a></li>
                                    <li><a class="dropdown-item" href="#">2</a></li>
                                    <li><a class="dropdown-item" href="#">3</a></li>
                                    <li><a class="dropdown-item" href="#">4</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                    aria-expanded="false">
                                    商城
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">1</a></li>
                                    <li><a class="dropdown-item" href="#">2</a></li>
                                    <li><a class="dropdown-item" href="#">3</a></li>
                                    <li><a class="dropdown-item" href="#">4</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                    aria-expanded="false">
                                    活動
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">1</a></li>
                                    <li><a class="dropdown-item" href="#">2</a></li>
                                    <li><a class="dropdown-item" href="#">3</a></li>
                                    <li><a class="dropdown-item" href="#">4</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                    aria-expanded="false">
                                    論壇
                                </a>
                               <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/GetAllArticle">論壇首頁</a></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/html/FitnessInsertPage.html">新增文章</a></li>
                                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/html/FitnessSelectPage.html">查詢文章</a></li>
                				</ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                    aria-expanded="false">
                                    會員
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="#">1</a></li>
                                    <li><a class="dropdown-item" href="#">2</a></li>
                                    <li><a class="dropdown-item" href="#">3</a></li>
                                    <li><a class="dropdown-item" href="#">4</a></li>
                                </ul>
                            </li>
                        </ul>
        	<a class="navbtn " href="#" data-toggle="modal" data-target="#exampleModal1"><img
                src="${pageContext.request.contextPath}/images/user__icon_new.png" alt="Custom Icon" height="30px" width="30px"></a>
            <a class="navbtn" href="./cart.html"><img src="${pageContext.request.contextPath}/images/cart_new.jpg" alt="Custom Icon" height="30px"
                width="30px"></a>
            <a class="navbtn" href="#" data-toggle="modal" data-target="#exampleModal3"><img
                src="${pageContext.request.contextPath}/images/message_new.png" alt="Custom Icon" height="30px" width="30px"></a>
                        <form class="d-flex" role="search">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                            <button type="submit" class="btn btn-outline-secondary">
                                Search
                            </button>
                        </form>
                    </div>
                </div>
            </nav>
        </header>
	<div align="center">
		<h2>所有文章</h2>
		
		
		<table border="1">
			<tr>
				<th>文章標號
				<th>文章標題
				<th>文章內容
				<th>時間
				<th>文章類型
				<th>文章圖片
				<th>更新
				<th>刪除
					<%
					List<ArticleBean> articleBeanList = (ArrayList<ArticleBean>) request.getAttribute("articleListBean");
					for (ArticleBean articleBean : articleBeanList) {
					%>
				
			<tr>
				<td><%=articleBean.getArticleId()%>
				<td><%=articleBean.getArticleTitle()%>
				<td><%=articleBean.getArticleContent()%>
				<td><%=articleBean.getArticleTime()%>
				<td><%=articleBean.getArticleType()%>
				<%String imageDataStr = Base64.getEncoder().encodeToString(articleBean.getArticleImg());
				String mimeType = articleBean.getArticleImgMimeType();
				String dataURI = "data:" + mimeType + ";base64," + imageDataStr;%>
				<td><img src="<%=dataURI%>" style="width: 100px;">
				<td>
				
					<form action="selectArticleById" method="post">
						<button class="button1" type="submit" name="articleId"
							value="<%=articleBean.getArticleId()%>">更新</button>
					</form>
					
					
				<td>
				
				
				<form action="deleteArticle" method="post" onsubmit="return confirm('確定要刪除這篇文章嗎？')">
						<button class="button1" type="submit" name="articleId"
							value="<%=articleBean.getArticleId()%>">刪除</button>
					</form>
					<%
					}
					%>
				
		</table>
		<h3>
			共<%=articleBeanList.size()%>篇文章
		</h3>
		<input type="button" name="Submit" value="返回"
			onclick="location.href='../html/FitnessBackYard.html'">
	</div>
	</div>
<script src="plugins/style.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
</body>
</html>