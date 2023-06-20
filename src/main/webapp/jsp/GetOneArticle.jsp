<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, javabeans.ArticleBean" %>
  <%!@SuppressWarnings("unchecked") %> 
<!DOCTYPE html>
<html lang="zh-Hants">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>查詢單筆</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
  <link rel="icon"
    href="https://cdn1.iconfinder.com/data/icons/diet-and-nutrition-7/64/weights-gym-fitness-exercise-workout-256.png"
    type="image/x-icon" />
    <link rel="stylesheet" href="../plugins/style.css" />
    
    
  <!-- href加上 . 表示當前目錄(HTML文件所在的目錄),是讓排版從vscode或直接點html都不會跑掉 -->
  <!-- ./ 或都不要寫直接 plugins/style.css , 但建議習慣寫./-->

  <style>
    *{
      margin: 0;
      padding: 0;
    }
    /* main {
      padding: 20px;
    } */

    h2 {
      font-size: 24px;
      /* margin-bottom: 10px; */
    }

    ul {
      list-style: none;
      margin: 0;
      padding: 0;
    }


    section ul li {
      /* -------------外框---------------- */
      border: 1px solid #ccc;
      padding: 20px;
      /* margin-bottom: 5px; */
    }

    h4 {
      font-size: 20px;
      /* margin: 0 0 5px; */
    }


    .topics {
      text-decoration: none;
      color: black;
    }

    .article-meta {
      color: #777;
      font-size: 14px;
    }

    .article-date {
      margin-right: 10px;
    }

    .article-topic {
      background-color: #ddd;
      border-radius: 4px;
      color: #555;
      display: inline-block;
      font-size: 12px;
      /* padding: 3px 6px; */
    }

    .pagination {
      margin-top: 20px;
    }

    .pagination ul {
      list-style: none;
      margin: 0;
      padding: 0;
    }

    .pagination li {
      display: inline-block;
      margin-right: 10px;
    }

    .pagination li:last-child {
      margin-right: 0;
    }

    .pagination a {
      background-color: #ddd;
      border-radius: 4px;
      color: #555;
      display: inline-block;
      padding: 3px 6px;
      text-decoration: none;
    }

    .pagination a:hover {
      background-color: #bbb;
    }
  </style>
</head>

<body>

  <div class="container-fluid">
    <header>
      <nav class="navbar navbar-expand-lg bg-body-tertiary">
        <div class="container-fluid">
          <a class="navbar-brand" href="#"><i class="fa fas fa-solid fa-dumbbell"></i> Oh GymGym</a>
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


    <main>
      <div class="container">
        <div class="row">
          
          <div class="col-2">
            <h2>主題分類</h2>
            <div id="list-example" class="list-group">
              <a class="list-group-item list-group-item-action" href="#list-item-1">Item 1</a>
              <a class="list-group-item list-group-item-action" href="#list-item-2">Item 2</a>
              <a class="list-group-item list-group-item-action" href="#list-item-3">Item 3</a>
              <a class="list-group-item list-group-item-action" href="#list-item-4">Item 4</a>
            </div>


          </div>

          <div class="col-10">
              <section>
              <h2>Latest Articles</h2>
				<%ArticleBean articleBean = (ArticleBean)request.getAttribute("ArticleBean2"); %>
				 <ul>
				 <li> 
				 <div>文章編號:<%=articleBean.getArticleId()%></div>
				 <div>會員編號:<%=articleBean.getUserId()%></div>
                  <h4>文章標題:<a href="#" class="topics"><%=articleBean.getArticleTitle() %></a></h4>
                  <p>文章內容:<%=articleBean.getArticleContent() %></p>
                  <%String imageDataStr = Base64.getEncoder().encodeToString(articleBean.getArticleImg());
				String mimeType = articleBean.getArticleImgMimeType();
				String dataURI = "data:" + mimeType + ";base64," + imageDataStr;%>
				  <span>圖片:<img src="<%=dataURI%>" style="width: 100px;"></span>
				  
                  <div class="article-meta">
                    <span class="article-date">發文時間:<%=articleBean.getArticleTime() %></span>
                    <span class="article-topic">發文主題:<%=articleBean.getArticleType() %></span>
                  </div>
                </li>			
              </ul>
            </section> 
            
            <nav class="pagination">
              <ul>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
              </ul>
            </nav>
            
          </div>
        </div>
      </div>
      
    </main>
  </div>
  
  <script src="plugins/style.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"></script>
</body>

</html>