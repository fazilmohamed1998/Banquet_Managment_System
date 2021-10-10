<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html enabled="${isHtmlCompressionEnabled}" compressJavaScript="true"
               compressCss="true">
  <%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

  <spring:message code="lbl.payment.table.col.id" scope="page" var="colId"/>
  <spring:message code="lbl.payment.table.col.reference" scope="page" var="colRef"/>
  <spring:message code="lbl.payment.table.col.customer" scope="page" var="colCust"/>
  <spring:message code="lbl.payment.table.col.amount" scope="page" var="colAmt"/>
  <spring:message code="lbl.payment.table.col.paid.on" scope="page" var="colPayOn"/>
  <spring:message code="lbl.payment.table.col.pay.method" scope="page" var="colPayMthd"/>
  <spring:message code="lbl.payment.table.col.status" scope="page" var="colSts"/>
  <spring:message code="lbl.payment.table.col.accept.by" scope="page" var="colAcptBy"/>
  <spring:message code="lbl.payment.table.col.actions" scope="page" var="colActions"/>
  <spring:message code="lbl.payment.table.action.print" scope="page" var="actionPrint"/>
  <spring:message code="lbl.payment.table.action.resend" scope="page" var="actionResend"/>
  <spring:message code="lbl.payment.table.action.modify" scope="page" var="lblModify"/>
  <spring:message code="lbl.payment.table.action.refund" scope="page" var="lblRefund"/>
  <spring:message code="lbl.payment.table.action.info" scope="page" var="lblInfo"/>
  <spring:message code="lbl.payment.refund.info" scope="page" var="lblRefundInfo"/>
  <spring:message code="lbl.payment.refund.info.refund.by" scope="page" var="lblRefundBy"/>
  <spring:message code="lbl.payment.refund.info.refund.on" scope="page" var="lblRefundOn"/>

  <spring:message code="lbl.payment.modal.modify.field.reference" scope="page" var="fldRef"/>
  <spring:message code="lbl.payment.modal.modify.field.paid.on" scope="page" var="fldPayOn"/>
  <spring:message code="lbl.payment.modal.modify.field.pay.method" scope="page" var="fldPayMethod"/>
  <spring:message code="lbl.payment.modal.modify.field.amount" scope="page" var="fldAmount"/>
  <spring:message code="lbl.payment.modal.make.field.reservation" scope="page" var="fldReserv"/>
  <spring:message code="lbl.payment.button.make.payment" scope="page" var="lblBtnMakePay"/>
  <spring:message code="lbl.payment.select.reservation" scope="page" var="lblSelReserv"/>
  <spring:message code="lbl.payment.select.payment.method" scope="page" var="lblSelPayMethod"/>
  <spring:message code="lbl.payment.select.status" scope="page" var="lblSelStatus"/>

  <spring:message code="lbl.common.cancel" scope="page" var="lblCancel"/>
  <spring:message code="lbl.common.total" scope="page" var="lblTotal"/>
  <spring:message code="lbl.common.proceed" scope="page" var="lblProceed"/>
  <spring:message code="lbl.common.reports" scope="page" var="lblReport"/>
  <spring:message code="lbl.common.from" scope="page" var="lblFrom"/>
  <spring:message code="lbl.common.to" scope="page" var="lblTo"/>
  <spring:message code="lbl.common.generate" scope="page" var="lblGenerate"/>
  <spring:message code="lbl.common.email" scope="page" var="lblEmail"/>
  <spring:message code="err.common.from.date" scope="page" var="errFrom"/>
  <spring:message code="err.common.to.date" scope="page" var="errTo"/>

  <spring:message code="err.payment.method.required" scope="page" var="errPayMethodReq"/>
  <spring:message code="err.payment.amount.required" scope="page" var="errAmountReq"/>
  <spring:message code="err.payment.receipt.resend.email.required" scope="page" var="errEmailReq"/>
  <spring:message code="err.payment.amount.invalid" scope="page" var="errAmountInvalid"/>
  <spring:message code="err.payment.receipt.resend.email.invalid" scope="page" var="errEmailInvalid"/>
  <spring:message code="err.payment.reservation.required" scope="page" var="errReservationReq"/>

  <spring:message code="conf.payment.refund" scope="page" var="confRefund"/>
  <spring:message code="conf.payment.refund.action.refund" scope="page" var="confActionRefund"/>

  <spring:message code="lbl.payment.success" scope="page" var="lblPaySuccess"/>
  <spring:message code="lbl.payment.update.success" scope="page" var="lblPayUpdateSuccess"/>
  <spring:message code="lbl.payment.refund.success" scope="page" var="lblPayRefundSuccess"/>
  <spring:message code="lbl.payment.receipt.resend.success" scope="page" var="lblReceiptResendSuccess"/>
  <spring:message code="lbl.common.demo.data.added.success" scope="page" var="lblDemoDataAddedSuccess"/>
  <html>
  <head>
    <jsp:include page="../app/resource.jsp"/>
    <!-- Module CSS -->
    <link href="/static/payment/css/payment.css" rel="stylesheet"/>
    <c:if test="${isDemoModeEnabled}">
      <style>
        #makePaymentModal .reference {
          width: 50%;
          float: left;
        }

        #makePaymentModal .demo {
          width: 50%;
          float: right;
          padding: 5px 0px;
        }

        #makePaymentModal .demo button {
          text-transform: uppercase;
        }
      </style>
    </c:if>
  </head>
  <body>
  <div class="content">
    <div class="payment-actions">
      <button type="button" class="btn btn-sm btn-primary btn-round" data-toggle="modal" data-target="#makePaymentModal"
              data-backdrop="static">
        <i class="fad fa-dollar-sign"></i>
          ${lblBtnMakePay}
      </button>
      <button type="button" class="btn btn-sm btn-primary btn-round" data-toggle="modal" data-target="#reportModal"
              data-backdrop="static">
        <i class="far fa-analytics"></i>
          ${lblReport}
      </button>
    </div>
    <table id="payment-tbl"
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
           data-page-size="15"
           data-show-footer="true"
           data-side-pagination="server"
           data-sort-name="payOn"
           data-sort-order="desc"
           data-url="/app/payment">
    </table>
    <div id="modifyPaymentModal" class="modal fade" tabindex="-1" role="dialog"
         aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-400px" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">
              <spring:message code="lbl.payment.modal.title.modify"/>
            </h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form:form method="POST" action="/app/payment/" modelAttribute="payment" data-parsley-validate=""
                     novalidate="">
            <input id="id" type="hidden" name="id">
            <div class="modal-body">
              <div class="pay-detail">
                <div class="reference">
                  <div class="font-weight-bold">${fldRef}</div>
                  <div id="ref">.....</div>
                </div>
                <div class="paid-on">
                  <div class="font-weight-bold text-right">${fldPayOn}</div>
                  <div id="payon" class="text-right">.....</div>
                </div>
              </div>
              <div class="form-group">
                <label class="font-weight-bold" for="payMethod">${fldPayMethod}</label>
                <select id="payMethod" name="payMethod.id" class="selectpicker form-control" data-hide-disabled="true"
                        data-live-search="true" title=""
                        data-parsley-trigger="change focusin focusout input"
                        data-parsley-error-message="${errPayMethodReq}" data-parsley-required>
                  <option disabled selected>${lblSelPayMethod}</option>
                  <c:forEach items="${payMethods}" var="payMethod">
                    <option value="${payMethod.value}">${payMethod.text}</option>
                  </c:forEach>
                </select>
              </div>
              <div class="form-group">
                <label class="font-weight-bold" for="amount">${fldAmount}</label>
                <input type="text" id="amount" name="amount" class="form-control inputmask text-right"
                       data-inputmask="'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2,
                        'digitsOptional': false, 'prefix': '${currencySymb}', 'placeholder': '0'"
                       data-parsley-required-message="${errAmountReq}" <%--data-parsley-range-message="${errAmountInvalid}"--%>
                       data-parsley-pattern-message="${errAmountInvalid}"
                       data-parsley-pattern="/(?=.*\d)^Rs?\s(([1-9]\d{0,2}(,\d{3})*)|0)?(\.\d{1,2})?$/"
                  <%--data-parsley-range="[1, 99999999999.99]"--%>
                       data-parsley-trigger="change keypress keydown keyup focusin focusout input"
                       data-parsley-required/>
              </div>
            </div>
            <div class="modal-footer">
              <button id="modify" type="button" class="btn btn-primary btn-round btn-sm">${lblModify}
              </button>
              <button type="reset" class="btn btn-secondary btn-round btn-sm" data-dismiss="modal">${lblCancel}
              </button>
            </div>
          </form:form>
        </div>
      </div>
    </div>
    <div id="refundPaymentModal" class="modal fade" tabindex="-1" role="dialog"
         aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">
              <spring:message code="lbl.payment.modal.title.refund"/>
            </h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form:form action="/app/payment/">
            <div class="modal-body">
              <input type="hidden" id="id">
                ${confRefund}
            </div>

            <div class="modal-footer">
              <button id="confirm" type="button" class="btn btn-danger btn-round btn-sm">${confActionRefund}
              </button>
              <button type="reset" class="btn btn-secondary btn-round btn-sm" data-dismiss="modal">${lblCancel}
              </button>
            </div>
          </form:form>
        </div>
      </div>
    </div>
    <div id="makePaymentModal" class="modal fade" tabindex="-1" role="dialog"
         aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-md" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">
              <spring:message code="lbl.payment.modal.title.make.payment"/>
            </h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>

          <form:form method="POST" action="/app/payment/" modelAttribute="payment" data-parsley-validate=""
                     novalidate="">
            <input id="reference" type="hidden" name="reference">
            <div class="modal-body">
              <div class="pay-detail">
                <div class="reference">
                  <div class="font-weight-bold">${fldRef}</div>
                  <div id="refMkPay">.....</div>
                </div>
                <c:if test="${isDemoModeEnabled}">
                  <div class="demo">
                    <div class="text-right">
                      <button type="button" id="demoBtn" class="btn btn-sm btn-primary btn-round">
                        <i class="fad fa-database"></i>
                        <spring:message code="lbl.common.demo"/>
                      </button>
                    </div>
                  </div>
                </c:if>
              </div>
              <div class="form-group">
                <label class="font-weight-bold" for="reservations">${fldReserv}</label>
                <select id="reservations" name="reservation.id" class="selectpicker form-control"
                        data-hide-disabled="true"
                        data-live-search="true" title="" data-parsley-trigger="change focusin focusout input"
                        data-parsley-error-message="${errReservationReq}"
                        data-parsley-errors-container="#reservation-error-container" data-parsley-required>
                  <option disabled selected>${lblSelReserv}</option>
                  <c:forEach items="${reservations}" var="reservation">
                    <option value="${reservation.value}">${reservation.text}</option>
                  </c:forEach>
                </select>
                <div id="reservation-error-container"></div>
              </div>
              <div class="form-group">
                <label class="font-weight-bold" for="payMethodMkPay">${fldPayMethod}</label>
                <select id="payMethodMkPay" name="payMethod.id" class="selectpicker form-control"
                        data-hide-disabled="true"
                        data-live-search="true" title="" data-parsley-trigger="change focusin focusout input"
                        data-parsley-error-message="${errPayMethodReq}"
                        data-parsley-errors-container="#payMethod-error-container" data-parsley-required>
                  <option disabled selected>${lblSelPayMethod}</option>
                  <c:forEach items="${payMethods}" var="payMethod">
                    <option value="${payMethod.value}">${payMethod.text}</option>
                  </c:forEach>
                </select>
                <div id="payMethod-error-container"></div>
              </div>
              <div class="form-group">
                <label class="font-weight-bold" for="amountMkPay">${fldAmount}</label>
                <input id="amountMkPay" type="text" name="amount" class="form-control inputmask text-right"
                       data-inputmask="'alias': 'numeric', 'groupSeparator': ',', 'autoGroup': true, 'digits': 2,
                        'digitsOptional': false, 'prefix': '${currencySymb}', 'placeholder': '0'"
                       data-parsley-required-message="${errAmountReq}" <%--data-parsley-range-message="${errAmountInvalid}"--%>
                       data-parsley-pattern-message="${errAmountInvalid}"
                       data-parsley-pattern="/(?=.*\d)^Rs?\s(([1-9]\d{0,2}(,\d{3})*)|0)?(\.\d{1,2})?$/"
                  <%--data-parsley-range="[1, 99999999999.99]"--%>
                       data-parsley-trigger="change"
                       data-parsley-required/>
              </div>
            </div>
            <div class="modal-footer">
              <button id="proceed" type="button" class="btn btn-success btn-round btn-sm">${lblProceed}
              </button>
              <button type="reset" class="btn btn-secondary btn-round btn-sm" data-dismiss="modal">${lblCancel}
              </button>
            </div>
          </form:form>
        </div>
      </div>
    </div>
    <div id="reportModal" class="modal fade" tabindex="-1" role="dialog"
         aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">
              <spring:message code="lbl.payment.modal.title.report"/>
            </h5>
            <button type="button" class="close" data-dismiss="modal"
                    aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <form:form method="POST" action="/app/payment/report" modelAttribute="criteria" data-parsley-validate=""
                     novalidate="">
            <div class="modal-body">
              <div class="form-group">
                <label class="font-weight-bold" for="fromRpt">${lblFrom}</label>
                <input id="fromRpt" name="from" type="text" class="form-control"
                       data-parsley-trigger="dp.change"
                       data-parsley-datelte-message="${errFrom}"
                       data-parsley-datelte="#toRpt"/>
              </div>
              <div class="form-group">
                <label class="font-weight-bold" for="toRpt">${lblTo}</label>
                <input id="toRpt" name="to" type="text" class="form-control"
                       data-parsley-trigger="dp.change"
                       data-parsley-dategte-message="${errTo}"
                       data-parsley-dategte="#fromRpt"/>
              </div>
              <div class="form-group">
                <label class="font-weight-bold" for="payMethodRpt">${fldPayMethod}</label>
                <select id="payMethodRpt" name="payMethodId" class="selectpicker form-control"
                        data-hide-disabled="true" data-live-search="true" title=""
                        data-parsley-trigger="change focusin focusout input"
                        data-parsley-errors-container="#payMethodRpt-error-container">
                  <option disabled selected>${lblSelPayMethod}</option>
                  <c:forEach items="${payMethods}" var="payMethod">
                    <option value="${payMethod.value}">${payMethod.text}</option>
                  </c:forEach>
                </select>
                <div id="payMethodRpt-error-container"></div>
              </div>
              <div class="form-group">
                <label class="font-weight-bold" for="statusRpt">${colSts}</label>
                <select id="statusRpt" name="statusId" class="selectpicker form-control"
                        data-hide-disabled="true" data-live-search="true" title=""
                        data-parsley-trigger="change focusin focusout input"
                        data-parsley-errors-container="#statusRpt-error-container">
                  <option disabled selected>${lblSelStatus}</option>
                  <c:forEach items="${statuses}" var="status">
                    <option value="${status.value}">${status.text}</option>
                  </c:forEach>
                </select>
                <div id="statusRpt-error-container"></div>
              </div>
            </div>
            <div class="modal-footer">
              <button id="generate" type="submit" class="btn btn-primary btn-round btn-sm">
                  ${lblGenerate}
              </button>
              <button type="button" class="btn btn-danger btn-round btn-sm" data-dismiss="modal">
                  ${lblCancel}
              </button>
            </div>
          </form:form>
        </div>
      </div>
    </div>
    <div id="resendReceiptModal" class="modal fade" tabindex="-1" role="dialog"
         aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">
              <spring:message code="lbl.payment.modal.title.resend.receipt"/>
            </h5>
            <button type="button" class="close" data-dismiss="modal"
                    aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
            <input id="payId" type="hidden" name="id">
          </div>
          <form:form method="GET" action="/app/payment/{id}/receipt/resend" modelAttribute="resendData"
                     data-parsley-validate="" novalidate="">
            <div class="modal-body">
              <div class="resend-msg"><spring:message code="lbl.payment.modal.message.resend.receipt"/></div>
              <div class="form-group">
                <input id="email" name="email" type="email" class="form-control"
                       data-parsley-required-message="${errEmailReq}"
                       data-parsley-email-message="${errEmailInvalid}"
                       data-parsley-trigger="change focusin focusout input"
                       data-parsley-email data-parsley-required>
              </div>
            </div>
            <div class="modal-footer">
              <button id="resend" type="submit" class="btn btn-info btn-round btn-sm">
                  ${actionResend}
              </button>
              <button type="button" class="btn btn-danger btn-round btn-sm" data-dismiss="modal">
                  ${lblCancel}
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
      var colRef = '${colRef}';
      var colCust = '${colCust}';
      var colAmt = '${colAmt}';
      var colPayOn = '${colPayOn}';
      var colPayMthd = '${colPayMthd}';
      var colSts = '${colSts}';
      var colAcptBy = '${colAcptBy}';
      var colActions = '${colActions}';

      var actionEdit = '${lblModify}';
      var actionRefund = '${lblRefund}';
      var actionInfo = '${lblInfo}';
      var actionPrint = '${actionPrint}';
      var actionResend = '${actionResend}';
      var lblTotal = '${lblTotal}';

      var currencySymb = '${currencySymb}';
      var lblConfRefund = '${confRefund}';

      var statusRefund = '${statusRefund}';
      var lblRefundInfo = '${lblRefundInfo}';
      var lblRefundBy = '${lblRefundBy}';
      var lblRefundOn = '${lblRefundOn}';

      var lblPaySuccess = "${lblPaySuccess}";
      var lblPayUpdateSuccess = "${lblPayUpdateSuccess}";
      var lblPayRefundSuccess = "${lblPayRefundSuccess}";
      var lblReceiptResendSuccess = "${lblReceiptResendSuccess}";

      <c:if test="${isDemoModeEnabled}">
      $('#demoBtn').on('click', function (e) {
          e.preventDefault();
          $.ajax({
              url: '/app/payment/demo/data',
              type: 'POST',
              beforeSend: showProgress(),
              processData: false,
              contentType: false,
              success: function (response, status, xhr) {
                  commonResponseHandler(response, status, xhr, function () {
                      hideProgress();
                      $('#makePaymentModal').modal('hide');
                      showNotification('success', '${lblDemoDataAddedSuccess}');
                      $table.bootstrapTable('refresh');
                  });
              },
              error: commonErrorHandler
          });
      });
      </c:if>
  </script>
  <!-- Module JS -->
  <script src="/static/payment/js/payment.js"></script>
  </body>
  </html>
</compress:html>
