<%-- 
    Document   : login
    Created on : 30-Mar-2017, 09:35:20
    Author     : Robin Yonge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<t:base>
    
    <jsp:body>
        <ul class="nav nav-pills">
            <li role="presentation"><a href="./">Home</a></li>
            <li role="presentation" class="active"><a href="loginPage">Login</a></li>
            <li role="presentation"><a href="leaderboard">Leaderboard</a></li>
            <li role="presentation"><a href="startGame">Start Game</a></li>
            <li role="presentation"><a href="playGame">Play Game</a></li>
            <li role="presentation"><a href="createAccount">Create Account</a></li>
        </ul> 
        <h2>Login</h2>
        <form action="login"method="POST" enctype="multipart/form-data"> 
            <input type="text" name="username" value="username" />
            <br>
            <input type="password" name="password" value="password" />
            <br>
            <input type="submit" value="Submit" />
        </form>
        <br>
        <br>
        <br>
    </jsp:body>
        
    
</t:base>