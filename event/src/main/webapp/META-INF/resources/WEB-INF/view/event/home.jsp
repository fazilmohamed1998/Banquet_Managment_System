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
                <link href="/static/event/css/event.css" rel="stylesheet"/>
            </head>
            <body>

            <h2>Event Management</h2>

            <h1><a href  ="add"> Add Event</a></h1>


                <form method = "get" action = "search">
                    <input type = "text" name = "keyboard"/>
                    <input type = "submit" value = "search">
                </form>

                <table border="1" cellpadding="5px">
                   <tr>
                       <th>Id</th>
                       <th>Customer</th>
                       <th>Event</th>
                       <th>Description</th>
                       <th>Location</th>
                       <th>From</th>
                       <th>To</th>
                       <th>Status</th>
                       <th>Action</th>

                    </tr>

                    <c:forEach items="${listEvent}" var="event">
                    <tr>
                        <td>${event.id}</td>
                        <td>${event.customer.name}</td>
                        <td>${event.eventType.type}</td>
                        <td>${event.description}</td>
                        <td>${event.location.name}</td>
                        <td>${event.from}</td>
                        <td>${event.to}</td>
                        <td>${event.status.name}</td>
                        <td>
                            <a href="edit?id=${event.id}">Edit</a>
                            &nbsp; &nbsp; &nbsp;
                            <a href="delete?id=${event.id}">delete</a>
                        </td>
                    </tr>
                    </c:forEach>

                 </table>






    <jsp:include page="../app/nav.jsp"/>
    <script type="text/javascript">
        var colId = '<spring:message code="lbl.event.table.col.id"/>';
        var colCust= '<spring:message code="lbl.event.table.col.customer"/>';
        var colEvent = '<spring:message code="lbl.event.table.col.event"/>';
        var colDescription = '<spring:message code="lbl.event.table.col.Description"/>';
        var colLocation = '<spring:message code="lbl.event.table.col.Location"/>';
        var colFrom = '<spring:message code="lbl.event.table.col.From"/>';
        var colTo = '<spring:message code="lbl.event.table.col.To"/>';
        var colStatus = '<spring:message code="lbl.event.table.col.Status"/>';
        var colactions = '<spring:message code="lbl.event.table.col.actions"/>';
    </script>

    <!-- Module JS -->
    <script src="/static/event/js/event.js"></script>
    </body>
    </html>
</compress:html>