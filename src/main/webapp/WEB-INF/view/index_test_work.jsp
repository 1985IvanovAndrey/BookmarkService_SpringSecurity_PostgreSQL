<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="ru">
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <%--<meta charset="UTF-8">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">--%>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>

    <title>Bookmark Service</title>

    <!-- Bootstrap core CSS -->
    <%--<link href="/resources/css/min.css" rel="stylesheet">--%>
    <style>
        <%@include file="/resources/css/main.css"%>
    </style>

    <%--<link href="/resources/css/starter-template.css" rel="stylesheet">--%>
</head>
<body>
<%--<div class="container">--%>
<%--<div align="right">--%>
<%--<form action="/test/search">--%>
<%--<input type="text" class="input-medium search-query" name="name">--%>
<%--<button type="submit" class="btn ">Search by name</button>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <div class="col-md-offset-8">
            <h3>Welcome, ${pageContext.request.userPrincipal.name} |
                <button onclick="document.forms['logoutForm'].submit()">Logout
                </button>
            </h3>
        </div>
    </c:if>
    <div class="alert alert-success" role="alert">
        <div align="left">
            <c:choose>
                <c:when test="${userRole=='user'}">
                    <script>
                        $(document).ready(function () {
                            $("#two").click(function () {
                                $("p").hide(1000);
                                alert("This button is for Admin only! You are not admin!");
                            });
                        });
                    </script>
                    <a class="navbar-brand">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                    <form action="#">
                        <button id="two" type="submit" class="btn btn-outline-primary btn-sm ">Only for ADMIN
                        </button>
                    </form>
                </c:when>
                <c:when test="${userRole=='admin'}">
                    <script>
                        $(document).ready(function (e) {
                            e.defaultChecked
                            $("#one").click(function () {
                                $("p").hide(1000);
                                alert("Access is allowed!");
                            });
                        });
                    </script>
                    <a class="navbar-brand">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                    <form action="/users">
                        <button id="one" type="submit" class="btn btn-outline-primary btn-sm ">Only for ADMIN
                        </button>
                    </form>
                </c:when>
            </c:choose>
        </div>
        <div align="center"><h1>Bookmark Service</h1></div>
        <%--<hr>--%>
        <%--<h1>--%>
        <%--<div align="center">Bookmark Service</div>--%>
        <%--</h1>--%>

        <%--<c:choose>--%>
        <%--<c:when test="${userRole=='user'}">--%>
        <%--<script>--%>
        <%--$(document).ready(function () {--%>
        <%--$("#two").click(function () {--%>
        <%--$("p").hide(1000);--%>
        <%--alert("This button is for Admin only! You are not admin!");--%>
        <%--});--%>
        <%--});--%>
        <%--</script>--%>
        <%--<a class="navbar-brand">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>--%>
        <%--<form action="#">--%>
        <%--<button id="two" type="submit" class="btn btn-outline-primary btn-sm ">Only for ADMIN</button>--%>
        <%--</form>--%>
        <%--</c:when>--%>
        <%--<c:when test="${userRole=='admin'}">--%>
        <%--<script>--%>
        <%--$(document).ready(function (e) {--%>
        <%--e.defaultChecked--%>
        <%--$("#one").click(function () {--%>
        <%--$("p").hide(1000);--%>
        <%--alert("Access is allowed!");--%>
        <%--});--%>
        <%--});--%>
        <%--</script>--%>
        <%--<a class="navbar-brand">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>--%>
        <%--<form action="/users">--%>
        <%--<button id="one" type="submit" class="btn btn-outline-primary btn-sm ">Only for ADMIN</button>--%>
        <%--</form>--%>
        <%--</c:when>--%>
        <%--</c:choose>--%>


        <%--<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top justify-content-between">--%>

        <%--<c:if test="${pageContext.request.userPrincipal.name != null}">--%>
        <%--<form id="logoutForm" method="POST" action="${contextPath}/logout">--%>
        <%--<input type="hidden" name="${_csrf.parameterId}" value="${_csrf.token}"/>--%>
        <%--</form>--%>

        <%--<h2><font color="white"> Welcome, ${pageContext.request.userPrincipal.name} |--%>
        <%--<button onclick="document.forms['logoutForm'].submit()">Logout</button>--%>
        <%--</font></h2>--%>
        <%--</c:if>--%>


        <%--<c:choose>--%>
        <%--<c:when test="${userRole=='user'}">--%>
        <%--<script>--%>
        <%--$(document).ready(function () {--%>
        <%--$("#two").click(function () {--%>
        <%--$("p").hide(1000);--%>
        <%--alert("This button is for Admin only! You are not admin!");--%>
        <%--});--%>
        <%--});--%>
        <%--</script>--%>
        <%--<a class="navbar-brand">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>--%>
        <%--<form action="#">--%>
        <%--<button id="two" type="submit" class="btn btn-outline-primary btn-sm ">Only for ADMIN</button>--%>
        <%--</form>--%>
        <%--</c:when>--%>
        <%--<c:when test="${userRole=='admin'}">--%>
        <%--<script>--%>
        <%--$(document).ready(function (e) {--%>
        <%--e.defaultChecked--%>
        <%--$("#one").click(function () {--%>
        <%--$("p").hide(1000);--%>
        <%--alert("Access is allowed!");--%>
        <%--});--%>
        <%--});--%>
        <%--</script>--%>
        <%--<a class="navbar-brand">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>--%>
        <%--<form action="/users">--%>
        <%--<button id="one" type="submit" class="btn btn-outline-primary btn-sm ">Only for ADMIN</button>--%>
        <%--</form>--%>
        <%--</c:when>--%>
        <%--</c:choose>--%>

        <%--<div class="collapse navbar-collapse" id="navbarNav">--%>

        <%--<ul class="navbar-nav mr-auto">--%>

        <%--<li class="nav-item active">--%>
        <%--<a class="nav-link" href="#"><font color="white"> Home </font><span class="sr-only">(current)</span></a>--%>
        <%--</li>--%>
        <%--<li class="nav-item">--%>
        <%--<a class="nav-link" href="#"></a>--%>
        <%--</li>--%>
        <%--<li class="nav-item">--%>
        <%--<a class="nav-link" href="#">sdsdfsdsf</a>--%>
        <%--</li>--%>
        <%--<li class="nav-item">--%>
        <%--<a class="nav-link disabled" href="#">Disabled</a>--%>
        <%--</li>--%>
        <%--</ul>--%>
        <%--</div>--%>

        <form action="/test/search">
            <div align="right">
                <input type="text" class="input-medium search-query" name="name">
                <button type="submit" class="btn ">Search by name</button>
            </div>
        </form>
    </div>
