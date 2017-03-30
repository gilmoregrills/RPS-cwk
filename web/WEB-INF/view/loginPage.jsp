<%-- 
    Document   : login
    Created on : 30-Mar-2017, 09:35:20
    Author     : gilmoregrills
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<t:base>
    
    <jsp:body>
        <h2>Create Account</h2>
        <form href="/login" method="POST" enctype="multipart/form-data"> 
            <input type="text" name="username" value="username" />
            <input type="password" name="password" value="password" />
            <input type="submit" value="Submit" />
        </form>
    </jsp:body>
        
    
</t:base>