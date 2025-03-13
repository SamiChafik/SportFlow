<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Se connecter</title>
    <style>
        body {
            background: url(./assets/images/background_3.jpg);
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            background-repeat: no-repeat;
        }

        #form {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 657px;
        }

        .form1 {
            color: white;
            font-size: 24px;
            background-color: rgb(177, 177, 177);
            padding: 3%;
            width: 60%;
            border-radius: 10px;
            background: rgba(23, 23, 23, 0.5);
            backdrop-filter: blur(8px);
            box-shadow: 0px 1px 5px rgb(61, 61, 61);
        }

        #btn {
            display: flex;
            justify-content: center;
            width: 100%;
            gap : 20px;
        }


        .btnLogin:hover {
            background-color: rgb(135, 182, 64);
            border-color: rgb(39, 167, 0);
        }
    </style>
<body>
<main>
    <section>
        <div id="form">
            <form id="loginForm" class="form1" action="login" method="post">
                <div>
                    <div class="form-group col-md-12">
                        <label for="email">Email</label>
                        <input type="text" class="form-control" id="email" name="email" placeholder="Email">
                    </div>
                    <div class="form-group col-md-12">
                        <label for="password">Mot de passe</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe">
                    </div>

                    <div id="btn">
                        <button type="submit" class="btn btn-primary loginBtn">Se connecter</button>
                        <%--                        <a href="user?action=new"><button class="btn btn-primary loginBtn">S'inscrire</button></a>--%>
                    </div>
                </div>
            </form>
        </div>
    </section>
</main>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
