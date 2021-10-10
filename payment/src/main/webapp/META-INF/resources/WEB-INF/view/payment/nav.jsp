<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: hashan
  Date: 8/1/19
  Time: 4:27 PM
  To change this template use File | Settings | File Templates.
--%>
<style>
  #payment-menu {
    padding-left: 12px;
  }
</style>
<spring:message code="lbl.nav.payment" var="lblPaymentNav"/>
<a id="payment-menu" href="/app/payment/home" data-toggle="tooltip" data-placement="top" title="${lblPaymentNav}">
  <i class="far fa-receipt"></i>
</a>