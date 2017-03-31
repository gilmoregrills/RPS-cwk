<%-- 
    Document   : createAccount
    Created on : 22-Mar-2017, 16:12:54
    Author     : Robin Yonge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<t:base>
    
    <jsp:body>
        <ul class="nav nav-pills">
            <li role="presentation"><a href="./">Home</a></li>
            <li role="presentation"><a href="loginPage">Login</a></li>
            <li role="presentation"><a href="leaderboard">Leaderboard</a></li>
            <li role="presentation"><a href="startGame">Start Game</a></li>
            <li role="presentation"><a href="playGame">Play Game</a></li>
            <li role="presentation" class="active"><a href="createAccount">Create Account</a></li>
        </ul> 
        <h2>Create Account</h2>
        <form action="create" method="POST" enctype="multipart/form-data"> 
            <input type="text" name="username" value="username" />
            <input type="password" name="password" value="password" />
            <input type="submit" value="Submit" />
        </form>
    </jsp:body>
        
    
</t:base>