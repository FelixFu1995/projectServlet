<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hants">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>新增課程</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
  <link rel="icon"
    href="https://cdn1.iconfinder.com/data/icons/diet-and-nutrition-7/64/weights-gym-fitness-exercise-workout-256.png"
    type="image/x-icon" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/style.css" />
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

        
        .button1{
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
          <a class="navbar-brand" href="${pageContext.request.contextPath}/FirstPage/Admin.jsp"><i class="fa fas fa-solid fa-dumbbell"></i> Oh GymGym</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
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
									<li><a class="dropdown-item" href="../GetAllActivity">近期賽事</a>
									</li>
								</ul></li>
              <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                  aria-expanded="false">
                  論壇
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
                                    管理員
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="../MemberQuery">會員查詢列表</a></li>
                                    <li><a class="dropdown-item" href="../logOut">登出</a></li>
                                    
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
    <h1>新增課程</h1>
    <!-- <em>強調<strong>重要的 文字語意標籤 -->
    <form action="../courseAction" method="post" enctype="multipart/form-data" onsubmit="return confirm('確定要新增這筆課程嗎？')">
    <input type="hidden" name="page" value="add">
        <fieldset>
            <div class="st1">
                <label for="account1" class="t1">課程名稱:</label> <input type="text" name="courseName" id="account1" autofocus
                    placeholder="請輸入課程名稱" autocomplete="off">
            </div>
            <div class="st1">
                <label for="account2" class="t1">教練姓名:</label> <input type="text" name="coachName" id="account2" autofocus
                    placeholder="請輸入教練姓名" autocomplete="off">
            </div>
            <div class="st1">
                <label for="account3" class="t1">課程價格:</label> <input type="text" name="courseCost" id="account3" autofocus
                    placeholder="請輸入課程價格" autocomplete="off">
            </div>
            <div class="st1">
                <label for="account4" class="t1">課程介紹:</label> <input type="text" name="courseIntroduce" id="account4" autofocus
                    placeholder="請輸入課程介紹" autocomplete="off">
            </div>
            <div class="st1">
              <label for="account5" class="t1">
              請上傳課程圖片:</label> <input type="file" name="courseImg" id="account5"
                autofocus>
            </div>

            <!-- <div class="st1">
                <label for="" class="t1">URL</label>
                <input type="url" name="url1" id="">
            </div> -->
        </fieldset>
        

        <div class="sub" style="margin: 15px auto;">
            <input type="submit" value="確定">
            <input type="reset" value="清除">
            <input type="button" name="Submit" value="取消"  onclick="location.href='${pageContext.request.contextPath}/jsp/Fitness.jsp'" >
        </div>
    </form>
        <footer class="py-3 my-4">
      <ul class="nav justify-content-center border-bottom pb-3 mb-3">
        <li class="nav-item">
          <a href="#" class="nav-link px-2 text-muted">關於我們</a>
        </li>
        <li class="nav-item">
          <a href="#" class="nav-link px-2 text-muted">常見問題</a>
        </li>
        <li class="nav-item">
          <a href="#" class="nav-link px-2 text-muted">服務條款</a>
        </li>
        <li class="nav-item">
          <a href="#" class="nav-link px-2 text-muted">客服中心</a>
        </li>
      </ul>
      <p class="text-center text-muted">
        © 2023 Oh Gym Gym. All Rights Reserved.
      </p>
    </footer>
  </div>
  <script src="plugins/style.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"></script>
</body>

</html>