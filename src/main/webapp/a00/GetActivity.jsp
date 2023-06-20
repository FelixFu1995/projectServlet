<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hants">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Oh Gym Gym</title>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/style11.css">
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
									<li><a class="dropdown-item" href="${pageContext.request.contextPath}/GetAllActivity">近期賽事</a>
									</li>
								</ul></li>
							<li class="nav-item dropdown"><a
								class="nav-link dropdown-toggle" href="#" role="button"
								data-bs-toggle="dropdown" aria-expanded="false">
									論壇 </a>
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
						<a class="navbtn" href="#" data-toggle="modal"
							data-target="#exampleModal1"><img
							src="${pageContext.request.contextPath}/images/user__icon_new.png" alt="Custom Icon" height="30px"
							width="30px" /></a> <a class="navbtn"
							href="${pageContext.request.contextPath}/cart.html"><img
							src="${pageContext.request.contextPath}/images/cart_new.jpg" alt="Custom Icon" height="30px"
							width="30px" /></a> <a class="navbtn" href="#"
							data-toggle="modal" data-target="#exampleModal3"><img
							src="${pageContext.request.contextPath}/images/message_new.png" alt="Custom Icon" height="30px"
							width="30px" /></a>
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
<body>
  <section class="w3l-simple-contact-form1">
    <div class="contact-form section-gap">
      <div class="wrapper">
        <div class="contact-form" style="max-width: 450px; margin: 0 auto;">
          <div class="form-mid">
            <form action="${pageContext.request.contextPath}/UpdateActivityView" method="post" enctype="multipart/form-data">
       		 <h2>Update Data</h2>
              <div class="field">
                <input type="text" class="form-control" name="activityId" readonly value="${aBean.activityId}" placeholder="活動編號" required="">
              </div>
              <div class="field">
                <input type="text" class="form-control" name="activityName" value="${aBean.activityName}" placeholder="活動名稱"
                  required="">
              </div>
              <div class="field">
                <input type="text" class="form-control" name="activityDate" value="${aBean.activityDate}" placeholder="活動日期"
                  required="">
              </div>
              <div class="field">
                <input type="text" class="form-control" name="activityLocation" value="${aBean.activityLocation}" placeholder="活動地點"
                  required="">
              </div>
              <div class="field">
                <input type="text" class="form-control" name="activityStatus" value="${aBean.activityStatus}" placeholder="活動狀態"
                  required="">
              </div>
               <div class="field">
                <input type="file" class="form-control" name="activityCover" value="${aBean.activityCover}" placeholder="活動封面"
                  required="">
              </div>
              <textarea name="activityContent" class="form-control" value="${aBean.activityContent}" placeholder="活動內容"
                required=""></textarea>
                        <input type="hidden" name="activityId" value="${aBean.activityId}">
       		 <button type="submit" class="btn btn-contact">修改</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>
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
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
		crossorigin="anonymous"></script>
</body>	
</html>