<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: hashan
  Date: 8/1/19
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
  #restaurant-menu {
    padding-left: 10px;
  }
</style>
<spring:message code="lbl.nav.restaurant" var="lblRestaurantNav"/>
<a id="restaurant-menu" href="/app/restaurant/home" data-toggle="tooltip" data-placement="top"
   title="${lblRestaurantNav}">
  <i class="far fa-utensils-alt"></i>
</a>