<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : leaderboard
    Created on : 22-Mar-2017, 16:11:45
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
            <li role="presentation" class="active"><a href="leaderboard">Leaderboard</a></li>
            <li role="presentation"><a href="startGame">Start Game</a></li>
            <li role="presentation"><a href="playGame">Play Game</a></li>
            <li role="presentation"><a href="createAccount">Create Account</a></li>
        </ul> 
        <c:if test="${user != null}">
        <h2>Your Score:</h2>
        <table id="userScores">
            <tr>
                <td><h4>Username</h4></td> 
                <td><h4>Score</h4></td>
            </tr>
            <tr>
                <td><p>${user}</p></td> 
                <td><p>${score}</p></td>    
            </tr>
        </table>
        </c:if>
    
        <h2>Leaderboard:</h2>
        <table id="allScores">
            <tr>
                <td><h4>Username</h4></td> 
                <td><h4>Score</h4></td>
            </tr>
            <c:forEach var="aUser" items="${all}" step="1">
                <tr>
                    <td><p>${aUser.getUsername()}</p></td> 
                    <td><p>${aUser.getScore()}</p></td>
                </tr>                    
            </c:forEach>
        </table>
    </jsp:body>
    
    
</t:base>