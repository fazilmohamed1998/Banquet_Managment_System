<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: hashan
  Date: 8/1/19
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
  #banquethall-menu {
    padding-left: 9px;
  }
</style>
<spring:message code="lbl.nav.banquet.hall" var="lblBanquetHallNav"/>
<a id="banquethall-menu" href="/app/banquet-hall/home" data-toggle="tooltip" data-placement="top"
   title="${lblBanquetHallNav}">
  <i class="far fa-hotel"></i>
</a>