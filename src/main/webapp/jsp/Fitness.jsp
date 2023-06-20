<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hants">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Oh Gym Gym</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
  <link rel="icon"
    href="https://cdn1.iconfinder.com/data/icons/diet-and-nutrition-7/64/weights-gym-fitness-exercise-workout-256.png"
    type="image/x-icon" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/Fitness.css" />
  <!-- href加上 . 表示當前目錄(HTML文件所在的目錄),是讓排版從vscode或直接點html都不會跑掉 -->
  <!-- ./ 或都不要寫直接 plugins/style.css , 但建議習慣寫./-->
</head>

<body>
  <div class="container-fluid">
    <header>
      <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
          <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/Fitness.jsp"><i class="fa fas fa-solid fa-dumbbell"></i> Oh GymGym</a>
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
                  <li><a class="dropdown-item" href="${pageContext.request.contextPath}/UserItemServlet?userState=checkItem">線上商城</a></li>
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
                  <li><a class="dropdown-item" href="#">1</a></li>
                  <li><a class="dropdown-item" href="#">2</a></li>
                  <li><a class="dropdown-item" href="#">3</a></li>
                  <li><a class="dropdown-item" href="#">4</a></li>
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
            <a class="navbtn" href="${pageContext.request.contextPath}/UserItemServlet?userState=intoCart"><img src="${pageContext.request.contextPath}/images/cart_new.jpg" alt="Custom Icon" height="30px"
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
    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
          aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
          aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
          aria-label="Slide 3"></button>
      </div>
      <div class="carousel-inner">
        <div class="carousel-item active" data-bs-interval="2000">
          <img src="${pageContext.request.contextPath}/images/gym11.avif" class="d-block w-100" alt="..." />
        </div>
        <div class="carousel-item" data-bs-interval="2500">
          <img src="${pageContext.request.contextPath}/images/gym22.avif" class="d-block w-100" alt="..." />
        </div>
        <div class="carousel-item" data-bs-interval="3000">
          <img src="${pageContext.request.contextPath}/images/gym33.avif" class="d-block w-100" alt="..." />
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
        data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
        data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <div style="
                height: 100%;
                display: flex;
                justify-content: center;
                align-items: center;
              ">
            <img src="${pageContext.request.contextPath}/images/44.avif" alt="" style="margin-top: 30px; max-width: 100%; max-height: 100%; href="#" />
          </div>
        </div>
        <div class="col-md-8">
          <div style="
                height: 100%;
                display: flex;
                flex-direction: column;
                justify-content: center;
              ">
            <h1>歡迎來到 _______ ！</h1>
            <p>這裡是要打的內容,主題發想之類的 ~~~~~~</p>
            <p>
              這裡是要打的內容,主題發想之類的 ~~~~~~
            </p>
            <p>
              這裡是要打的內容,主題發想之類的 ~~~~~~
            </p>
            <button type="button" class="btn btn-dark" style="align-self: flex-end">
              立即預約
            </button>
          </div>
        </div> 
      </div>
    </div>
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

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"></script>
    
</body>

</html>