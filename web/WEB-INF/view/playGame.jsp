<%-- 
    Document   : playGame
    Created on : 22-Mar-2017, 16:13:43
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
            <li role="presentation" class="active"><a href="playGame">Play Game</a></li>
            <li role="presentation"><a href="createAccount">Create Account</a></li>
        </ul> 
        <h2>RO SHAM BO</h2>
        <form href="makeMove" method="POST" enctype="multipart/form-data"> 
            <input type="text" name="move" value="Choose Your Move"/>
            <input type="submit" value="SHOW!"/>
        </form>
    </jsp:body>
    
    
</t:base>
