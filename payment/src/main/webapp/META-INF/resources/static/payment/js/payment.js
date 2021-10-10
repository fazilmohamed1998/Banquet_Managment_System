var $table = $('#payment-tbl');
$(function () {
    $('#payment-menu').addClass('active');
    $('#payment-menu').siblings().removeClass('active');

    $('#fromRpt, #toRpt').datetimepicker({
        icons: {
            previous: "far fa-chevron-left",
            next: "far fa-chevron-right"
        },
        defaultDate: null,
        maxDate: new Date(),
        format: "DD-MMM-YYYY"
    }).on('dp.change', function (e) {
        console.log('changed...');
        var $form = $(this).parents('form:first');
        if ($form) {
            $form.parsley().validate()
        } else {
            $(this).parsley().isValid();
        }
    });
    $('#fromRpt, #toRpt').val("");
    initTable();
});

function actionFormatter(value, row, index) {
    var actions = '<button class="btn btn-link link-primary edit" data-toggle="modal" data-target="#modifyPaymentModal" '
        + 'data-backdrop="static" data-id="' + row.id + '" title="' + actionEdit + '">'
        + '<i class="fad fa-edit"></i></button>';
    if (row.status.toLowerCase() == statusRefund.toLowerCase()) {
        actions += '<button class="btn btn-link link-info info" data-trigger="hover" data-toggle="popover" title="' + lblRefundInfo + '" ';
        ajaxGet('/app/payment/' + row.id + '/refund/info', false, false, function (response) {
            actions += 'data-content="<div><b>' + lblRefundBy + '</b>\t' + response.result.refundBy + '</div>'
                + '<div><b>' + lblRefundOn + '</b>\t' + response.result.refundOn + '</div>"';
        });
        actions += ' data-id="' + row.id + '" title="' + actionInfo + '"><i class="fad fa-info-circle"></i></button>';
    } else {
        actions += '<button class="btn btn-link link-danger refund" data-toggle="modal" data-target="#refundPaymentModal" '
            + 'data-backdrop="static" data-toggle="tooltip" data-id="' + row.id + '" title="' + actionRefund + '">'
            + '<i class="fad fa-undo"></i></button>';
    }
    actions += '<button class="btn btn-link link-success print" data-toggle="tooltip" title="' + actionPrint + '" onclick="printJS(\'/app/payment/' + row.id + '/receipt\')">'
        + '<i class="fad fa-print"></i></button>'

    actions += '<button class="btn btn-link link-info resend" title="' + actionResend + '" data-id="' + row.id
        + '" data-toggle="modal" data-target="#resendReceiptModal" data-backdrop="static"><i class="fad fa-share-all">'
        + '</i></button>'

    ;
    return actions;
}

function totalTextFormatter(data) {
    return lblTotal;
}

function totalCountFormatter(data) {
    return data.length;
}

function totalPriceFormatter(data) {
    var field = this.field;
    var tot = data.map(function (row) {
        if (row.status.toLowerCase() != statusRefund.toLowerCase()) {
            return +row[field];
        }
        return +0;
    }).reduce(function (sum, i) {
        return sum + i
    }, 0);
    return formatCurrency(tot);
}

function amountFormatter(value, row, index) {
    if (row.status.toLowerCase() == statusRefund.toLowerCase()) {
        value = -Math.abs(value);
    }
    return formatCurrency(value);
}

function rowFormatter(row, index) {
    if (row.status.toLowerCase() == statusRefund.toLowerCase()) {
        return {classes: 'row-refund'};
    }
    return {classes: ''};
}

function initTable() {
    $table.bootstrapTable('destroy').bootstrapTable({
        height: '550',
        columns: [{
            title: colId,
            field: 'id',
            align: 'center',
            valign: 'middle',
            sortable: true,
            footerFormatter: totalTextFormatter
        }, {
            title: colRef,
            field: 'reference',
            align: 'center',
            valign: 'middle'
        }, {
            title: colCust,
            field: 'customer',
            align: 'center',
            valign: 'middle',
            footerFormatter: totalCountFormatter
        }, {
            title: colAmt,
            field: 'amount',
            align: 'right',
            valign: 'middle',
            class: 'amount',
            formatter: amountFormatter,
            footerFormatter: totalPriceFormatter
        }, {
            title: colPayMthd,
            field: 'payMethod',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            title: colPayOn,
            field: 'payOn',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            title: colAcptBy,
            field: 'acceptBy',
            align: 'center',
            valign: 'middle'
        }, {
            title: colSts,
            field: 'status',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            title: colActions,
            align: 'center',
            events: window.actionEvents,
            formatter: actionFormatter
        }],
        classes: 'table',
    });
}

$('#makePaymentModal').on('show.bs.modal', function (e) {
    $(this).find('form:first').parsley().reset();
    ajaxGet('/app/payment/reference', false, false, function (response) {
        $('#reference').val(response.result);
        $('#refMkPay').html(response.result);
    });
}).on('hidden.bs.modal', function (e) {
    $(this).find('form:first').parsley().reset();
    $('#reservations').selectpicker('val', $("#reservations option:first").val());
    $('#payMethodMkPay').selectpicker('val', $("#payMethodMkPay option:first").val());
    $('#amountMkPay').val('');
});

