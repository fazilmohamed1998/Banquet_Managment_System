<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: hashan
  Date: 8/1/19
  Time: 1:18 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
  #dashboard-menu {
    padding-top: 9px;
  }
</style>
<spring:message code="lbl.nav.dashboard" var="lblDashboardNav"/>
<div class="user-logout">
  <div class="profile">
  </div>
  <ul class="dropdown">
    <li class="user">${loggedEntity}</li>
    <li><a href="/app/auth/logout"><span><i class="icon-envelope icon-large"></i><spring:message
            code="lbl.logout"/></span></a></li>
  </ul>
</div>
<div class="navbar">
  <%--<sec:authorize access="hasRole('ROLE_BANQUET_HALL_MGMT')">
    <a id="dashboard-menu" href="/app/dashboard" data-toggle="tooltip" data-placement="top"
       title="${lblDashboardNav}">
      <i class="far fa-tachometer-alt"></i>
    </a>
  </sec:authorize>--%>
  <sec:authorize access="hasRole('ROLE_CUSTOMER_MGMT')">
    <c:catch var="e">
      <jsp:include page="../customer/nav.jsp"/>
    </c:catch>
  </sec:authorize>
  <sec:authorize access="hasRole('ROLE_EVENT_MGMT')">
    <c:catch var="e">
      <jsp:include page="../event/nav.jsp"/>
    </c:catch>
  </sec:authorize>
  <sec:authorize access="hasRole('ROLE_RESERVATION')">
    <c:catch var="e">
      <jsp:include page="../reservation/nav.jsp"/>
    </c:catch>
  </sec:authorize>
  <sec:authorize access="hasRole('ROLE_PAYMENT')">
    <c:catch var="e">
      <jsp:include page="../payment/nav.jsp"/>
    </c:catch>
  </sec:authorize>
  <sec:authorize access="hasRole('ROLE_FOOD_MGMT')">
    <c:catch var="e">
      <jsp:include page="../food/nav.jsp"/>
    </c:catch>
  </sec:authorize>
  <sec:authorize access="hasRole('ROLE_BANQUET_HALL_MGMT')">
    <c:catch var="e">
      <jsp:include page="../banquethall/nav.jsp"/>
    </c:catch>
  </sec:authorize>
  <sec:authorize access="hasRole('ROLE_RESTAURANT_MGMT')">
    <c:catch var="e">
      <jsp:include page="../restaurant/nav.jsp"/>
    </c:catch>
  </sec:authorize>
  <sec:authorize access="hasRole('ROLE_USER_MGMT')">
    <c:catch var="e">
      <jsp:include page="../user/nav.jsp"/>
    </c:catch>
  </sec:authorize>
</div>
<script>
    function dropDown(el) {
        this.dd = el;
        this.initEvents();
    }

    dropDown.prototype = {
        initEvents: function () {
            var obj = this;
            obj.dd.on('click', function (event) {
                $(this).toggleClass('active');
                event.stopPropagation();
            });
        }
    };

    $(function () {
        new dropDown($('.profile'));
        $(document).click(function () {
            $('.profile').removeClass('active');
        });
    });
</script>