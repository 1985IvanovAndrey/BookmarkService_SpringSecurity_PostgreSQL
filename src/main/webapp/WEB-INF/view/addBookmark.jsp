<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
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
    <style>
        <%@include file="/resources/css/min.css"%>
    </style>
    <link href="/resources/css/starter-template.css" rel="stylesheet">
    <title>Add Bookmark</title>
</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
    <%--<a class="navbar-brand" href="#">Welcome Bookmark Service!!</a>--%>
    <%--<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">--%>
    <%--<span class="navbar-toggler-icon"></span>--%>
    <%--</button>--%>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterId}" value="${_csrf.token}"/>
        </form>

        <h4><font color="white"> Welcome ${pageContext.request.userPrincipal.name} |
            <button size="1" onclick="document.forms['logoutForm'].submit()">Logout</button>
        </font></h4>
    </c:if>

    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#"><span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="i.ua"></a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#"></a>
            </li>
        </ul>
    </div>
    <%--<div style="align-content: center">--%>
    <%--<form class="form-inline my-2 my-lg-0">--%>
    <%--<input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">--%>
    <%--<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>--%>
    <%--</form>--%>
    <%--</div>--%>
</nav>

<div class="container">
    <div class="row">
        <div class="col-3">

        </div>
        <div class="col-6">
            <h3>Add bookmark in group "${nameGroup1}":</h3>

            <form action="/test/addBookmark">
                <div class="form-group">
                    <label for="bookmark" class="col-form-label-sm"></label>
                    <input type="ID" class="form-control col-form-label-sm" id="bookmark" name="bookmark"
                           placeholder="name_bookmark">
                    <c:if test="${not empty name_bookmark}">
                        <h5><font color="red"> Не заполено поле ${name_bookmark}!!!</font></h5>
                    </c:if>

                </div>
                <div class="form-group">
                    <label for="urlBookmark" class="col-form-label-sm"></label>
                    <input type="ID" class="form-control col-form-label-sm" id="urlBookmark" name="urlBookmark"
                           placeholder="url_bookmark">
                    <c:if test="${not empty url}">
                        <h5><font color="red"> Не заполено поле ${url}!!!!</font></h5>
                    </c:if>
                </div>
                <div class="form-group">
                    <label for="description" class="col-form-label-sm"></label>
                    <%--<input type="ID" class="form-control" id="description"  name="description"--%>
                           <%--placeholder="enter description_bookmark">--%>
                    <textarea class="form-control" rows="3" id="description"
                              name="description" placeholder="description"></textarea>
                    <c:if test="${not empty description}">
                        <h5><font color="red">Не заполено поле ${description}!!!</font></h5>
                    </c:if>
                </div>
                <input class=" btn btn-outline-primary btn-sm" type="submit" value="Add bookmark">
            </form>
            <c:if test="${not empty addBookmark}">
                <h4><font color="#ff4324">${addBookmark}</font></h4>
            </c:if>
            <form action="/test/getHome">
                <input class=" btn btn-outline-primary btn-sm" type="submit" value="Home">
            </form>
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
