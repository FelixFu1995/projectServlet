<%@page import="java.util.*"%>
<%@page import="javabeans.ArticleBean"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%!@SuppressWarnings("unchecked") %> 
<!DOCTYPE html>
<html lang="zh-Hants">

<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>新增文章頁面</title>
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
	.form{
	        border: 1px solid black;
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

        <div class="container">
            <div class="row">
                <div class="col-6">

                    <h1>預覽文章</h1>
        <form action="${pageContext.request.contextPath}/GetAllArticle" method="get" enctype="multipart/form-data">
                    
                  	<%
                  	ArticleBean articleBean = (ArticleBean)request.getAttribute("articleBean2"); 
                  
					%>
				  <div class="form">
				  		文章編號: <div> <%=articleBean.getArticleId()%></div>
                        <br>
	
                    	會員編號: <div> <%=articleBean.getUserId()%></div>
                        <br>
                            主題分類
                          <div><%=articleBean.getArticleType()%></div>
                        <br>
                        標題：
                        <div><%=articleBean.getArticleTitle()%></div><br>
                     
                     	        <div class="st1">
                       照片:
                <%String imageDataStr = Base64.getEncoder().encodeToString(articleBean.getArticleImg());
				String mimeType = articleBean.getArticleImgMimeType();
				String dataURI = "data:" + mimeType + ";base64," + imageDataStr;%>
				<div><img src="<%=dataURI%>" style="width: 100px;"> </div>
                                <!-- multiple可以多選文件提交 -->
                                         
                             </div><br>  
                        文章內容：<br>
                        <div><%=articleBean.getArticleContent()%></div><br><br>
                       時間:<br>
                       <div><%=articleBean.getArticleTime()%></div>
								
					</div>
                          <!-- 發表發文 trigger modal -->
  
                          <button type="button" class="btn btn-dark .text-dark " data-bs-toggle="modal"data-bs-target="#publishModal">
                              發表
                          </button>
                          <!-- Modal -->
                          <div class="modal fade" id="publishModal" data-bs-backdrop="static" data-bs-keyboard="false"
                              tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                              <div class="modal-dialog">
                                  <div class="modal-content">
                                      <div class="modal-header">
                                          <h5 class="modal-title" id="staticBackdropLabel">發表發文</h5>
                                          <button type="button" class="btn-close" data-bs-dismiss="modal"
                                              aria-label="Close"></button>
                                      </div>
                                      <div class="modal-body">
                                          你確定要發表發文嗎?
                                      </div>
                                      <div class="modal-footer">
                                          <button type="submit" class="btn btn-secondary"
                                              data-bs-dismiss="modal">取消</button>
                                          <button type="submit" class="btn btn-dark">確定</button>
                                      </div>
                                  </div>
                              </div>
                          </div>

                        

                        <!-- 取消發表發文 trigger modal -->
                        <button type="button" class="btn btn-dark .text-dark" data-bs-toggle="modal" data-bs-target="#cancelModal">
                            取消
                        </button>

                        <!-- Modal -->
                        <div class="modal fade" id="cancelModal" data-bs-backdrop="static" data-bs-keyboard="false"
                            tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="staticBackdropLabel">取消發文</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal"
                                            aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        你確定要取消發文嗎?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="submit" class="btn btn-secondary"
                                            data-bs-dismiss="modal">取消</button>
                                        <button type="submit" class="btn btn-dark">確定</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    
                </div>
                <div class="col-6">
                    <!-- <p>666</p> -->
                </div>
            </div>
        </div>

    



        
    </div>
    <script src="plugins/style.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
</body>

</html>