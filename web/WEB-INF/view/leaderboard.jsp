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
        <h2>Leaderboard:</h2>
        
        <h2>Your Score:</h2>
        <c:if test="if (session.getAttribute('user') != null)" var="loggedIn" scope="session">
            <p><jsp:getProperty name="session" property="attributeNames" /></p>
        </c:if>
        
    </jsp:body>
    
    
</t:base>