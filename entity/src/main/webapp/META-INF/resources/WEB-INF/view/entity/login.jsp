<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hashan
  Date: 8/1/19
  Time: 4:55 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
  <jsp:include page="../app/resource.jsp"/>
  <!-- Module CSS -->
  <link href="/static/entity/css/entity.css" rel="stylesheet"/>
  <!-- Module JS -->
  <script src="/static/entity/js/entity.js"></script>
</head>
<body>
<spring:message code="lbl.entity.login.field.username" var="lblUsername" scope="page"/>
<spring:message code="lbl.entity.login.field.password" var="lblPassword" scope="page"/>
<spring:message code="lbl.entity.login.button.sign.in" var="lblSignInBtn" scope="page"/>
<spring:message code="lbl.entity.login.forgot.password" var="lblForgotPass" scope="page"/>
<spring:message code="lbl.entity.login.keep.logged.in" var="lblKeepLoggedIn" scope="page"/>
<form class="login-form hover-shadow" action="/app/auth/login" method="post">
  <div class="bms-logo">
  </div>
  <div class="form-group">
    <input type="text" id="username" name="username" class="form-control"
           placeholder="${lblUsername}" autofocus>
  </div>
  <div class="form-group">
    <input type="password" id="password" name="password" class="form-control"
           placeholder="${lblPassword}">
  </div>
  <div class="form-group">
    <button class="btn btn-md btn-primary btn-block" type="submit">${lblSignInBtn}</button>
  </div>
  <c:choose>
    <c:when test="${not empty error}">
      <label class="msg error">${error}</label>
    </c:when>
    <c:when test="${not empty msg}">
      <label class="msg success">${msg}</label>
    </c:when>
  </c:choose>
  <div class="form-group text-center">
    <div class="custom-control custom-control-inline custom-checkbox">
      <input type="checkbox" class="custom-control-input" id="remember-me">
      <label class="custom-control-label pt-2" for="remember-me">${lblKeepLoggedIn}</label>
    </div>
  </div>
  <div class="text-center">
    <a href="#" class="link">${lblForgotPass}</a>
  </div>
</form>
</body>
</html>