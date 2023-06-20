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

    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/UserCart.css?ver=123" />
    <script  src="${pageContext.request.contextPath}/JS/jquery-3.6.4.min.js"></script>



    <title>商品列表</title>
</head>

<body>
    <!-- NAV BAR -->
    <header>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/FirstPage/FirstPage.jsp"><i class="fa fas fa-solid fa-dumbbell"></i> Oh GymGym</a>
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
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/UserItemServlet?userState=checkItem">商品列表</a></li>
                                <li><a class="dropdown-item" href="#">2</a></li>

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
                    <a class="navbtn" href="${pageContext.request.contextPath}/UserItemServlet?userState=intoCart"><img src="${pageContext.request.contextPath}/images/cart_new.jpg" alt="Custom Icon"
                            height="30px" width="30px"></a>
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

     <!-- 包起整體頁面的div -->
     <div class="wholePageContent">
        <main class="main-content">
            <div class="container">
                <div class="wrapper wrapper-content animated fadeInRight">
                    <div class="row">
                        <div class="col-md-9">
                            <div class="ibox">
                                <div class="ibox-title">
                                    <span class="pull-right">(<strong>${length}</strong>) items</span>
                                    <h5>Items in your cart</h5>
                                    <font color="red">${msg}<br><br></font>
                                </div>
        				<c:forEach items="${CartList}" var="com">
        				<form action="${pageContext.request.contextPath}/UserItemServlet" method="post">
                                <div class="ibox-content">
                                    <div class="table-responsive">
                                        <table class="table shoping-cart-table">
                                            <tbody>
                                            <tr>
                                                <td width="90">
                                                    <div class="cart-product-imitation">
                                                    <img
					src="data:image/png;base64,${com.comPicBase64}" name="comPic" class="comPic" style="width:100%; height:100%"/>
                                                    </div>
                                                </td>
                                                <td class="desc">
                                                    <h3>
                                                    <a href="#" class="text-navy">
                                                        ${com.comName}
                                                    </a>
                                                    </h3>
                                                    <p class="small">
                                                        ${com.comType}
                                                    </p>
                                                    <dl class="small m-b-none">
                                                        <dt>Description lists</dt>
                                                        <dd>${com.comContent}</dd>
                                                    </dl>
                
                                                    <div class="m-t-sm">
                                                        
                                                        <button type="submit" class="text-muted"><i class="fa fa-trash"></i> Remove item</button>
                                                    </div>
                                                </td>
                                               <!--  
                                                <td>
                                                    ${com.comPrice}
                                                    <s class="small text-muted">$230,00</s> 
                                                </td>
                                                -->
                                                <td width="65" height="fit-content">
                                                    <input type="text" class="form-control itemNum" value="${com.itemNum}"  data-price="${com.comPrice}"/>
                                                </td>
                                                <td>
                                                    <h5>
                                                       $: ${com.comPrice}
                                                    </h5>
                                                </td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <input type="hidden" name="comId" value="${com.comId}">
                                <input type="hidden" name="itemNumber" value="${com.itemNum}">
                                <input type="hidden" name="userState" value="removeCartItem">
                                </form>
                                </c:forEach>
                                
	                                <div class="ibox-content">
	                                    <a href="${pageContext.request.contextPath}/UserItemServlet?userState=checkItem"><button class="btn btn-white"><i class="fa fa-arrow-left"></i> Continue shopping</button></a>
	                                </div>
                            </div>
                        </div>
               
                        <div class="col-md-3">
                        <form action="${pageContext.request.contextPath}/UserItemServlet" method="post">
                            <div class="ibox">
                                <div class="ibox-title">
                                    <h5>Cart Summary</h5>
                                </div>
                                <div class="ibox-content">
                                    <span>
                                        Total
                                    </span>
                                    <h2 class="font-bold total">
                                        $0
                                    </h2>
                
                                    <hr>
                                    <span class="text-muted small">
                                        *For United States, France and Germany applicable sales tax will be applied
                                    </span>
                                    <div class="m-t-sm">
                                        <div class="btn-group">
                                        <button type="submit"  class="btn btn-primary pull-right"><i class="fa fa fa-shopping-cart"></i>   結帳</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:forEach items="${CartList}" var="com">
                            	<input type="hidden" name="comId" value="${com.comId}">
	                             <input type="hidden" name="itemNum-${com.comId}" id="itemNum-${com.comId}" value="${com.itemNum}">
	                             <input type="hidden" name="totalPrice" class="totalPrice">
								<input type="hidden" name="userState" value="payMoney">
                            </c:forEach>
                            </form>
                            <div class="ibox">
                                <div class="ibox-title">
                                    <h5>Support</h5>
                                </div>
                                <div class="ibox-content text-center">
                                    <h3><i class="fa fa-phone"></i> 0800-092-000</h3>
                                    <span class="small">
                                        Please contact with us if you have any questions. We are avaliable 24h.
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>

    <!-- footer -->
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
<script >
$(document).ready(function() {
	  // 計算總價格
	  updateTotal();

	  // 綁定 input 的 change 事件
	  $('.itemNum').on('change', function() {
	    const itemNum = $(this).val();
	    const comId = $(this).closest('form').find('[name="comId"]').val();
		  console.log(comId);
		
	    // 更新 hidden 標籤
	    $(`#itemNum-`+comId).val(itemNum);
	    
	    
	    const itemNums = document.querySelectorAll('.itemNum');

		// 透過迴圈取得每個數量欄位的值
		itemNums.forEach(function(itemNum) {
		  console.log(itemNum.value);
		  console.log("----- ");
		  console.log($(`#itemNum-${comId}`).val());
		});
		
	    updateTotal();
	    
	  });
	
	});

	function updateTotal() {
	  let total = 0;

	  $('.itemNum').each(function() {
	    const price = parseInt($(this).data('price'), 10);
	    const num = parseInt($(this).val(), 10);
	    if (!isNaN(num)) {
	      total += price * num;
	    }
	  });

	  $('.total').text('$' + total.toFixed(2));
	  $('.totalPrice').val(total.toFixed(2));
	}
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