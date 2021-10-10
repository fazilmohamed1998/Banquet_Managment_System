<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html enabled="${isHtmlCompressionEnabled}" compressJavaScript="true"
               compressCss="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <html>
    <head>
        <jsp:include page="../app/resource.jsp"/>
        <!-- Module CSS -->
        <link href="/static/food/css/food.css" rel="stylesheet"/>
    </head>
    <body>
    <div class="content">
        <div class="container-fluid">
            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#food-content" data-url="/app/food">
                        <spring:message code="lbl.tab.heading.food"/>
                    </a>
                </li>
                <li>
                    <a href="#food-menu-content" data-url="/app/food/menu">
                        <spring:message code="lbl.tab.heading.food.menu"/>
                    </a>
                </li>
            </ul>
            <div class="tab-content">
                <div class="tab-pane active" id="food-content"></div>
                <div class="tab-pane" id="food-menu-content"></div>
            </div>
        </div>
            <%-- This is food menu content</p>
                me div eka athule ui eka hadahan --%>
    </div>
    <jsp:include page="../app/nav.jsp"/>
    <!-- Module JS -->
    <script src="/static/food/js/food.js"></script>
    </body>
    </html>
</compress:html>