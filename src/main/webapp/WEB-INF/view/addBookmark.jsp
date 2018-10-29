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
    <title>Add Bookmark</title>
</head>
<body>
<h4>Add bookmark in group "${nameGroup1}":</h4>
<div class="col-5">
    <form action="/test/addBookmark">
        <div class="form-group">
            <label for="bookmark" class="col-form-label-sm"></label>
            <input type="ID" class="form-control col-form-label-sm" id="bookmark" name="bookmark"
                   placeholder="enter name_bookmark">
            <c:if test="${not empty name_bookmark}">
                <h5><font color="red"> Не заполено поле ${name_bookmark}!!!</font></h5>
            </c:if>

        </div>
        <div class="form-group">
            <label for="urlBookmark" class="col-form-label-sm"></label>
            <input type="ID" class="form-control col-form-label-sm" id="urlBookmark" name="urlBookmark"
                   placeholder="enter url_bookmark">
            <c:if test="${not empty url}">
                <h5><font color="red"> Не заполено поле ${url}!!!!</font></h5>
            </c:if>
        </div>
        <div class="form-group">
            <label for="description" class="col-form-label-sm"></label>
            <input type="ID" class="form-control col-form-label-sm" id="description" name="description"
                   placeholder="enter description_bookmark">
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
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/3.2.1/css/font-awesome.min.css" rel="stylesheet"
          media="screen">
    <link href="../nestednav.css" rel="stylesheet">
</div>

</body>
</html>