</div>
<%--</nav>--%>
<div class="container">
    <div class="row">
        <div class="col-4">
            <form action="/test/add">
                <div class="form-group">
                    <label for="group" class="col-form-label-sm"><h3>Create new group:</h3></label>
                    <input type="ID" class="form-control col-form-label-sm" id="group" name="group"
                           placeholder="name group">
                </div>
                <input class=" btn btn-outline-primary btn-sm" type="submit" value="Add group">

                <c:if test="${not empty name}">
                    <h4><font color="red"> Неверно указано название ${name}!!!</font></h4>
                </c:if>
                <c:if test="${not empty message}">
                    <h4><font color="red">${message}!!!</font></h4>
                </c:if></form>
            <c:if test="${!empty groupsForUser}">
                <h3>Groups:</h3>
                <table class="table table-responsive-sm table-bordered">
                    <c:forEach items="${groupsForUser}" var="group">
                        <tr align="center">
                            <td>${group.nameGroup}</td>
                            <td><a href="<c:url value='/test/remove/${group.id}'/>">del</a></td>
                            <td><a href="<c:url value='/test/addInGroup/${group.id}'/>">+</a></td>
                            <td><a href="<c:url value='/test/getBookmarksFromOneGroup/${group.id}'/>">show</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
        <div class="col-sm-4">
            <c:if test="${!empty listBookmarks}">
            <h3> Print bookmarks group "${nameGroup}"</h3>
            <div class="table-responsive">
                <table class="table table-striped">
                    <tbody>
                    <c:forEach items="${listBookmarks}" var="bookmark">
                        <tr align="center">
                            <td><h4><a href="${bookmark.urlBookmark}">${bookmark.bookmark}</a></h4></td>&nbsp;
                            <td><h4><a href="<c:url value='/test/removeBookmark/${bookmark.id}'/>"><font
                                    color="#ff4324">del</font></a></h4></td>
                        </tr>
                        <tr align="center">
                            <td>${bookmark.description}</td>
                            <td></td>
                        </tr>
                        </tr>
                        <tr align="center">
                            <td>${bookmark.urlBookmark}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><hr></td><td><hr></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </c:if>
            </div>
        </div>
        <div class="col-sm-4">
            <c:if test="${!empty bookmarkForSearch}">
            <br>
            <h3> Searching results</h3>
            <div class="table-responsive">
                <table class="table table-striped">
                    <tbody>
                    <c:forEach items="${bookmarkForSearch}" var="bookmark">
                        <tr align="center">
                            <td><h4><a href="${bookmark.urlBookmark}">${bookmark.bookmark}</a></h4></td>&nbsp;
                        </tr>

                        <tr align="center">
                            <td>${bookmark.urlBookmark}</td>
                        </tr>
                        <tr align="center">
                            <td> ${bookmark.description}</td>
                        </tr>
                        <tr>
                            <td><br></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </c:if>
            </div>
        </div>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet"
              media="screen">
        <link href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/3.2.1/css/font-awesome.min.css" rel="stylesheet"
              media="screen">
        <link href="../nestednav.css" rel="stylesheet">
    </div>
</div>
</div>
</body>
</html>