$('#makePaymentModal form #proceed').on('click', function (e) {
    e.preventDefault();
    var $form = $(this).parents('form:first');
    if ($form.parsley().validate()) {
        var formData = new FormData($($form).get(0));
        formData.set('amount', formData.get('amount').split(currencySymb)[1].replace(/,/gi, ''));
        ajaxPost($form, formData, function () {
            showProgress();
        }, function (response) {
            setTimeout(function () {
                hideProgress();
            }, 100);
            $('#makePaymentModal').modal('hide');
            showNotification('success', lblPaySuccess);
            $table.bootstrapTable('refresh');
        });
    }
});

$('#modifyPaymentModal').on('show.bs.modal', function (e) {
    var $invoker = $(e.relatedTarget);
    ajaxGet('/app/payment/' + $invoker.data('id'), false, false, function (response) {
        $('#id').val(response.result.id);
        $('#ref').html(response.result.reference);
        $('#payon').html(response.result.paidOn);
        $('#payMethod').selectpicker('val', response.result.payMethod.id);

        var amountStr = response.result.amount.toString();
        if (!amountStr.includes('.')) {
            amountStr += '.00';
        }
        $('#amount').val(amountStr.replace('.', ''));
    });
}).on('hidden.bs.modal', function (e) {
    $(this).find('form:first').parsley().reset();
});

$('#modifyPaymentModal form #modify').on('click', function (e) {
    e.preventDefault();
    var $form = $(this).parents('form:first');
    if ($form.parsley().validate()) {
        var formData = new FormData($($form).get(0));
        formData.set('amount', formData.get('amount').split(currencySymb)[1].replace(/,/gi, ''));
        ajaxPost($form, formData, function () {
            showProgress();
        }, function (response) {
            setTimeout(function () {
                hideProgress();
            }, 100);
            $('#modifyPaymentModal').modal('hide');
            showNotification('success', lblPayUpdateSuccess);
            $table.bootstrapTable('refresh');
        });
    }
});

$('#refundPaymentModal').on('show.bs.modal', function (e) {
    var $invoker = $(e.relatedTarget);
    var id = $invoker.data('id');
    var selRow = $table.bootstrapTable('getRowByUniqueId', id);
    var confMsg = $(this).find('.modal-body').html();
    confMsg = confMsg.replace('{0}', currencySymb + formatCurrency(selRow.amount));
    $(this).find('.modal-body').html(confMsg);
    $('#id').val(id);
}).on('hidden.bs.modal', function (e) {
    $(this).find('.modal-body').html(lblConfRefund);
});

$('#refundPaymentModal form #confirm').on('click', function (e) {
    e.preventDefault();
    var $form = $(this).parents('form:first');

    ajaxDelete($form.attr('action') + $('#id').val(), function () {
        showProgress();
    }, function (response) {
        setTimeout(function () {
            hideProgress();
        }, 35);
        $('#refundPaymentModal').modal('hide');
        showNotification('success', lblPayRefundSuccess);
        $table.bootstrapTable('refresh');
    });
});

$('#reservations, #payMethodMkPay').on('changed.bs.select', function (e, clickedIndex, isSelected, previousValue) {
    $(this).parsley().validate();
});

$table.on('load-success.bs.table', function (data) {
    $('.info').popover({
        html: true,
        placement: 'bottom'
    });
});

$('#amountMkPay').on('change', function () {
    $(this).parsley().validate();
});

$('#reportModal form #generate').on('click', function (e) {
    e.preventDefault();
    var $form = $(this).parents('form:first');
    if ($form.parsley().validate()) {
        var queryStr = $form.serialize();
        printJS($form.attr('action') + "?" + queryStr);
    }
});

$('#resendReceiptModal').on('show.bs.modal', function (e) {
    var $invoker = $(e.relatedTarget);
    var id = $invoker.data('id');
    ajaxGet('/app/payment/' + id + '/customer-email', false, false, function (response) {
        $('#email').val(response.result);
        $('#payId').val(id);
    });
}).on('hidden.bs.modal', function (e) {
    $(this).find('form:first').parsley().reset();
    $('#email').val('');
    $('#payId').val('');
});

$('#resendReceiptModal form #resend').on('click', function (e) {
    e.preventDefault();
    var $form = $(this).parents('form:first');
    if ($form.parsley().validate()) {
        showProgress();
        var queryStr = $form.serialize();
        ajaxGet(($form.attr('action').replace("{id}", $('#payId').val()) + "?" + queryStr), false, false, function (response) {
            $('#resendReceiptModal').modal('hide');
            hideProgress();
            showNotification('success', lblReceiptResendSuccess.replace('{0}', $('#email').val()));
        });
    }
});