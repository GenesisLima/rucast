<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <!-- Standard Meta -->
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <!-- Site Properties -->
    <title>RUCast - Login</title>

    <!-- Stylesheets -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
</head>
<body>

<c:if test = "${!empty param.error}">
<div class="alert alert-warning">
       <strong>Warning!</strong> <c:out value ="${param.error}"/>  
</div>
  </c:if>
  
        <div class="container">
        <form class="form-horizontal" role="form" method="POST" action="/login">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <h2>RUCast - Login</h2>
                    <hr>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="form-group has-danger">
                        <label class="sr-only" for="username">Login</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-at"></i></div>
                            <input type="username" name="username" class="form-control" id="username"
                                   placeholder="Login" required autofocus>
                        </div>
                    </div>
                </div>
               
                <div class="col-md-3">
                    <div class="form-control-feedback">
                        <span class="text-danger align-middle">
                          <!--  <i class="fa fa-close"></i> -->   
                        <!-- Put login error message here -->   
                        </span>
                    </div>
                </div>    
                          
            </div>
           
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="sr-only" for="password">Senha</label>
                        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                            <div class="input-group-addon" style="width: 2.6rem"><i class="fa fa-key"></i></div>
                            <input type="password" name="password" class="form-control" id="password"
                                   placeholder="Senha" required>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="form-control-feedback">
                        <span class="text-danger align-middle">
                        <!-- Put password error message here -->   
                       
                        </span>
                    </div>
                </div>
            </div>
<!--             <div class="row"> -->
<!--                 <div class="col-md-3"></div> -->
<!--                 <div class="col-md-6" style="padding-top: .35rem"> -->
<!--                     <div class="form-check mb-2 mr-sm-2 mb-sm-0"> -->
<!--                         <label class="form-check-label"> -->
<!--                             <input class="form-check-input" name="remember" -->
<!--                                    type="checkbox" > -->
<!--                             <span style="padding-bottom: .15rem">Remember me</span> -->
<!--                         </label> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
            <div class="row" style="padding-top: 1rem">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <button type="submit" class="btn btn-info"><i class="fa fa-sign-in"></i> Entrar</button>
                    <a class="btn btn-link" href="/password/reset">Esqueceu a Senha?</a>
                </div>
            </div>
        </form>
    </div>
</body>
</html>