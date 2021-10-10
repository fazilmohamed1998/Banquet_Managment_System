<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html enabled="${isHtmlCompressionEnabled}" compressJavaScript="true"
               compressCss="true">
    <%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

    <spring:message code="lbl.reservation.table.col.id" scope="page" var="colId"/>
    <spring:message code="lbl.reservation.table.col.customer" scope="page" var="colCust"/>
    <spring:message code="lbl.reservation.table.col.reservation.no" scope="page" var="colReservatioNo"/>
    <spring:message code="lbl.reservation.table.col.event" scope="page" var="colEvent"/>
    <spring:message code="lbl.reservation.table.col.reserved.on" scope="page" var="colResOn"/>
    <spring:message code="lbl.reservation.table.col.reserved.by" scope="page" var="colResBy"/>
    <spring:message code="lbl.reservation.table.col.status" scope="page" var="colStatus"/>
    <spring:message code="lbl.reservation.table.col.type" scope="page" var="colType"/>
    <spring:message code="lbl.reservation.table.col.actions" scope="page" var="colActions"/>
    <spring:message code="lbl.reservation.table.action.print" scope="page" var="actionPrint"/>
    <spring:message code="lbl.reservation.table.action.modify" scope="page" var="lblModify"/>
    <spring:message code="lbl.reservation.table.action.cancel" scope="page" var="lblCancel"/>

    <spring:message code="lbl.reservation.modal.modify.field.reference" scope="page" var="fldRef"/>
    <spring:message code="lbl.reservation.modal.modify.field.customer" scope="page" var="fldCust"/>
    <spring:message code="lbl.reservation.modal.modify.field.event" scope="page" var="fldEvent"/>
    <spring:message code="lbl.reservation.modal.modify.field.location" scope="page" var="fldLocation"/>
    <spring:message code="lbl.reservation.modal.modify.field.from" scope="page" var="fldFrom"/>
    <spring:message code="lbl.reservation.modal.modify.field.to" scope="page" var="fldTo"/>
    <spring:message code="lbl.reservation.modal.modify.field.description" scope="page" var="fldDescription"/>
    <spring:message code="lbl.reservation.modal.modify.field.reserved.on" scope="page" var="fldResOn"/>
    <spring:message code="lbl.reservation.modal.modify.field.status" scope="page" var="fldStatus"/>
    <spring:message code="lbl.reservation.modal.modify.field.type" scope="page" var="fldType"/>
    <spring:message code="lbl.reservation.modal.make.field.customer" scope="page" var="fldCustomer"/>
    <spring:message code="lbl.reservation.modal.make.field.event" scope="page" var="fldEvent"/>
    <spring:message code="lbl.reservation.modal.make.field.type" scope="page" var="fldType"/>
    <spring:message code="lbl.reservation.modal.make.field.reserved.by" scope="page" var="fldReservedBy"/>
    <spring:message code="lbl.reservation.select.customer" scope="page" var="lblSelCustomer"/>
    <spring:message code="lbl.reservation.select.event" scope="page" var="lblSelEvent"/>
    <spring:message code="lbl.reservation.select.location" scope="page" var="lblSelLocation"/>
    <spring:message code="lbl.reservation.select.type" scope="page" var="lblSelType"/>
    <spring:message code="lbl.reservation.select.reserved.by" scope="page" var="lblSelReservedBy"/>
    <spring:message code="lbl.reservation.button.make.payment" scope="page" var="lblBtnMakeReservation"/>

    <spring:message code="err.common.to.date" scope="page" var="errTo"/>
    <spring:message code="err.common.from.date" scope="page" var="errFrom"/>
    <spring:message code="lbl.common.cancel" scope="page" var="lblCancel"/>
    <spring:message code="lbl.common.total" scope="page" var="lblTotal"/>
    <spring:message code="lbl.common.proceed" scope="page" var="lblProceed"/>
    <spring:message code="lbl.common.reports" scope="page" var="lblReport"/>
    <spring:message code="lbl.common.generate" scope="page" var="lblGenerate"/>

    <spring:message code="err.reservation.method.required" scope="page" var="errResMethodReq"/>
    <spring:message code="err.reservation.status.required" scope="page" var="errStatusReq"/>
    <spring:message code="err.reservation.customer.required" scope="page" var="errCustomerReq"/>
    <spring:message code="err.reservation.event.required" scope="page" var="errEventReq"/>
    <spring:message code="err.reservation.location.required" scope="page" var="errLocationReq"/>
    <spring:message code="err.reservation.type.required" scope="page" var="errTypeReq"/>
    <spring:message code="err.reservation.reserved.by.required" scope="page" var="errReservedByReq"/>

    <spring:message code="conf.reservation.cancel" scope="page" var="confCancel"/>
    <spring:message code="conf.reservation.cancel.action.cancel" scope="page" var="confActionCancel"/>

    <spring:message code="lbl.reservation.success" scope="page" var="lblReservationSuccess"/>
    <spring:message code="lbl.reservation.report.success" scope="page" var="lblReservationReportSuccess"/>
    <spring:message code="lbl.reservation.update.success" scope="page" var="lblReservationUpdateSuccess"/>
    <spring:message code="lbl.reservation.refund.success" scope="page" var="lblReservationRefundSuccess"/>

    <html>
    <head>
        <jsp:include page="../app/resource.jsp"/>
        <!-- Module CSS -->
        <link href="/static/reservation/css/reservation.css" rel="stylesheet"/>
    </head>
    <body>
    <div class="reservation-actions">
        <button type="button" class="btn btn-sm btn-primary btn-round" data-toggle="modal"
                data-target="#makeReservationModal"
                data-backdrop="static">
                ${lblBtnMakeReservation}
        </button>
        <button type="button" class="btn btn-primary btn-round btn-sm" data-toggle="modal"
                data-target="#reservationReportModal"
                data-backdrop="static">
            <i class="far fa-analytics"></i>
                ${lblReport}
        </button>
    </div>
    <div class="content">
        <table id="reservation-tbl"
               data-toolbar="#toolbar"
               data-search="true"
               data-show-refresh="true"
               data-show-fullscreen="true"
               data-show-export="true"
               data-buttons-class="primary btn-lg"
               data-show-search-button="true"
               data-minimum-count-columns="2"
               data-show-pagination-switch="true"
               data-row-style="rowFormatter"
               data-show-search-clear-button="true"
               data-pagination="true"
               data-id-field="id"
               data-unique-id="id"
               data-page-list="[10, 15, 25, 50, all]"
               data-page-size="10"
               data-show-footer="true"
               data-side-pagination="server"
               data-url="/app/reservation">
        </table>
        <div id="modifyReservationModal" class="modal fade" tabindex="-1" role="dialog"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-400px" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">
                            <spring:message code="lbl.reservation.modal.title.modify"/>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form:form method="POST" action="/app/reservation/" modelAttribute="reservation"
                               data-parsley-validate=""
                               novalidate="">
                        <input id="id" type="hidden" name="id">
                        <div class="modal-body">
                            <div class="reservation-detail">
                                <div class="customer">
                                    <div class="font-weight-bold">${fldCust}</div>
                                    <div id="customer">.....</div>
                                </div>
                                <div class="event">
                                    <div class="font-weight-bold text-right">${fldEvent}</div>
                                    <div id="event" class="text-right">.....</div>
                                </div>
                                <div class="reserved-on">
                                    <div class="font-weight-bold text-right">${fldResOn}</div>
                                    <div id="reservedOn" class="text-right">.....</div>
                                </div>
                                <div class="type">
                                    <div class="font-weight-bold">${fldType}</div>
                                    <div id="type">.....</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="font-weight-bold" for="from">${fldFrom}</label>
                                <div class="form-group">
                                    <input id="from1" type="text" name="event.from" class="datepicker form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="font-weight-bold" for="To">${fldTo}</label>
                                <div class="form-group">
                                    <input id="To1" type="text" name="event.to" class="datepicker form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="status">${fldStatus}</label>
                                <select id="status" name="status.id" class="selectpicker form-control"
                                        data-hide-disabled="true"
                                        data-live-search="true" title=""
                                        data-parsley-trigger="change focusin focusout input"
                                        data-parsley-error-message="${errStatusReq}" data-parsley-required>
                                    <c:forEach items="${statuses}" var="status">
                                        <option value="${status.value}">${status.text}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button id="modify" type="button" class="btn btn-primary btn-round btn-sm">${lblModify}
                            </button>
                            <button type="reset" class="btn btn-secondary btn-round btn-sm"
                                    data-dismiss="modal">${lblCancel}
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>

        <div id="cancelReservationModal" class="modal fade" tabindex="-1" role="dialog"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">
                            <spring:message code="lbl.reservation.modal.title.cancel"/>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form:form action="/app/reservation/">
                        <input type="hidden" id="idCancel">
                        <div class="modal-body">
                                ${confCancel}
                        </div>

                        <div class="modal-footer">
                            <button id="confirm" type="button"
                                    class="btn btn-danger btn-round btn-sm">${confActionCancel}
                            </button>
                            <button type="reset" class="btn btn-secondary btn-round btn-sm"
                                    data-dismiss="modal">${lblCancel}
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>

        <div id="makeReservationModal" class="modal fade" tabindex="-1" role="dialog"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-md" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">
                            <spring:message code="lbl.reservation.modal.title.make.reservation"/>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <form:form method="POST" action="/app/reservation/" modelAttribute="reservation"
                               data-parsley-validate=""
                               novalidate="">
                        <input id="reference" type="hidden" name="reservationNo">
                        <div class="modal-body">
                            <div class="reservation-detail">
                                <div class="reference">
                                    <div class="font-weight-bold">${fldRef}</div>
                                    <div id="refMkReservation">.....</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="font-weight-bold" for="customers">${fldCustomer}</label>
                                <select id="customers" name="customer.id" class="selectpicker form-control"
                                        data-hide-disabled="true"
                                        data-live-search="true" title=""
                                        data-parsley-trigger="change focusin focusout input"
                                        data-parsley-error-message="${errCustomerReq}"
                                        data-parsley-errors-container="#customers-error-container"
                                        data-parsley-required>
                                    <option disabled selected>${lblSelCustomer}</option>
                                    <c:forEach items="${customers}" var="customers">
                                        <option value="${customers.value}">${customers.text}</option>
                                    </c:forEach>
                                </select>
                                <div id="customers-error-container"></div>
                            </div>
                            <div class="form-group">
                                <label class="font-weight-bold" for="events">${fldEvent}</label>
                                <select id="events" name="event.eventType.id" class="selectpicker form-control"
                                        data-hide-disabled="true"
                                        data-live-search="true" title=""
                                        data-parsley-trigger="change focusin focusout input"
                                        data-parsley-error-message="${errEventReq}"
                                        data-parsley-errors-container="#events-error-container" data-parsley-required>
                                    <option disabled selected>${lblSelEvent}</option>
                                    <c:forEach items="${events}" var="events">
                                        <option value="${events.value}">${events.text}</option>
                                    </c:forEach>
                                </select>
                                <div id="events-error-container"></div>
                            </div>
                            <div class="form-group">
                                <label class="font-weight-bold" for="events">${fldLocation}</label>
                                <select id="locations" name="event.location.id" class="selectpicker form-control"
                                        data-hide-disabled="true"
                                        data-live-search="true" title=""
                                        data-parsley-trigger="change focusin focusout input"
                                        data-parsley-error-message="${errLocationReq}"
                                        data-parsley-errors-container="#locations-error-container"
                                        data-parsley-required>
                                    <option disabled selected>${lblSelLocation}</option>
                                    <c:forEach items="${locations}" var="location">
                                        <option value="${location.value}">${location.text}</option>
                                    </c:forEach>
                                </select>
                                <div id="locations-error-container"></div>
                            </div>
                            <div class="form-group">
                                <label class="font-weight-bold" for="from">${fldFrom}</label>
                                <div class="form-group">
                                    <input id="from" type="text" name="event.from" class="datepicker form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="font-weight-bold" for="To">${fldTo}</label>
                                <div class="form-group">
                                    <input id="To" type="text" name="event.to" class="datepicker form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="font-weight-bold" for="desc">${fldDescription}</label>
                                <textarea name="event.description" id="desc" class="form-control"></textarea>
                            </div>
                                <%--<div class="form-group">
                                    <label class="font-weight-bold" for="types">${fldType}</label>
                                    <select id="types" name="type.id" class="selectpicker form-control"
                                            data-hide-disabled="true"
                                            data-live-search="true" title="" data-parsley-trigger="change focusin focusout input"
                                            data-parsley-error-message="${errTypeReq}"
                                            data-parsley-errors-container="#types-error-container" data-parsley-required>
                                        <option disabled selected>${lblSelType}</option>
                                        <c:forEach items="${types}" var="types">
                                            <option value="${types.value}">${types.text}</option>
                                        </c:forEach>
                                    </select>
                                    <div id="types-error-container"></div>
                                </div>--%>
                        </div>
                        <div class="modal-footer">
                            <button id="proceed" type="button" class="btn btn-success btn-round btn-sm">${lblProceed}
                            </button>
                            <button type="reset" class="btn btn-secondary btn-round btn-sm"
                                    data-dismiss="modal">${lblCancel}
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>

        <div id="reservationReportModal" class="modal fade" tabindex="-1" role="dialog"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">
                            <spring:message code="lbl.reservation.modal.title.reservation.reports"/>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal"
                                aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <form:form method="GET" action="/app/reservation/report" modelAttribute="criteria"
                               data-parsley-validate=""
                               novalidate="">
                        <div class="modal-body">
                            <div class="form-group">
                                <label class="font-weight-bold" for="from3">${fldFrom}</label>
                                <input id="from3" name="from" type="text" class="form-control"
                                       data-parsley-trigger="dp.change"
                                       data-parsley-datelte-message="${errFrom}"
                                       data-parsley-datelte="#To2"/>
                            </div>
                            <div class="form-group">
                                <label class="font-weight-bold" for="To2">${fldTo}</label>
                                <input id="To2" name="to" type="text" class="form-control"
                                       data-parsley-trigger="dp.change"
                                       data-parsley-dategte-message="${errTo}"
                                       data-parsley-dategte="#from3"/>
                            </div>
                            <!--<div class="form-group">
                                <span class="mandatory-warn">*These fields are mandatory</span>
                            </div>-->
                        </div>

                        <div class="modal-footer">
                            <button id="generate" type="submit" class="btn btn-primary btn-round btn-sm">
                                    ${lblGenerate}
                            </button>
                            <button type="reset" class="btn btn-danger btn-round btn-sm"
                                    data-dismiss="modal">${lblCancel}
                            </button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>


    </div>


    <jsp:include page="../app/nav.jsp"/>
    <script type="text/javascript">
        var colId = '${colId}';
        var colCust = '${colCust}';
        var colReservatioNo = '${colReservatioNo}';
        var colEvent = '${colEvent}';
        var colResOn = '${colResOn}';
        var colResBy = '${colResBy}';
        var colStatus = '${colStatus}';
        var colType = '${colType}';
        var colActions = '${colActions}';

        var actionEdit = '${lblModify}';
        var actionCancel = '${lblCancel}';
        var actionPrint = '${actionPrint}';

        var lblConfCancel = '${confCancel}';

        var lblReservationSuccess = "${lblReservationSuccess}";
        var lblReservationReportSuccess = "${lblReservationReportSuccess}";
        var lblReservationUpdateSuccess = "${lblReservationUpdateSuccess}";
        var lblReservationCancelSuccess = "${lblReservationRefundSuccess}";
    </script>
    <!-- Module JS -->
    <script src="/static/reservation/js/reservation.js"></script>
    </body>
    </html>
</compress:html>