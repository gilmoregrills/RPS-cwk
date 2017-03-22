<%-- 
    Document   : base
    Created on : 22-Mar-2017, 15:06:12
    Author     : gilmoregrills
--%>

<%@tag description="base template" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>

<%-- any content can be specified here e.g.: --%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<link href='https://fonts.googleapis.com/css?family=Raleway|Lato:400,700|Bree+Serif&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="css/base.css" />
<html>
  <body>
      <div id="wrap">
        <div id="pageheader" class="col-md-12">
          <h1>Rock Paper Scissors</h1>
          <h4> a dumb game by dumb students </h4>
          <p>Temporay Navigation</p>
            <a href="startGame.jsp">Start/Join Game |</a>
            <a href="playGame.jsp"> Play Game Page |</a>
            <a href="leaderboard.jsp"> Leaderboard |</a>
            <a href="createAccount.jsp"> Create Account Page |</a>
            <a href="index.jsp"> Home/Login Page</a>
          <jsp:invoke fragment="header"/>
        </div>

        <div id="body" class="col-md-12">
          <jsp:doBody/>
        </div>
      </div>
    
    <div id="pagefooter" class="col-md-12">
        <p></p>
        <a href="https://github.com/gilmoregrills/rps-cwk">Project git repo can be found here</a>
      <jsp:invoke fragment="footer"/>
    </div>
  </body>
</html>