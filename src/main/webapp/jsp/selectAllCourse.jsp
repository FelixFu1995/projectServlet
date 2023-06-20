<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*, javabeans.CourseBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%!@SuppressWarnings("unchecked")%>
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>新增課程</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<link rel="icon"
	href="https://cdn1.iconfinder.com/data/icons/diet-and-nutrition-7/64/weights-gym-fitness-exercise-workout-256.png"
	type="image/x-icon" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/plugins/style.css" />
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script>
	$(function() {
		$("#tabs").tabs();
	});
</script>
<style>
h1 {
	font-size: 3em;
	text-align: center;
}

fieldset {
	width: 500px;
	border: 2px inset lightblue;
	border-radius: 15px;
	margin: 0 auto;
}

legend {
	font-size: 2em;
	margin: auto;
}

.st1 {
	border-bottom: 3px dashed gray;
	padding: 10px;
	margin: 20px;
	width: 450px;
}

.sub {
	width: 450px;
	margin: 20px;
	text-align: center;
}

.t1 {
	width: 200px;
	float: left;
	text-align: right;
}

.button1 {
	width: 100%;
	background-color: #ec8a0a;
	color: white;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<header>
			<nav class="navbar navbar-expand-lg bg-body-tertiary">
				<div class="container-fluid">
					<a class="navbar-brand" href="${pageContext.request.contextPath}/FirstPage/Admin.jsp"><i
						class="fa fas fa-solid fa-dumbbell"></i> Oh GymGym</a>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
						aria-controls="navbarSupportedContent" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<ul class="navbar-nav me-auto mb-2 mb-lg-0">
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false"> 課程 </a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item"
										href="${pageContext.request.contextPath}/jsp/insertCourse.jsp">新增課程</a></li>
									<li><a class="dropdown-item"
										href="${pageContext.request.contextPath}/selectAllCourse">全部課程</a></li>
								</ul></li>
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
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false"> 論壇 </a>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item" href="#">1</a></li>
									<li><a class="dropdown-item" href="#">2</a></li>
									<li><a class="dropdown-item" href="#">3</a></li>
									<li><a class="dropdown-item" href="#">4</a></li>
								</ul></li>
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
						<a class="navbtn " href="#" data-toggle="modal"
							data-target="#exampleModal1"><img
							src="${pageContext.request.contextPath}/images/user__icon_new.png"
							alt="Custom Icon" height="30px" width="30px"></a> <a
							class="navbtn" href="./cart.html"><img
							src="${pageContext.request.contextPath}/images/cart_new.jpg"
							alt="Custom Icon" height="30px" width="30px"></a> <a
							class="navbtn" href="#" data-toggle="modal"
							data-target="#exampleModal3"><img
							src="${pageContext.request.contextPath}/images/message_new.png"
							alt="Custom Icon" height="30px" width="30px"></a>
						<form class="d-flex" role="search">
							<input class="form-control me-2" type="search"
								placeholder="Search" aria-label="Search" />
							<button type="submit" class="btn btn-outline-secondary">
								Search</button>
						</form>
					</div>
				</div>
			</nav>
		</header>
		<article style="background-color: #cfcbcb;">
			<div class="container">

				<div class="container-fluid" id="tabs">
					<ul>

						<%
						List<CourseBean> cbeans = (ArrayList<CourseBean>) request.getAttribute("cbeans");
						for (CourseBean cbean : cbeans) {
						%>
						<li><a href="#tabs-<%=cbean.getCourseId()%>"><%=cbean.getCourseName()%></a></li>
						<%
						}
						%>

					</ul>
					<%
					for (CourseBean cbean : cbeans) {
					%>

					<div id="tabs-<%=cbean.getCourseId()%>">
						<div class="dropdown" style="float: right">
							<button class="btn btn-secondary dropdown-toggle" type="button"
								id="dropdownMenuButton1" data-bs-toggle="dropdown"
								aria-expanded="false"></button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
								<li><a class="dropdown-item" href="#"><form
											action="courseAction" method="post">
											<input type="hidden" name="page" value="update">
											<button class="btn btn-outline-secondary" type="submit"
												name="courseId" value="<%=cbean.getCourseId()%>">更新</button>
										</form></a></li>
								<li><a class="dropdown-item" href="#"><form
											action="courseAction" method="post"
											onsubmit="return confirm('確定要刪除這筆資料嗎？')">
											<input type="hidden" name="page" value="delete">
											<button class="btn btn-outline-secondary" type="submit"
												name="courseId" value="<%=cbean.getCourseId()%>">刪除</button></a>
									</form></li>
							</ul>
						</div>
						<p>
						<div class="row">
							<div class="col-md-6  d-flex ">
								<div class="row">
									<div class="col-md-6">
										<h2>課程編號</h2>
									</div>
									<div class="col-md-6">
										<h2><%=cbean.getCourseId()%></h2>
									</div>
									<hr>
									<div class="col-md-6">
										<h2>課程名稱</h2>

									</div>
									<div class="col-md-6">
										<h2><%=cbean.getCourseName()%></h2>
									</div>
									<hr>
									<div class="col-md-6">
										<h2>教練名稱</h2>
									</div>
									<div class="col-md-6">
										<h2><%=cbean.getCoachName()%></h2>
									</div>
									<hr>
									<div class="col-md-6">
										<h2>課程價格</h2>
									</div>
									<div class="col-md-6">
										<h2><%=cbean.getCourseCost()%></h2>
									</div>
								</div>
							</div>
							<div class="col-md-6 d-flex justify-content-center">
<!-- 								style="justify-content: center; -->
  <%
  String imageDataStr = Base64.getEncoder().encodeToString(cbean.getCourseImg());
  String mimeType = cbean.getCourseImgmimeType();
  String dataURI = "data:" + mimeType + ";base64," + imageDataStr;
  %>
  <form id="uploadForm" action="courseAction" method="post"
								enctype="multipart/form-data">
							<label class="upload_cover">
								<input type="hidden" name="page" value="updateImg">
<%-- 								<input type="hidden" name="courseId" value="<%=cbean.getCourseId()%>"> --%>
								<input type="file" name="courseImg" id="upload_cover"
									style="display:none";>
								<img src="<%=dataURI%>" class="img-fluid mx-auto">
								<div class="d-grid gap-1 col-2 mx-auto m-1">
									<button class="btn btn-outline-secondary" type="submit"
										name="courseId" value="<%=cbean.getCourseId()%>";>更新</button>
								</div>

								</label>

								</form>
							</div>
							<hr>
							<div class="col-md-12" style="text-align: center;">
								<h3>課程介紹</h3>
							</div>
							<div class="col-md-12">
								<br>
								<h3><%=cbean.getCourseIntroduce()%></h3>
							</div>

						</div>


						</p>
					</div>

					<%
					}
					%>
					
				</div>
<div class="d-grid gap-1 col-2 mx-auto m-3 ">
						<input type="button" class="btn btn-outline-dark"
							name="Submit" value="返回" style="text-align: center;"
							onclick="location.href='${pageContext.request.contextPath}/jsp/Fitness.jsp'">
					</div>
					<hr>
			</div>
			
		</article>
		
		<footer class="py-3 my-4">
			<ul class="nav justify-content-center border-bottom pb-3 mb-3">
				<li class="nav-item"><a href="#"
					class="nav-link px-2 text-muted">關於我們</a></li>
				<li class="nav-item"><a href="#"
					class="nav-link px-2 text-muted">常見問題</a></li>
				<li class="nav-item"><a href="#"
					class="nav-link px-2 text-muted">服務條款</a></li>
				<li class="nav-item"><a href="#"
					class="nav-link px-2 text-muted">客服中心</a></li>
			</ul>
			<p class="text-center text-muted">© 2023 Oh Gym Gym. All Rights
				Reserved.</p>
		</footer>
	</div>
	<script src="plugins/style.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
		crossorigin="anonymous"></script>
</body>

</html>