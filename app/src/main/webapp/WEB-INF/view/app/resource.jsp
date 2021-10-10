<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: HaShaN
  Date: 6/29/2019
  Time: 11:07 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- favicon -->
<link type="image/x-icon" href="/static/app/img/favicon.ico" rel="shortcut icon">
<title><spring:eval expression="@modulePropertyBean['app.name']"/></title>
<!-- Metas -->
<meta name="viewport"
      content="width=device-width, initial-scale=1.00, minimum-scale=1.00, maximum-scale=1.00, user-scalable=no">
<!-- CSS-->
<!-- Bootstrap 4 -->
<link href="/static/app/css/bootstrap/bootstrap.min.css" rel="stylesheet"/>
<!--Google Font-->
<link href="/static/app/font/googlefont/all.css?family=Poppins:200,300,400,600,700,800"
      rel="stylesheet"/>
<!-- FontAwesome Icons -->
<link href="/static/app/font/fontawesome/all.css" rel="stylesheet"/>
<!-- Bootstrap Table -->
<link href="/static/app/css/bootstrap-table/bootstrap-table.min.css" rel="stylesheet"/>
<!-- Select Picker, full documentation here: http://silviomoreto.github .io/bootstrap-select -->
<link href="/static/app/css/select-picker/bootstrap-select.css" rel="stylesheet"/>
<!-- DateTime Picker, full documentation here: http://eonasdan.github.io/bootstrap-datetimepicker -->
<link href="/static/app/css/datetime-picker/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
<!-- InputMask CSS -->
<link href="/static/app/css/input-mask/inputmask.css" rel="stylesheet"/>
<!-- Parsley Validator CSS -->
<link href="/static/app/css/parsley-validator/parsley.css" rel="stylesheet"/>
<!-- PrintJs CSS -->
<link href="/static/app/css/printjs/print.min.css" rel="stylesheet"/>
<!-- Common CSS -->
<link href="/static/common/css/common.css" rel="stylesheet"/>
<!-- App CSS -->
<link href="/static/app/css/app.css" rel="stylesheet"/>

<!--JS-->
<!-- JQuery 3.3.1 -->
<script src="/static/app/js/jquery/jquery-3.4.1.min.js"></script>
<script src="/static/app/js/popper.min.js"></script>
<!-- Bootstrap 4 -->
<script src="/static/app/js/bootstrap/bootstrap.min.js"></script>
<!-- Bootstrap Table -->
<script src="/static/app/js/bootstrap-table/bootstrap-table.min.js"></script>
<!-- Select Picker, full documentation here: http://silviomoreto.github .io/bootstrap-select -->
<script src="/static/app/js/select-picker/bootstrap-select.js"></script>
<!-- Moment JS, full documentation here: https://momentjs.com/docs/ -->
<script src="/static/app/js/moment/moment.min.js"></script>
<!-- DateTime Picker, full documentation here: http://eonasdan.github.io/bootstrap-datetimepicker -->
<script src="/static/app/js/datetime-picker/bootstrap-datetimepicker.min.js"></script>
<!-- Notifications Plugin -->
<script src="/static/app/js/bootstrap-notify/bootstrap-notify.js"></script>
<!-- InputMask JS -->
<script src="/static/app/js/input-mask/jquery.inputmask.min.js"></script>
<!-- Parsley Validator JS -->
<script src="/static/app/js/parsley-validator/parsley.min.js"></script>
<!-- PrintJs JS -->
<script src="/static/app/js/printjs/print.min.js"></script>
<!-- Common JS -->
<script src="/static/common/js/common.js"></script>
<!-- App JS -->
<script src="/static/app/js/app.js"></script>

<!-- Common Modals -->
<jsp:include page="../common/modal/progress.jsp"/>
<jsp:include page="../common/modal/confirm.jsp"/>
