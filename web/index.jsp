<%-- 
    Document   : index
    Created on : 22-Mar-2017, 15:08:04
    Author     : gilmoregrills
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<t:base>
      
    <jsp:body>
        <ul class="nav nav-pills">
            <li role="presentation" class="active"><a href="./">Home</a></li>
            <li role="presentation"><a href="loginPage">Login</a></li>
            <li role="presentation"><a href="leaderboard">Leaderboard</a></li>
            <li role="presentation"><a href="startGame">Start Game</a></li>
            <li role="presentation"><a href="playGame">Play Game</a></li>
            <li role="presentation"><a href="createAccount">Create Account</a></li>
        </ul> 
        <div class="button1"><a href ="loginPage">
            <button type="button" class="btn btn-secondary btn-lg" >Login</button>
        </a></div>
        
        <div class="button2"><a href="createAccount">
            <button type="button" class="btn btn-secondary btn-lg">Create Account</button>
            </a></div>
    </jsp:body>
        
</t:base>