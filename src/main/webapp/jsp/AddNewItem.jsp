<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/AddNewItem.css?ver=123" />
    <!-- href加上 . 表示當前目錄(HTML文件所在的目錄),是讓排版從vscode或直接點html都不會跑掉 -->
    <!-- ./ 或都不要寫直接 plugins/style.css , 但建議習慣寫./-->


    <title>新增商品</title>
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

    <div class="title">
        <h2>新增商品</h2>
    </div>
    <!-- 包起整體頁面的div -->
    <div class="wholePageContent">
        <main class="main-content"> 
        <font color="red">${msg}<br><br></font>
            <form action="${pageContext.request.contextPath}/ComServlet" method="post" enctype="multipart/form-data">
                <div class="add-input">
                    <label for="comName">商品名稱: </label>
                    <input type="text" name="comName" id="comName" required >
                </div>
                <div class="add-input">
                    <label for="comNum">商品數量: </label>
                    <input type="text" name="comNumber" id="comNum" required pattern="[0-9]+">
                </div>
                <div class="add-input">
                    <label for="comPrice">商品價格: </label>
                    <input type="text" name="comPrice" id="comPrice" required pattern="[0-9]+">
                </div>
                <div class="add-input">
                    <label for="comType">商品類型: </label>
                    <select name="comType" id="">
                        <option value="上衣">上衣</option>
                        <option value="褲子">褲子</option>
                        <option value="護具">護具</option>
                        <option value="配件">配件</option>
                    </select>
                </div>
                <div class="add-input">
                    <label for="comStatus">商品狀態: </label>
                    <select name="comStatus" id="">
                        <option value="全新上架">全新上架</option>
                        <option value="熱賣">熱賣</option>
                        <option value="已完售">已完售</option>
                        <option value="缺貨">缺貨</option>
                    </select>
                </div>
                <div class="add-input">
                    <label for="comContent">商品描述: </label>
                    <textarea name="comContent" id="comContent" cols="30" rows="5" autocomplete="off"></textarea>
                </div>
                <div class="add-input">
                    <label for="comPic" >商品圖片: </label>
                    <input type="file" name="comPic" id="comPic" required onchange="previewImage(this)" >
                </div>
                <div class="preview">
                    <img id="previewImg">
                </div>
                <div class="submit">
                	<input type="hidden" name="userState" value="addNewItem">
                    <input type="submit" value="確認新增">
                    <input type="reset" value="清除" onclick="clearPreview()">
                    
                </div>
            </form>
        </main>
    </div>

    <script>
        // 預覽圖片
        function previewImage(input) {
            var previewImg = document.querySelector('#previewImg');
            var file = input.files[0];
            var reader = new FileReader();
            reader.onload = function(e) {
                previewImg.src = e.target.result;
            }
            console.log(file);
            reader.readAsDataURL(file);
        }
        function clearPreview() {
        var previewImg = document.querySelector('#previewImg');
        previewImg.src = "";
    }

    document.querySelector('form').addEventListener('submit', function(event) {
    event.preventDefault(); // 取消表單提交事件

    if (confirm('您確定要新增資料嗎？')) { 
      // 如果使用者按下“確定”按鈕，則提交表單
      document.querySelector('form').submit();
    }
  });
    </script>




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