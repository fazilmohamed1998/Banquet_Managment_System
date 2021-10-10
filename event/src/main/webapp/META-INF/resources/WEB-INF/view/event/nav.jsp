<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: hashan
  Date: 8/1/19
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
  #event-menu {
    padding-left: 12px;
  }
</style>
<spring:message code="lbl.nav.event" var="lblEventNav"/>
<a id="event-menu" href="/app/event/home" data-toggle="tooltip" data-placement="top" title="${lblEventNav}">
  <i class="far fa-calendar-alt"></i>
</a>
