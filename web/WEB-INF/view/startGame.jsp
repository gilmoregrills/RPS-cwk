<%-- 
    Document   : otherpage
    Created on : 22-Mar-2017, 15:20:22
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
            <li role="presentation"  class="active"><a href="startGame">Start Game</a></li>
            <li role="presentation"><a href="playGame">Play Game</a></li>
            <li role="presentation"><a href="createAccount">Create Account</a></li>
        </ul> 
        <h2>Start Game</h2>
        <!--
        <form href="/startGame" method="POST" enctype="multipart/form-data"> 
            <input type="text" name="player1" value="player1"/>
            <input type="text" name="player2" value="player2"/>
            <input type="submit" value="Start"/>
        </form>
        -->
        <h2>Join Game</h2>
        <p>Join a game!!</p>
    </jsp:body>
    
    
</t:base>