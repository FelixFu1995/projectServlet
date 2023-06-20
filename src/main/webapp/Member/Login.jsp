<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/style.css" />
    <!-- href加上 . 表示當前目錄(HTML文件所在的目錄),是讓排版從vscode或直接點html都不會跑掉 -->
    <!-- ./ 或都不要寫直接 plugins/style.css , 但建議習慣寫./-->
    <style>
        /* Center the form and set width */
        body {
            background-color: #e4e4e4;
        }

        header { 
            /* 調整bar 無法填滿整條問題 */
            /*a width: 110%;
            margin-left: -5%; */

        }

        .container {

            transform: scale(1.5);
            /* 調整比例以放大元素 */
        }

        .login-form {
            /* max-width: 400px; */
            width: 250px;
            height: 350px;
            margin: 100px auto;
            margin-top: 200px;
            padding: 30px;
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        .text-center {
            font-size: 20px;
            margin: 5px;
        }

        /* Style form elements */
        .form-group {


            /* margin-bottom: 10px; */

            font-size: 10px;
        }

        .form-control {
            /* width: 100px; */
            font-size: 10px;
        }

        .form-control:focus {
            box-shadow: none;

        }

        .btn-login {
            width: auto;
            /* font-weight: bold; */
            margin-top: 30px;
            /* 登入按鈕的字型大小 */
            font-size: 12px;

            text-align: right;
        }

        .login {
            text-align: right;
        }

        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            color: #0056b3;
            text-decoration: underline;
        }

        .registration-link {
            text-align: right;
        }
    </style>
</head>

<body onload='showError()'>
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

                </div>
            </nav>
        </header>
        <div class="container">
            <form class="login-form" action="../login" method="POST">
                <h2 class="text-center mb-4">Oh GymGym</h2>
                <div class="form-group">
                    <label for="userAccount">帳號</label>
                    <input type="text" id="userAccount" name="userAccount" class="form-control" placeholder="請輸入帳號"
                        required>
                </div>
                <div class="form-group">
                    <label for="userPassword">密碼</label>
                    <input type="password" id="userPassword" name="userPassword" class="form-control"
                        placeholder="請輸入密碼" required>
                </div>
                <div class="login">
                    <button type="submit" class="btn btn-primary btn-login">登入</button>
                </div>
                <div class="mt-2 registration-link">
                    沒有帳號嗎? <a href="FitnessRsgister.html">註冊</a>
                </div>

            </form>
        </div>


        <script src="plugins/style.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
            crossorigin="anonymous"></script>

        <script>
            function showError() {
                var urlParams = new URLSearchParams(window.location.search);
                if (urlParams.has("error") && urlParams.get("error") === "1") {
                    alert("帳號密碼錯誤");
                }
            }
        </script>
</body>

</html>