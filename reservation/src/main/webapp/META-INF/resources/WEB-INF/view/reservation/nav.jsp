<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: hashan
  Date: 8/1/19
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
  #reservation-menu {
    padding-left: 12px;
  }
</style>
<spring:message code="lbl.nav.reservation" var="lblReservationNav"/>
<a id="reservation-menu" href="/app/reservation/home" data-toggle="tooltip" data-placement="top"
   title="${lblReservationNav}">
  <i class="far fa-calendar-check"></i>
</a>