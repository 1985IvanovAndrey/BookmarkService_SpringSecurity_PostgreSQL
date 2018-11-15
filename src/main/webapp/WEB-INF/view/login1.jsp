<%--<c:set var="contextPath" value="${pageContext.request.contextPath}"/>--%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Bookmark Service</title>
    <style>
        <%@include file="/resources/css/style.css"%>
    </style>
</head>
<body>
<div class="container">
    <section id="content">
        <form method="POST" action="${contextPath}/login1" class="form-signin">
            <h1>Welcome to the Bookmark Service</h1>
            <%--<div>--%>
            <div class="form-group ${error != null ? 'has-error' : ''}">
                <span>${message}</span>
                <input name="username" type="email" class="form-control" placeholder="Email" required=""
                       autofocus="true"/>
            </div>
            <div>
                <input name="password" type="password" class="form-control" placeholder="Password" required=""/>
                <br>
                <span>${error}</span>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>
            <a>
                <input type="submit" value="Log in"/>
                <%--<input type="submit">Log In</input>--%>

                <%--<a href="#">Lost your password?</a>--%>
                <%--<input type="submit"<a href="/registration" value="Register"/></a>--%>
                <%--<a href="/registration">Register</a>--%>
                <h2 input type="submit"><a href="/registration">Register</a></h2>
</div>
</form><!-- form -->

</section><!-- content -->
</body>
</html>
